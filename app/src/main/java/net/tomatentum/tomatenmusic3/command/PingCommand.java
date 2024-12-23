package net.tomatentum.tomatenmusic3.command;

import java.time.Duration;
import java.time.Instant;

import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;

import net.tomatentum.marinara.interaction.InteractionHandler;
import net.tomatentum.marinara.interaction.commands.annotation.SlashCommand;

public class PingCommand implements InteractionHandler {
    
    @SlashCommand(
        name = "ping",
        description = "Tests bot's connection."
    )
    public void execPing(SlashCommandInteraction interaction) {
        Duration ping = Duration.between(interaction.getCreationTimestamp(), Instant.now());
        interaction.createImmediateResponder()
            .append("Pong! " + ping.toMillis() + "ms")
            .setFlags(MessageFlag.EPHEMERAL)
            .respond().join();

    }

}
