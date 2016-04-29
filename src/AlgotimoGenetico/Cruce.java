/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgotimoGenetico;

import Nodos.AdministradorNodos;
import static Nodos.AdministradorNodos.getAdministradorNodos;
import java.util.ArrayList;

/**
 *
 * @author erley
 */
public class Cruce {
    Convertidor conver = new Convertidor();
    ArrayList<Object[]> IndividuoArmado = new ArrayList<>();
    int longitud = 0;
    ArrayList<Object> puntos = new ArrayList<>();
    
    public int numeroBits(int x){
       int num = (int) ((Math.log(x))/(Math.log(2)));
       return num+1;
    }
    
    private void adicionarPunto(Object[] individuo)
    {
        Object[] nuevoIndividuo = new Object[individuo.length + puntos.size()];
        int cont = 0, cont2 = 0;
        for (int i = 0; i < nuevoIndividuo.length; i++) {
            if(i == (Integer)puntos.get(cont)){
                nuevoIndividuo[i] = ".";
                if(cont <= 4){
                    cont++;
                }
            }
            else{
                nuevoIndividuo[i] = individuo[cont2];
                cont2++;
            }
        }
        for (int i = 0; i < nuevoIndividuo.length; i++) {
            System.out.print(nuevoIndividuo[i]);
        }
        System.out.println(" ");
    }
    
    /*Organiza la nueva generación en un array de Object*/
    private Object[][] organizarNuevaGeneracion(ArrayList<Object[]> newGeneracion)
    {
        Object[][] nuevaGeneracion = new Object[newGeneracion.size()][longitud];
        int cont = 0;
        for (Object[] individuo : newGeneracion) {
            for (int i = 0; i < individuo.length; i++) {
                nuevaGeneracion[cont][i] = individuo[i];
            }
            cont++;
        }
        return nuevaGeneracion;
    }
    
    public void recombinacion(double [][] muestra)
    {
        
        int tamañoMuestra = muestra.length;
        ArrayList<Object[]> newGeneracion = new ArrayList<>();
        this.ArmarIndividuo(muestra);
        int cont_madre = 0, cont = 0;
        for (Object[] Padre : IndividuoArmado) {
            for (Object[] Madre : IndividuoArmado) {
                if(cont_madre <= 7)
                {
                    newGeneracion.add(Cruzar(Padre, Madre));
                    cont_madre++;
                    cont++;
                    if(cont_madre == 7)
                    {
                        cont_madre = 0;
                    }
                }
                if(cont == 10)
                {
                    cont_madre = 8;
                }
            }
        }
        for (Object[] individuo : newGeneracion) {
            adicionarPunto(individuo);
        }
        //Object[][] nuevaGeneracion = organizarNuevaGeneracion(newGeneracion);
        /*for (int i = 0; i < nuevaGeneracion.length; i++) {
            for (int j = 0; j < nuevaGeneracion[i].length; j++) {
                System.out.print(nuevaGeneracion[i][j]);
            }
            System.out.println(" ");
        }*/
        //return nuevaGeneracion;
    }
    
    public Object[] Cruzar(Object [] padre, Object [] madre)
    {
        longitud = padre.length;
        Object[] hijo = new Object [longitud];
        int contador = 0;
        boolean bandera = true;
        for(int i = 0; i < longitud; i++)
        {
            if(bandera == true){
                hijo[i] = padre[i];
                contador++;
                if(contador == 2){
                    bandera = false;
                    contador = 0;
                }
            }
            else{
                hijo[i] = madre[i];
                contador++;
                if(contador == 2)
                {
                    bandera = true;
                    contador = 0;
                }
            }
        } 
        return hijo;
    }
    
    public void ArmarIndividuo(double [][] muestra)
    {
        ArrayList<ArrayList<Object[]>> IndividuosBinario = new ArrayList<>();
        ArrayList<Object[]> individuos = new ArrayList<>();
        
        ArrayList<ArrayList<Object[]>> ListAuxiliarIndividuos = new ArrayList<>();
        ArrayList<Object[]> auxiliarIndividuos = new ArrayList<>();
        
        /*Con este for recorro cada dato y lo convierto en binario y lo almaceno en el ArrayList*/
        for (int i = 0; i < muestra.length; i++) {
            individuos.clear();
            for (int j = 0; j < muestra[i].length; j++) {
                double decimal = muestra[i][j];
                if(j == 0 || j == 1)
                {
                    individuos.add(conver.DecDoubleToBinlargo(decimal, 10));
                }
                else if(j == 2)
                {
                    individuos.add(conver.convertEnteroToBinary(decimal, 4));
                }
                else
                {
                    individuos.add(conver.DecDoubleToBin(decimal));
                }
            }
            IndividuosBinario.add((ArrayList<Object[]>) individuos.clone());
        }
        
        /*Con este For Elimino los puntos para poder Armar el individuo completo y hacer la recombinacion*/
        int cont = 0, cont_puntos = 1;
        for (ArrayList<Object[]> SujetoBinario  : IndividuosBinario)
        {
            auxiliarIndividuos.clear();
            int cont_peaje = 0;
            for (Object[] caracteristica: SujetoBinario)
            {
                if(cont_peaje == 2){
                    auxiliarIndividuos.add(caracteristica.clone());
                }
                cont_peaje++;
                for (int i = 0; i < caracteristica.length; i++)
                {
                    if(caracteristica[i].toString().equals(".")){
                        Object [] aux2 = this.eliminarPunto(caracteristica, i);
                        auxiliarIndividuos.add(aux2.clone());
                        if(cont_puntos <= 6)
                        {
                            puntos.add(cont);
                            cont_puntos++;
                        }
                    }
                    cont++;
                }
            }
            ListAuxiliarIndividuos.add((ArrayList<Object[]>) auxiliarIndividuos.clone());
        }
        
        int longitud = LongitudIndividuo(ListAuxiliarIndividuos.get(0));
        for (ArrayList<Object[]> Listado : ListAuxiliarIndividuos) {
            IndividuoArmado.add(this.Armar(Listado, longitud).clone());
        }
    }
    
    private Object [] eliminarPunto(Object[] individuo, int pos)
    {
        if(pos == individuo.length - 2)
        {
            individuo[pos] = individuo[pos + 1];
        }
        else
        {
            for (int j = pos + 1; j < individuo.length; j++) {
                individuo[j-1] = individuo[j];
            }
        }
        Object [] auxiliar = new Object[individuo.length - 1];
        for (int k = 0; k < auxiliar.length; k++) {
            auxiliar[k] = individuo[k];
        }
        individuo = new Object[auxiliar.length - 1];
        individuo = auxiliar;
        return individuo;
    }
    
    private Object[] Armar(ArrayList<Object[]> Individuo, int longitud)
    {
        Object [] IndividuoArmado = new Object[longitud];
        int contador = 0;
        for (Object[] list : Individuo) {
            for (int i = 0; i < list.length; i++) {
                IndividuoArmado[contador] = list[i];
                contador++;
            }
        }
        return IndividuoArmado;
    }
    
    private int LongitudIndividuo(ArrayList<Object []> individuo)
    {
        int longitud = 0;
        for (Object[] indi : individuo) {
            for (Object dato : indi) {
                longitud = longitud + 1;
            }
        }
        return longitud;
    }
        
    public static void main(String[] args) {
        AdministradorNodos admin = getAdministradorNodos();
        admin.Star(1, 3);
        double [][] poblacion = admin.getPoblacion();
        Muestra muestra= new Muestra(poblacion);
        double[][] m = muestra.muestra(10);
        Cruce cru = new Cruce();
        cru.recombinacion(m);
    }
}
