import request from '@/utils/request.js'

export function getPrograms() {
  return request({
    url: '/program',
    method: 'get'
  })
}

export function getProgramById(id) {
  return request({
    url: `/program/${id}`,
    method: 'get'
  })
}
