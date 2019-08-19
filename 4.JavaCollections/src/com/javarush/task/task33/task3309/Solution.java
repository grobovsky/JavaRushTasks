package com.javarush.task.task33.task3309;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

/*
Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тегом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:
toXmlWithComment(firstSecondObject, "second", "it's a comment")

Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second><![CDATA[need CDATA because of < and >]]></second>
<!--it's a comment-->
<second/>
</first>

Требования:
1. Метод toXmlWithComment должен быть статическим.
2. Метод toXmlWithComment должен быть публичным.
3. Если во входящем xml отсутствует искомый тег, то добавлять комментарии не нужно.
4. Количество комментариев вставленных в xml должно быть равно количеству тегов tagName.
5. Метод toXmlWithComment должен возвращать xml в виде строки преобразованной в соответствии с условием задачи.
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setCoalescing(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        marshaller.marshal(obj, doc);

        NodeList list = doc.getElementsByTagName("*");
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getFirstChild().getNodeType() == Node.TEXT_NODE) {
                Node node = list.item(i).getFirstChild();
                if (node.getTextContent().matches(".*[<>&\'\"].*")) {
                    String content = node.getTextContent();
                    CDATASection cdataSection = doc.createCDATASection(content);
                    list.item(i).replaceChild(cdataSection, node);
                }
            }
            if (list.item(i).getNodeName().equals(tagName)) {
                list.item(i).getParentNode().insertBefore(doc.createComment(comment), list.item(i));

            }
        }

        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static void main(String[] args) throws Exception {
        First first = new First();
        System.out.println(toXmlWithComment(first, "second", "it's a comment"));
    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}
