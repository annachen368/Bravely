package io.github.annachen368.bravely.data.remote

import io.github.annachen368.bravely.data.dto.ChatRequest
import io.github.annachen368.bravely.data.dto.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIApi {
    @POST("v1/chat/completions")
    suspend fun chatCompletions(
        @Header("Authorization") auth: String,
        @Header("OpenAI-Project") projectId: String,
        @Body body: ChatRequest
    ): ChatResponse
}
