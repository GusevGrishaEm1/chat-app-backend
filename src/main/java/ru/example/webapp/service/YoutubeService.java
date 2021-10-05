package ru.example.webapp.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.stereotype.Service;
import ru.example.webapp.domain.dto.video.VideoDto;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class YoutubeService {

    private static final String apiKey = "AIzaSyAhBZ8FHG2P5jE3XvQaoR-VGzEMXZ7RdLg";

    public VideoDto findVideo(String channelName, String videoName) throws IOException {
        YouTube youtube = getYouTube();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        Collections.addAll(list1, "id");
        YouTube.Search.List search = youtube.search().list(list1);
        search.setKey(apiKey);
        search.setQ(videoName);
        list4.add("video");
        search.setType(list4);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        BigInteger likes = BigInteger.valueOf(0);
        BigInteger viewers = BigInteger.valueOf(0);
        String url = "https://www.youtube.com/watch?v=";
        for(SearchResult searchResult : searchResultList) {
            Collections.addAll(list2, "statistics", "snippet");
            YouTube.Videos.List videos = youtube.videos().list(list2);
            list3.add(searchResult.getId().getVideoId());
            videos.setId(list3);
            videos.setKey(apiKey);
            VideoListResponse videoListResponse = videos.execute();
            List<Video> findedVideo = videoListResponse.getItems();
            if(findedVideo.get(0).getSnippet().getChannelTitle().equals(channelName)) {
                url += findedVideo.get(0).getId();
                likes= findedVideo.get(0).getStatistics().getLikeCount();
                viewers = findedVideo.get(0).getStatistics().getViewCount();
                break;
            }
        }
        VideoDto video = new VideoDto();
        video.setUrl(url);
        video.setLikes(likes);
        video.setViewers(viewers);
        return video;
    }

    private YouTube getYouTube() {
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }}).setApplicationName("youtube-spring-boot-demo").build();
        return youtube;
    }

}
