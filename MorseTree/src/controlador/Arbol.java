package controlador;

import Hilos.Camino;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import estructura.ArbolBinario;
import estructura.Nodo;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author JohanGilces
 */
public class Arbol {

    private Pane root;
    private ArbolBinario<String> morseTree;
    public static final double RADIUS = 15;
    public static final double VGAP = 60;

    public Arbol(Pane root, ArbolBinario<String> bt) {
        this.root = root;
        morseTree = bt;
     
    }

    public Pane getRoot() {
        return root;
    }



    public void mostrarArbol() {
        root.getChildren().clear();
        if (morseTree.getRoot() != null) {
            mostrarArbol(morseTree.getRoot(), 500 , VGAP, 250);
        }
    }

    private void mostrarArbol(Nodo<String> actual, double x, double y, double hGap) {
        Circle circulo = new Circle(x, y, RADIUS);
        circulo.setFill(Color.LIGHTSKYBLUE);
        if (actual.getLeft() != null) {
            root.getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            mostrarArbol(actual.getLeft(), x - hGap, y + VGAP, hGap / 2);
        }
        if (actual.getRight() != null) {
           
            root.getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            mostrarArbol(actual.getRight(), x + hGap, y + VGAP, hGap / 2);
        }
        circulo.setStroke(Color.BLACK);
        root.getChildren().addAll(circulo, new Text(x -4 , y + 4, actual.data));
    }

//     private void buscar(String frase) {
//        int size = root.getChildren().size();
//        Thread thread = new Thread(new PathThread(VGAP,code,size,this));
//        thread.start();
//    
//    }

  /*  private void displayTree(BST.TreeNode<Integer> current,
            double x, double y, double hGap) {
        if (current.left != null) {
// Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
// Draw the left subtree recursively
            displayTree(current.left, x - hGap, y + vGap, hGap / 2);
        }
        if (current.right != null) {
// Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
// Draw the right subtree recursively
            displayTree(current.right, x + hGap, y + vGap, hGap / 2);
        }
// Display the current node
        Circle circulo = new Circle(x, y, radius);
        circulo.setFill(Color.WHITE);
        circulo.setStroke(Color.BLACK);
        getChildren().addAll(circulo,
                new Text(x - 4, y + 4, current.element + ""));
    }

*/
}
