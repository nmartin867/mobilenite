package com.nmartindev.fortnite.data

import com.nmartindev.fortnite.domain.Error
import com.nmartindev.fortnite.domain.NetworkException
import com.nmartindev.fortnite.domain.NetworkResult
import com.nmartindev.fortnite.domain.Success
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.lang.reflect.Type

class NetworkResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = networkRequest { response }
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = ApiException<T>(t)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }
        })
    }

    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()
    override fun clone(): Call<NetworkResult<T>> = NetworkResultCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() { proxy.cancel() }
}

class NetworkCallAdapter(
    private val resultType: Type
): CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {

    }

}

suspend fun <T: Any> networkRequest(
    block: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val res = block()
        val body = res.body()
        if(res.isSuccessful && body != null) {
            Success(body)
        } else {
            Error(code = res.code(), message = res.message())
        }
    } catch (e: HttpException) {
        Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkException(e)
    }
}