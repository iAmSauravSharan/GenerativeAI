package com.sauravsharan.generativeai.openai.response.completion

import com.sauravsharan.generativeai.openai.request.completion.CompletionRequest
import com.sauravsharan.generativeai.openai.request.completion.Conversation


data class CompletionChoice(
    /**
     * when used in conversation, use message template
     */
    var message: List<Conversation>? = null,

    /**
     * when used in normal completion, use text template
     */
    var text: String? = null,

    /**
     * This index of this completion in the returned list.
     */
    var index: Int? = null,

    /**
     * The log probabilities of the chosen tokens and the top [CompletionRequest.logprobs] tokens
     */
    var logprobs: LogProbResponse? = null,

    /**
     * The reason why GPT-3 stopped generating, possible reason could be -> stop, length, content_filter, null.
     * stop: API returned complete model output
     * length: Incomplete model output due to max_tokens parameter or token limit
     * content_filter: Omitted content due to a flag from our content filters
     * null: API response still in progress or incomplete
     */
    var finish_reason: String? = null
)
