package project.project.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDto {
	private int board_idx;
	private String title;
	private String contents;
	private int hit_cnt;
	private LocalDateTime created_date;
	private String creator_id;
	private LocalDateTime updated_date;

}
