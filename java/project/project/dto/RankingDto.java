package project.project.dto;

import lombok.Data;

@Data
public class RankingDto {
	private String userId;
	private int gameIdx;
	private int score;
	private String savedTime;
}
