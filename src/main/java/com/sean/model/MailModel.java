package com.sean.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Component
public class MailModel {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private TemplateEngine tempEngine;


    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zivhoo@163.com");
        helper.setTo("452456385@qq.com");
        helper.setSubject("主题：模板邮件");

        Context context = new Context();
        context.setVariable("username","Account");
        String text =  tempEngine.process("mail", context);
        helper.setText(text, true);

        mailSender.send(mimeMessage);
        System.out.println("邮件已发送");
    }
}
