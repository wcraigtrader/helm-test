const express = require('express');
const fetch = require('node-fetch');
const path = require('path');

const app = express();
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(express.static(path.join(__dirname, 'public')));

const DEFAULT_READER = 'http://localhost:9002/messages';
const READER_URL = process.env.READER_URL || DEFAULT_READER;

async function tryFetch(url) {
  const res = await fetch(url, { timeout: 5000 });
  if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
  return res.json();
}

async function fetchMessages() {
  const candidates = [READER_URL];
  if (READER_URL.endsWith('/messages')) candidates.push(READER_URL.replace(/\/messages$/, '/api/messages'));
  if (!READER_URL.endsWith('/messages')) candidates.push(READER_URL.replace(/\/$/, '') + '/messages');

  for (const url of candidates) {
    try {
      const data = await tryFetch(url);
      if (Array.isArray(data)) return data;
      if (Array.isArray(data.messages)) return data.messages;
    } catch (err) {
      // try next
    }
  }
  throw new Error('Failed to fetch messages from reader API at ' + READER_URL);
}

function groupMessages(messages) {
  const grouped = {};
  for (const m of messages) {
    const version = m.version || m.v || 'unknown';
    const hostname = m.hostname || m.host || m.hostName || 'unknown';
    grouped[version] = grouped[version] || {};
    const bucket = grouped[version];
    bucket[hostname] = bucket[hostname] || { count: 0, samples: [] };
    bucket[hostname].count++;
    if (bucket[hostname].samples.length < 5) bucket[hostname].samples.push(m);
  }
  return grouped;
}

app.get('/api/summary', async (req, res) => {
  try {
    const messages = await fetchMessages();
    res.json({ total: messages.length, summary: groupMessages(messages) });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.get('/', async (req, res) => {
  try {
    const messages = await fetchMessages();
    const summary = groupMessages(messages);
    res.render('index', { summary, total: messages.length, readerUrl: READER_URL });
  } catch (err) {
    res.status(500).send('<h1>Error</h1><pre>' + err.message + '</pre>');
  }
});

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Reader-summary webapp listening on ${port} (reader=${READER_URL})`));
