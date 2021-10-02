package project.project.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="GameDto : 게임 ", description="게임 내용")
@Data
public class GameDto {
	@ApiModelProperty(value="게임번호")
	private int idx;
	@ApiModelProperty(value="게임 제목")
	@NotBlank
	private String gameTitle;
	@ApiModelProperty(value="게임 출시일자")
	private String createdDate;
	@ApiModelProperty(value="플레이 횟수")
	private String playedCnt;
}
