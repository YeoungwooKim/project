package project.project.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto {
	@NotNull
	private String userId;
	@NotNull
	private String userPw;
	@NotNull
	private String userEmail;
	private int enabled;
	private int emailValidationCnt;
	private String disabledDate;
}
