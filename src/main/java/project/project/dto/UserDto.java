package project.project.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="UserDto : 유저 ", description="유저 내용")
@Data
public class UserDto {
	@ApiModelProperty(value="유저 아이디")
	@NotNull
	private String userId;
	@ApiModelProperty(value="유저 비밀번호")
	@NotNull
	private String userPw;
	@ApiModelProperty(value="유저 이메일")
	@NotNull
	private String userEmail;
	@ApiModelProperty(value="활성화 유무")
	private int enabled;
	@ApiModelProperty(value="이메일 인증 횟수")
	private int emailValidationCnt;
	@ApiModelProperty(value="비활성화된 날짜.")
	private String disabledDate;
}
