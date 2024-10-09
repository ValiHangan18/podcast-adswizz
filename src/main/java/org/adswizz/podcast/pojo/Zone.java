package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Zone {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in zone that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public String id;
    @JsonIgnore
    public Integer maxAds;
    @JsonIgnore
    public Integer maxDuration;


    @JsonIgnore
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public Integer getMaxAds() {
        return maxAds;
    }
    @JsonProperty("maxAds")
    public void setMaxAds(Integer maxAds) {
        this.maxAds = maxAds;
    }

    @JsonIgnore
    public Integer getMaxDuration() {
        return maxDuration;
    }
    @JsonProperty("maxDuration")
    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }
}
