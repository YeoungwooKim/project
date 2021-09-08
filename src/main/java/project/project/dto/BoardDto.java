package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class BoardDto {

	@NotNull
	private int boardIdx;

	@NotBlank
	@Size(min = 1)
	private String title;

	@NotBlank
	@Size(min = 1, message="컨탠츠는 최소 1글자 이상입니다.")
	private String contents;

	private int hitCnt;

	private String createdDate;

	private String creatorId;

	private String updatedDate;

}
