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
import morsetree.MorseTree;
import static morsetree.MorseTree.arbolBinarioMorse;
import static morsetree.MorseTree.mapCodeMorse;


public class ArbolBinario<E> {
    Nodo<E> root;

    public ArbolBinario() {
    }
    

    public boolean isEmpty() {
        return root == null;
    }

    private Nodo<E> searchNode(E data) {
        return searchNode(data, root);
    }

    private Nodo<E> searchNode(E data, Nodo<E> n) {
        if (n == null) return n; //No preguntar con isEmpty 
        else if (n.getData().equals(data)) return n;
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

    public Nodo<E> getRoot() {
        return root;
    }
 
    public static String codificarMorse(List<String> codigos){
        Nodo<String> q=(Nodo<String>) arbolBinarioMorse.root;
        String cadena="";
        for(String c: codigos){  
            if(c.equals(".")) q = q.right;
            else   q = q.left; 
            if(q==null) return cadena;
            if(!q.getData().equals("") && q!=arbolBinarioMorse.root){
                cadena += q.getData();
                q = (Nodo<String>) arbolBinarioMorse.root;
            }  
        }
        return cadena;
    }
     public static Queue<String> decodificarMorse(String frase){
        Queue<String> caminos = new LinkedList<>();
        String c=frase.toUpperCase();
        for (int x=0;x<frase.length();x++){
           List<String> signos =  mapCodeMorse.get(Character.toString(c.charAt(x)));
           if(signos==null) caminos.offer(" ");
           else for(String l:signos)  caminos.offer(l);
           caminos.offer(Character.toString(c.charAt(x)));
        }     
        return caminos;
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
                  if (i == camino.size()-1)  { nd.right.setData(letra) ;return true; }
                  nd = nd.right;
              }
              else if (signo.equals("-")) {
                  if (nd.left == null)nd.left = new Nodo<>("");
                  if (i == camino.size()-1) {nd.left.setData(letra); return true;}
                  nd = nd.left;
              }            
          }
          return false;
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

