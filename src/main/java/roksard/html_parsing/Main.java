package roksard.html_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    final static String fileName = "target\\classes\\xml_parse\\words-to-extract.html";
    final static String url = "https://www.goethe.de/prj/mwd/ru/glo.html";

    public static void find(List<NodeCriteria> cr, int currentCr, Elements elements) {
        for(Element elem : elements) {
            if (cr.get(currentCr).tagFits(elem.tagName()) && cr.get(currentCr).classNameFits(elem.className())) {
                if (currentCr < cr.size())
                find(cr, currentCr+1, elem.children());
            }
        }
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
        List<NodeCriteria> searchPath = new ArrayList<NodeCriteria>() {{
            add(new NodeCriteria(".*glossar-text.*", ".*"));
            add(new NodeCriteria(".*", "div"));
            add(new NodeCriteria(".*", "p"));
        }};
        int level = 0;
        for (Element elem : doc.getAllElements()) {
            NodeCriteria cr = null;
            if (searchPath.size() == level) {
                for (Element elemc : elem.children()) {
                    System.out.println(elemc);
                }
            } else {
                cr = searchPath.get(level);
            }
            if (cr != null) {
                if (cr.tagFits(elem.tagName()) && cr.classNameFits(elem.className())) {
                    level++;
                    //System.out.println(level);
                } else {
                    level = 0;
                }
            }
        }
    }

}
