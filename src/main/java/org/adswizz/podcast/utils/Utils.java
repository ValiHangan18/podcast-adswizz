package org.adswizz.podcast.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adswizz.podcast.PodcastRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Utils {

    public static ArrayList<PodcastRow> getPodcastRowsFromFile(String filename) {

        ArrayList<PodcastRow> podcasts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(line);

                PodcastRow podcastRow = objectMapper.treeToValue(jsonNode, PodcastRow.class);

                podcasts.add(podcastRow);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return podcasts;
    }

    public static ArrayList<PodcastRow> getPodcastRowsFromTestFile() {
        return getPodcastRowsFromFile("src/test/resources/downloads.txt");
    }

    public static Map.Entry<String, Long> getMaxEntryFromMap(Map<String, Long> podcastCountMap){
        return podcastCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }
}