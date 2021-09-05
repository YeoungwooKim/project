package project.project.dto;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class BoardDto {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdDate;
	private String creatorId;
	private String updatedDate;
	
	public void printAll() {
		log.debug("boardIdx : "+ boardIdx);
		log.debug("title : "+ title);
		log.debug("contents : "+ contents);
		log.debug("hitCnt : "+ hitCnt);
		log.debug("createdDate : "+ createdDate);
		log.debug("creatorId : "+ creatorId);
		log.debug("updatedDate : "+ updatedDate);
	}
}
