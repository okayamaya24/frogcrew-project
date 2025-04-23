import { createRouter, createWebHistory } from 'vue-router'
import CrewMemberView from '../views/CrewMemberView.vue'
import CreateCrewMemberView from '../views/CreateCrewMemberView.vue'
import ViewCrewProfileView from '@/views/ViewCrewProfileView.vue'
import ViewGameScheduleView from '@/views/ViewGameScheduleView.vue'
import ViewCrewListView from '@/views/ViewCrewListView.vue'
import SubmitAvailabilityView from '@/views/SubmitAvailabilityView.vue'

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
  },
  {
    path: '/view-profile',
    name: 'view-profile',
    component:ViewCrewProfileView
  },
  {
    path: '/view-game-schedule',
    name: 'view-game-schedule',
    component: ViewGameScheduleView
  },
  {
    path:'/view-crew-list',
    name: 'view-crew-list',
    component: ViewCrewListView
  },
  {
    path: '/submit-availability',
    name: 'submit-availability',
    component: SubmitAvailabilityView
  }

]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router