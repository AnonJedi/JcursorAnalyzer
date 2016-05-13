package com.omstu.cursorAnalyzer.controller;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.service.UserService;
import com.omstu.cursorAnalyzer.view.MessageBox;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegButtonClickController extends MouseAdapter {
    private UserService service = new UserService();

    @Override
    public void mousePressed(MouseEvent e) {
        TextField text = (TextField) e.getComponent()
                .getParent()
                .getComponent(Common.USERNAME_TEXTFIELD_NUMBER);
        Button startStopButton = (Button) e.getComponent()
                .getParent()
                .getComponent(Common.START_STOP_BUTTON_NUMBER);
        String username = text.getText().replace(' ', '_');
        try {
            if (!username.equals("") && service.registerUser(username)) {
                startStopButton.setEnabled(true);
                text.setText("");
                Label user = (Label) e.getComponent()
                        .getParent()
                        .getComponent(Common.USER_NAME_LABEL_NUMBER);
                user.setText("User: " + username);
                Label counter = (Label) e.getComponent()
                        .getParent()
                        .getComponent(Common.USER_TEST_COUNTER_NUMBER);
                counter.setText("0");
                e.getComponent()
                        .getParent()
                        .getComponent(Common.COUNTER_LABEL_NUMBER)
                        .setVisible(true);
            } else {
                if (username.equals("")) new MessageBox("Error", "Please enter some name", "OK");
                else new MessageBox("Error", "This name already registered", "OK");
                startStopButton.setEnabled(false);
            }
        } catch (ServiceException ex) {
            new MessageBox("Error", ex.getMessage(), "OK");
        }

    }
}
