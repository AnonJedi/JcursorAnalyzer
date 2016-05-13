package com.omstu.cursorAnalyzer.repository;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class UserRepository {
    private final String DBPath;
    private DocumentBuilderFactory builderFactory;
    private TransformerFactory transformerFactory;

    public UserRepository(String DBPath) {
        this.DBPath = DBPath;
        builderFactory = DocumentBuilderFactory.newInstance();
        transformerFactory = TransformerFactory.newInstance();
    }

    public Document getXMLDocument() throws RepositoryException {
        try {
            builderFactory.setValidating(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(DBPath);
        } catch (FileNotFoundException e) {
            createEmptyXML();
            return getXMLDocument();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Error occurred while build XML");
        }
    }

    private void createEmptyXML() throws RepositoryException {
        try {
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(Common.DB_TEMPLATE));
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(DBPath, false), "UTF-8");
            int read = inputStream.read();
            while (read != -1) {
                outputStream.write((char) read);
                read = inputStream.read();
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RepositoryException("Error occurred while create new DB file");
        }
    }

    public void create(Document document) throws RepositoryException {
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(DBPath));
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new RepositoryException("Error occurred while saving user in file");
        }
    }
}
