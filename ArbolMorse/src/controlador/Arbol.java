package controlador;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import estructura.ArbolBinario;
import estructura.Nodo;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import morsetree.MorseTree;
import static morsetree.MorseTree.arbolBinarioMorse;

/**
 *
 * @author JohanGilces
 */
public class Arbol {

    private Pane vista;
    private ArbolBinario<String> morseTree= arbolBinarioMorse;
    public static final double RadioCirculo = 15;
    public static final double VERTICAL = 60;

    public Arbol(Pane vista) {
        this.vista = vista;    
    }

    public Pane getRoot() {
        return vista;
    }

    public void mostrarArbol() {
        vista.getChildren().clear();
        if (morseTree.getRoot() != null) {
            mostrarArbol(morseTree.getRoot(), MorseTree.DIMENSION/2 , VERTICAL, MorseTree.DIMENSION/4);
        }
    }

    private void mostrarArbol(Nodo<String> actual, double x, double y, double hGap) {
        Circle circulo = new Circle(x, y, RadioCirculo);
        circulo.setFill(Color.LIGHTSKYBLUE);
        if (actual.getLeft() != null) {
            vista.getChildren().add(new Line(x - hGap, y + VERTICAL, x, y));
            mostrarArbol(actual.getLeft(), x - hGap, y + VERTICAL, hGap / 2);
        }
        if (actual.getRight() != null) {
           
            vista.getChildren().add(new Line(x + hGap, y + VERTICAL, x, y));
            mostrarArbol(actual.getRight(), x + hGap, y + VERTICAL, hGap / 2);
        }
        circulo.setStroke(Color.BLACK);
        vista.getChildren().addAll(circulo, new Text(x -4 , y + 4, actual.getData()));
    }


}
