package project.project.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GameDto {
	private int idx;
	@NotBlank
	private String gameTitle;
	private String createdDate;
	private String playedCnt;
}
