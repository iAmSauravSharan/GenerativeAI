package com.sauravsharan.generativeai.model

import com.google.gson.JsonObject
import com.sauravsharan.generativeai.openai.request.completion.Conversation
import com.sauravsharan.generativeai.util.Constants.DEFAULT_TEMPERATURE
import com.sauravsharan.generativeai.util.Constants.DEFAULT_TOKEN_SIZE
import com.sauravsharan.generativeai.util.MODELS
import com.sauravsharan.generativeai.util.OpenAiConfig
import com.sauravsharan.generativeai.util.VENDORS

data class MessageRequest(
    var query: String? = null,
    var model: String = MODELS.GPT_3_POINT_5.model,
    var vendor: String = VENDORS.OPEN_AI.model,
    var intention: String = OpenAiConfig.INTENTIONS.GREET.model,
    var messages: List<Conversation>?,
    var responseCreativity: Double = DEFAULT_TEMPERATURE,
    var tokenSize: Int = DEFAULT_TOKEN_SIZE,
    var body: JsonObject
)
