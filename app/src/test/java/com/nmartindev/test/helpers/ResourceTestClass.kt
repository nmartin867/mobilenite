package com.nmartindev.test.helpers

import java.io.BufferedReader
import java.io.InputStreamReader

interface IResourceTestClass {
    val resourceString: String
}

class ResourceTestClass(private val resourcePath: String): IResourceTestClass {
    override val resourceString: String by lazy {
        ResourceTestClass::class.java.classLoader?.let { loader ->
            loader.getResourceAsStream(resourcePath)?.let { inputStream ->
                inputStream.use {
                  BufferedReader(InputStreamReader(it)).readText()
                }
            }
        } ?: kotlin.run {
            throw Throwable("Resource not found")
        }
    }
}