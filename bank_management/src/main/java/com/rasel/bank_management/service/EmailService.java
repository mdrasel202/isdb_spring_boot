package com.rasel.bank_management.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import com.rasel.bank_management.utils.GmailServiceUtil;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {

    @Autowired
    private GmailServiceUtil gmailServiceUtil;

    public void sendEmailWithAttachment(String to, String subject, String body)
            throws MessagingException, IOException, GeneralSecurityException {

        Gmail service = gmailServiceUtil.getGmailService();

        // Create the email content
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress("me"));
        email.addRecipient(RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);

        // Create multipart message
        MimeMultipart multipart = new MimeMultipart();

        // Body part
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body);
        multipart.addBodyPart(bodyPart);

        // Attachment part
//        MimeBodyPart attachmentPart = new MimeBodyPart();
//        attachmentPart.setFileName(file.getOriginalFilename());
//        attachmentPart.setContent(file.getBytes(), file.getContentType());
//        multipart.addBodyPart(attachmentPart);

        // Set content to email
        email.setContent(multipart);

        // Convert to Gmail Message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessage = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessage);

        Message message = new Message();
        message.setRaw(encodedEmail);

        service.users().messages().send("me", message).execute();
    }
}