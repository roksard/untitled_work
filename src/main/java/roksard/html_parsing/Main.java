package roksard.html_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.goethe.de/prj/mwd/ru/glo.html").get();
        List<NodeCriteria> searchPath = new ArrayList<NodeCriteria>() {{
            add(new NodeCriteria(".*glossar-text.*", ".*"));
            add(new NodeCriteria(".*", "div"));
            add(new NodeCriteria(".*", ".*"));
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
