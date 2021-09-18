package project.project.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {
	private int boardIdx;
	private int commentIdx;
	private String creatorId;
	private String contents;
	private LocalDateTime createdDate;
}
