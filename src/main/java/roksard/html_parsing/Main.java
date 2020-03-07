package roksard.html_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import roksard.html_parsing.NodeFinder.NodeCriteria;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static roksard.html_parsing.NodeFinder.NodeFinder.find;

public class Main {
    final static String fileName = "target\\classes\\html_parse\\words-to-extract.html";
    final static String outputFile = "target\\classes\\html_parse\\words-extracted.txt";
    final static String url = "https://www.goethe.de/prj/mwd/ru/glo.html";
    static boolean logging = false;

    static void log(String text) {
        if (!logging) {
            return;
        }
        System.out.println(text);
    }

    static String filter(String input) {
        return input.replaceAll("[;,\\n,\\r]+", "").trim();
    }

    public static void main(String[] args) throws Exception {
        File file = new File(fileName);
        Document doc = null;
        if (file.exists()) {
            doc = Jsoup.parse(file, "UTF-8");
            System.out.println("using file.");
        } else {
            doc = Jsoup.connect(url).get();
        }

        List<NodeCriteria> criteriaList = new ArrayList<NodeCriteria>() {{
            add(new NodeCriteria("row.*", "div"));
            add(new NodeCriteria("col.*", "div"));
            add(new NodeCriteria(".*", "p"));
        }};
        List<Element> found = find(criteriaList, doc);
        StringBuilder output = new StringBuilder();
        found.forEach(elem -> {
            Iterator<TextNode> textNodes = elem.textNodes().iterator();
            for (Element ch : elem.children()) {
                if (ch.tagName().equals("strong")) {
                    Pattern p = Pattern.compile("(.*)\\((.*)\\)");
                    Matcher matcher = p.matcher(filter(ch.text()));
                    if (matcher.find() && matcher.groupCount() == 2) {
                        output
                                .append(output.length() > 0 ? "\n" : "")
                                .append(matcher.group(1).trim())
                                .append(";")
                                .append(matcher.group(2).trim())
                                .append(";-;");
                    }
                } else {
                    if (textNodes.hasNext()) {
                        output
                                .append(filter(textNodes.next().text()));
                    }
                }
            }
        });
        try (FileWriter fw = new FileWriter(outputFile)) {
            fw.write(output.toString());
            System.out.println("file write success");
        }
    }

}
