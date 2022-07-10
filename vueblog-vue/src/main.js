import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import axios from 'axios'
import moment from 'moment'
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import Prism from 'prismjs';


VueMarkdownEditor.use(vuepressTheme, {
    Prism,
});

import "./axios"
const app = createApp(App)


app.config.globalProperties.$axios = axios
app.config.globalProperties.$moment = moment;

installElementPlus(app)
app.use(store).use(router).use(VueMarkdownEditor).mount('#app')