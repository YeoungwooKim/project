package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	@NotNull	
	private int boardIdx;
	@NotNull
	private int commentIdx;
	private String creatorId;
	@NotBlank
	private String contents;
	private String createdDate;
	private String deletedYn;
}
