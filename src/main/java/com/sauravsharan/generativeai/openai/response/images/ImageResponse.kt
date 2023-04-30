package com.sauravsharan.generativeai.openai.response.images

data class ImageResponse(

    var created: Long? = null,

    var data: List<ImageUrl?>? = null

)
