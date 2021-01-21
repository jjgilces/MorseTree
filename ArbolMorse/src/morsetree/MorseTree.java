
package morsetree;

import controlador.Alerta;
import estructura.ArbolBinario;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static final HashMap<String,List<String>> mapaCodigoMorse = ArbolBinario.codesMorse();
    public static  ArbolBinario<String> arbolBinarioMorse = new ArbolBinario<>();
     public static final int DIMENSION = 1005;
    public static void main(String[] args) {
        arbolBinarioMorse= ArbolBinario.crearArbolMorse(mapaCodigoMorse);
        launch(args);
    }
     @Override
    public void start(Stage stage)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Principal.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("ENCRIPTADOR");
            stage.setScene(scene);
            stage.setOnCloseRequest(e->{
                Optional<ButtonType> result = Alerta.confirmation();
                if(result.get()==ButtonType.OK){
                    Platform.exit();
                }});
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MorseTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchElementException ex) {
            System.err.println("Cierre de ventana emergente"+ex);;
        }
    }
}
