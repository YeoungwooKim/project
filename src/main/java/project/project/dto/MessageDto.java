package project.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@ApiModel(value="MessageDto : 메세지 ", description="메세지 내용")
@Data
public class MessageDto {
	@ApiModelProperty(value="메세지 번호")
	@NotNull
	private int idx;
	@ApiModelProperty(value="보내는사람")
	private String sender;
	@ApiModelProperty(value="받는사람")
	@NotBlank
	private String recipient;
	@ApiModelProperty(value="제목")
	@NotBlank
	private String title;
	@ApiModelProperty(value="내용")
	@NotBlank
	private String content;
	@ApiModelProperty(value="전송 일자")
	private String postDate;
	@ApiModelProperty(value="읽음 유무")
	private int isRead;
	@ApiModelProperty(value="삭제 유무")
	private String deletedYn;
	
	public MessageDto(String sender, String recipient, String title, String content) {
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
		this.content = content;
	}
	
}
