package com.sauravsharan.generativeai.openai.request.edit

import com.fasterxml.jackson.annotation.JsonProperty

data class EditRequest(
    /**
     * The name of the model to use.
     * Required if using the new v1/edits endpoint.
     */
    val model: String,

    /**
     * The input text to use as a starting point for the edit.
     */
    val input: String,

    /**
     * The instruction that tells the model how to edit the prompt.
     * For example, "Fix the spelling mistakes"
     */
    val instruction: String,

    /**
     * How many edits to generate for the input and instruction.
     */
    var n: Int? = 1,

    /**
     * What sampling temperature to use. Higher values means the model will take more risks.
     * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
     *
     * We generally recommend altering this or [EditRequest.topP] but not both.
     */
    var temperature: Double? = 1.0,

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass.So 0.1 means only the tokens comprising the top 10% probability mass are
     * considered.
     *
     * We generally recommend altering this or [EditRequest.temperature] but not both.
     */
    @JsonProperty("top_p")
    var topP: Double? = null
)