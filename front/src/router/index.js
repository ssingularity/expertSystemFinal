import Vue from 'vue'
import Router from 'vue-router'

import main from '../view/main'
import expert from '../view/expert/expertList'
import add from '../view/expert/addExpert'
import edit from '../view/expert/editExpert'
import expertDetail from '../view/expert/expertDetail'
import programDetail from "../view/program/programDetail"
import programList from "../view/program/programList"
import addProgram from "@/view/program/addProgram";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/home',
            component: main
        },
        {
            path: '/expert',
            component: expert
        },
        {
            path: '/editExpert',
            component: add
        },
        {
            path: '/reverseExpert',
            component: edit
        },
        {
            path: '/programDetail',
            component: programDetail
        },
        {
            path: '/programList',
            component: programList
        },
        {
            path: '/expertDetail',
            component: expertDetail
        },
        {
            path: '/addProgram',
            component: addProgram
        },
        {
            path: '/',
            redirect: '/expert'
        }
    ]
})
