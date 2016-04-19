package AlgotimoGenetico;

/**
 *
 * @author erley
 */
public class Evaluacion {
    
    double [][] generacion;

    public double [][] getDatosevaluarMuestra(double[][] generation){
        
        this.generacion = generation;
        double[][] MEvaluada = new double [generation.length][2];
        double suma=0;
        for (int i = 0; i < generation.length; i++) {
            double [] individuo = new double [7];
            for (int j = 0; j < generation[i].length; j++) {
               individuo[j]= generation[i][j];
            }
            MEvaluada[i][0]= funcionEvaluadora(individuo);
            suma += MEvaluada[i][0];
        }
        
        for (int i = 0; i < MEvaluada.length; i++) {
            double valor = 0;
            MEvaluada[i][1] = (MEvaluada[i][0]/suma)*100;
        }
        
        return MEvaluada;
    }
    
    public double funcionEvaluadora(double [] individuo){
        
        double x=0;
        
        for (int i = 0; i < individuo.length; i++) {
            x = x + individuo[i];
        }
        
        return x;
    }
    
    public double [][] getGeneracionOrdenada(){
        double [] vector = new double[this.generacion.length];
         int [] indices = new int[this.generacion.length];
        double [] ordenado ;
         double [] desOrdenado;
        double [][] generacionOrdenada = new double [this.generacion.length][this.generacion[0].length];
        
        for (int i = 0; i < this.generacion.length; i++) {
            double [] individuo = new double [7]; 
            for (int j = 0; j < this.generacion[i].length; j++) {
               individuo[j]= this.generacion[i][j];
            }
         vector[i]=this.funcionEvaluadora(individuo);
            
        }
        desOrdenado = vector.clone();
        ordenado = this.burbuja(vector).clone();
        
        
        for (int i = 0; i < ordenado.length; i++) {
            for (int j = 0; j < desOrdenado.length; j++) {
                if(ordenado[i]==desOrdenado[j]){
                    indices[i]=j;
                }
            }
        }
        
        for (int i = 0; i < this.generacion.length; i++) {
            for (int j = 0; j < this.generacion[i].length; j++) {
                generacionOrdenada[i][j]=this.generacion[indices[i]][j];
            }
        }
        
        
        return generacionOrdenada;
    }
    
    private double [] burbuja(double[] arreglo){
       for(int i = 0; i < arreglo.length - 1; i++)
        {
            for(int j = 0; j < arreglo.length - 1; j++)
            {
                if (arreglo[j] > arreglo[j + 1])
                {
                    double tmp = arreglo[j+1];
                    arreglo[j+1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
       
       return arreglo;
    }
}
