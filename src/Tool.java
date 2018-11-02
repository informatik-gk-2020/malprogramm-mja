import basis.*;

import java.awt.*;

public class Tool {
    Stift pen;

    public void setMousePosition(int x, int y){

    }

    public void setMousePressed(boolean mousePressed){

    }

    public void setColor(Color color) {
        pen.setzeFarbe(color);
    }

    public void setSize(int size) {
        pen.setzeLinienBreite(size);
    }
}
