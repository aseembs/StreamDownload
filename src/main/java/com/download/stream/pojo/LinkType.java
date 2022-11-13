package com.download.stream.pojo;

public enum LinkType {
    STREAM ("stream"),
    DOWNLOAD ("download");

    private final String text;

    LinkType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
