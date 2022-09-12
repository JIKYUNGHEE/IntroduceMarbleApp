package kr.co.ganeg.introducemarvelapp.api.data;

public class CharacterDataWrapper {
    private String attributionText;
    private CharacterDataContainer data;

    public String getAttributionText() {
        return attributionText;
    }

    public CharacterDataContainer getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CharacterDataWrapper{" +
                "attributionText='" + attributionText + '\'' +
                ", data=" + data +
                '}';
    }
}
