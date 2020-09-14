package kpk.dev.data.utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kpk.dev.domain.entity.ResponseModel
import retrofit2.HttpException
import java.io.IOException

data class ErrorResponse(val errorDescription: String)

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResponseModel<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResponseModel.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResponseModel.Failure("Unknown Error occurred")
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResponseModel.Failure(errorResponse?.errorDescription)
                }
                else -> ResponseModel.Failure(null)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        return ErrorResponse(throwable.message())
    } catch (exception: Exception) {
        null
    }
}