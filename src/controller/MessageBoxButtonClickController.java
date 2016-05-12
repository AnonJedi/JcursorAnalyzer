package controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageBoxButtonClickController extends MouseAdapter {
    Frame f;
    public MessageBoxButtonClickController(Frame f) {
        this.f = f;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.f.dispose();
    }
}
