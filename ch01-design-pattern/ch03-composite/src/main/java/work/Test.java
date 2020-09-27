package work;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyTreeNode treeNode = getTree();
        treeNode.show();

        treeNode.remove(0);
        System.out.println("删除第一个节点后：");
        treeNode.show();
    }

    private static MyTreeNode getTree() {

        SAXReader reader = new SAXReader();
        try {

            Document document = reader.read("src/main/java/xpath_example/target.xml");
            Element rootEle = document.getRootElement();
            String name = rootEle.getName();

            MyTreeNode treeNode = new MyTreeNode(name, name);
            scanTree(rootEle, treeNode, name);

            return treeNode;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void scanTree(Element parentElements, MyTreeNode parentNode, String parentXpath) {
        List<Element> elements = parentElements.elements();

        for (Element element : elements) {
            String name = element.getName();
            String xPath = parentXpath + "/" + name + "(" + element.getTextTrim() + ")";

            MyTreeNode treeNode = new MyTreeNode(name, xPath);
            parentNode.add(treeNode);

            scanTree(element, treeNode, xPath);
        }
    }
}
