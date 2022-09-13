package kr.co.ganeg.introducemarvelapp.data;

public class CharacterDataContainer {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private CharacterData[] results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public CharacterData[] getResults() {
        return results;
    }
}
