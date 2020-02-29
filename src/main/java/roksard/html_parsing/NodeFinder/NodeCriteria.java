package roksard.html_parsing.NodeFinder;

public class NodeCriteria {
    String className;
    String tag;

    public boolean tagFits(String value) {
        return value.matches(tag);
    }

    public boolean classNameFits(String value) {
        return value.matches(className);
    }

    public NodeCriteria(String className, String tag) {
        this.className = className;
        this.tag = tag;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
