<template>
    <div>
      <h1>My Profile</h1>
  
      <div v-if="loading">
        Loading profile...
      </div>
  
      <div v-else-if="error">
        <p>Error loading profile: {{ error }}</p>
      </div>
  
      <div v-else>
        <p><strong>First Name:</strong> {{ crewMember.firstName }}</p>
        <p><strong>Last Name:</strong> {{ crewMember.lastName }}</p>
        <p><strong>Email:</strong> {{ crewMember.email }}</p>
        <p><strong>Phone Number:</strong> {{ crewMember.phoneNumber }}</p>
        <p><strong>Role:</strong> {{ crewMember.role }}</p>
        <p><strong>Qualified Position:</strong> {{ crewMember.qualifiedPosition }}</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  
  const crewMember = ref({})
  const loading = ref(true)
  const error = ref(null)
  
  onMounted(async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/crewmembers/1')
      crewMember.value = response.data
    } catch (err) {
      console.error('Error fetching profile:', err)
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
  
  p {
    margin: 8px 0;
  }
  </style>