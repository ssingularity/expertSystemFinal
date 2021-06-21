import request from '@/utils/request.js'

export function getRecordByProgramId(programId) {
  return request({
    url: `/program/${programId}/record`,
    method: 'get'
  })
}

export function getRecordByExpertId(expertId) {
  return request({
    url: `/expert/${expertId}/record`,
    method: 'get'
  })
}
