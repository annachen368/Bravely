package io.github.annachen368.bravely.domain.repository

import io.github.annachen368.bravely.domain.model.ChatMessage

interface ChatRepository {
    suspend fun getChatResponse(messages: List<ChatMessage>): String
}