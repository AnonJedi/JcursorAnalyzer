package controller;

import common.Common;
import service.UserService;
import view.MessageBox;

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
        if (!username.equals("") && service.registrateUser(username)) {
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
    }
}
