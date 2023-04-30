package com.sauravsharan.generativeai.util

import java.util.*

object Utils {

    val TOKEN_EXPIRATION_DURATION: Date = Date(Date().time + (2 * 24 * 60 * 60 * 1000))

}


class InvalidRequestException(message: String) : Exception(message)