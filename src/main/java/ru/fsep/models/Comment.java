package ru.fsep.models;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
public class Comment {
    private Long id;
    private String text;
    private Long videoId;
    private String highlightText;

    public static class Builder {
        private Long id;
        private String text;
        private Long videoId;
        private String highlightText;


        public Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder videoId(Long videoId) {
            this.videoId = videoId;
            return this;
        }

        public Builder highlightText(String highlightText) {
            this.highlightText = highlightText;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    private Comment(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.videoId = builder.videoId;
        this.highlightText = builder.highlightText;
    }

    public Comment() {
    }

    public Comment(Long id, String text, Long videoId, String highlightText) {
        this.id = id;
        this.text = text;
        this.videoId = videoId;
        this.highlightText = highlightText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getHighlightText() {
        return highlightText;
    }

    public void setHighlightText(String highlightText) {
        this.highlightText = highlightText;
    }
}
