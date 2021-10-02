package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="BoardDto : 게시글 ", description="게시글 내용")
@Data
public class BoardDto {
	@ApiModelProperty(value="게시글번호")
	@NotNull
	private int boardIdx;

	@ApiModelProperty(value="게시글 제목")
	@NotBlank
	@Size(min = 1)
	private String title;

	@ApiModelProperty(value="게시글 내용")
	@NotBlank
	@Size(min = 1, message="컨탠츠는 최소 1글자 이상입니다.")
	private String contents;

	@ApiModelProperty(value="조회수")
	private int hitCnt;

	@ApiModelProperty(value="게시 일자")
	private String createdDate;

	@ApiModelProperty(value="작성자")
	private String creatorId;

	@ApiModelProperty(value="수정 일자")
	private String updatedDate;

}
