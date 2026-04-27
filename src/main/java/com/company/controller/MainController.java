package com.company.controller;

import static com.company.utils.Utils.*;

public class MainController {
    public void mene() {
        while (true) {
            System.out.println("""
                    1. Create a new ad
                    2. My ads
                    3. Ads
                    4. Search
                    0. Logout
                    """);
            switch (getNum("Choose one")) {
                case 1 -> {  }
                case 2 -> {  }
                case 3 -> {  }
                case 4 -> {  }
                case 0 -> { return; }
                default -> System.out.println("wrong input");
            }
        }
    }
}
