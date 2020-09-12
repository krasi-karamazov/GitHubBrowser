package kpk.dev.data.datasource.remote

import kpk.dev.domain.domain.entity.ResponseModel
import kpk.dev.domain.domain.entity.SupportDeskListItem
import kpk.dev.domain.domain.entity.SupportDeskUserParameter

interface ISupportDeskRemoteDataSource {
    fun getSupportDeskUsers(): ResponseModel<List<SupportDeskListItem>>
    fun getSupportDeskUserByParameter(supportDeskUserParameter: SupportDeskUserParameter): ResponseModel<SupportDeskListItem>
}