# Reader Summary Webapp

Simple Express webapp that fetches messages from a reader web API and displays a summary grouped by version and hostname.

Usage

1. Install dependencies:

```bash
cd webapp
npm install
```

2. Run (defaults to reader at http://localhost:9002/messages):

```bash
READER_URL=http://localhost:9002/messages npm start
```

3. Open http://localhost:3000

Notes
- The reader API URL can be overridden with `READER_URL` environment variable.
- The app exposes a JSON endpoint at `/api/summary`.
