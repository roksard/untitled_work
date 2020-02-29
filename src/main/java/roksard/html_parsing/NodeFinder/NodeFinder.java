package roksard.html_parsing.NodeFinder;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeFinder {
    /**
     * Поиск по всему дереву элементов, при совпадении всех критериев, узел считается
     * найденным и добавляется в результаты поиска.
     * Пример использования критериев: нужно найти все теги данных таблицы (td)
     * можно использовать просто new NodeCriteria(".*", "td"),
     * либо учитывать вложенность, если у нас например внутри есть div
     * <row><td><div</div></td></row>:
     *Arrays.asList(new NodeCriteria[] {new NodeCriteria(".*", "row"),
     *                 new NodeCriteria(".*", "td"),
     *                 new NodeCriteria(".*", "div")});
     *
     * @param crs список критериев по порядку старший, дочерний, его дочерний итд
     *      * чем длиннее список тем глубже искомый узел
     *      * Критерии className и tag задаются regex строкой
     * @param rootElement корневой элемент от которого начинаем поиск (он не должен входить в список критериев)
     * @return
     */
    public static List<Element> find(List<NodeCriteria> crs, Element rootElement) {

        return find(crs, 0, rootElement.children());
    }
    static List<Element> find(List<NodeCriteria> crs, int crId, Elements elements) {
        List<Element> result = new ArrayList<>();
        NodeCriteria cr = crs.get(crId);
        for(Element elem : elements) {
            if (cr.tagFits(elem.tagName()) && cr.classNameFits(elem.className())) {
                if (crId+1 < crs.size()) {
                    result.addAll(find(crs, crId+1, elem.children()));
                } else {
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
}
