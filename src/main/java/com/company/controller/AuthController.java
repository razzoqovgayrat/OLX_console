package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.service.AuthService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalTime;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static com.company.utils.Utils.*;

public class AuthController {

    private final AuthService authService = AuthService.getInstance();
    private final MainController mainController = new MainController();

    public void start() {
        while (true) {
            System.out.println("""
                    1. Login
                    2. Register
                    0. Exit""");
            switch (getNum("Choose one")) {
                case 1 -> login();
                case 2 -> registration();
                case 0 -> {return;}
                default -> System.out.println("wrong input");
            }
        }
    }

    private void login() {
        String phoneNumber = getStr("Enter phone");
        String password = getStr("Enter password");

        boolean res = authService.login(new AuthDTO("", phoneNumber, password, ""));
        if (res) {
            mainController.menu();
        } else {
            System.out.println("wrong");
        }
    }

    private void registration() {
        String fullName = getStr("Enter FullName");
        String phoneNumber = getStr("Enter phone number");
        String password = getStr("Enter password");
        String email = getStr("Enter email");

        boolean verification = verification(email);
        if (verification) {
            boolean registration = authService.registration(new AuthDTO(fullName, phoneNumber, password, email));
            if (registration) {
                System.out.println("success");
            } else {
                System.out.println("User already exist");
            }
        } else System.out.println("wrong code");
    }

    public boolean verification(String toEmail) {
        Properties properties = getProperties();

        String fromEmail = "d2ab07b27ce9b0";
        String password = "9ab46b05932a39";

        Session session = getSession(properties, fromEmail, password);
        int code = new Random().nextInt(100000, 999999);
        Message message = getMessage(session, fromEmail, toEmail, code);

        LocalTime end = LocalTime.now().plusMinutes(2);
        CompletableFuture.runAsync(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException ignored) {}
        }, Executors.newSingleThreadExecutor());
        System.out.println("message send");

        int enterTheCode = getNum("Enter the code");
        if (LocalTime.now().isBefore(end)) {
            if (enterTheCode == code) {
                return true;
            }
            System.out.println("wrong password");
            return false;
        }
        System.out.println("Time expired");
        return false;
    }

    private static Message getMessage(Session session, String fromEmail, String toEmail, int code) {
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // email
            message.setSubject("this is a confirmation code");
            message.setContent("<h1 style=\"color:red;\">Code: " + code + "</h1>", "text/html");
        } catch (MessagingException ignored) {}

        return message;
    }

    private static Session getSession(Properties properties, String username, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.STARTTLS.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

}
