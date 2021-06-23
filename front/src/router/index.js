import Vue from 'vue'
import Router from 'vue-router'

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
        path: '/',
        redirect: '/expert'
      },
      {
        path: '/expert',
        component: expert
      },
      {
          path: '/add_expert',
          component: add
      },
      {
          path: '/reverse_expert',
          component: edit
      },
      {
          path: '/program_detail',
          component: programDetail
      },
      {
          path: '/program',
          component: programList
      },
      {
          path: '/expert_detail',
          component: expertDetail
      },
      {
          path: '/add_program',
          component: addProgram
      },
    ]
})
