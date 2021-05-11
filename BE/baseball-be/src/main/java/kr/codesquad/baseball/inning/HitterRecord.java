package kr.codesquad.baseball.inning;

import java.util.List;

public class HitterRecord {
    private long id;
    private int battingOrder;
    private String name;
    List<String> results;
    private boolean isOut;

    public HitterRecord(long id, int battingOrder, String name, List<String> results, boolean isOut) {
        this.id = id;
        this.battingOrder = battingOrder;
        this.name = name;
        this.results = results;
        this.isOut = isOut;
    }

    public long getId() {
        return id;
    }

    public int getBattingOrder() {
        return battingOrder;
    }

    public String getName() {
        return name;
    }

    public List<String> getResults() {
        return results;
    }

    public boolean isOut() {
        return isOut;
    }
}
