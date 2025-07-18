package io.github.annachen368.bravely.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.annachen368.bravely.data.remote.OpenAIApi
import io.github.annachen368.bravely.data.repository.ChatRepositoryImpl
import io.github.annachen368.bravely.domain.repository.ChatRepository
import io.github.annachen368.bravely.domain.usecase.GetChatResponseUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.openai.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideOpenAIApi(client: OkHttpClient): OpenAIApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAIApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRepository(api: OpenAIApi): ChatRepository {
        return ChatRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetChatResponseUseCase(repository: ChatRepository): GetChatResponseUseCase {
        return GetChatResponseUseCase(repository)
    }
}