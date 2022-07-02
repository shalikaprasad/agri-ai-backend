package kln.se.agri.ai.commons.model;

public class SQLQuery {

    private String name;
    private Long total;
    private boolean isBigger;
    private Double number;
    private int[] array;

    public SQLQuery() {
    }

    public SQLQuery(String name, int[] array) {
        this.name = name;
        this.array = array;
    }

    public SQLQuery(String name, Double number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean isBigger() {
        return isBigger;
    }

    public void setBigger(boolean bigger) {
        isBigger = bigger;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
