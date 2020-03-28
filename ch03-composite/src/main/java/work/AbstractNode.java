package work;

import lombok.Data;

@Data
public abstract class AbstractNode {

    protected String name;
    protected String xpath;

    public AbstractNode(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

    public abstract void show();
}
