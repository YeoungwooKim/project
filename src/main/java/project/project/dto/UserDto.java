package project.project.dto;

import java.util.List;

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
}
