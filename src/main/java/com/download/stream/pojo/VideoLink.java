package com.download.stream.pojo;

import lombok.Data;

@Data
public class VideoLink {
    private int number;
    private String title;
    private String date;
    private LinkType linkType;
    private String link;
}
