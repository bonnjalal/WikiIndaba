package com.bonnjalal.wikiindaba.presentation.state

import java.lang.Exception
import javax.annotation.concurrent.Immutable

//@Immutable
sealed class DataState<out R> {
    data class Success <out T>(val data:T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}
