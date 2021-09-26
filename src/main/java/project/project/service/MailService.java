package project.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import project.project.dto.MailDto;

@Service
public class MailService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;	
    private static String FROM_ADDRESS = "wooyastdy@gmail.com";
   
    public boolean mailSend(MailDto mail) {
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        try {
        	mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        return true;
    }
}
