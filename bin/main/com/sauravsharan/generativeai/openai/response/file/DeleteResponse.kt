package com.sauravsharan.generativeai.openai.response.file

data class DeleteResponse(

    /**
     * The id of the object.
     */
    var id: String? = null,

    /**
     * The type of object deleted, for example "file" or "model"
     */
    var `object`: String? = null,

    /**
     * True if successfully deleted
     */
    var deleted: Boolean

)
