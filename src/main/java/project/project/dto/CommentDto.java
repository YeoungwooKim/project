package project.project.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {
	private int board_idx;
	private String creator_id;
	private String contents;
	private LocalDateTime created_date;
}
