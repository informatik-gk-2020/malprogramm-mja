import basis.*;

public class MalprogrammWindow {
    Fenster window;
    Maus mouse;

    Tool currentTool;

    public MalprogrammWindow() {
        window = new Fenster(900, 900);
        mouse = new Maus();
        currentTool = new PenTool();
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause();

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
        }
    }
}
