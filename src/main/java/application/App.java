package application;

import calculator.ControllerButton;
import calculator.Screen;

public class App {
    public static void main(String[] args) {
        Screen screen = new Screen();
        ControllerButton controllerButton = new ControllerButton(screen);
        screen.setVisible(true);
    }
}
