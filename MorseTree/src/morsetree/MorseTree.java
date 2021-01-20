
package morsetree;

import estructura.ArbolBinario;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author johan_p9pyxb1
 */
public class MorseTree extends Application{

    public static final HashMap<String,List<String>> mapCodeMorse = ArbolBinario.codesMorse();
    public static  ArbolBinario<String> arbolBinarioMorse = new ArbolBinario<>();
     public static final int ventana = 1005;
    public static void main(String[] args) {
        arbolBinarioMorse= ArbolBinario.crearArbolMorse(mapCodeMorse);
        launch(args);
    }
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/MorseT.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("ENCRIPTADOR");
        stage.setScene(scene);
        stage.show();
    }
}
