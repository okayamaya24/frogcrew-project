import { createRouter, createWebHistory } from 'vue-router'
import CrewMemberView from '../views/CrewMemberView.vue'
import CreateCrewMemberView from '../views/CreateCrewMemberView.vue'

const routes = [
  {
    path: '/',
    name: 'crew',
    component: CrewMemberView
  },
  {
    path: '/create',
    name: 'create-crew',
    component: CreateCrewMemberView
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router