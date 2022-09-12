package kr.co.ganeg.introducemarvelapp.api.data;

import java.util.Arrays;

public class CharacterDataContainer {
    private int offset;
    private int total;
    private CharacterData[] results;

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public CharacterData[] getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "CharacterDataContainer{" +
                "offset=" + offset +
                ", total=" + total +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
