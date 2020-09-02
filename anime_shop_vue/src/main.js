import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/vant.js'
import 'vant/lib/index.css';
import Vant, {Form, NoticeBar} from "vant";
import {Search} from 'vant';
import {Pagination} from 'vant';
import {Card} from 'vant';
import {NavBar} from 'vant';
import {Swipe, SwipeItem} from 'vant';
import {GoodsAction, GoodsActionButton} from 'vant';
import {Sku} from 'vant';
import {Checkbox, CheckboxGroup} from 'vant';
import {SwipeCell} from 'vant';
import {Stepper} from 'vant';
import {SubmitBar} from 'vant';
import {Cell, CellGroup} from 'vant';
import {Image as VanImage} from 'vant';
import { Tab, Tabs } from 'vant';
import { AddressList } from 'vant';
import validator_user from "./utils/validator_user";
import { Lazyload } from 'vant';

Vue.use(Vant, NoticeBar, Search, Pagination,
    Card, NavBar, Swipe, SwipeItem, GoodsAction
    , GoodsActionButton, Sku, Checkbox, CheckboxGroup,
    SwipeCell, Stepper, SubmitBar, Cell, CellGroup,
    VanImage,Tab,Tabs,Form,AddressList,Lazyload);

Vue.prototype.$validator_user = validator_user
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: function (h) {
        return h(App)
    }
}).$mount('#app')
