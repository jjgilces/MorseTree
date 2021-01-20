package estructura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ArbolBinario<E> {
    Nodo<E> root;
    

    public boolean isEmpty() {
        return root == null;
    }

    private Nodo<E> searchNode(E data) {
        return searchNode(data, root);
    }

    private Nodo<E> searchNode(E data, Nodo<E> n) {
        if (n == null) return n; //No preguntar con isEmpty 
        else if (n.data.equals(data)) return n;
        else {
            Nodo<E> nl = searchNode(data, n.left);
            if (nl != null)return nl;
            return searchNode(data, n.right);
        }
    }

    public boolean add(E child, E parent) {
        Nodo<E> nchild = new Nodo<>(child);
        if (isEmpty() && parent == null) {
            root = nchild;
            return true;
        }
        Nodo<E> np = searchNode(parent);
        Nodo<E> nce = searchNode(child);
        if (nce == null && np != null) {
            if (np.left == null) {
                np.left = nchild;
                return true;
            } else if (np.right == null) {
                np.right = nchild;
                return true;
            }
        }
        return false;
    } 
    
    public int height() {
        return height(root);
    }

    private int height(Nodo<E> n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public Nodo<E> getRoot() {
        return root;
    }
 
    public static String codificarMorse(List<String> codigos, ArbolBinario<String> arbol){
        return arbol.codificarMorse(codigos);
    }
    private String codificarMorse(List<String> codigos){
         Nodo<String> q=(Nodo<String>) this.root;
         String cadena="";
        for(String c: codigos){  
            if(c.equals(".")) q = q.right;
            else   q = q.left; 
            if(q==null) return cadena;
            if(!q.data.equals("") && q!=root){
                cadena += q.data;
                q = (Nodo<String>) this.root;
            }  
        }
        return cadena;
    }  

    public static ArbolBinario<String> crearArbolMorse(HashMap<String, List<String>> mapa){
        ArbolBinario<String> morse = new ArbolBinario<>();
        morse.add("START");     
        mapa.entrySet().forEach((e) -> {
            morse.addMorse(e.getKey(), e.getValue());
        });
           
        return morse;
    }
    public boolean add(E child) {
      Nodo<E> nc = new Nodo<>(child);
      if (!isEmpty())   return false;   
      root = nc;
      return true; 
    }
    public boolean addMorse(String letra, List<String> camino){
         Nodo nd = root;
         if(letra==null) return false;
          for (int i=0; i<camino.size();i++) {
              String signo= camino.get(i);
              if (".".equals(signo)) {
                  if (nd.right == null) nd.right = new Nodo<>("");
                  if (i == camino.size()-1)  { nd.right.data = letra ;return true; }
                  nd = nd.right;
              }
              else if (signo.equals("-")) {
                  if (nd.left == null)nd.left = new Nodo<>("");
                  if (i == camino.size()-1) {nd.left.data = letra; return true;}
                  nd = nd.left;
              }            
          }
          return false;
    }

    public void anchura() {
        if (!isEmpty()) {
            Queue<Nodo<E>> cola = new LinkedList<>();
            cola.offer(root);
            while (!cola.isEmpty()) {
                Nodo<E> n = cola.poll();
                System.out.print(n.data +" ");
                if (n.left != null) {
                    cola.offer(n.left);
                }
                if (n.right != null) {
                    cola.offer(n.right);
                }
            }
        }
        System.out.println("");
    }
    private void mostrarArbol(final Pane vb, final Nodo<E> n, final int nivel) {
        HBox hb = new HBox();
        hb.setTranslateY(70 * nivel);
        vb.getChildren().add(hb);
        final Circle cir = new Circle(20);
        cir.setFill(Color.CHOCOLATE);
        final StackPane st = new StackPane();
        st.getChildren().add(cir);
        if (n != null) {
            final Label lbl = new Label(n.data.toString());
            lbl.setTextFill(Color.WHITE);
            st.getChildren().add(lbl);
            mostrarArbol(vb, n.left, nivel + 1);
            mostrarArbol(vb, n.right, nivel + 1);
        } else {

            cir.setOpacity(0);
            if (nivel < height() - 1) {
                mostrarArbol(vb, null, nivel + 1);
                mostrarArbol(vb, null, nivel + 1);
            }
        }

    }

    public static HashMap<String,List<String>> codesMorse() {
        HashMap<String,List<String>> CodeMorse= new HashMap<>();
        try {      
            List<String> lineas = Files.readAllLines(Paths.get("src/recursos/traducciones.txt"));  
            for(String linea : lineas){
                LinkedList<String> valor = new LinkedList<>();
                String[] parts = linea.split("\\|");
                for(int i =1;i<parts.length;i++) valor.add(parts[i]);
                CodeMorse.put( parts[0],valor);
            }    
           
        } catch (IOException ex) {
            Logger.getLogger(ArbolBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(CodeMorse);
        return CodeMorse;
    }
}

