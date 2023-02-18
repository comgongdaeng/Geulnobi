package hello.geulenobi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailAuthService {
    private final RedisTemplate<String, String> redisTemplate;
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;


    public String generateCode(String email) {
        String code = generateRandomCode();
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(5));
        return code;
    }

    public boolean validateCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(email);
            return true;
        }
        return false;
    }


    private String generateRandomCode() {
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 7; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    code.append((char) (rnd.nextInt(26) + 97));
                    break;
                case 1:
                    code.append((char) (rnd.nextInt(26) + 65));
                    break;
                case 2:
                    code.append((rnd.nextInt(10)));
                    break;
            }
        }
        return code.toString();
    }


    public void sendEmailMessage(String email) throws Exception {

        String code = generateCode(email);
        // 인증코드 생성
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
        message.setSubject("[인증 코드] " + code); // 이메일 제목
        message.setText(setContext(code), "utf-8", "html"); // 내용 설정(Template Process)


        message.setFrom(new InternetAddress("aksla0207@naver.com", "글도비 인증 코드 발송 테스트"));
        //네이버는 "보내는 사람 email = 서버 user"동일 해야만 동작하는 것 같습니다.
        //TODO 하드코딩... 나중에 변경할 수 있도록 수정하기

        emailSender.send(message); // 이메일 전송
    }

    private String setContext(String code) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("code", code); // Template 에 전달할 데이터 설정
        return templateEngine.process("mail", context); // mail.html
    }
}
