package keiken.test.openai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keiken.test.openai.model.ChatHistory;
import keiken.test.openai.repository.ChatHistoryRepository;

@Service
public class ChatService {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    public void saveChatHistory(ChatHistory chatHistory) {
        //ChatHistory chatHistory = new ChatHistory(prompt, response);
        chatHistoryRepository.save(chatHistory);
    }
    
    public List<ChatHistory> getChatHistory() {
        return chatHistoryRepository.findAll();
    }
}
