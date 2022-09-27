package com.study.member.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class MailSendService {
	
	@Inject
	JavaMailSenderImpl mailSender;
	
	private String randomKey() {
		String randomKey = "";
		Random random = new Random();
		for(int i = 0; i < 6; i++) {
			randomKey += random.nextInt(10);
			
		}
		return randomKey;
	}
	
	public String sendAuthMail(String mail) throws MessagingException{
		//실제로 메일을 보내기
		String randomKey = randomKey();
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		mimeMessage.setSubject("메일제목");
		mimeMessage.setText("메일내용." + "\n 인증 번호 : " + randomKey);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
		mailSender.send(mimeMessage);
		return randomKey;
	}
	
	
	
	
}
