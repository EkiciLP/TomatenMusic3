package net.tomatentum.tomatenmusic3;

import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.github.cdimascio.dotenv.Dotenv;
import net.tomatentum.marinara.Marinara;
import net.tomatentum.marinara.wrapper.javacord.JavacordWrapper;
import net.tomatentum.tomatenmusic3.command.PingCommand;

public class App {

    public static void main(String[] args) {
        new App().connect();  
    }

    private Config config;
    private DiscordApi client;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Terminal terminal;

    private Marinara marinara;

    private App() {
        Dotenv env = Dotenv.configure().ignoreIfMissing().load();
        this.config = new Config(env);

        LoggerContext loggerctx = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (config.isDevelopment())
            loggerctx.getLogger("root").setLevel(Level.DEBUG);
        initJline();
    }

    public void connect() {
        client = new DiscordApiBuilder()
            .setToken(config.token())
            .addIntents(Intent.GUILD_VOICE_STATES)
            .login().join();
        initMarinara();
        logger.info("connected as {}", client.getYourself().getName());
    }

    private void initJline() {
        try {
            this.terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JlineAppender.Terminal = this.terminal;
    }

    private void initMarinara() {
        this.marinara = Marinara.load(new JavacordWrapper(client));

        marinara.getRegistry().addInteractions(new PingCommand());
        
        marinara.getRegistry().registerCommands();
    }
}
