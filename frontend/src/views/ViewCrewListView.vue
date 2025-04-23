<template>
    <div>
      <h1>Crew Members</h1>
  
      <div v-if="loading">
        Loading crew list...
      </div>
  
      <div v-else-if="error">
        <p>Error loading crew list: {{ error }}</p>
      </div>
  
      <div v-else>
        <table border="1" cellpadding="10">
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Role</th>
              <th>Qualified Position</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="member in crewList" :key="member.id">
              <td>{{ member.id }}</td>
              <td>{{ member.firstName }}</td>
              <td>{{ member.lastName }}</td>
              <td>{{ member.email }}</td>
              <td>{{ member.phoneNumber }}</td>
              <td>{{ member.role }}</td>
              <td>{{ member.qualifiedPosition }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  
  const crewList = ref([])
  const loading = ref(true)
  const error = ref(null)
  
  onMounted(async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/crewmembers')
      crewList.value = response.data
    } catch (err) {
      console.error('Error fetching crew list:', err)
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