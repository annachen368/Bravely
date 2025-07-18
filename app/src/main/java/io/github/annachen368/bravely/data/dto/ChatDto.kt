package io.github.annachen368.bravely.data.dto

data class ChatRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<Message>
)

data class Message(
    val role: String, // "user" or "assistant"
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)