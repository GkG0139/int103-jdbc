package com.jdbc;

import java.util.Random;

public class Utils {

  static String generateStudentId() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      int digit = random.nextInt(10);
      sb.append(digit);
    }
    return sb.toString();
  }

  static String randomName() {
    String vowels = "aeiou";
    String consonants = "bcdfghjklmnpqrstvwxyz";
    Random random = new Random();

    StringBuilder builder = new StringBuilder();
    int length = random.nextInt(4) + 4; // 4-7 letters
    for (int i = 0; i < length; i++) {
      if (i % 2 == 0) {
        builder.append(consonants.charAt(random.nextInt(consonants.length())));
      } else {
        builder.append(vowels.charAt(random.nextInt(vowels.length())));
      }
    }
    return (
      builder.toString().substring(0, 1).toUpperCase() +
      builder.toString().substring(1)
    );
  }

  public static String generateRandomEmail() {
    String[] domains = {
      "gmail.com",
      "yahoo.com",
      "hotmail.com",
      "aol.com",
      "outlook.com",
    };
    String randomDomain = domains[(int) (Math.random() * domains.length)];
    String email = "";
    for (int i = 0; i < 10; i++) {
      char c = (char) ((int) (Math.random() * 26) + 97);
      email += c;
    }
    email += "@";
    email += randomDomain;
    return email;
  }
}
