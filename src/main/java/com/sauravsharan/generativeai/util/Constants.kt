package com.sauravsharan.generativeai.util

object Constants {

    const val APP_NAME = "GenerativeAI"

    const val JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4"
    const val JWT_HEADER = "Authorization"

    const val IMAGE = "image"
    const val PROMPT = "prompt"
    const val CONVERSATION = "conversation"

    const val DEFAULT_TOKEN_SIZE = 1000
    const val DEFAULT_TEMPERATURE = 0.2

}

object ErrorMsgFor {
    const val CRASH = "Oops! Unreachable"
    const val UNKNOWN_ERROR = "Unknown Error Occurred"

    const val INVALID_VENDOR = "Please select a valid vendor"
    const val INVALID_IMAGE = "Please select a valid image"
    const val INVALID_PROMPT = "Please enter a valid prompt"
    const val INVALID_ENDPOINT = "You are trying to reach incorrect endpoint!"

}

object Preference {

    const val MODEL = "model"
    const val VENDOR = "vendor"
    const val INTENTION = "intention"
    const val RESPONSE_CREATIVITY = "response_creativity"

}

object OpenAiConfig {

    public const val API_TOKEN = "ENTER_YOUT_OPEN_AI_API_ACCESS_KEY"
    public const val TIMEOUT = 500

    enum class INTENTIONS(val model: String) {
        GREET("greet"),
        ALL_MODELS("all-models"),
        LETS_SNAP("lets-snap"),
        LETS_ASK("lets-ask"),
        LETS_CHAT("lets-chat"),
    }

}


enum class MODELS(val model: String) {
    TEXT_DA_VINCI("text-davinci-003"),
    GPT_3_POINT_5("gpt-3.5-turbo"), // 4096 token limit
    GPT_4("gpt-4")
}

enum class VENDORS(val model: String) {
    OPEN_AI("open_ai")
}