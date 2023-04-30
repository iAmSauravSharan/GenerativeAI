package com.sauravsharan.generativeai.model

import java.sql.Timestamp

data class MessageResponse(
    val to: String = "",
    val from: String = "",
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis()),
    var content: String? = null,
    var images: List<String>? = null,
    val type: Int = 1,
)
