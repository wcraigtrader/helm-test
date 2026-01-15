# webapp (Angular)

This is a minimal Angular app that polls the reader service `/messages` endpoint once per second and displays counts grouped by `hostname` and `version`.

Local dev:

```bash
cd webapp
npm install
npm start    # runs `ng serve --port 9000 --proxy-config proxy.conf.json`
```

The dev server runs on `http://localhost:9000` and the proxy forwards `/messages` to `http://localhost:9002`.

Note: This scaffold expects `npm` and the Angular CLI packages to be installed (the project declares them in `package.json`). If you prefer a containerized dev environment I can add a Dockerfile.
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
