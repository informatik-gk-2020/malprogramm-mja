import basis.*;

public class MalprogrammWindow {
    Fenster window;
    Maus mouse;
    Tool currentTool;
    WahlBox wahlBoxRot;
    WahlBox wahlBoxGruen;
    WahlBox wahlBoxBlau;
    WahlBox wahlBoxSchwarz;
    WahlBoxGruppe wahlBoxGruppe;
    Knopf buttonPen;
    Knopf buttonEraser;

    public MalprogrammWindow() {
        window = new Fenster(900, 900);
        mouse = new Maus();

        currentTool = new Tool();

        wahlBoxGruen = new WahlBox("Grün", 50, 30, 100, 40);
        wahlBoxRot = new WahlBox("Rot", 50, 50, 100, 40);
        wahlBoxBlau = new WahlBox("Blau", 50, 70, 100, 40);
        wahlBoxSchwarz = new WahlBox("Schwarz", 50, 90, 100, 40);
        wahlBoxGruppe = new WahlBoxGruppe();
        wahlBoxGruppe.fuegeEin(wahlBoxGruen);
        wahlBoxGruppe.fuegeEin(wahlBoxRot);
        wahlBoxGruppe.fuegeEin(wahlBoxBlau);
        wahlBoxGruppe.fuegeEin(wahlBoxSchwarz);


        buttonPen = new Knopf("Draw", 10, 10, 50, 20);
        buttonEraser = new Knopf("Erase", 70, 10, 50, 20);
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause();

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
            wahlBoxPressed();
            checkToolButtons();
        }
    }

    public void wahlBoxPressed() {
        if (wahlBoxRot.wurdeGeaendert()) {
            currentTool.setColor(Farbe.ROT);
        } else if (wahlBoxGruen.wurdeGeaendert()) {
            currentTool.setColor(Farbe.GRUEN);
        } else if (wahlBoxBlau.wurdeGeaendert()) {
            currentTool.setColor(Farbe.BLAU);
        } else if (wahlBoxSchwarz.wurdeGeaendert()) {
            currentTool.setColor(Farbe.SCHWARZ);
        }
    }

    /**
     * Setzt das aktuelle Tool
     * @param tool das neue Tool
     */
    public void setCurrenTool(Tool tool) {
        currentTool = tool;
        wahlBoxSchwarz.setzeZustand(true);
    }

    public void checkToolButtons() {
        if (buttonPen.wurdeGedrueckt()) {
            setCurrenTool(new PenTool());
        } else if (buttonEraser.wurdeGedrueckt()) {
            setCurrenTool(new EraserTool());
        }
    }
}
