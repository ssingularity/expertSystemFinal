import request from '@/utils/request.js'

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

export function getExpertsByOffset(type, search, offset) {
  const url = type === ''
    ? `/api/expert?offset=${offset-1}&`
    : `/api/expert?offset=${offset-1}&type=${type}&search=${search}`
  return request({
    url,
    method: 'get'
  })
}

export function updateExcel(formData) {
  return request.post('/api/expert/excel', formData, {
    headers: {
      'Content-Type': 'multipart/form-data;charset=UTF-8'
    },
  })
}

