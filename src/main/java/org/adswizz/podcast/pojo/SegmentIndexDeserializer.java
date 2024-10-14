package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

class SegmentIndexDeserializer extends JsonDeserializer<Segments> {
    @Override
    public Segments deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Segments segments = new Segments();
        if (node.has("aw_0_ais.nextEventMs")) {
            segments.setAw_0_ais_nextEventMs(new String[]{node.get("aw_0_ais.nextEventMs").asText()});
        }
        if (node.has("aw_0_ais_adBreakIndex")) {
            segments.setAw_0_ais_adBreakIndex(new String[]{node.get("aw_0_ais.adBreakIndex").asText()});
        }
        return segments;
    }
}