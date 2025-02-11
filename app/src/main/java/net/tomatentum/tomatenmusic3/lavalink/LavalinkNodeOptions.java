package net.tomatentum.tomatenmusic3.lavalink;

import dev.arbjerg.lavalink.client.NodeOptions;
import dev.arbjerg.lavalink.client.loadbalancing.IRegionFilter;

public record LavalinkNodeOptions(String name, String host, int port, String password, IRegionFilter regionGroup) {
    
    public NodeOptions toNodeOptions() {
        return new NodeOptions.Builder()
            .setName(name)
            .setServerUri("ws://{}:{}".formatted(host, port))
            .setPassword(password)
            .setRegionFilter(regionGroup)
            .build();
    }
}
