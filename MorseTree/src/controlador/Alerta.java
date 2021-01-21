
package controlador;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
/**
 *
 * @author johan
 */
public abstract class Alerta {
    public static Optional<ButtonType> confirmation(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("CONFIRMATION ALERT");
        alert.setHeaderText("CERRAR EL PROGRAMA");
        alert.setContentText("SI DA CLIC EN ACEPTAR, TERMINARÁ EL PROGRAMA \n ¿ESTÁ SEGURO QUE DESEA CONTINUAR?");
        return alert.showAndWait();    
    }

    public static void mostrarAlerta(String mensaje,String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
