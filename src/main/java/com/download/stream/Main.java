package com.download.stream;

import com.download.stream.pojo.VideoLink;
import com.download.stream.utils.CommandLineUtils;
import com.download.stream.utils.JsonUtils;
import com.download.stream.utils.PCloudUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<VideoLink> videoLinks = JsonUtils.extractJsonData();
        CommandLineUtils.executeCommand("mkdir -p /tmp");
        videoLinks.forEach(i -> {
            final String filePath = String.format("/tmp/[%d] %s %s.mp4", i.getNumber(), i.getTitle(), i.getDate());
            ProcessBuilder pb = CommandLineUtils.getYoutubeDlCommand(i.getLink(), filePath, System.getenv("COOKIES"));
            try {
                CommandLineUtils.executeCommand(pb);
                //PCloudUtils.uploadFile(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
