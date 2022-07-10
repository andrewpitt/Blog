import { createStore } from 'vuex'

export default createStore({
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
  },
  getters: {
    //get
    getUser: state => {
      return state.userInfo
    }
  },
  mutations: {
    //set
    SET_TOKEN: (state, token) => {
      state.token = token;
      localStorage.setItem("token", token)
    } ,
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
      //sessionstorage存不了对象，所以序列化一下
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
    } ,
    REMOVE_INFO: (state) => {
      state.token = '';
      state.userInfo = {};
      localStorage.setItem("token", '');
      sessionStorage.setItem("userInfo", JSON.stringify(''))
    }
  },
  actions: {
  },
  modules: {
  }
})