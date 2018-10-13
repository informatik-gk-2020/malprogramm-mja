import basis.*;

public class MalprogrammWindow {
    Fenster window;
    Maus mouse;

    Knopf buttonPen;
    Knopf buttonEraser;
    Tool currentTool;

    public MalprogrammWindow() {
        window = new Fenster(900, 900);
        mouse = new Maus();
        currentTool = new PenTool();
        buttonPen = new Knopf("Draw", 10, 10, 50, 20);
        buttonEraser = new Knopf("Erase", 70, 10, 50, 20);
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause();

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
            buttonPressed();
        }
    }

    public void buttonPressed() {
        if (buttonPen.wurdeGedrueckt()) {
            currentTool = new PenTool();
        } else if (buttonEraser.wurdeGedrueckt()) {
            currentTool = new EraserTool();
        }
    }
}
