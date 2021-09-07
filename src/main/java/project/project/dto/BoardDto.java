package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class BoardDto {

	Logger log = LoggerFactory.getLogger(this.getClass());

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

	public void printAll() {
		log.debug("boardIdx : " + boardIdx);
		log.debug("title : " + title);
		log.debug("contents : " + contents);
		log.debug("hitCnt : " + hitCnt);
		log.debug("createdDate : " + createdDate);
		log.debug("creatorId : " + creatorId);
		log.debug("updatedDate : " + updatedDate);
	}
}
