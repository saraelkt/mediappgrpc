package com.exemple.mediaclient.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String url;
    private long durationSeconds;
    private CreatorDto creator;

    @Override
    public String toString() {
        return "VideoDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", durationSeconds=" + durationSeconds +
                ", creator=" + (creator != null ? creator.getName() : null) +
                '}';
    }
}