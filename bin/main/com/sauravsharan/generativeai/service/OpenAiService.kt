package com.sauravsharan.generativeai.service

import com.google.gson.Gson
import com.sauravsharan.generativeai.model.MessageRequest
import com.sauravsharan.generativeai.model.MessageResponse
import com.sauravsharan.generativeai.openai.OpenAiApi
import com.sauravsharan.generativeai.openai.OpenAiApiService
import com.sauravsharan.generativeai.openai.request.completion.CompletionRequest
import com.sauravsharan.generativeai.openai.request.completion.Conversation
import com.sauravsharan.generativeai.openai.request.images.ImageVariationRequest
import com.sauravsharan.generativeai.openai.response.images.ImageUrl
import com.sauravsharan.generativeai.openai.response.model.Model
import com.sauravsharan.generativeai.util.Constants.CONVERSATION
import com.sauravsharan.generativeai.util.Constants.DEFAULT_TOKEN_SIZE
import com.sauravsharan.generativeai.util.Constants.IMAGE
import com.sauravsharan.generativeai.util.Constants.PROMPT
import com.sauravsharan.generativeai.util.ErrorMsgFor
import com.sauravsharan.generativeai.util.InvalidRequestException
import com.sauravsharan.generativeai.util.OpenAiConfig
import com.sauravsharan.generativeai.util.OpenAiConfig.API_TOKEN
import com.sauravsharan.generativeai.util.OpenAiConfig.TIMEOUT

class OpenAiService {

    private val api: OpenAiApi

    init {
        val openAi = OpenAiApiService(API_TOKEN, TIMEOUT).build()
        api = OpenAiApi(openAi)
    }

    private fun greet(): MessageResponse {
        return MessageResponse(content = "Hello There!")
    }

    private fun getAllModels(): List<Model>? {
        return api.listModels()
    }

    private fun generateImageVariations(imagePath: String): List<ImageUrl?>? {
        val request = ImageVariationRequest(
            imagePath = imagePath,
            n = (Math.random() * 10).toInt()
        )
        val response = api.generateImageVariations(request)
        return response.data
    }

    private fun getCompletions(
        prompt: String,
        model: String,
        tokenSize: String = DEFAULT_TOKEN_SIZE.toString(),
        temperature: String
    ): MessageResponse {
        val completionRequest = CompletionRequest(
            model = model,
            prompt = prompt,
            temperature = temperature.toDouble(),
            maxTokens = tokenSize.toInt()
        )
        val response = api.createCompletion(completionRequest)

        return response.getMessageResponse(false)
    }

    private fun chatCompletions(
        model: String,
        messages: List<Conversation>,
        tokenSize: String = DEFAULT_TOKEN_SIZE.toString(),
        temperature: String
    ): MessageResponse {
        val completionRequest = CompletionRequest(
            model = model,
            messages = messages,
            temperature = temperature.toDouble(),
            maxTokens = tokenSize.toInt()
        )
        val response = api.chatCompletions(completionRequest)
        return response.getMessageResponse(true)
    }

    fun execute(request: MessageRequest): String {
        lateinit var response: Any
        when (request.intention) {
            OpenAiConfig.INTENTIONS.GREET.model -> {
                response = OpenAiService().greet()
            }

            OpenAiConfig.INTENTIONS.ALL_MODELS.model -> {
                response = OpenAiService().getAllModels()!!
                return Gson().toJson(response, List::class.java)
            }

            OpenAiConfig.INTENTIONS.LETS_SNAP.model -> {
                if (request.body.has(IMAGE)) {
                    response = OpenAiService().generateImageVariations(
                        request.body.get(IMAGE).asString)!!
                    return Gson().toJson(response, List::class.java)
                } else {
                    throw InvalidRequestException(ErrorMsgFor.INVALID_IMAGE)
                }
            }

            OpenAiConfig.INTENTIONS.LETS_ASK.model -> {
                if (request.body.has(PROMPT)) {
                    response = OpenAiService().getCompletions(
                        prompt = request.body.get(PROMPT).asString,
                        model = request.model,
                        temperature = request.responseCreativity.toString(),
                        tokenSize = request.tokenSize.toString()
                    )
                } else throw InvalidRequestException(ErrorMsgFor.INVALID_PROMPT)
            }

            OpenAiConfig.INTENTIONS.LETS_CHAT.model -> {
                if (request.body.has(CONVERSATION)) {
                    val conversations = Gson().fromJson<List<Conversation>>(
                        request.body.get(CONVERSATION).asString,
                        List::class.java)
                    response = OpenAiService().chatCompletions(
                        messages = conversations,
                        model = request.model,
                        temperature = request.responseCreativity.toString(),
                        tokenSize = request.tokenSize.toString()
                    )
                } else throw InvalidRequestException(ErrorMsgFor.INVALID_PROMPT)
            }

            else -> throw InvalidRequestException(ErrorMsgFor.INVALID_ENDPOINT)
        }
        return Gson().toJson(response, MessageResponse::class.java)
    }

}