package xpath_example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.util.List;

public class XpathTest {

    public static void main(String[] args) {

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read("src/main/java/xpath_example/target.xml");
            XPath xPath = new DefaultXPath("/resources/product[@name='QQ']/account[@id='123456789']/password")
                    ;//注意，这里的resources，是根节点的的标签名；
            List<Element> list = xPath.selectNodes(document.getRootElement());

            for (Element element : list) {
                String textTrim = element.getTextTrim();
                System.out.println(textTrim);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
