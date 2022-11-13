package com.download.stream.utils;

import com.download.stream.pojo.VideoLink;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final String FILE_PATH = "links.json";

    public static List<VideoLink> extractJsonData() throws FileNotFoundException {
        final Type REVIEW_TYPE = new TypeToken<List<VideoLink>>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(FILE_PATH));
        List<VideoLink> data = gson.fromJson(reader, REVIEW_TYPE);
        return data;

    }
}
