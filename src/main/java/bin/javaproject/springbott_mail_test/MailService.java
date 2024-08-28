package bin.javaproject.springbott_mail_test;


import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class MailService {


    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleMail(Collection<String> receivers, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(receivers.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAttachmetMail(Collection<String> receivers, String subject, String content, Collection<DataSource> attachments) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receivers.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content);

            for (DataSource source : attachments) {
                helper.addAttachment(source.getName(), source);
            }

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
