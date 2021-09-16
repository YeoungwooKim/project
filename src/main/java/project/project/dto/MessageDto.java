package project.project.dto;

import lombok.Data;

@Data
public class MessageDto {
	private int idx;
	private String sender;
	private String recipient;
	private String title;
	private String content;
	private String postDate;
	private int isRead;
}
