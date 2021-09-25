package project.project.dto;

import java.util.Random;

import lombok.Data;

@Data
public class MailDto {
	private String address;
	private String title;
	private String message;
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
