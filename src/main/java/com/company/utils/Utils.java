package com.company.utils;

import java.util.Scanner;

public class Utils {
    private static final Scanner scannerNum = new Scanner(System.in);
    private static final Scanner scannerStr = new Scanner(System.in);


    public static String currentUserId;

    public static String getStr(String text) {
        System.out.print(text + ": ");
        return scannerStr.nextLine();
    }

    public static int getNum(String text) {
        System.out.print(text + ": ");
        return scannerNum.nextInt();
    }
}
