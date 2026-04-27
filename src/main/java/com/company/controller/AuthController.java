package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.service.AuthService;

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
//            mainController.menu();
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

    private boolean verification(String email) {

        return true;
    }

}
