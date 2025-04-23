<template>
    <div>
      <h1>Crew Members</h1>
  
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
          <tr v-for="crew in crewMembers" :key="crew.id">
            <td>{{ crew.id }}</td>
            <td>{{ crew.firstName }}</td>
            <td>{{ crew.lastName }}</td>
            <td>{{ crew.email }}</td>
            <td>{{ crew.phoneNumber }}</td>
            <td>{{ crew.role }}</td>
            <td>{{ crew.qualifiedPosition }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const crewMembers = ref([]);
  
  onMounted(async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/crewmembers');
      crewMembers.value = response.data;
    } catch (error) {
      console.error('Error fetching crew members:', error);
    }
  });
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
    text-align: left;
    padding: 8px;
  }
  </style>