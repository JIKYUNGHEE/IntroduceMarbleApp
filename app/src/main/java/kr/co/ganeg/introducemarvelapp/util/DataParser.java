package kr.co.ganeg.introducemarvelapp.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.ganeg.introducemarvelapp.api.MarvelResult;
import kr.co.ganeg.introducemarvelapp.data.CharacterData;
import kr.co.ganeg.introducemarvelapp.data.CharacterDataContainer;
import kr.co.ganeg.introducemarvelapp.data.CharacterDataWrapper;
import kr.co.ganeg.introducemarvelapp.data.SectionData;
import kr.co.ganeg.introducemarvelapp.data.SectionDataContainer;
import kr.co.ganeg.introducemarvelapp.data.SectionDataWrapper;
import kr.co.ganeg.introducemarvelapp.data.SectionSummary;
import kr.co.ganeg.introducemarvelapp.data.Url;
import kr.co.ganeg.introducemarvelapp.model.CharacterModel;
import kr.co.ganeg.introducemarvelapp.model.SectionModel;

public class DataParser {

    public static MarvelResult<CharacterModel> parse(CharacterDataWrapper dataWrapper) {
        MarvelResult<CharacterModel> result = new MarvelResult<>();
        CharacterDataContainer dataContainer = dataWrapper.getData();
        if (dataContainer != null) {
            result.setOffset(dataContainer.getOffset());
            result.setTotal(dataContainer.getTotal());

            CharacterData[] results = dataContainer.getResults();
            if (results != null) {
                List<CharacterModel> characterList = new ArrayList<>(results.length);
                for (CharacterData characterData : results) {
                    CharacterModel character = new CharacterModel();
                    character.setId(characterData.getId());
                    character.setName(characterData.getName());
                    character.setDescription(characterData.getDescription());
                    character.setThumbnail(characterData.getThumbnail());
                    character.setImage(characterData.getImage());
                    List<Url> urls = new ArrayList<>();
                    for (Url url : characterData.getUrls()) {
                        if (Url.TYPE_DETAIL.equals(url.getType())) {
                            character.setDetail(url.getUrl());
                        } else if (Url.TYPE_WIKI.equals(url.getType())) {
                            character.setWiki(url.getUrl());
                        } else if (Url.TYPE_COMICLINK.equals(url.getType())) {
                            character.setComicLink(url.getUrl());
                        }
                        urls.add(url);
                    }
                    character.setUrls(urls);
                    character.setComics(parseSection(characterData.getComics().getItems()));
                    character.setSeries(parseSection(characterData.getSeries().getItems()));
                    character.setStories(parseSection(characterData.getStories().getItems()));
                    character.setEvents(parseSection(characterData.getEvents().getItems()));
                    characterList.add(character);
                }
                result.setEntries(characterList);
            }
        }
        result.setAttribution(dataWrapper.getAttributionText());
        return result;
    }

    private static List<SectionModel> parseSection(SectionSummary[] items) {
        List<SectionModel> list = new ArrayList<>();
        for (SectionSummary summary : items) {
            SectionModel section = new SectionModel();
            section.setId(summary.getId());
            section.setTitle(summary.getName());
            list.add(section);
        }
        return list;
    }

    public static MarvelResult<SectionModel> parse(SectionDataWrapper dataWrapper) {
        MarvelResult<SectionModel> result = new MarvelResult<>();
        SectionDataContainer dataContainer = dataWrapper.getData();
        if (dataContainer != null) {
            result.setOffset(dataContainer.getOffset());
            result.setTotal(dataContainer.getTotal());
            SectionData[] results = dataContainer.getResults();
            if (results != null) {
                List<SectionModel> list = new ArrayList<>(results.length);
                for (SectionData sectionData : results) {
                    SectionModel SectionModel = new SectionModel();
                    SectionModel.setId(sectionData.id);
                    SectionModel.setTitle(sectionData.title);
                    SectionModel.setThumbnail(sectionData.getThumbnail());
                    SectionModel.setImage(sectionData.getImage());
                    list.add(SectionModel);
                }
                result.setEntries(list);
            }
        }
        result.setAttribution(dataWrapper.getAttributionText());
        return result;
    }
}
