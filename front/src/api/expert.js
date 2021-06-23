import request from '@/utils/request.js'
import axios from 'axios'

export function insertExpert(data) {
  return request({
    url: '/api/expert',
    method: 'post',
    data
  })
}

export function updateExpert(data) {
  return request({
    url: '/api/expert',
    method: 'put',
    data
  })
}

export function getExperts() {
  return request({
    url: '/api/expert',
    method: 'get'
  })
}

export function getBlockedExperts() {
  return request({
    url: '/api/expert/blocked',
    method: 'get'
  })
}

export function getExpertById(id) {
  return request({
    url: `/api/expert/${id}`,
    method: 'get'
  })
}

