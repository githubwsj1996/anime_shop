import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/Index";
import Anime_index from "../views/Anime_index";
import User_actions from "../views/User_actions";
import User_cart from "../views/User_cart";
import Login from "../views/Login";
import Register from "../views/Register";
import Anime_details from "../views/Anime_details";
import Anime_order from "../views/Anime_order";
import User_order from "../views/User_order";
import User_info from "../views/User_info";
import User_addressList from "../views/User_addresslist";
import User_addressAdd from "../views/User_addressAdd";
import User_addressEdit from "../views/User_addressEdit";
import ErrorInfo from "../views/ErrorInfo";
import SIdentify from "../utils/SIdentify";
Vue.use(VueRouter)


const routes = [
    {
        path: '/',
        component: Index,
        redirect: '/anime_index',
        show: true,
        children: [
            {path: '/anime_index', name: '首页', iconName: 'home-o', component: Anime_index},
            {path: '/user_cart', name: '购物车', iconName: 'cart-o', component: User_cart},
            {path: '/user_actions', name: '我的', iconName: 'user-circle-o', component: User_actions},
        ]
    },
    {
        path: '/login',
        name: '登录',
        component: Login,
        show: false,
    },
    {
        path: "/register",
        name: '注册',
        component: Register,
        show: false
    },
    {
        path: '/anime_details',
        name: '商品详情',
        component: Anime_details,
        show: false,
    },
    {
        path: "/anime_order",
        name: '订单页面',
        component: Anime_order,
        show: false
    },
    {
        path: "/user_order",
        name: '用户订单',
        component: User_order,
        show: false
    },
    {
        path: "/user_info",
        name: '用户信息',
        component: User_info,
        show: false
    },
    {
        path: "/user_addressList",
        name: '地址管理',
        component: User_addressList,
        show: false
    },
    {
        path: "/user_addressAdd",
        name: '新增地址',
        component: User_addressAdd,
        show: false
    },
    {
        path: "/user_addressEdit",
        name: '编辑地址',
        component: User_addressEdit,
        show: false
    },
    {
        path: "/errorInfo",
        name:'报错页面',
        component: ErrorInfo,
        show: false
    }

]

const router = new VueRouter({
    mode: 'history',
    base: "/animeShop_vue",
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.length === 0) {
        from.name ? next({
            name: from.name
        }) : next('/errorInfo');
    } else {
        next();
    }
});

export default router
