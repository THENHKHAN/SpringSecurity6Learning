package com.nhkhan.SpringbootSecurityLearning;

public class tesssssssssssst {
    public static void main(String[] args) {
//        String str = null; // null
//        String str = ""; // empty
//        String str = "null"; // non-empty
        String str = "  "; // blank - (spaces, tabs, newlines, etc.).
        if (str == null) {
            System.out.println("String is null");
        } else if (str.isEmpty()) {
            System.out.println("String is empty");
        } else if (str.isBlank()) {
            System.out.println("String is blank");
        } else {
            System.out.println("String is not empty and not blank");
        }
    }
}
