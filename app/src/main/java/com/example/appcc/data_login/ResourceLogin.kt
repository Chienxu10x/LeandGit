package com.example.appcc.data_login

import java.lang.Exception

sealed class ResourceLogin<out R> {
    data class Success<out R>(val result: R) : ResourceLogin<R>() // Include the result property here
    data class Error(val exception: Exception) : ResourceLogin<Nothing>()
    object InProgress : ResourceLogin<Nothing>()
    object Initial : ResourceLogin<Nothing>()

}
