package io.github.annachen368.bravely.presentation.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.annachen368.bravely.domain.model.ChatMessage
import io.github.annachen368.bravely.domain.usecase.GetChatResponseUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatResponseUseCase: GetChatResponseUseCase
) : ViewModel() {

    var messages by mutableStateOf<List<ChatMessage>>(emptyList())
        private set

    var response by mutableStateOf("")
        private set

    fun sendMessage(input: String) {
        viewModelScope.launch {
            val newMessage = ChatMessage("user", input)
            val updatedMessages = messages + newMessage
            try {
                val reply = getChatResponseUseCase(updatedMessages)
                messages = updatedMessages + ChatMessage("assistant", reply)
                response = reply
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    messages = updatedMessages + ChatMessage(
                        "assistant",
                        "You're sending messages too fast. Please wait and try again."
                    )
                } else {
                    messages = updatedMessages + ChatMessage(
                        "assistant",
                        "Oops! Something went wrong."
                    )
                }
            } catch (e: Exception) {
                Log.d("OpenAI", "Crashes: ${e.message}")
            }
        }
    }
}
