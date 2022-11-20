package com.program.msemail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.program.msemail.enums.StatusEmail;
import com.program.msemail.models.EmailModel;
import com.program.msemail.repositories.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository repository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail (EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
						
		}catch(MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return repository.save(emailModel);
		}		
		
	}	

}
