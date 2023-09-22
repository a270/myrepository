import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Cart from "../views/Cart";
import Info from "../views/Info";
import Login from "../views/Login";
import OrderDetail from "../views/OrderDetail";
import Pay from "../views/Pay";
import Order from "../views/Order";
import Mine from "../views/Mine";
import Register from "../views/Register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/cart',
    component: Cart
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/mine',
    component: Mine
  },
  {
    path: '/order',
    component: Order
  },
  {
    path: '/pay',
    component: Pay
  },
  {
    path: '/orderDetail',
    component: OrderDetail
  },
  {
    path: '/info',
    component: Info
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/',
    component: Login
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//解决路由跳转错误
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('access-user')
    next()
  } else if(to.path.startsWith('/register')){
    next()
  }
  else {
    let user = JSON.parse(window.localStorage.getItem('access-user'))
    if (!user) {
      next({path: '/login'})
    } else {
      //校验token
      // axios({
      //   url:'http://localhost:8686/account-service/user/checkToken',
      //   method:'get',
      //   headers:{
      //     token:user.token
      //   }
      // }).then((response) => {
      //   if(response.data.code == -1){
      //     let instance = Toast('登录超时！请重新登录！');
      //     setTimeout(() => {
      //       instance.close();
      //     }, 2000)
      //     next({path: '/login'})
      //   }
      // })
      next()
    }
  }
})

export default router
