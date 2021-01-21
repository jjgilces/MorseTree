package controlador;

import static controlador.Arbol.RadioCirculo;
import static controlador.Arbol.VERTICAL;
import static controlador.PlayMusic.reproducir;
import estructura.ArbolBinario;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import morsetree.MorseTree;

/**
 *
 * @author Johan
 */
public class Camino implements Runnable {

    private double x, y, hGap;
    private String code;
    private Pane pane;

    public Camino(double vgap, String code, int size, Pane pane) {
        this.pane = pane;
        this.y = vgap;
        this.code = code;
        this.x = MorseTree.DIMENSION / 2;
        this.hGap = MorseTree.DIMENSION / 4;

    }

    public Camino(Pane pane) {
        this.pane = pane;
        this.x = MorseTree.DIMENSION / 2;
        this.hGap = MorseTree.DIMENSION / 4;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            Circle circuloInicio = new Circle(x, y, RadioCirculo);
        circuloInicio.setFill(Color.RED);
        pane.getChildren().add(circuloInicio);
        });
        try {
            Thread.sleep(500);
            Queue<String> codigos = ArbolBinario.decodificarMorse(code);
            System.out.println(codigos);
            while (!codigos.isEmpty()) {
                String simbolo = codigos.poll();
                switch (simbolo) {
                    case ".":
                        Platform.runLater(() -> moverPunto());
                        Thread.sleep(400);
                        break;
                    case "-":
                        Platform.runLater(() -> moverRaya());
                         Thread.sleep(600);
                        break;
                    case " ":            
                        volverIncio();
                         Thread.sleep(700);
                        break;
                    default:   
                      Platform.runLater(() -> encontrado());
                       Thread.sleep(200);
                       volverIncio();
                        break;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Camino.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }

        cleanView();
    }

    private void cleanView() {
        Platform.runLater(() -> pane.getChildren().remove(131, pane.getChildren().size()));
    }

    private void volverIncio() {
        x = MorseTree.DIMENSION / 2;
        y = VERTICAL;
        hGap = MorseTree.DIMENSION / 4;
        cleanView();
    }

    private void moverPunto() {  
        reproducir("Punto.mpeg");
        Line linea = new Line(x, y, x + hGap, y + VERTICAL);
        linea.setStroke(Color.RED);
        pane.getChildren().add(linea);
        x += hGap;
        y += VERTICAL;
        hGap /= 2;

    }

    private void moverRaya() {
        reproducir("Raya.mpeg");
        Line linea = new Line(x - hGap, y + VERTICAL, x, y);
        linea.setStroke(Color.RED);
        pane.getChildren().add(linea);
        x -= hGap;
        y += VERTICAL;
        hGap /= 2;
    }

    private void encontrado(){
      Circle circle2 = new Circle(x, y, RadioCirculo);
      circle2.setFill(Color.GREEN);
      System.out.println("hola");
      pane.getChildren().add(circle2);
        
    }
  
}
