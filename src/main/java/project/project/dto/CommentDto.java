package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="CommentDto : 댓글", description="댓글 내용")
@Data
public class CommentDto {
	@ApiModelProperty(value="참조하는 게시글 번호")
	@NotNull	
	private int boardIdx;
	@ApiModelProperty(value="댓글 번호")
	@NotNull
	private int commentIdx;
	@ApiModelProperty(value="댓글 작성자")
	private String creatorId;
	@ApiModelProperty(value="댓글 내용")
	@NotBlank
	private String contents;
	@ApiModelProperty(value="댓글 작성일자")
	private String createdDate;
	@ApiModelProperty(value="댓글 삭제여부")
	private String deletedYn;
}
