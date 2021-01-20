
package controlador;

import Hilos.Camino;
import static controlador.Arbol.VGAP;
import estructura.ArbolBinario;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import static morsetree.MorseTree.arbolBinarioMorse;
import static morsetree.MorseTree.mapCodeMorse;

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
        int size = paneArbol.getChildren().size();
        String textoMorse= ArbolBinario.codificarMorse(mapCodeMorse.get("A"));
        System.out.println("texto morse");
        System.out.println(textoMorse);
        System.out.println();
        Thread thread = new Thread(new Camino(VGAP,txtTraducir.getText(),size,paneArbol));
        thread.start();
    }

}
