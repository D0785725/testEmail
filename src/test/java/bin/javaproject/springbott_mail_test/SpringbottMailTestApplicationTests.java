package bin.javaproject.springbott_mail_test;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.util.ByteArrayDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.List;

@SpringBootTest
class SpringbottMailTestApplicationTests {

    @Autowired
    MailService mailService;


    @Test
    void contextLoads() {
    }
    @Test
    public void testSimpleMail() {

        mailService.sendSimpleMail(
                List.of("wy411197@gmail.com"),
                "Simple html",
                "<html><body><p>你好！</p><p>My name is <b>Vincent</b>.</p></body></html>"
        );
    }



    @Test
    public void testAttachmetMail() {
        ClassPathResource resource = new ClassPathResource("static/cat01.jpg");


        // 将资源转换为 DataSource
        try (InputStream inputStream = resource.getInputStream()) {
            DataSource dataSource = new ByteArrayDataSource(inputStream, "image/jpeg");
            mailService.sendAttachmetMail(
                    List.of("wy411197@gmail.com"),
                    "Cat Cat Cat",
                    "Welcome to cat World ",
                    List.of(  dataSource)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
