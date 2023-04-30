package com.sauravsharan.generativeai.service;

import com.sauravsharan.generativeai.model.MessageRequest
import com.sauravsharan.generativeai.util.ErrorMsgFor
import com.sauravsharan.generativeai.util.InvalidRequestException
import com.sauravsharan.generativeai.util.VENDORS
import java.io.InvalidObjectException

object GenerativeAiService {

    @JvmStatic
    fun execute(
        request: MessageRequest
    ): String {
        when(request.vendor) {
            VENDORS.OPEN_AI.model -> return launchOpenAi(request)
        }
        throw InvalidRequestException(ErrorMsgFor.INVALID_VENDOR)
    }

    private fun launchOpenAi(
        request: MessageRequest
    ): String {
        return OpenAiService().execute(request)
    }

}
