package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

class SegmentIndexDeserializer extends JsonDeserializer<Segments> {
    @Override
    public Segments deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Root root = jp.readValueAs(Root.class);
        Segments account = new Segments();
        if (root != null && root.user != null) {
            account.setAw_0_ais_nextEventMs(new String[]{root.user.id});
            if (root.user.username != null) {
                account.setAw_0_ais_adBreakIndex(new String[]{root.user.username.content});
            }
        }
        return account;
    }

    private static class Root {
        public User user;
        public String stat;
    }

    private static class User {
        public String id;
        public UserName username;
    }

    private static class UserName {
        public String content;
    }
}