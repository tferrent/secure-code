package com.biomerieux.level4;

// a collection of sensitive secrets necessary for the software to operate
public final class Constants {

    public static final String PRIVATE_KEY = System.getenv("PRIVATE_KEY");
    public static final String PUBLIC_KEY = System.getenv("PUBLIC_KEY");

    public static final String SECRET_KEY = "TjWnZr4u7x!A%D*G-KaPdSgVkXp2s5v8";

    public static final String PASSWORD_HASHER = "MD5_hasher";

    private Constants() {
    }
}
