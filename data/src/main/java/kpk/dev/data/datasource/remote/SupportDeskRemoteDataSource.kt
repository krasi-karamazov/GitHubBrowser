package kpk.dev.data.datasource.remote

import kpk.dev.data.api.ApiService
import kpk.dev.data.api.entity.Dto
import kpk.dev.domain.domain.entity.ResponseModel
import kpk.dev.domain.domain.entity.SupportDeskListItem
import kpk.dev.domain.domain.entity.SupportDeskUserParameter
import javax.inject.Inject

class SupportDeskRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    ISupportDeskRemoteDataSource {

    override fun getSupportDeskUsers(): ResponseModel<List<SupportDeskListItem>> {
        val list: List<Dto.SupportDeskStaffItem>? = apiService.getSupportDeskItemsList()
        return if (!list.isNullOrEmpty()) {
            ResponseModel.Success(list.map {
                it.map()
            })
        } else {
            ResponseModel.Failure("Could not fetch staff data")
        }
    }

    override fun getSupportDeskUserByParameter(supportDeskUserParameter: SupportDeskUserParameter): ResponseModel<SupportDeskListItem> {
        TODO("Not yet implemented")
    }
}