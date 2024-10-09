package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;
import java.util.Map;

public class Segments {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in segments that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public String[] aw_0_ais_adBreakIndex;
    @JsonIgnore
    public String[] aw_0_ais_nextEventMs;

    @JsonIgnore
    public String[] getAw_0_ais_adBreakIndex() {
        return aw_0_ais_adBreakIndex;
    }
    //@JsonDeserialize(using = SegmentIndexDeserializer.class)
    @JsonProperty("aw_0_ais_adBreakIndex")
    public void setAw_0_ais_adBreakIndex(String[] aw_0_ais_adBreakIndex) {
        this.aw_0_ais_adBreakIndex = aw_0_ais_adBreakIndex;
    }

    @JsonIgnore
    public String[] getAw_0_ais_nextEventMs() {
        return aw_0_ais_nextEventMs;
    }
    //@JsonDeserialize(using = SegmentIndexDeserializer.class)
    @JsonProperty("aw_0_ais_nextEventMs")
    public void setAw_0_ais_nextEventMs(String[] aw_0_ais_nextEventMs) {
        this.aw_0_ais_nextEventMs = aw_0_ais_nextEventMs;
    }
}
