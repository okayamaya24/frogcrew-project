<template>
    <div>
      <h1>Submit Availability</h1>
  
      <form @submit.prevent="submitAvailability">
        <label for="crewId">Crew Member ID:</label>
        <input
          type="number"
          id="crewId"
          v-model="form.crewId"
          required
          placeholder="Enter your Crew Member ID"
        />
  
        <label for="date">Date:</label>
        <input
          type="date"
          id="date"
          v-model="form.date"
          required
        />
  
        <label for="timeBlock">Time Block:</label>
        <select id="timeBlock" v-model="form.timeBlock" required>
          <option disabled value="">Select Time Block</option>
          <option>Morning</option>
          <option>Afternoon</option>
          <option>Evening</option>
        </select>
  
        <button type="submit">Submit</button>
      </form>
  
      <p v-if="message">{{ message }}</p>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  
  const form = ref({
    crewId: '',
    date: '',
    timeBlock: ''
  })
  
  const message = ref('')
  
  async function submitAvailability() {
    try {
      const response = await axios.post('http://localhost:8080/api/availability', {
        date: form.value.date,
        timeBlock: form.value.timeBlock,
        crewMember: {
          id: parseInt(form.value.crewId)
        }
      })
      console.log('Submitted:', response.data)
      message.value = 'Availability submitted successfully!'
      form.value = { crewId: '', date: '', timeBlock: '' }
    } catch (err) {
      console.error('Error submitting availability:', err)
      message.value = 'Error submitting. Please check your inputs.'
    }
  }
  </script>
  
  <style scoped>
  form {
    display: flex;
    flex-direction: column;
    max-width: 400px;
  }
  
  label {
    margin-top: 10px;
  }
  
  input, select {
    padding: 8px;
    margin-bottom: 10px;
  }
  
  button {
    background-color: #293840;
    color: white;
    padding: 10px;
    border: none;
    cursor: pointer;
  }
  
  p {
    margin-top: 15px;
    font-weight: bold;
  }
  </style>