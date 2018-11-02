import basis.Stift;

import java.awt.*;

public class PenTool extends Tool {

    public PenTool() {
        pen = new Stift();
        pen.setzeLinienBreite(20);
    }

    @Override
    public void setMousePosition(int x, int y) {
        pen.bewegeBis(x, y);
    }

    @Override
    public void setMousePressed(boolean mousePressed) {
        if (mousePressed) {
            pen.runter();
        } else {
            pen.hoch();
        }
    }


}
