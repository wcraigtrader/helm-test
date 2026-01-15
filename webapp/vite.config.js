import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 9000,
    proxy: {
      // forward API calls to local reader service running on port 9002
      '/messages': {
        target: 'http://localhost:9002',
        changeOrigin: true,
        secure: false
      }
    }
  }
})
