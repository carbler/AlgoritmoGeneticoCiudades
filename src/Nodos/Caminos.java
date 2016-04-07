/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import java.util.ArrayList;

/**
 *
 * @author erley
 */
public class Caminos {
    
    ArrayList<ArrayList<Integer>> caminos = new ArrayList<>();
    
    public ArrayList<ArrayList<Integer>> busqueda(int nodoInicial,int nodoFinal, int [][] mAdya){
    
        caminos(nodoInicial, nodoFinal, mAdya);
        return  this.caminos;
    }
    /**
     * Metodo para la primera iteracion  
     * @param nodoInicial
     * @param nodoFinal
     * @param mAdya 
     */
    private void caminos(int nodoInicial,int nodoFinal, int [][] mAdya){
          
          ArrayList<Integer> vertices =this.vecinos(nodoInicial, mAdya); // vertices del nodo inicial
          ArrayList<Integer> ruta = new ArrayList<>(); // array list, que me almacena los nodos de una ruta
           
           
           for (Integer vertice : vertices) {  
            ruta.clear();  //limpiamos la ruta
            ruta.add(nodoInicial);    // agregamos el nodo inicial
            ruta.add(vertice);       // agregamos el vertice a verificar
            this.caminos(nodoInicial, nodoFinal, ruta, mAdya); // llamamos a camino con la ruta
        
           }
           
     
    }
    
    /**
     * Metodo recursivo cuando cuando ya hay una ruta
     * @param nodoInicial
     * @param nodoFinal
     * @param ruta
     * @param mAdya 
     */
    private void caminos(int nodoInicial,int nodoFinal,ArrayList<Integer>ruta, int [][] mAdya){

        int x = ruta.size(); // tamaño del la ruta
        if(ruta.get(x-1)==nodoFinal){ //verificamos si el ultimo nodo de la ruta es el nodo final
           ArrayList<Integer> rutaAux = (ArrayList<Integer>) ruta.clone(); //clonamos la ruta terminada
           this.caminos.add(rutaAux);  // agregamos el clon al array de rutas
    
        }else{
            ArrayList<Integer> vertices = vecinos(ruta.get(x-1),mAdya); //guardamos los vertices del ultimo elemento de la ruta
            for (Integer vertice : vertices) {   // recorremos todos los vertices
                if (!ruta.contains(vertice)) {   //verificamos si el vertice ya está en la ruta

                          if(vertice==nodoFinal){  
                            ruta.add(vertice); // agregamos el vertice a la ruta
                            ArrayList<Integer> rutaAux = (ArrayList<Integer>) ruta.clone(); // clonamos la ruta
                            this.caminos.add(rutaAux); // Agregamos la ruta clonada, al array de rutas
                            ruta.remove(vertice);  // eliminamos el vertices, por si hay mas caminos a verificar
                          }else{

                            ruta.add(vertice);  // agregamos el vertice
                            caminos(nodoInicial, nodoFinal, ruta, mAdya);  // recurrimos a la funcion con la nueva ruta
                            ruta.remove(vertice); // removemos el vertices, por si hay mas caminos a verificar

                          }

                }


            }
        }
 

    }
      
      /**
       * Metodo que devuelve los vertices un nodo
       * @param nodoInicial
       * @param mAdya
       * @return 
       */
      private ArrayList<Integer> vecinos(int nodoInicial,int [][] mAdya){
        ArrayList<Integer> conexiones = new ArrayList<>();
        for (int j = 0; j < mAdya.length; j++) {
            if(mAdya[nodoInicial][j]==1){
               conexiones.add(j); 
              
            }
        }
        return conexiones;
    }
   
}
