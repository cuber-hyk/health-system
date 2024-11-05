import Vue from "vue";
import axios from "axios";
import App from "./App.vue";
import "./registerServiceWorker";
import plugins from "./plugins";
import router from "./router";
import store from "./store";
// import echarts from "./assets/js/echarts.min.js";
import * as echarts from "echarts";

//需要挂载到Vue原型上
Vue.prototype.$echarts = echarts;

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

Vue.use(plugins, {
  host: "http://60.204.223.60:5000/",
  // host: "http://localhost:5000/"
});

import JsonExcel from "vue-json-excel";
Vue.component("downloadExcel", JsonExcel);

new Vue({
  router,
  store,
  echarts,
  render: (h) => h(App),
}).$mount("#app");
