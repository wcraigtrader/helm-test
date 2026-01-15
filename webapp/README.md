# webapp

Simple Vue 3 (Vite) app that polls the reader service `/messages` endpoint once per second and displays counts grouped by `hostname` and `version`.

Local dev:

```bash
cd webapp
npm install
npm run dev
```

The Vite config proxies `/messages` to `http://localhost:8080` for local development. Ensure the `reader` service is running on port 8080.
