package org.example;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HtmlTagCounter {

    public static Map<String, Integer> countTags(String urlString) throws Exception {
        URL url = new URL(urlString);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder html = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            html.append(line);
        }

        reader.close();

        Map<String, Integer> result = new HashMap<>();

        Pattern pattern = Pattern.compile("<\\s*(\\w+)");
        Matcher matcher = pattern.matcher(html.toString());

        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            result.put(tag, result.getOrDefault(tag, 0) + 1);
        }

        return result;
    }
}
