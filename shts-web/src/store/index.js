import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        tabActive: 0
    },
    mutations: {
        changeActive(state, active) {
            state.tabActive = active
        }

    },
    actions: {
    }
})
