package com.sandstrom.wigellportal.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordChecker {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Din hashade version från den externa sidan
        String bcryptHash = "$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq";

        // Klartextlösenordet
        String plainPassword = "pass";

        // Kontrollera om klartextlösenordet matchar hashvärdet
        boolean isMatch = encoder.matches(plainPassword, bcryptHash);

        System.out.println("Matches: " + isMatch);
    }
}

