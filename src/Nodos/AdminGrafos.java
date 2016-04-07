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
public class AdminGrafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
         int matriz[][]={{0,1,1,1,0},
                         {0,1,1,1,0},
                         {0,1,1,1,0},
                         {0,1,1,1,0},
                         {0,1,1,1,0},
                         };
         int inicio=1;
         int fin = 3;
         Caminos ob = new Caminos();
         
         ArrayList<ArrayList<Integer>> Total = ob.busqueda(inicio-1,fin-1,matriz);
         System.out.println("Numero de rutas : "+Total.size());
         System.out.println("************************");
         for (ArrayList<Integer> camino : Total) {
             for (Integer nodo : camino) {
                 System.out.println(nodo+1);
             }
             System.out.println("*****************");
        }
    }
    
}
