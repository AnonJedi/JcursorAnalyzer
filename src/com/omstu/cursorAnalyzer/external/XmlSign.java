package com.omstu.cursorAnalyzer.external;

public class XmlSign<T> {
    //private int _id;
    private String _description;
    private T _value = null;

    public XmlSign(String description, T value) {
        _description = description;
        _value = value;
    }

    public String getDescription() {
        return _description;
    }

    public String getValue() {
        return _value.toString();
    }
}
