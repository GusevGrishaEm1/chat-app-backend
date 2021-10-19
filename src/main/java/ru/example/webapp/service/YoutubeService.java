package ru.example.webapp.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import org.springframework.stereotype.Service;
import ru.example.webapp.domain.dto.video.ChannelDto;
import ru.example.webapp.domain.dto.video.RandomCommentDto;
import ru.example.webapp.domain.dto.video.VideoDto;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
public class YoutubeService {

    public static final String apiKey = "AIzaSyAhBZ8FHG2P5jE3XvQaoR-VGzEMXZ7RdLg";

    public VideoDto findVideo(String channelName, String videoName) throws IOException {
        YouTube youtube = getYouTube();
        List<String> listTemp = new ArrayList<>();
        Collections.addAll(listTemp, "id");
        YouTube.Search.List search = youtube.search().list(listTemp);
        search.setKey(apiKey);
        search.setQ(videoName);
        listTemp = new ArrayList<>();
        listTemp.add("video");
        search.setType(listTemp);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        BigInteger likes = BigInteger.valueOf(0);
        BigInteger viewers = BigInteger.valueOf(0);
        String url = "https://www.youtube.com/watch?v=";
        for(SearchResult searchResult : searchResultList) {
            listTemp = new ArrayList<>();
            Collections.addAll(listTemp, "statistics", "snippet");
            YouTube.Videos.List videos = youtube.videos().list(listTemp);
            listTemp = new ArrayList<>();
            listTemp.add(searchResult.getId().getVideoId());
            videos.setId(listTemp);
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

    public ChannelDto findChannel(String channelName) throws IOException {
        ChannelDto channel = new ChannelDto();
        YouTube youtube = getYouTube();
        List<String> listTemp = new ArrayList<>();
        Collections.addAll(listTemp, "id");
        YouTube.Search.List search = youtube.search().list(listTemp);
        search.setQ(channelName);
        search.setKey(apiKey);
        listTemp = new ArrayList<>();
        listTemp.add("channel");
        search.setType(listTemp);
        SearchListResponse searchResponse = search.execute();
        Optional<SearchResult> searchResult = searchResponse.getItems().stream().findFirst();
        channel.setUrlChannel("https://www.youtube.com/channel/"+searchResult.get().getId().getChannelId());
        listTemp = new ArrayList<>();
        Collections.addAll(listTemp, "id","snippet");
        search = youtube.search().list(listTemp);
        search.setChannelId(searchResult.get().getId().getChannelId());
        search.setKey(apiKey);
        search.setMaxResults(5L);
        search.setOrder("date");
        listTemp = new ArrayList<>();
        listTemp.add("video");
        search.setType(listTemp);
        searchResponse = search.execute();
        List<SearchResult> searchResults = searchResponse.getItems();
        for (SearchResult result : searchResults) {
            channel.addUrlVideo("https://www.youtube.com/watch?v=" + result.getId().getVideoId());
        }
        return  channel;
    }

    public RandomCommentDto findRandomComment(String channelName, String videoName) throws IOException {
        YouTube youtube = getYouTube();
        List<String> listTemp = new ArrayList<>();
        Collections.addAll(listTemp, "id");
        YouTube.Search.List search = youtube.search().list(listTemp);
        search.setKey(apiKey);
        search.setQ(videoName);
        listTemp = new ArrayList<>();
        listTemp.add("video");
        search.setType(listTemp);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        Video videoTemp = new Video();
        for(SearchResult searchResult : searchResultList) {
            listTemp = new ArrayList<>();
            Collections.addAll(listTemp, "id", "snippet");
            YouTube.Videos.List videos = youtube.videos().list(listTemp);
            listTemp = new ArrayList<>();
            listTemp.add(searchResult.getId().getVideoId());
            videos.setId(listTemp);
            videos.setKey(apiKey);
            VideoListResponse videoListResponse = videos.execute();
            List<Video> findedVideo = videoListResponse.getItems();
            if(findedVideo.get(0).getSnippet().getChannelTitle().equals(channelName)) {
                videoTemp = findedVideo.get(0);
                break;
            }
        }
        listTemp = new ArrayList<>();
        Collections.addAll(listTemp, "snippet", "replies");
        YouTube.CommentThreads.List commentsThreads = youtube.commentThreads().list(listTemp);
        commentsThreads.setKey(apiKey);
        commentsThreads.setVideoId(videoTemp.getId());
        CommentThreadListResponse commentThreadListResponse = commentsThreads.execute();
        List<CommentThread> commentThreads = commentThreadListResponse.getItems();
        RandomCommentDto comment = new RandomCommentDto();
        comment.setText(commentThreads.get(new Random().nextInt(commentThreads.size())).getSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        comment.setLogin(commentThreads.get(new Random().nextInt(commentThreads.size())).getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
        return  comment;
    }
    private YouTube getYouTube() {
        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                request -> {
                }).setApplicationName("youtube-spring-boot-demo").build();
    }

}
