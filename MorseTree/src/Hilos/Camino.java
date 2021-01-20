package Hilos;

import static controlador.Arbol.RADIUS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author PC
 */
public class Camino implements Runnable {

    private double x;
    private double y;
    private double hGap;
    private String code;
    private int size;
    private double vgap;
    private Pane pane;

    public Camino(double vgap, String code, int size, Pane pane) {
        this.pane=pane;
        this.y = vgap;
        this.code = code;
        this.size = size;
        this.x = pane.getWidth() / 2;
        this.hGap = pane.getWidth() / 4;
        this.vgap = vgap;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            Circle circle2 = new Circle(x, y, RADIUS);
            circle2.setFill(Color.ORANGE);
            circle2.setStroke(Color.BLACK);
            pane.getChildren().add(circle2);
        });
        try {
            Thread.sleep(600);
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '.') {
                    moveRight();
                } else if (code.charAt(i) == '-') {
                    moveLeft();
                } else {
                    returnToRoot(i);
                    i++;
                }
                Platform.runLater(    
                        new Runnable() {
            @Override public void run() {
                Circle circle2 = new Circle(x, y, RADIUS);
                circle2.setFill(Color.ORANGE);
                circle2.setStroke(Color.BLACK);
                pane.getChildren().add(circle2);
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

    private void returnToRoot(int i) throws InterruptedException {
        if (i + 1 < code.length() && code.charAt(i + 1) != ' ') {
            x = pane.getWidth() / 2;
            y = vgap;
            hGap = pane.getWidth() / 4;
            Thread.sleep(600);
        }
        cleanView();
    }

    private void cleanView() {
        Platform.runLater(()    -> pane.getChildren().remove(size, pane.getChildren().size()));
    }

    private void playMusic(String musicFile) {
        Media sound = new Media(this.getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(10);
    }

}
