package com.example.testprojek.util

sealed class Condition<T>(data: T? = null) {
    class Success<T>(data: T?): Condition<T>(data)
    class Empty<T>: Condition<T>()
}