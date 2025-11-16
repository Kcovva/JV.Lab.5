package org.example;

import java.io.Serializable;
import java.util.Map;

public class TagStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> tagCounts;

    public TagStats(Map<String, Integer> tagCounts) {
        this.tagCounts = tagCounts;
    }

    public Map<String, Integer> getTagCounts() {
        return tagCounts;
    }
}

