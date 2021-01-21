package controlador;

import static controlador.Arbol.RADIUS;
import static controlador.Arbol.VGAP;
import static controlador.PlayMusic.reproducir;
import estructura.ArbolBinario;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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

  public Camino( Pane pane) {
        this.pane= pane;
        this.y = vgap;
        this.x = MorseTree.ventana / 2;
        this.hGap = MorseTree.ventana / 4;
    }
    

    @Override
    public void run() {  
        Platform.runLater(() -> {
            pintarCirculo(Color.RED);
        });
        try {       
            Thread.sleep(600);
            Queue<String> codigos = ArbolBinario.decodificarMorse(code);
            System.out.println(codigos);
            while(!codigos.isEmpty()){
                String simbolo=codigos.poll();
                if (simbolo.equals(".")) {
                    Platform.runLater(()-> moverPunto());
                } else if (simbolo.equals("-")) {
                    Platform.runLater(()-> moverRaya() );
                } else {
                   Platform.runLater(()-> pintarCirculo(Color.GREEN));
                   volverIncio();      
                }
                Thread.sleep(600);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Camino.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }

        cleanView();
    }

    private void cleanView() {
      Platform.runLater(()-> pane.getChildren().remove(131, pane.getChildren().size()));
    }

   
     private void volverIncio(){
        x =  MorseTree.ventana / 2;
        y = vgap;
        hGap = MorseTree.ventana / 4;
        cleanView();
     }
     
     private void moverPunto() {
       reproducir("Punto.mpeg");
        Line linea   =new Line(x, y , x+hGap, y+vgap);
        linea.setStroke(Color.RED);
        pane.getChildren().add(linea);
         x += hGap;
        y += vgap;
        hGap /= 2;
  
    }
     
    private void moverRaya(){
       reproducir("Raya.mpeg");
        Line linea   =new Line(x - hGap, y + VGAP, x, y);
        linea.setStroke(Color.RED);
        pane.getChildren().add(linea);  
        x -= hGap;
        y += vgap;
        hGap /= 2;
    }
     
    private void pintarCirculo(Color c){            
       Circle circle2 = new Circle(x, y, RADIUS);
       circle2.setFill(c);
       System.out.println("PINTANDO ERDE");
       pane.getChildren().add(circle2);
    }
}
