package io.github.annachen368.bravely.domain.model

data class ChatMessage(
    val role: String, // "user" or "assistant"
    val content: String
)
