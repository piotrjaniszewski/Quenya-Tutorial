package pl.piotrjaniszewski.quenyatutorial;

import pl.piotrjaniszewski.quenyatutorial.gui.components.MyFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame myFrame = new MyFrame("Quenya Tutorial");
            }
        });
    }
}
