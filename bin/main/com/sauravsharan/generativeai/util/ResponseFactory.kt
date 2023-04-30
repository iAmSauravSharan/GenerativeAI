package com.sauravsharan.generativeai.util

import com.sauravsharan.generativeai.model.Option
import kotlin.random.Random

class OptionsFactory(private val intention: String) {

    fun getOptions(): List<Option> {
        val options = mutableListOf<Option>()
        when (intention) {
            "qna" -> {
                options.add(Option("Who built Taj mahal?"))
                options.add(Option("Suggest me a skin care routine for an oily screen?"))
                options.add(Option("What is the name of the 10th planet?"))
                options.add(Option("Explain polymorphism like I am a 5 years old"))
                options.add(Option("What is Indian Panel code related to 420"))
                options.add(Option("Write me a summary for a java developer role"))
            }
        }
        return options;
    }

}

class ResponseFactory {
    fun getErrorResponse(): String {
        val options = arrayOf(
            "I am done for the day. I can not take it anymore.",
            "Sorry, I can't find what you are looking for",
            "I am feeling sleepy now. Can we talk later!",
            "Goosh! you are annoying"
        );
        val index = Random.nextInt(options.size)
        return options[index]
    }

}