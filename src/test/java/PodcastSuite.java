import org.adswizz.podcast.pojo.Opportunity;
import org.adswizz.podcast.PodcastRow;
import org.adswizz.podcast.utils.DateUtils;
import org.adswizz.podcast.utils.Utils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class PodcastSuite {

    @Test
    public void testSanFranciscoMostViewedPodcast() {

        String expectedShowId = "Who Trolled Amber";
        long expectedDownloads = 24L;

        ArrayList<PodcastRow> podcasts = Utils.getPodcastRowsFromTestFile();

        Map<String, Long> entriesMap = podcasts.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("San Francisco"))
                .collect(Collectors.groupingBy(p -> p.getDownloadIdentifier().getShowId(), Collectors.counting()));

        Map.Entry<String, Long> maxEntry = Utils.getMaxEntryFromMap(entriesMap);

        assert maxEntry != null : "Max entry should not be null";

        System.out.println("Most popular show is: " + maxEntry.getKey());
        System.out.println("Number of downloads is: " + maxEntry.getValue());

        assert maxEntry.getKey().equals(expectedShowId) : "Most popular show should be '" + expectedShowId + "'";
        assert maxEntry.getValue().equals(expectedDownloads) : "Number of downloads should be " + expectedDownloads;
    }


    @Test(priority = 1)
    public void testMostUsedDevice() {

        String expectedDevice = "mobiles & tablets";
        long expectedDownloads = 60L;

        ArrayList<PodcastRow> podcasts = Utils.getPodcastRowsFromTestFile();

        Map<String, Long> entriesMap = podcasts.stream()
                .collect(Collectors.groupingBy(
                        PodcastRow::getDeviceType, Collectors.counting()));

        Map.Entry<String, Long> maxEntry = Utils.getMaxEntryFromMap(entriesMap);

        assert maxEntry != null : "Max entry should not be null";

        System.out.println("Most popular device is: " + maxEntry.getKey());
        System.out.println("Number of downloads is: " + maxEntry.getValue());

        assert maxEntry.getKey().equals(expectedDevice) : "Most popular device should be '" + expectedDevice + "'";
        assert maxEntry.getValue().equals(expectedDownloads) : "Number of downloads should be " + expectedDownloads;
    }

    @Test(priority = 2)
    public void testOpportunityPreroll() {

        HashMap<String, Integer> expectedShowIdOpportunityMap = new HashMap<>();
        expectedShowIdOpportunityMap.put("Stuff You Should Know", 40);
        expectedShowIdOpportunityMap.put("Who Trolled Amber", 40);
        expectedShowIdOpportunityMap.put("Crime Junkie", 30);
        expectedShowIdOpportunityMap.put("The Joe Rogan Experience", 10);

        ArrayList<PodcastRow> podcasts = Utils.getPodcastRowsFromTestFile();
        HashMap<String, Integer> entriesMap = new HashMap<>();

        for (PodcastRow podcastRow : podcasts) {
            for (Opportunity opportunity : podcastRow.getOpportunities()) {
                for (String index2 : opportunity.getPositionUrlSegments().getAw_0_ais_adBreakIndex()) {
                    if (index2.equals("preroll")) {
                        entriesMap.put(podcastRow.getDownloadIdentifier().getShowId(), entriesMap.getOrDefault(podcastRow.getDownloadIdentifier().getShowId(), 0) + 1);
                    }
                }
            }
        }

        entriesMap.entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                })
                .forEach(entry -> System.out.println("Show id: " + entry.getKey() + ", Preroll Opportunity Number: " + entry.getValue()));

        assert entriesMap.equals(expectedShowIdOpportunityMap) : "Opportunity count should be \n" + expectedShowIdOpportunityMap + " but found \n" + entriesMap;
    }


    @Test(priority = 3)
    public void testWeeklyShows() {

        Integer weekInMilliSeconds = 604800000;
        ArrayList<PodcastRow> podcasts = Utils.getPodcastRowsFromTestFile();
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
    }
}
