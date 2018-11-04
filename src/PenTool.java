import basis.Stift;

import java.awt.*;

public class PenTool extends Tool {

    public PenTool() {
        pen = new Stift();
    }

    /*
    Bewegt den Stift zu den übergebenen Koordinaten
     */
    @Override
    public void setMousePosition(int x, int y) {
        pen.bewegeBis(x, y);
    }

    /*
    Setzt den Stift hoch/runter mittels des übergeben Booleans
     */
    @Override
    public void setMousePressed(boolean mousePressed) {
        if (mousePressed) {
            pen.runter();
        } else {
            pen.hoch();
        }
    }
}
