package kpk.dev.domain.entity

sealed class ResponseModel<ResponseData> {
    class Success<ResponseData>(val responseData: ResponseData? = null) : ResponseModel<ResponseData>()
    class Failure<ResponseData>(val errorMessage: String? = null) : ResponseModel<ResponseData>()
}