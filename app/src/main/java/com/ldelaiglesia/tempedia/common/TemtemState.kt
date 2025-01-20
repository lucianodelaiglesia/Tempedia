package com.ldelaiglesia.tempedia.common

interface TemtemState<T> {
    val isLoading: Boolean
    val data: T?
    val error: String
}