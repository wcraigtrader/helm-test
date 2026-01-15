# webapp

Simple Vue 3 (Vite) app that polls the reader service `/messages` endpoint once per second and displays counts grouped by `hostname` and `version`.

Local dev:

```bash
cd webapp
npm install
npm run dev
```

The Vite config proxies `/messages` to `http://localhost:9002` for local development. The dev server runs on port `9000`.

Ensure the `reader` service is running on port `9002` (or adjust `webapp/vite.config.js` accordingly).
