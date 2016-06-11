package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.repository.UserRepository;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserService {

    private UserRepository repository;

    public UserService() {
        repository = new UserRepository(Common.DB_PATH);
    }

    public boolean registerUser(final String username) throws ServiceException {
        boolean result;
        try {
            Document document = repository.getXMLDocument();
            if (!checkExistingUser(document, username)) {
                createUser(document, username);
                result = true;
            } else result = false;
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return result;
    }

    private boolean checkExistingUser(Document document, String username) {
        NodeList classNodes = document.getElementsByTagName("Class");
        for (int i = 0; i < classNodes.getLength(); i++) {
            if (username.equals(classNodes.item(i).getAttributes().getNamedItem("name").getNodeValue())) {
                return true;
            }
        }
        return false;
    }

    private void createUser(Document document, String username) throws ServiceException {
        Attr nameAttr = document.createAttribute("name");
        nameAttr.setValue(username);

        Element newClass = document.createElement("Class");
        newClass.setAttributeNode(nameAttr);

        NodeList classNodes = document.getElementsByTagName("Features");
        classNodes.item(classNodes.getLength() - 1).appendChild(newClass);

        try {
            repository.insert(document);
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
