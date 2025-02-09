package net.tomatentum.tomatenmusic3.lavalink;

import java.util.Collections;
import java.util.Set;

import org.javacord.api.DiscordApi;

import dev.arbjerg.lavalink.client.Helpers;
import dev.arbjerg.lavalink.client.LavalinkClient;
import dev.arbjerg.lavalink.client.NodeOptions;
import net.tomatentum.tomatenmusic3.Config;

public class LavalinkWrapper {

    private Config config;
    private DiscordApi client;

    private LavalinkClient lavaClient;
    
    public LavalinkWrapper(Config config, DiscordApi client) {
        this.config = config;
        this.client = client;

        this.lavaClient = new LavalinkClient(Helpers.getUserIdFromToken(config.token()));
        getNodes().forEach((x) -> lavaClient.addNode(x));
    }

    protected Set<NodeOptions> getNodes() {
        //TODO
        return Collections.emptySet();
    }
}
