package org.adswizz.podcast.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class DownloadIdentifier {

    protected Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        properties.put(key, value);
        System.out.println("Found fields in download identifier that are not defined in pojo: " + properties);
    }

    @JsonIgnore
    public String client;
    @JsonIgnore
    public Double publisher;
    @JsonIgnore
    public String podcastId;
    @JsonIgnore
    public String showId;
    @JsonIgnore
    public String episodeId;
    @JsonIgnore
    public String downloadId;

    @JsonIgnore
    public String getClient() {
        return client;
    }
    @JsonProperty("client")
    public void setClient(String client) {
        this.client = client;
    }

    @JsonIgnore
    public Double getPublisher() {
        return publisher;
    }
    @JsonProperty("publisher")
    public void setPublisher(Double publisher) {
        this.publisher = publisher;
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
    public String getShowId() {
        return showId;
    }
    @JsonProperty("showId")
    public void setShowId(String showId) {
        this.showId = showId;
    }

    @JsonIgnore
    public String getEpisodeId() {
        return episodeId;
    }
    @JsonProperty("episodeId")
    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    @JsonIgnore
    public String getDownloadId() {
        return downloadId;
    }
    @JsonProperty("downloadId")
    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }
}
