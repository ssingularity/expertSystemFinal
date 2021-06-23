import request from '@/utils/request.js'

export function getRecordByProgramId(programId) {
  return request({
    url: `/api/program/${programId}/record`,
    method: 'get'
  })
}

export function getRecordByExpertId(expertId) {
  return request({
    url: `/api/expert/${expertId}/record`,
    method: 'get'
  })
}

export function insertRecord(data) {
  return request({
    url: `/api/record`,
    method: 'post',
    data
  })
}

export function deleteRecord(id) {
  return request({
    url: `/api/record/${id}`,
    method: 'delete',
  })
}

export function updateRecord(data) {
  return request({
    url: `/api/record`,
    method: 'put',
    data
  })
}
