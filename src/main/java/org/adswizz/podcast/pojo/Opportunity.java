package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Opportunity {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in opportunity that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public Long originalEventTime;
    @JsonIgnore
    public Double maxDuration;
    @JsonIgnore
    public String podcastId;
    @JsonIgnore
    public Zones zones;
    @JsonIgnore
    public Segments positionUrlSegments;
    @JsonIgnore
    public Double insertionRate;


    @JsonIgnore
    public Long getOriginalEventTime() {
        return originalEventTime;
    }
    @JsonProperty("originalEventTime")
    public void setOriginalEventTime(Long originalEventTime) {
        this.originalEventTime = originalEventTime;
    }

    @JsonIgnore
    public Double getMaxDuration() {
        return maxDuration;
    }
    @JsonProperty("maxDuration")
    public void setMaxDuration(Double maxDuration) {
        this.maxDuration = maxDuration;
    }

    @JsonIgnore
    public String getPodcastId() {
        return podcastId;
    }
    @JsonProperty("podcastId")
    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    @JsonIgnore
    public Zones getZones() {
        return zones;
    }
    @JsonProperty("zones")
    public void setZones(Zones zones) {
        this.zones = zones;
    }

    @JsonIgnore
    public Segments getPositionUrlSegments() {
        return positionUrlSegments;
    }
    @JsonProperty("positionUrlSegments")
    public void setPositionUrlSegments(Segments positionUrlSegments) {
        this.positionUrlSegments = positionUrlSegments;
    }

    @JsonIgnore
    public Double getInsertionRate() {
        return insertionRate;
    }
    @JsonProperty("insertionRate")
    public void setInsertionRate(Double insertionRate) {
        this.insertionRate = insertionRate;
    }
}
