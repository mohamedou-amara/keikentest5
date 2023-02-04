package keiken.test.openai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import keiken.test.openai.model.ChatHistory;

@Repository
public interface ChatHistoryRepository extends MongoRepository<ChatHistory, String> {
}