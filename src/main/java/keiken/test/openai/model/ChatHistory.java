package keiken.test.openai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ChatHistory")
public class ChatHistory {
	    @Id
	    private String id;
	    private String prompt;
	    private String response;
	    
	   //constructors
	    
	    //getters and setters
		public String getId() {
			return id;
		}
		public ChatHistory(String prompt, String response) {
			this.prompt = prompt;
			this.response = response;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPrompt() {
			return prompt;
		}
		public void setPrompt(String prompt) {
			this.prompt = prompt;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
}
