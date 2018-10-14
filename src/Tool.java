import basis.*;

public class Tool {
    static Stift pen;
    static WahlBox wahlBoxRot;
    static WahlBox wahlBoxGruen;
    static WahlBox wahlBoxBlau;
    static WahlBox wahlBoxSchwarz;
    static WahlBoxGruppe wahlBoxGruppe;
    static Knopf buttonPen;
    static Knopf buttonEraser;

    public Tool() {
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

    public void setMousePosition(int x, int y){

    }

    public void setMousePressed(boolean mousePressed){

    }

    public static void wahlBoxPressed() {
        if (wahlBoxRot.wurdeGeaendert()) {
            pen.setzeFarbe(Farbe.ROT);
        } else if (wahlBoxGruen.wurdeGeaendert()) {
            pen.setzeFarbe(Farbe.GRUEN);
        } else if(wahlBoxBlau.wurdeGeaendert()) {
            pen.setzeFarbe(Farbe.BLAU);
        } else if (wahlBoxSchwarz.wurdeGeaendert()) {
            pen.setzeFarbe(Farbe.SCHWARZ);
        }
    }

    public static void buttonPressed(Tool currentTool) {
        if (buttonPen.wurdeGedrueckt()) {
            MalprogrammWindow.currentTool = new PenTool();
        } else if (buttonEraser.wurdeGedrueckt()) {
            MalprogrammWindow.currentTool = new EraserTool();
        }
    }
}
