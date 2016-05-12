package view;

import controller.MessageBoxButtonClickController;

import java.awt.*;

public class MessageBox extends Frame {
    public MessageBox(String title, String message, String buttonText) {
        super(title);
        setLayout(null);
        int centerX = 500;
        int centerY = 300;
        setBounds(centerX, centerY, 300, 180);
        Label messageLabel = new Label(message);
        messageLabel.setBounds(50, 30, 300, 70);
        add(messageLabel);
        Button button = new Button(buttonText);
        button.addMouseListener(new MessageBoxButtonClickController(this));
        button.setBounds(125, 120, 50, 30);
        add(button);
        setVisible(true);
    }
}
