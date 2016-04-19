package Nodos;

import java.util.ArrayList;

/**
 *
 * @author erley
 */
public class AdministradorNodos {
    public static AdministradorNodos Admin;
    private Caminos obCaminos;
    private int matriz[][]={{0,1,1,1,1,1,1,1},
                            {1,0,1,1,1,1,1,1},
                            {1,1,0,1,1,1,1,1},
                            {1,1,1,0,1,1,1,1},
                            {1,1,1,1,0,1,1,1},
                            {1,1,1,1,1,0,1,1},
                            {1,1,1,1,1,1,0,1},
                            {1,1,1,1,1,1,1,0},
                            };
    
    private ArrayList<ArrayList<Integer>> totalCaminos;

    private ArrayList<Vertices> ListadoVertices;
    
    

    /**
     * Constructor creamos la instacia de la clase caminos y la guardamos en
     * obCaminos e iniciamos los ArraysLists Respectivos
     */
    private AdministradorNodos() {
        this.obCaminos = new Caminos();
        
        totalCaminos = new ArrayList<ArrayList<Integer>>();

        ListadoVertices = new ArrayList<Vertices>();
        
    }
    
   
    /**
     * Aplicanco el patron Silgenton;
     * @return 
     */
    public static AdministradorNodos getAdministradorNodos(){
        if(Admin==null){
            Admin = new AdministradorNodos();
            return Admin;
        }
       return Admin;     
    }
    
    public void Star(int nodoInicial, int nodoFinal){
       if(this.totalCaminos.isEmpty()&&this.ListadoVertices.isEmpty()){
             this.getRutasPosibles(nodoInicial, nodoFinal);
             this.cargarVariables();
       }
    }
    
    public ArrayList<ArrayList<Integer>> getCaminos(){
        return this.totalCaminos;
    }
    
    public double[][] getPoblacion(){
        double[][] poblacion = new double[this.totalCaminos.size()][7];
        int i=0; // contador sujetos
        for (ArrayList<Integer> ruta  : this.totalCaminos) {
        double distancia=0,tiempo=0, peajes=0, estado_via=0, tipo_via=0, condiciones_via=0, seguridad_via=0;
        int contador = 0; // numero de vertices del sujeto
            
         ArrayList<Vertices> temp = this.getVerticesRuta(ruta);
            for (Vertices temp1 : temp) {
             distancia += (double) temp1.getDistancia()*0.01;
             tiempo += (double) temp1.getTiempo()*0.1;
             peajes += (double) temp1.getPeajes();
             estado_via += temp1.getEstado_via();
             condiciones_via += temp1.getCondiciones_via();
             seguridad_via += temp1.getSeguridad_via();
             contador++;
            }
        // cargamos todas los datos del sujeto en nuestra matriz     
     
            poblacion[i][0]=distancia;
            poblacion[i][1]= tiempo;
            poblacion[i][2]= peajes;
            poblacion[i][3]= this.StandarDecimal((double)estado_via/contador);
            poblacion[i][4]= this.StandarDecimal((double) tipo_via/contador);
            poblacion[i][5]= this.StandarDecimal((double)condiciones_via/contador);
            poblacion[i][6]= this.StandarDecimal((double)seguridad_via/contador);
            
            
            i++;
                    
        }
        
        return poblacion;
    }
    
    /**
     * Medoto que devuelve todos los camninos posibles
     *
     * @param nodoInicial
     * @param nodoFinal
     * @return ArrayList<ArrayList> con caminos
     */
    private void getRutasPosibles(int nodoInicial, int nodoFinal) {
        this.totalCaminos = this.obCaminos.busqueda(nodoInicial - 1, nodoFinal - 1, matriz);

        //return totalCaminos;
    }

    /**
     * Llenado de la lista de vertices entre nodos
     */
    private void cargarVariables() {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                if (this.matriz[i][j] == 1) {
                    Vertices segmento = new Vertices();
                    segmento.setInicio(i);
                    segmento.setFin(j);
                    segmento.setDistancia((int) (Math.random()* 100+50));
                    segmento.setTipo_via((int) (Math.random()* 3+0));
                    segmento.setSeguridad_via((int) (Math.random()* 2+0));
                    segmento.setCondiciones_via((int) (Math.random()* 2+0));
                    segmento.setEstado_via((int) (Math.random()* 3+0));
                    segmento.setTiempo((double) segmento.getDistancia()/this.calcularVelocidad(segmento.getEstado_via(),segmento.getTipo_via(),segmento.getCondiciones_via()));
                    segmento.setPeajes((int) (Math.random()* 2+0));
                    this.ListadoVertices.add(segmento);
                }
            }
        }
    }
    
    /**
     * Funcion que devuelve los vertices de una ruta 
     * @param ruta
     * @return 
     */
    public ArrayList<Vertices> getVerticesRuta(ArrayList<Integer> ruta){
        ArrayList<Vertices> Ruta = new ArrayList<Vertices>();
        
        for (int i = 0; i < ruta.size(); i++) {
            if(i+1 != ruta.size()){
                Ruta.add(this.getVertice(ruta.get(i), ruta.get(i+1)));
            }
        }
        
        return Ruta;
        
    }
    
    /**
     * Funcion que devuelve un vertice especifico
     * @param nodoInicial
     * @param nodoFinal
     * @return Vertice Objeto
     */
    private Vertices getVertice(int nodoInicial, int nodoFinal){
        for (Vertices vertice : this.ListadoVertices) {
            if(vertice.getInicio()==nodoInicial&&vertice.getFin()==nodoFinal){
                return vertice;
            }
        }
        
        return null;
    }
    
    /**
     * Metodo que calcula la velocidad promedio basandose en el estado de la via, el tipo de via, y las condiciones de la via
     * @param estado_via
     * @param tipo_via
     * @param condiciones
     * @param distancia
     * @return  vel km/hora
     */
    private int calcularVelocidad(int estado_via, int tipo_via, int condiciones){
    int vel = 0;
    
    switch (estado_via){
        case 0: 
            switch(tipo_via){
            case 0:
                if(condiciones==0){
                    vel=120;
                }else{
                    vel=80;
                }
            break;
            case 1:
                if(condiciones==0){
                    vel=100;
                }else{
                    vel=70;
                }
            break;
            case 2:
                if(condiciones==0){
                    vel=80;
                }else{
                    vel=60;
                }
            break;
            }
            break;
            
        case 1: 
           switch(tipo_via){
            case 0:
                if(condiciones==0){
                    vel=110;
                }else{
                    vel=75;
                }
            break;
            case 1:
                if(condiciones==0){
                    vel=95;
                }else{
                    vel=65;
                }
            break;
            case 2:
                if(condiciones==0){
                    vel=75;
                }else{
                    vel=60;
                }
            break;
            }
            break;          
        case 2: 
           switch(tipo_via){
            case 0:
                if(condiciones==0){
                    vel=90;
                }else{
                    vel=70;
                }
            break;
            case 1:
                if(condiciones==0){
                    vel=85;
                }else{
                    vel=65;
                }
            break;
            case 2:
                if(condiciones==0){
                    vel=70;
                }else{
                    vel=55;
                }
            break;
            }
            break;            
    }
    
    return vel;
     
    }
    
    
    /**
     * Funcion que estandariza el decimal.. aproxima si se pasa o no de 0.5
     * @param decimal
     * @return double
     */
    private double StandarDecimal(double decimal){
          double temp1 = (double)decimal;
            int temp2 = (int)decimal;
            double temp3 = (double) temp1 -temp2;
            
            if(temp3<0.5){
             return (double)temp2 ;   
            }else if(temp3>0.5){
             return (double) temp2+1;
            }else{
             return(double) temp2+temp3;  
            }
    }
    
}
