import VueRouter from "vue-router";

import NotFound from "@/components/NotFound";
import MainPage from "@/components/MainPage";


export default new VueRouter ({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'MainPage',
            component: MainPage
        },
        {
            path: '*',
            name: 'notFound',
            component: NotFound
        }
    ]
})