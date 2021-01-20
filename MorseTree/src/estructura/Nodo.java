/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura;

/**
 *
 * @author Johan
 */
public class Nodo<E> {
        public E data;
        protected Nodo<E> left;
        protected Nodo<E> right;

        public Nodo(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return  data +"";
        }

    public Nodo<E> getLeft() {
        return left;
    }

    public Nodo<E> getRight() {
        return right;
    }
          

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setRight(Nodo<E> right) {
        this.right = right;
    }
    public boolean isHoja(){
        return (left==null && right==null);
    }
}
