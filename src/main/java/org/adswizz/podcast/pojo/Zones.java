package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Zones {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in zones that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public Zone zone1;

    @JsonIgnore
    public Zone getZone1() {
        return zone1;
    }
    @JsonProperty("zone1")
    public void setZone1(Zone zone1) {
        this.zone1 = zone1;
    }
}
