import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const state = {
    sidebarShow: 'responsive',
    sidebarMinimize: false,
    userToken: "",
    userName: "",
    MainLink: "http://localhost:8800/api/",
    ListCategory:[
        {id:1,text:"Loại máy"},
        {id:2,text:"Loại sản phẩm"},
        {id:3,text:"Loại thương hiệu"},
        {id:4,text:"Nhu cầu"},
    ]


}

const mutations = {
    toggleSidebarDesktop(state) {
        const sidebarOpened = [true, 'responsive'].includes(state.sidebarShow)
        state.sidebarShow = sidebarOpened ? false : 'responsive'
    },
    toggleSidebarMobile(state) {
        const sidebarClosed = [false, 'responsive'].includes(state.sidebarShow)
        state.sidebarShow = sidebarClosed ? true : 'responsive'
    },
    set(state, [variable, value]) {
        state[variable] = value
    }
}

export default new Vuex.Store({
    state,
    mutations
})