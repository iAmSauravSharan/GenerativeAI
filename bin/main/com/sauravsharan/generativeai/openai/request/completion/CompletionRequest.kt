package com.sauravsharan.generativeai.openai.request.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class CompletionRequest (

    /**
     * The name of the model to use.
     * to get list of all models, refer /model API.
     */
    var model: String,

    /**
     * to be used when using chatCompletions
     * use it in order to talk to bot like a conversation
     */
    var messages: List<Conversation>? = null,

    /**
     * to be used when using completions
     * An optional prompt to complete from
     */
    var prompt: String? = null,

    /**
     * A unique identifier representing your end-user,
     * which can help OpenAI to monitor and detect abuse.
     */
    var role: String? = null,

    /**
     * The maximum number of tokens to generate.
     * Requests can use up to 2048 tokens shared between prompt and completion.
     * (One token is roughly 4 characters for normal English text)
     * newest model can support upto 4096 tokens
     */
    var maxTokens: Int? = 16,

    /**
     * What sampling temperature to use. Higher values means the model will take more risks.
     * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
     *
     * We generally recommend using this or [CompletionRequest.topP] but not both.
     */
    var temperature: Double? = 1.0,

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are
     * considered.
     *
     * We generally recommend using this or [CompletionRequest.temperature] but not both.
     */
    @JsonProperty("top_p")
    var topP: Double? = null,

    /**
     * How many completions to generate for each prompt.
     *
     * Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for [CompletionRequest.maxTokens] and [CompletionRequest.stop].
     */
    var n: Int? = null,

    /**
     * Whether to stream back partial progress.
     * If set, tokens will be sent as data-only server-sent events as they become available,
     * with the stream terminated by a data: DONE message.
     */
    var stream: Boolean? = null,

    /**
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 10, the API will return a list of the 10 most likely tokens.
     * The API will always return the logprob of the sampled token,
     * so there may be up to logprobs+1 elements in the response.
     */
    var logprobs: Int? = null,

    /**
     * Echo back the prompt in addition to the completion
     */
    var echo: Boolean = false,

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     */
    var stop: List<String>? = null,

    /**
     * Number between -2.0 and 2.0 (default 0) that penalizes new tokens based on whether they appear in the text so far.
     * Increases the model's likelihood to talk about new topics.
     */
    var presencePenalty: Double? = null,

    /**
     * Number between -2.0 and 2.0 (default 0) that penalizes new tokens based on their existing frequency in the text so far.
     * Decreases the model's likelihood to repeat the same line verbatim.
     */
    var frequencyPenalty: Double? = null,

    /**
     * Generates best_of completions server-side and returns the "best"
     * (the one with the lowest log probability per token).
     * Results cannot be streamed.
     *
     * When used with [CompletionRequest.n], best_of controls the number of candidate completions and n specifies how many to return,
     * best_of must be greater than n.
     */
    var bestOf: Int? = null,

    /**
     * comes after a completion of inserted text.
     */
    var suffix: String? = null,

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100.
     *
     * https://beta.openai.com/docs/api-reference/completions/create#completions/create-logit_bias
     */
    var logitBias: Map<String, Int>? = null,

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     */
    var user: String? = null
)
