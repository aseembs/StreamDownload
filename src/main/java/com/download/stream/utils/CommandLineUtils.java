package com.download.stream.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandLineUtils {

    public static ProcessBuilder getFfmpegCommand(String link, String filePath) {
        return new ProcessBuilder("ffmpeg", "-protocol_whitelist",
                "file,http,https,tcp,tls,crypto", "-i", link, "-c", "copy", filePath);
    }

    public static ProcessBuilder getYoutubeDlCommand(String link, String filePath, String cookies) {
        ProcessBuilder pb = new ProcessBuilder("youtube-dl", link, "-f", "bestvideo+bestaudio/best",
                "-o", filePath);
        if(cookies != null)
            return pb.command("--add-header", cookies);
        return pb;
    }

    public static void executeCommand(String command) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec(command);
        displayStreams(proc.getErrorStream(), "Error");
        displayStreams(proc.getInputStream(), "Input");
        proc.waitFor();
    }

    public static void executeCommand(ProcessBuilder processBuilder) throws IOException, InterruptedException {
        Process proc = processBuilder.start();
        displayStreams(proc.getErrorStream(), "Error");
        displayStreams(proc.getInputStream(), "Input");
        proc.waitFor();
    }


    private static void displayStreams(InputStream inputStream, String label) throws IOException {
        String line;
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader rdr = new BufferedReader(isr);
        System.out.println("**********\n" + label +"\n**********");
        int i = 0;
        while((line = rdr.readLine()) != null) {
            System.out.print(++i + ": ");
            System.out.println(line);
        }
    }
}
