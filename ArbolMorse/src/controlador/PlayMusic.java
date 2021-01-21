
package controlador;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author johan_p9pyxb1
 */
public abstract class PlayMusic {
     public static void reproducir(String musicFile) {
        Media sound = new Media(new File(("src/recursos/"+musicFile)).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(10);
    }

}
