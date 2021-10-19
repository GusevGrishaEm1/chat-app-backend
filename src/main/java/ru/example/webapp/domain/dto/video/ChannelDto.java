package ru.example.webapp.domain.dto.video;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChannelDto {
    private String urlChannel;
    private List<String> urlVideoList;

    public ChannelDto() {
        urlChannel = "";
        urlVideoList = new ArrayList<>();
    }

    public void addUrlVideo(String url) {
        urlVideoList.add(url);
    }
}
