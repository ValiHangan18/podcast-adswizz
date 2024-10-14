package org.adswizz.podcast;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.adswizz.podcast.pojo.DownloadIdentifier;
import org.adswizz.podcast.pojo.Opportunity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PodcastRow {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in podcast that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public String deviceType;
    @JsonIgnore
    public String country;
    @JsonIgnore
    public String listenerId;
    @JsonIgnore
    public Integer agency;
    @JsonIgnore
    public String city;
    @JsonIgnore
    public DownloadIdentifier downloadIdentifier;
    @JsonIgnore
    public ArrayList<Opportunity> opportunities;

    @JsonIgnore
    public String getDeviceType() {
        return deviceType;
    }
    @JsonProperty("deviceType")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @JsonIgnore
    public String getCountry() {
        return country;
    }
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonIgnore
    public String getListenerId() {
        return listenerId;
    }
    @JsonProperty("listenerId")
    public void setListenerId(String listenerId) {
        this.listenerId = listenerId;
    }

    @JsonIgnore
    public Integer getAgency() {
        return agency;
    }
    @JsonProperty("agency")
    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    @JsonIgnore
    public String getCity() {
        return city;
    }
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonIgnore
    public DownloadIdentifier getDownloadIdentifier() {
        return downloadIdentifier;
    }
    @JsonProperty("downloadIdentifier")
    public void setDownloadIdentifier(DownloadIdentifier downloadIdentifier) {
        this.downloadIdentifier = downloadIdentifier;
    }

    @JsonIgnore
    public ArrayList<Opportunity> getOpportunities() {
        return opportunities;
    }
    @JsonProperty("opportunities")
    public void setOpportunities(ArrayList<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
