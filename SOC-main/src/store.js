import Vue from 'vue';
import Vuex from 'vuex';
// import Axios from 'axios';


Vue.use(Vuex)



export default new Vuex.Store({

    state: {
        orderDetail: {},
        saveOrderDetail: {},
        StoreCart: [],
        CompareCart: [],
        searchText: "",
        searchProduct: 0,
        minWidth: "",
        tokenUser: "",
        totalCart: "",
        InfoPersonal: {},
        userName: "",
        allDataUser: "",
        categorySearch: 0,
        StoreCartUser: {},
        test: [],
        totalFavorites: [],
        getDanhMucCategory: [],
        MainLink: "http://127.0.0.1:8800/api/"
    },
    getters: {},
    mutations: {

    },
    actions: {
        login() {

        }
    }
})