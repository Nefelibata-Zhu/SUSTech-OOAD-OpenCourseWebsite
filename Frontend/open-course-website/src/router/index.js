import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path: '/main',
    name: 'main',
    component: () => import(/* webpackChunkName: "about" */ '../views/MainView.vue')
  },
  {
    path: '/course/:courseID',
    name: 'course',
    component: () => import(/* webpackChunkName: "about" */ '../views/CourseView.vue')
  },
  {
    path: '/chapter',
    name: 'chapter',
    component: () => import(/* webpackChunkName: "about" */ '../views/ChapterView.vue')
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import(/* webpackChunkName: "about" */ '../views/AdminView.vue')
  },
  {
    path: '/rank',
    name: 'rank',
    component: () => import(/* webpackChunkName: "about" */ '../views/RankView.vue')
  },
  {
    path: '/remark',
    name: 'remark',
    component: () => import(/* webpackChunkName: "about" */ '../views/RemarkView.vue')
  },
  {
    path: '/test',
    name: 'test',
    component: () => import(/* webpackChunkName: "about" */ '../views/TestTest.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
