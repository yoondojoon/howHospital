package kr.or.iei;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender sender;
	
	/*
	
	public boolean sendMail (String mailTitle, String reciver, String mailContent) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		boolean result = false;
		
		
		
		try {
			helper.setSentDate(new Date());
			helper.setFrom(new InternetAddress("unofficialhyokyung@gmail.com","병원어때."));
			helper.setTo(reciver);
			
			helper.setSubject(mailTitle);
			helper.setText(mailContent, true);
			sender.send(message);
			result = true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
		
		
	}

	*/

	public String sendCode(String memberEmail) {
		
		 
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		Random r = new Random();
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<4;i++) {
			
			int flag = r.nextInt(3);
			if(flag == 0 ) {
				int randomCode = r.nextInt(10);
				sb.append(randomCode);
				
			}else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65);
				sb.append(randomCode);
				
			}else if(flag == 2) {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
				
			}
		}
		
		
		
		try {
			helper.setSentDate(new Date());
			helper.setFrom(new InternetAddress("unofficialhyokyung@gmail.com","병원어때."));
			helper.setTo(memberEmail);
			
			helper.setSubject("병원어때 이메일 인증");
			helper.setText(
					"<h1>병원어때 이메일 인증 안내</h1>"
					+"<h3>인증번호는 [<span style='color:#4389BA;'>"
					+sb.toString()
					+"</span>]입니다</h3>"					
					, true);
			sender.send(message);
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			
			sb = null;
			
			e.printStackTrace();
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			
			sb = null;
			
			e.printStackTrace();
			
			
		}
		
		if(sb == null) {
			return null;
		}else {
			return sb.toString();
		}
		
	}
	
}
