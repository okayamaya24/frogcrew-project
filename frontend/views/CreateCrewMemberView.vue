<template>
    <h1>Create a Crew Member Profile</h1>
    <div>
      <form v-if="!isConfirming" @submit.prevent="confirmInput" name="detailsForm">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required v-model="formData.firstName" placeholder="John" @blur="touched.firstName = true" :class="{ 'invalid': touched.firstName && !formData.firstName }">
  
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required v-model="formData.lastName" placeholder="Doe" @blur="touched.lastName = true" :class="{ 'invalid': touched.lastName && !formData.lastName }">
  
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required v-model="formData.email" placeholder="johndoe@email.com" @blur="touched.email = true" :class="{ 'invalid': touched.email && !formData.email }">
  
        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required v-model="formData.phoneNumber" placeholder="Format: 123-456-7890" @blur="touched.phoneNumber = true" :class="{ 'invalid': touched.phoneNumber && !formData.phoneNumber }">
  
        <label for="role">Role:</label>
        <input type="text" id="role" name="role" required v-model="formData.role" placeholder="Enter Your Role Here" @blur="touched.role = true" :class="{ 'invalid': touched.role && !formData.role }">
  
        <div :class="{ 'invalid-radio': touched.qualifiedPosition && !formData.qualifiedPosition }" @blur="touched.qualifiedPosition = true" tabindex="0">
          <label for="qualifiedPosition">Qualified Position:</label>
          <input type="radio" id="sport-broadcasters" name="qualifiedPosition" required value="Sport Broadcaster" v-model="formData.qualifiedPosition">
          <label for="sport-broadcasters">Sport Broadcaster</label>
  
          <input type="radio" id="paid-freelancers" name="qualifiedPosition" required value="Paid Freelancer" v-model="formData.qualifiedPosition">
          <label for="paid-freelancers">Paid Freelancer</label>
  
          <input type="radio" id="paid-students" name="qualifiedPosition" required value="Paid Student" v-model="formData.qualifiedPosition">
          <label for="paid-students">Paid Student</label>
        </div>
  
        <input type="submit" value="Confirm">
      </form>
  
      <div v-else>
        <h3>Confirm Your Information</h3>
        <p>First Name: {{ formData.firstName }}</p>
        <p>Last Name: {{ formData.lastName }}</p>
        <p>Email: {{ formData.email }}</p>
        <p>Phone Number: {{ formData.phoneNumber }}</p>
        <p>Role: {{ formData.role }}</p>
        <p>Qualified Position: {{ formData.qualifiedPosition }}</p>
  
        <button @click="editInput">Edit</button>
        <button @click="submitForm">Submit</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive, ref } from 'vue'
  import { useRouter } from 'vue-router'
  import axios from 'axios'
  
  const router = useRouter()
  
  const formData = reactive({
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    role: '',
    qualifiedPosition: ''
  })
  
  const touched = reactive({
    firstName: false,
    lastName: false,
    email: false,
    phoneNumber: false,
    role: false,
    qualifiedPosition: false
  })
  
  const isConfirming = ref(false)
  
  function confirmInput() {
    isConfirming.value = true
  }
  
  function editInput() {
    isConfirming.value = false
  }
  
  async function submitForm() {
    const crewMemberData = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      phoneNumber: formData.phoneNumber,
      role: formData.role,
      qualifiedPosition: formData.qualifiedPosition
    }
  
    try {
      const response = await axios.post('http://localhost:8080/api/crewmembers', crewMemberData)
      console.log('Crew member registered:', response.data)
      alert('Registration successful!')
  
      // Clear form
      Object.keys(formData).forEach(key => formData[key] = '')
      isConfirming.value = false
  
      // Optionally route to crew member list page later
      // router.push('/')
    } catch (error) {
      console.error('Error during registration:', error.response?.data || error.message)
      alert('Registration failed. Please try again.')
    }
  }
  </script>
  
  <style scoped>
  .invalid {
    border: 2px dashed red;
  }
  
  .invalid-radio {
    border: 2px dashed red;
    padding: 10px;
  }
  
  form {
    display: flex;
    flex-direction: column;
    max-width: 400px;
  }
  
  form > div {
    margin-bottom: 10px;
  }
  
  button, input[type="submit"] {
    margin-top: 10px;
  }
  </style>
