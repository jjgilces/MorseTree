
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johan_p9pyxb1
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void comenzarArbol() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/MorseT.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Encriptador");
            stage.setScene(scene);
            stage.setOnCloseRequest(e->{
            Optional<ButtonType> result = Alerta.confirmation();
            if(result.get()!=ButtonType.OK){            
                Platform.exit();
                 e.consume();
            }else{
                Platform.exit();
            }});
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
