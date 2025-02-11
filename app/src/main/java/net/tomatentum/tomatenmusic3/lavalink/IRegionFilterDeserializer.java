package net.tomatentum.tomatenmusic3.lavalink;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import dev.arbjerg.lavalink.client.loadbalancing.IRegionFilter;
import dev.arbjerg.lavalink.client.loadbalancing.RegionGroup;

public class IRegionFilterDeserializer extends StdDeserializer<IRegionFilter> {

    public static ObjectMapper register(ObjectMapper mapper) {
        SimpleModule module =
            new SimpleModule("IRegionFilterDeserializer", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(IRegionFilter.class, new IRegionFilterDeserializer());
        mapper.registerModule(module);
        return mapper;
    }

    public IRegionFilterDeserializer() {
        super(IRegionFilter.class);
    }
    
    @Override
    public IRegionFilter deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        String regionString = parser.getValueAsString();
        return RegionGroup.INSTANCE.valueOf(regionString);
    }
    
}
