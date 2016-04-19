package AlgotimoGenetico;

/**
 *
 * @author erley
 */
public class Evaluacion {
    
    public double [][] evaluarMuestra(double[][] generation){
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
}
