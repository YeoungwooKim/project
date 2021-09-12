package project.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String userPw;
	private String userEmail;
	private int enabled;
}
