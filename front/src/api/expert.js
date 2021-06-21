import request from '@/utils/request.js'

export function insertExpert(data) {
  return request({
    url: '/expert',
    method: 'post',
    data
  })
}

export function getExperts() {
  return request({
    url: '/expert',
    method: 'get'
  })
}

export function getExpertById(id) {
  return request({
    url: `/expert/${id}`,
    method: 'get'
  })
}
