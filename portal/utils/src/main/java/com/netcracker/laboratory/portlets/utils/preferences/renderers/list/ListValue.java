package com.netcracker.laboratory.portlets.utils.preferences.renderers.list;

public class ListValue {
    private String name;
    private String value;

    public ListValue() {
    }

    public ListValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
