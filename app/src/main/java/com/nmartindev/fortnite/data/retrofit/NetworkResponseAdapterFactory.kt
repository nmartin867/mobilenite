package com.nmartindev.fortnite.data.retrofit

import com.nmartindev.fortnite.domain.Error
import com.nmartindev.fortnite.domain.NetworkException
import com.nmartindev.fortnite.domain.NetworkResult
import com.nmartindev.fortnite.domain.Success
import com.nmartindev.fortnite.domain.UnknownError
import okhttp3.Request
import okio.Timeout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseCall<T : Any>(
    private val delegate: Call<T>
): Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
       return delegate.enqueue(object : Callback<T> {
           override fun onResponse(call: Call<T>, response: Response<T>) {
               val body = response.body()
               val code = response.code()

               if(response.isSuccessful) {
                   if(body != null) {
                       callback.onResponse(
                           this@NetworkResponseCall,
                           Response.success(Success(body))
                       )
                   } else {
                       callback.onResponse(
                           this@NetworkResponseCall,
                           Response.success(
                               NetworkException(Throwable("Empty body"))
                           )
                       )
                   }
               } else {
                   callback.onResponse(
                       this@NetworkResponseCall,
                       Response.success(
                           Error(code, body.toString())
                       )
                   )
               }
           }

           override fun onFailure(call: Call<T>, throwable: Throwable) {
               val networkResponse: NetworkResult<T> = when (throwable) {
                   is IOException -> NetworkException(throwable)
                   else -> UnknownError(throwable)
               }
               callback.onResponse(
                   this@NetworkResponseCall,
                   Response.success(networkResponse)
               )
           }
       })
    }

    override fun execute(): Response<NetworkResult<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isExecuted() = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}

class NetworkResultAdapter<S : Any>(
    private val successType: Type
): CallAdapter<S, Call<NetworkResult<S>>> {

    override fun responseType() = successType

    override fun adapt(call: Call<S>): Call<NetworkResult<S>> {
        return NetworkResponseCall(call)
    }

}

class NetworkResponseAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if(Call::class.java != getRawType(returnType)){
            return null
        }

        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResult<<Foo>> or Call<NetworkResult<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)

        if(getRawType(responseType) != NetworkResult::class.java){
            return null
        }

        // the response type is NetworkResult and should be parameterized
        check(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResult<Foo> or NetworkResult<out Foo>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)

        return NetworkResultAdapter<Any>(successBodyType)
    }
}