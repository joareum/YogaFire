package com.yogafire.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	@Value("${spring.mail.username}")
	private String senderEmail;
	
	private final JavaMailSender mailSender;
	private final RedisUtil redisUtil;
	
	public EmailAuthResponse sendEmail(String toEmail) {
		if(redisUtil.existData(toEmail)) {
			redisUtil.deleteData(toEmail);
		}
		
		try {
			MimeMessage emailForm = createEmailForm(toEmail);
			mailSender.send(emailForm);
			return new EmailAuthResponse(true, "인증번호가 메일로 전송되었습니다.");
		} catch(MessageingException | MailSendException e) {
			return new EmailAuthResponse(false, "메일 전송 중 오류가 발생하였습니다. 다시 시도해주세요.");
		}
		
		private MimeMessage createEmailForm(String email) throws MessagingException{
			String authCode = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
			MimeMessage message = mailSender.createMimeMessage();
		}
	}
}
