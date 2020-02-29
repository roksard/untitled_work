package roksard.html_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public static List<Element> find(List<NodeCriteria> crs, int crId, Elements elements) {
        List<Element> result = new ArrayList<>();
        log("elements size: " + elements.size());
        NodeCriteria cr = crs.get(crId);
        for(Element elem : elements) {
            log(elem.tagName());
            if (cr.tagFits(elem.tagName()) && cr.classNameFits(elem.className())) {
                if (crId+1 < crs.size()) {
                    result.addAll(find(crs, crId+1, elem.children()));
                } else {
                    //found
                    log("found: (" + elem.tagName() + ")");
                    result.add(elem);
                }
            } else {
                if (elem.children().size() > 0) {
                    result.addAll(find(crs, crId, elem.children()));
                }
            }
        }
        return result;
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
        int criteriaId = 0;
        List<Element> found = find(criteriaList, criteriaId, doc.children());
        StringBuilder output = new StringBuilder();
        found.forEach(elem -> {
            Iterator<TextNode> textNodes = elem.textNodes().iterator();
            for (Element ch : elem.children()) {
                if (ch.tagName().equals("strong")) {
                    output
                            .append("\n")
                            .append(filter(ch.text()))
                            .append(";-;");
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
