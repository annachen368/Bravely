package io.github.annachen368.bravely.data.repository

import io.github.annachen368.bravely.data.dto.ChatRequest
import io.github.annachen368.bravely.data.dto.Message
import io.github.annachen368.bravely.data.remote.OpenAIApi
import io.github.annachen368.bravely.domain.model.ChatMessage
import io.github.annachen368.bravely.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val api: OpenAIApi
) : ChatRepository {

    override suspend fun getChatResponse(messages: List<ChatMessage>): String {
        val request = ChatRequest(
            messages = messages.map {
                Message(role = it.role, content = it.content)
            }
        )

        val response = api.chatCompletions("Bearer YOUR_API_KEY", request)
        return response.choices.firstOrNull()?.message?.content.orEmpty()
    }
}
