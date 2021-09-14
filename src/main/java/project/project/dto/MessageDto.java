package project.project.dto;

import lombok.Data;

@Data
public class MessageDto {
	private String sender;
	private String recipient;
	private String title;
	private String content;

}
