
package controlador;

import static controlador.Arbol.VERTICAL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author johan_p9pyxb1
 */
public class MorseTController implements Initializable {

    @FXML 
    Pane paneArbol;
    
    @FXML 
    TextField txtTraducir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       new Arbol(paneArbol).mostrarArbol();
       
    }    
    
    public void traducir(){ 

        if(txtTraducir.getText().equals("")) Alerta.mostrarAlerta("Por favor ingrese una frase", "No ha ingresado");
        else{
        int size = paneArbol.getChildren().size();
        Thread thread = new Thread(new Camino(VERTICAL,txtTraducir.getText(),size,paneArbol));
        thread.start();
        }
    }

}
