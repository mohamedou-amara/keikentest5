package keiken.test.openai.chatgpt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import keiken.test.openai.OpenAiApiClient;
import keiken.test.openai.OpenAiApiClient.OpenAiService;
import keiken.test.openai.model.ChatHistory;
import keiken.test.openai.service.ChatService;


@Controller
public class ChatGptController {
	
	private static final String MAIN_PAGE = "index";
	
	@Autowired private ObjectMapper jsonMapper;
	@Autowired private OpenAiApiClient client;
	@Autowired private ChatService chatService;
	
	private String chatWithGpt3(String message) throws Exception {
		var completion = CompletionRequest.defaultWith(message);
		var postBodyJson = jsonMapper.writeValueAsString(completion);
		var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
		var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
		return completionResponse.firstAnswer().orElseThrow();
	}
	
	@GetMapping(path = "/message")
	public ResponseEntity<Object> index() throws JsonProcessingException {
	    
		List<ChatHistory> allChat = chatService.getChatHistory();
	    ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(allChat);
	    //return new ResponseEntity<>(json, HttpStatus.OK);
	    
	    return ResponseEntity.ok().body(json);
	}
	
	@PostMapping(path = "/message")
	public ResponseEntity<Object> chat(@RequestBody String dto) {
		
	  try {
	    String response = chatWithGpt3(dto);
	    System.out.println(dto);
	    System.out.println(response);
	    
	    //new history
	    try {
	    ChatHistory chatHistory = new ChatHistory(dto, response);
	    chatService.saveChatHistory(chatHistory);
	    }catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
	    List<ChatHistory> allChat = chatService.getChatHistory();
	    
	    ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(allChat);
	    //return new ResponseEntity<>(json, HttpStatus.OK);
	    
	    return ResponseEntity.ok().body(json);
	  } catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	  }
	}
	
}
