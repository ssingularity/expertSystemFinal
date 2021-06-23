import request from '@/utils/request.js'

export function getPrograms() {
  return request({
    url: '/api/program',
    method: 'get'
  })
}

export function getProgramById(id) {
  return request({
    url: `/api/program/${id}`,
    method: 'get'
  })
}

export function addProgram(data) {
  return request({
    url: '/api/program',
    method: 'post',
    data
  })
}

export function autoChooseProgramById(id) {
  return request({
    url: `/api/program/${id}/auto`,
    method: 'post'
  })
}

export function endProgramById(id) {
  return request({
    url: `/api/program/${id}/state`,
    method: 'put'
  })
}
