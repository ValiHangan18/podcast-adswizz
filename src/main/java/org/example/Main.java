package org.example;

import org.adswizz.podcast.pojo.Opportunity;
import org.adswizz.podcast.PodcastRow;
import org.adswizz.podcast.utils.DateUtils;
import org.adswizz.podcast.utils.Utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<PodcastRow> podcasts = Utils.getPodcastRowsFromTestFile();
        System.out.println("################################################################################################");

        String expectedShowId = "Who Trolled Amber";
        long expectedDownloads = 24L;
        Map<String, Long> entriesMap = podcasts.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("San Francisco"))
                .collect(Collectors.groupingBy(p -> p.getDownloadIdentifier().getShowId(), Collectors.counting()));

        Map.Entry<String, Long> maxEntry = Utils.getMaxEntryFromMap(entriesMap);

        assert maxEntry != null : "Max entry should not be null";

        System.out.println("Most popular show is: " + maxEntry.getKey());
        System.out.println("Number of downloads is: " + maxEntry.getValue());

        assert maxEntry.getKey().equals(expectedShowId) : "Most popular show should be '" + expectedShowId + "'";
        assert maxEntry.getValue().equals(expectedDownloads) : "Number of downloads should be " + expectedDownloads;

        System.out.println("################################################################################################");

        String expectedDevice = "mobiles & tablets";
        expectedDownloads = 60L;

        entriesMap = podcasts.stream()
                .collect(Collectors.groupingBy(
                        PodcastRow::getDeviceType, Collectors.counting()));
        maxEntry = Utils.getMaxEntryFromMap(entriesMap);

        assert maxEntry != null : "Max entry should not be null";

        System.out.println("Most popular device is: " + maxEntry.getKey());
        System.out.println("Number of downloads is: " + maxEntry.getValue());

        assert maxEntry.getKey().equals(expectedDevice) : "Most popular device should be '" + expectedDevice + "'";
        assert maxEntry.getValue().equals(expectedDownloads) : "Number of downloads should be " + expectedDownloads;

        System.out.println("################################################################################################");

        HashMap<String, Integer> expectedShowIdOpportunityMap = new HashMap<>();
        expectedShowIdOpportunityMap.put("Stuff You Should Know", 40);
        expectedShowIdOpportunityMap.put("Who Trolled Amber", 40);
        expectedShowIdOpportunityMap.put("Crime Junkie", 30);
        expectedShowIdOpportunityMap.put("The Joe Rogan Experience", 10);

        HashMap<String, Integer> secondEntriesMap = new HashMap<>();

        for (PodcastRow podcastRow : podcasts) {
            for (Opportunity opportunity : podcastRow.getOpportunities()) {
                for (String index2 : opportunity.getPositionUrlSegments().getAw_0_ais_adBreakIndex()) {
                    if (index2.equals("preroll")) {
                        secondEntriesMap.put(podcastRow.getDownloadIdentifier().getShowId(), secondEntriesMap.getOrDefault(podcastRow.getDownloadIdentifier().getShowId(), 0) + 1);
                    }
                }
            }
        }

        secondEntriesMap.entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                })
                .forEach(entry -> System.out.println("Show id: " + entry.getKey() + ", Preroll Opportunity Number: " + entry.getValue()));

        assert secondEntriesMap.equals(expectedShowIdOpportunityMap) : "Opportunity count should be \n" + expectedShowIdOpportunityMap + " but found \n" + entriesMap;

        System.out.println("################################################################################################");

        Integer weekInMilliSeconds = 604800000;
        Map<String, Set<Long>> mapEpoque = new HashMap<>();

        for (PodcastRow podcastRow : podcasts) {
            for (Opportunity opportunity : podcastRow.getOpportunities()) {
                mapEpoque.computeIfAbsent(podcastRow.getDownloadIdentifier().getShowId(), k -> new HashSet<>()).add(opportunity.getOriginalEventTime());
            }
        }
        System.out.println("Weekly shows are:");
        for (Map.Entry<String, Set<Long>> entry : mapEpoque.entrySet()) {
            Integer valid = 0;
            for (Long value1 : entry.getValue()) {
                for (Long value2 : entry.getValue()) {
                    if (value1.equals(value2 + weekInMilliSeconds)) {
                        valid++;
                        if (valid.equals(entry.getValue().size() - 1)) {
                            System.out.println(entry.getKey() + " - " + DateUtils.formatLocalDateTimeInUTC(value1));
                        }
                    }
                }
            }
        }
        System.out.println("################################################################################################");
    }
}