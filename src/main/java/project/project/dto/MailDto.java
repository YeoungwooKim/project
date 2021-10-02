package project.project.dto;

import java.util.Random;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="MailDto : 메일 ", description="메일 내용")
@Data
public class MailDto {
	@ApiModelProperty(value="주소")
	private String address;
	@ApiModelProperty(value="제목")
	private String title;
	@ApiModelProperty(value="내용")
	private String message;
	@ApiModelProperty(value="자동 생성 비밀번호")
	private String generatedPass;
	
	public MailDto(String address) {
		this.address = address;
		this.title = "forget password?";
		this.generatedPass = genPass(System.currentTimeMillis());
		this.message = "your password is '" + generatedPass + "'";
	}

	public String genPass(long time) {
		int length = (int) (Math.random() * 7) + 3;
		String res = "";
		Random random = new Random();
		random.setSeed(time);

		for (int i = 0; i < length; i++) {
			if (random.nextBoolean())
				res += (char) ((int) (Math.random() * 26) + 65);
			else
				res += (char) ((int) (Math.random() * 26) + 97);
		}
		return res;
	}

}
