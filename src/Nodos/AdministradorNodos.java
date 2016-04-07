package Nodos;

import java.util.ArrayList;

/**
 *
 * @author erley
 */
public class AdministradorNodos {

    private Caminos obCaminos;
    private int matriz[][]={{0, 1, 1, 1, 0},
                            {0, 1, 1, 1, 0},
                            {0, 1, 1, 1, 0},
                            {0, 1, 1, 1, 0},
                            {0, 1, 1, 1, 0},};
    
    private ArrayList<ArrayList<Integer>> totalCaminos;

    private ArrayList<Vertices> ListadoVertices;

    /**
     * Constructor creamos la instacia de la clase caminos y la guardamos en
     * obCaminos
     */
    public AdministradorNodos() {
        this.obCaminos = new Caminos();
    }

    /**
     * Medoto que devuelve todos los camninos posibles
     *
     * @param nodoInicial
     * @param nodoFinal
     * @return ArrayList<ArrayList> con caminos
     */
    public ArrayList<ArrayList<Integer>> getRutasPosibles(int nodoInicial, int nodoFinal) {
        this.totalCaminos = this.obCaminos.busqueda(nodoInicial - 1, nodoFinal - 1, matriz);

        return totalCaminos;
    }

    /**
     * Llenado de la lista de vertices entre nodos
     */
    private void cargarVariables() {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                if (this.matriz[i][j] == 1) {
                    Vertices segmento = new Vertices();
                    segmento.inicio = i;
                    segmento.fin = j;
                    this.ListadoVertices.add(segmento);
                }
            }
        }
    }

    public ArrayList<Vertices> getVariablesRuta(ArrayList<Integer> ruta){
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
            if(vertice.inicio==nodoInicial&&vertice.fin==nodoFinal){
                return vertice;
            }
        }
        
        return null;
    }
    
}
