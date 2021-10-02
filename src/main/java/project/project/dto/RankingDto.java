package project.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="RankingDto : 랭킹 ", description="랭킹 내용")
@Data
public class RankingDto {
	@ApiModelProperty(value="게임 인덱스")
	private int gameIdx;
	@ApiModelProperty(value="게임 제목")
	private String gameTitle;
	@ApiModelProperty(value="유저 아이디")
	private String userId;
	@ApiModelProperty(value="점수")
	private int score;
	private int playedCnt;
	@ApiModelProperty(value="점수 저장시간")
	private String savedTime;
}
