package io.github.annachen368.bravely.domain.usecase

import io.github.annachen368.bravely.domain.model.ChatMessage
import io.github.annachen368.bravely.domain.repository.ChatRepository

class GetChatResponseUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(messages: List<ChatMessage>): String {
        return repository.getChatResponse(messages)
    }
}