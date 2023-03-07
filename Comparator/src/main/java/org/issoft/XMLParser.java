package org.issoft;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    final String fileName = "config.xml";
    private final static String PATH = "config.xml";
    private static Map<String, String> fieldToSort = new LinkedHashMap<>();

    public static Map<String, String> parseXml() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document doc;

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new File(PATH));
            NodeList sorting = doc.getElementsByTagName("sort");
            NodeList fields = sorting.item(0).getChildNodes();
            for (int i = 0; i < fields.getLength(); i++) {
                if (fields.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    String fieldName = fields.item(i).getNodeName();
                    String sortValue = fields.item(i).getTextContent();
                    fieldToSort.put(fieldName, sortValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return fieldToSort;
    }

}
