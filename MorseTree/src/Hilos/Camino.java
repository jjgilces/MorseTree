package Hilos;

import static controlador.Arbol.RADIUS;
import static controlador.Arbol.VGAP;
import estructura.ArbolBinario;
import estructura.Nodo;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import morsetree.MorseTree;

/**
 *
 * @author Johan
 */
public class Camino implements Runnable {

    private double x,y,vgap,hGap;
    private String code;
    private Pane pane;

    public Camino(double vgap, String code, int size, Pane pane) {
        this.pane= pane;
        this.y = vgap;
        this.code = code;
        this.x = MorseTree.ventana / 2;
        this.hGap = MorseTree.ventana / 4;
        this.vgap = vgap;
    }

    @Override
    public void run() {
        Circle circle2 = new Circle(x, y, RADIUS);
        Platform.runLater(() -> {       
            circle2.setFill(Color.ORANGE);
            circle2.setStroke(Color.BLACK);
            System.out.println();
            pane.getChildren().add(circle2);
        });
        try {       
            Thread.sleep(600);
            Queue<String> codigos = ArbolBinario.decodificarMorse(code);
            System.out.println(codigos);
            while(!codigos.isEmpty()){
                String simbolo=codigos.poll();
                if (simbolo.equals(".")) {
                    moveRight();
                } else if (simbolo.equals("-")) {
                    moveLeft();
                } 
                Platform.runLater(    
                        new Runnable() {
                @Override public void run() {
                 Line linea   =new Line(x, y , x+hGap, y+vgap);
                 linea.setStroke(Color.RED);
                pane.getChildren().add(linea);
                 }});
                Thread.sleep(600);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Camino.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }

        cleanView();
    }

    private void moveRight() {
        x += hGap;
        y += vgap;
        hGap /= 2;
        playMusic("/recursos/Punto.mpeg");
    }

    private void moveLeft() {
        x -= hGap;
        y += vgap;
        hGap /= 2;
        playMusic("/recursos/Raya.mpeg");
    }


    private void cleanView() {
      Platform.runLater(()-> pane.getChildren().remove(131, pane.getChildren().size()));

    }

    private void playMusic(String musicFile) {
        Media sound = new Media(this.getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(10);
    }

    
   /* private void mostrarArbol(Nodo<String> actual, double x, double y, double hGap) {
        Circle circulo = new Circle(x, y, RADIUS);
        circulo.setFill(Color.LIGHTSKYBLUE);
        if (actual.getLeft() != null) {
            root.getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            mostrarArbol(actual.getLeft(), x - hGap, y + VGAP, hGap / 2);
        }
        if (actual.getRight() != null) {
           new Line
            root.getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            mostrarArbol(actual.getRight(), x + hGap, y + VGAP, hGap / 2);
        }
        circulo.setStroke(Color.BLACK);
        root.getChildren().addAll(circulo, new Text(x -4 , y + 4, actual.getData()));
    }
     public void mostrarArbol() {
        root.getChildren().clear();
        if (morseTree.getRoot() != null) {
            mostrarArbol(morseTree.getRoot(), MorseTree.ventana/2 , VGAP, MorseTree.ventana/4);
        }
    }*/
}
