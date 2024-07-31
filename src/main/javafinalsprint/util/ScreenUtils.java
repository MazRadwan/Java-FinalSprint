package main.javafinalsprint.util;


import java.awt.*;

public class ScreenUtils {
    private static GraphicsDevice selectedScreen;

    static {
        // Initialize with the default screen (usually the primary monitor)
        selectedScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    public static void setPreferredScreen(int screenIndex) {
        GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        if (screenIndex >= 0 && screenIndex < screens.length) {
            selectedScreen = screens[screenIndex];
        } else {
            System.err.println("Invalid screen index. Using default screen.");
        }
    }

    public static void centerOnScreen(Window window) {
        Rectangle bounds = selectedScreen.getDefaultConfiguration().getBounds();
        int x = bounds.x + (bounds.width - window.getWidth()) / 2;
        int y = bounds.y + (bounds.height - window.getHeight()) / 2;
        window.setLocation(x, y);
    }

    public static GraphicsDevice getSelectedScreen() {
        return selectedScreen;
    }

    public static void printScreenInfo() {
        GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        System.out.println("Screen Information:");
        for (int i = 0; i < screens.length; i++) {
            GraphicsDevice screen = screens[i];
            Rectangle bounds = screen.getDefaultConfiguration().getBounds();
            System.out.println("Screen " + i + ": " +
                               "ID=" + screen.getIDstring() + ", " +
                               "Bounds=" + bounds + ", " +
                               "Is Default=" + (screen == GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()));
        }
    }
}