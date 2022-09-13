package kr.co.ganeg.introducemarvelapp.model;

import java.io.Serializable;
import java.util.List;

import kr.co.ganeg.introducemarvelapp.data.Url;

public class CharacterModel implements Serializable {

    private long mId;
    private String mName;
    private String mDescription;
    private String mThumbnail;
    private String mImage;
    private List<Url> mUrls;
    private List<SectionModel> mComics;
    private List<SectionModel> mSeries;
    private List<SectionModel> mStories;
    private List<SectionModel> mEvents;
    private String mDetail;
    private String mWiki;
    private String mComicLink;

    public CharacterModel() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public List<Url> getUrls() {
        return mUrls;
    }

    public void setUrls(List<Url> mUrls) {
        this.mUrls = mUrls;
    }

    public List<SectionModel> getComics() {
        return mComics;
    }

    public void setComics(List<SectionModel> comics) {
        mComics = comics;
    }

    public List<SectionModel> getSeries() {
        return mSeries;
    }

    public void setSeries(List<SectionModel> series) {
        mSeries = series;
    }

    public List<SectionModel> getStories() {
        return mStories;
    }

    public void setStories(List<SectionModel> stories) {
        mStories = stories;
    }

    public List<SectionModel> getEvents() {
        return mEvents;
    }

    public void setEvents(List<SectionModel> events) {
        mEvents = events;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getWiki() {
        return mWiki;
    }

    public void setWiki(String wiki) {
        mWiki = wiki;
    }

    public String getComicLink() {
        return mComicLink;
    }

    public void setComicLink(String comicLink) {
        mComicLink = comicLink;
    }

    @Override
    public String toString() {
        return "CharacterModel{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mThumbnail='" + mThumbnail + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mComics size=" + mComics.size() +
                ", mSeries size=" + mSeries.size() +
                ", mStories size=" + mStories.size() +
                ", mEvents size=" + mEvents.size() +
                ", mDetail='" + mDetail + '\'' +
                ", mWiki='" + mWiki + '\'' +
                ", mComicLink='" + mComicLink + '\'' +
                '}';
    }
}
