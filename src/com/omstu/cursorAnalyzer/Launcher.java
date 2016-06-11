package com.omstu.cursorAnalyzer;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.view.AppView;

import java.io.File;

public class Launcher {
    public static void main(String[] args) {
        File file = new File(Common.OUT_PATH);
        if (!file.exists()) {
            if (file.mkdir()) System.out.println("Result directory was created");
            else {
                System.out.println("Error occurred while creation result directory");
                return;
            }
        }
        AppView app = new AppView("Diplom");
    }
}
