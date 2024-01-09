import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'
import uuidv4  from 'uuid'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

axios.defaults.headers.post['Content-Type']='application/json;charset=UTF-8';
const app = createApp(App)
app.use(router).mount('#app')
app.use(uuidv4)
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.config.globalProperties.$http = axios
axios.defaults.baseURL = "http://localhost:9000"
