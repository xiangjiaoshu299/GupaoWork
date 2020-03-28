package work;

import java.util.ArrayList;
import java.util.List;

public class TreeNode extends AbstractNode {

    private List<AbstractNode> children;

    public TreeNode(String name, String xpath) {
        super(name, xpath);
        this.children = new ArrayList<AbstractNode>();
    }

    public void add(AbstractNode abstractNode){
        this.children.add(abstractNode);
    }

    public void remove(int index){
        this.children.remove(index);
    }

    public void show() {
        System.out.println("xpath: " + this.xpath);

        for (AbstractNode child : children) {
            child.show();
        }
    }
}
