<template>
  <div class="container">
    <h1>Messages (grouped)</h1>
    <div class="controls">
      <label>Polling: <input type="checkbox" v-model="polling" /></label>
      <span v-if="lastUpdated">Last: {{ new Date(lastUpdated).toLocaleTimeString() }}</span>
    </div>

    <div v-if="Object.keys(groups).length === 0">No messages</div>

    <div v-for="(hostGroups, hostname) in groups" :key="hostname" class="host-block">
      <h2>{{ hostname }}</h2>
      <ul>
        <li v-for="(count, version) in hostGroups" :key="version">{{ version }}: {{ count }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'

export default {
  setup() {
    const polling = ref(true)
    const lastUpdated = ref(null)
    const groups = reactive({})
    let timer = null

    const fetchMessages = async () => {
      try {
        const res = await fetch('/messages')
        if (!res.ok) return
        const data = await res.json()
        // data expected: array of MessageDTO { version, hostname, ... }
        // rebuild groups
        for (const k of Object.keys(groups)) delete groups[k]
        for (const m of data) {
          const host = m.hostname || 'unknown'
          const ver = m.version || 'unknown'
          groups[host] = groups[host] || {}
          groups[host][ver] = (groups[host][ver] || 0) + 1
        }
        lastUpdated.value = Date.now()
      } catch (e) {
        console.error('fetch messages failed', e)
      }
    }

    onMounted(() => {
      fetchMessages()
      timer = setInterval(() => { if (polling.value) fetchMessages() }, 1000)
    })
    onBeforeUnmount(() => clearInterval(timer))

    return { polling, groups, lastUpdated }
  }
}
</script>

<style>
body { font-family: system-ui, sans-serif; }
.container { max-width: 800px; margin: 2rem auto; }
.host-block { border: 1px solid #eee; padding: 0.5rem; margin: 0.5rem 0 }
.controls { margin-bottom: 1rem }
</style>
