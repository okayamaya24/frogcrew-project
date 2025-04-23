<template>
    <div>
      <h1>General Game Schedule</h1>
  
      <div v-if="loading">
        Loading game schedule...
      </div>
  
      <div v-else-if="error">
        <p>Error loading game schedule: {{ error }}</p>
      </div>
  
      <div v-else>
        <table border="1" cellpadding="10">
          <thead>
            <tr>
              <th>Date</th>
              <th>Time</th>
              <th>Location</th>
              <th>Home Team</th>
            <th>Opponent Team</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="game in games" :key="game.id">
              <td>{{ game.date }}</td>
              <td>{{ game.time }}</td>
              <td>{{ game.location }}</td>
              <td>{{ game.hometeam }}</td>
              <td>{{ game.opponentteam }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  
  const games = ref([])
  const loading = ref(true)
  const error = ref(null)
  
  onMounted(async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/games')
      games.value = response.data
    } catch (err) {
      console.error('Error fetching games:', err)
      error.value = err.message || 'Unknown error'
    } finally {
      loading.value = false
    }
  })
  </script>
  
  <style scoped>
  h1 {
    margin-bottom: 20px;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th, td {
    text-align: center;
    padding: 8px;
  }
  </style>