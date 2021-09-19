package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class MessageDto {
	@NotNull
	private int idx;
	@NotBlank
	private String sender;
	@NotBlank
	private String recipient;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private String postDate;
	private int isRead;
	private String deletedYn;
	
	public MessageDto(String sender, String recipient, String title, String content) {
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
		this.content = content;
	}
	
}
