import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store =   new Vuex.Store({
    state: {
      login: false,
    },
    getters:{
      login: state =>{
        let login = state.login
        if(!login){
          login = JSON.parse(window.sessionStorage.getItem('login'))
        }
        return login
      }
    },
    mutations:{
      setlogin:(state,login)=>{
        state.login = login
        window.sessionStorage.setItem('login',JSON.stringify(login))
      }
    }
  })
  export default store