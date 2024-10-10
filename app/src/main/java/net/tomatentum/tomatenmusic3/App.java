package net.tomatentum.tomatenmusic3;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import io.github.cdimascio.dotenv.Dotenv;

public class App {

    public static void main(String[] args) {  
        new App().connect();  
    }

    private static String LOGGERPATTERN = "%-22d{dd MMM yyyy HH:mm:ss} (%t) [%c{3}] %p: %m%n";

    private Config config;
    private DiscordApi client;
    private Logger logger;

    private App() {
        Dotenv env = Dotenv.configure().ignoreIfMissing().load();
        this.config = new Config(env);
        initLogger();
    }

    private void initLogger() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        AppenderComponentBuilder cbuilder = 
                builder.newAppender("Console", "Console")
                .add(builder
                    .newLayout("PatternLayout")
                    .addAttribute("pattern", LOGGERPATTERN)
            );
        RootLoggerComponentBuilder rlbuilder = 
            builder.newRootLogger(config.isDevelopment() ? Level.DEBUG : Level.INFO)
            .add(builder.newAppenderRef("Console"));

        BuiltConfiguration logconf = builder.add(cbuilder).add(rlbuilder).build();

        Configurator.reconfigure(logconf);
        logger = LogManager.getLogger(this);
    }

    public void connect() {
        client = new DiscordApiBuilder()
            .setToken(config.token())
            .addIntents(Intent.GUILD_VOICE_STATES)
            .login().join();
        logger.log(Level.INFO, "connected as {}", client.getYourself().getName());
    }



}
