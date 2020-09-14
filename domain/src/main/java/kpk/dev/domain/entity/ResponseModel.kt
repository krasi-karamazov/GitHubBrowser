package kpk.dev.domain.entity

sealed class ResponseModel<out ResponseData> {
    class Success<out ResponseData>(val responseData: ResponseData? = null) : ResponseModel<ResponseData>()
    class Failure(val errorMessage: String? = null) : ResponseModel<Nothing>()
}