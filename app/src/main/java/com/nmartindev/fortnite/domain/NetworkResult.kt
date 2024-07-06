package com.nmartindev.fortnite.domain

sealed interface NetworkResult<T : Any>

class Success<T : Any>(val data: T) : NetworkResult<T>
class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class NetworkException<T : Any>(val e: Throwable) : NetworkResult<T>