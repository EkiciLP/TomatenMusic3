package net.tomatentum.tomatenmusic3.lavalink;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javacord.api.DiscordApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.arbjerg.lavalink.client.Helpers;
import dev.arbjerg.lavalink.client.LavalinkClient;
import net.tomatentum.tomatenmusic3.Config;

public class LavalinkWrapper {

    private static final String NODES_FILE_NAME = "lavanodes.json";
    private static final String NODES_INFO_FORMAT = "Node {} info:\n\tURI: {}\n\tRegion: {}";

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DiscordApi client;
    private JsonFactory jsonFactory;

    private LavalinkClient lavaClient;
    
    public LavalinkWrapper(Config config, DiscordApi client, JsonFactory jsonFactory) {
        this.client = client;
        this.jsonFactory = jsonFactory;

        this.lavaClient = new LavalinkClient(Helpers.getUserIdFromToken(config.token()));
        getNodes().forEach(node -> {
            lavaClient.addNode(node.toNodeOptions());
            logger.info("Registered node {}", node.name());
            logger.debug(NODES_INFO_FORMAT, node.name(), node.toNodeOptions().getServerUri(), node.regionGroup());
        });
    }

    protected Set<LavalinkNodeOptions> getNodes() {
        File nodesFile = new File(
            new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParent(),
            NODES_FILE_NAME);
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        IRegionFilterDeserializer.register(objectMapper);

        try {
            List<LavalinkNodeOptions> nodes = objectMapper.readValue(nodesFile, new TypeReference<List<LavalinkNodeOptions>>(){});
            return new HashSet<>(nodes);
        } catch (IOException e) {
            logger.error("lavanodes.json seems to not exist.", e);
            return Collections.emptySet();
        }
    }

    public LavalinkClient client() {
        return this.lavaClient;
    }

    public DiscordApi discordClient() {
        return this.client;
    }
}
