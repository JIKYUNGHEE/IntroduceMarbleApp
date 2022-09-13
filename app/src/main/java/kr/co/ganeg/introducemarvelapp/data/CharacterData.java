package kr.co.ganeg.introducemarvelapp.data;

import java.util.Arrays;

public class CharacterData {

    private long id;
    private String name;
    private String description;
    private Image thumbnail;
    private SectionList comics;
    private SectionList series;
    private SectionList stories;
    private SectionList events;
    private Url[] urls;

    public String getThumbnail() {
        return Image.getUrl(thumbnail.getPath(), Image.SIZE_STANDARD_LARGE, thumbnail.getExtension());
    }

    public String getImage() {
        return Image.getUrl(thumbnail.getPath(), Image.SIZE_STANDARD_INCREDIBLE, thumbnail.getExtension());
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public SectionList getComics() {
        return comics;
    }

    public SectionList getSeries() {
        return series;
    }

    public SectionList getStories() {
        return stories;
    }

    public SectionList getEvents() {
        return events;
    }

    public Url[] getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return "CharacterData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                ", comics=" + comics +
                ", series=" + series +
                ", stories=" + stories +
                ", events=" + events +
                ", urls=" + Arrays.toString(urls) +
                '}';
    }
}
