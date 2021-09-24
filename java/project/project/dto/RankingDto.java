package project.project.dto;

import lombok.Data;

@Data
public class RankingDto {
	private int gameIdx;
	private String gameTitle;
	private String userId;
	private int score;
	private int playedCnt;
	private String savedTime;
}
