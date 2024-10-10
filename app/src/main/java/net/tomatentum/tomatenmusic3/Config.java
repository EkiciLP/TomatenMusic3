package net.tomatentum.tomatenmusic3;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private Dotenv env;

    public Config(Dotenv env) {
        this.env = env;
    }

    public String token() {
        return env.get("TOKEN");
    }

    public boolean isDevelopment() {
        return env.get("ENVIRONMENT").equals("Development");
    }
}
