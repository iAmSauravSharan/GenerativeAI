package com.sauravsharan.generativeai.openai.response.completion

import com.sauravsharan.generativeai.model.MessageResponse
import com.sauravsharan.generativeai.util.ResponseFactory


data class CompletionResponse(
    /**
     * A unique id assigned to this completion.
     */
    var id: String? = null,

    /**
     * The type of object returned, should be "text_completion"
     */
    var `object`: String? = null,

    /**
     * The creation time in epoch seconds.
     */
    var created: Long = 0,

    /**
     * The GPT-3 model used.
     */
    var model: String? = null,

    /**
     * A list of generated completions.
     */
    var choices: List<CompletionChoice>? = null,

    /**
     * The API usage for this request
     */
    var usage: Usage? = null
) {

    fun getMessageResponse(isConversation: Boolean): MessageResponse {
        val response = MessageResponse();
        try {
            if (null != choices && choices!!.isNotEmpty()) {
                if (isConversation) {
                    return conversationByParsing(response)
                }
                var content = choices!![0].text ?: ""
                while (content.startsWith("\n")) {
                    content = content.replaceFirst("\n", "")
                }
                while (content.endsWith("\n")) {
                    content = content.removeSuffix("\n")
                }
                if (content.contains("https://images")) {

                    val firstIndex = content.indexOfFirst { it == '(' }
                    val lastIndex = content.indexOfLast { it == ')' }


                    val images = mutableListOf<String>()
                    images.add(content.substring(firstIndex + 1, lastIndex))
                    response.images = images
                } else response.content = content
            } else {
                response.content = ResponseFactory().getErrorResponse()
            }
        } catch (ex: Exception) {
            response.content = ResponseFactory().getErrorResponse()
        }
        return response;
    }

    private fun conversationByParsing(response: MessageResponse): MessageResponse {
        val conversations = choices!![0].message
        response.content = if (!conversations.isNullOrEmpty()) conversations[conversations.size - 1].content
        else ResponseFactory().getErrorResponse()
        return response
    }

}
