/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgotimoGenetico;

import Nodos.AdministradorNodos;
import static Nodos.AdministradorNodos.getAdministradorNodos;

/**
 *
 * @author erley
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        AdministradorNodos admin = getAdministradorNodos();
        admin.Star(1, 3);
        double [][] poblacion = admin.getPoblacion();
        
        Muestra muestra= new Muestra(poblacion);
        Evaluacion e = new Evaluacion();
        
        double[][] m = muestra.muestra(4);
        
        double [][] ev = e.evaluarMuestra(m);
        
        for (int i = 0; i < m.length; i++) {
            String Linea="";
            for (int j = 0; j < m[i].length; j++) {
               Linea = Linea + m[i][j]+" ";
            }
            System.out.println(Linea);
        }
        System.out.println("******************************");
        for (int i = 0; i < ev.length; i++) {
            String Linea="";
            for (int j = 0; j < ev[i].length; j++) {
               Linea = Linea+ev[i][j]+" ";
            }
            System.out.println(Linea);
        }
    }
    
}
