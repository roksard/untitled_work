package roksard.xml_parse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.xmlbeans.XmlObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    final static String file = "C:\\Users\\ruf\\IdeaProjects\\untitled_work\\src\\main\\resources\\xml_parse\\words-to-extract.html";

    public static void main(String[] args) throws Exception {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);


        JsonNode jsonNode = xmlMapper.readTree(new File(file));
        System.out.println(jsonNode);

    }
}
