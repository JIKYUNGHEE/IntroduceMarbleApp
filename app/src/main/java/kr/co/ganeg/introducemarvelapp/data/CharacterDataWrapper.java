package kr.co.ganeg.introducemarvelapp.data;

public class CharacterDataWrapper {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private CharacterDataContainer data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public CharacterDataContainer getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CharacterDataWrapper{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", data=" + data +
                '}';
    }
}
