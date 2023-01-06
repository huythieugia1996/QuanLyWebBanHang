import 'core-js/stable'
import Vue from 'vue'
import App from './App'
import router from './router'
import CoreuiVue from '@coreui/vue'
import { iconsSet as icons } from './assets/icons/icons.js'
import store from './store'
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import axios from 'axios'
import VueAxios from 'vue-axios'
import JwPagination from 'jw-vue-pagination';
// import Toasted from 'vue-toasted';

// Vue.use(Toasted)

// Vue.use(Toasted, Options)

Vue.component('jw-pagination', JwPagination);
Vue.use(BootstrapVue);
Vue.use(VueAxios, axios)
Vue.use(IconsPlugin);
Vue.config.performance = true
Vue.use(CoreuiVue)
Vue.prototype.$log = console.log.bind(console)

new Vue({
    el: '#app',
    router,
    store,
    icons,
    template: '<App/>',
    components: {
        App
    }
})