package org.example;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String sourceFile;
    private final String maxWordsLine;
    private final int maxWordsCount;
    private final Set<String> searchSet;
    private final List<String> matchedLines;

    public Result(Path sourceFile, String maxWordsLine, int maxWordsCount,
                      Set<String> searchSet, List<String> matchedLines) {
        this.sourceFile = sourceFile.toString();
        this.maxWordsLine = maxWordsLine;
        this.maxWordsCount = maxWordsCount;
        this.searchSet = searchSet;
        this.matchedLines = matchedLines;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Source file: ").append(sourceFile).append(System.lineSeparator());
        sb.append("Max words in a line: ").append(maxWordsCount).append(System.lineSeparator());
        sb.append("Line with max words: ").append(System.lineSeparator());
        sb.append(maxWordsLine).append(System.lineSeparator()).append(System.lineSeparator());
        sb.append("Search set: ").append(searchSet).append(System.lineSeparator());
        sb.append("Matched lines (containing all search words):").append(System.lineSeparator());
        if (matchedLines.isEmpty()) {
            sb.append("[none]").append(System.lineSeparator());
        } else {
            for (String l : matchedLines) {
                sb.append(l).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}

