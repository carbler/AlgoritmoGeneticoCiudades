
package AlgotimoGenetico;

/**
 *
 * @author erley
 */
public class Muestra {
    double[][] muestra;
    double [][] poblacion;
    int [] vector;
    public Muestra(double[][] poblacion){
        this.poblacion= poblacion;
        
    }

    
    public double[][] muestra(int cantidad){
        this.vector = new int[cantidad];// vector que guardan los indices para que no se repitan los sujetos
                
        this.muestra = new double [cantidad][7];
        for (int i = 0; i < cantidad; i++) {
            int x= (int) (Math.random()*this.poblacion.length +0);
            if (!verificar(x)) {
                this.muestra[i][0]=this.poblacion[x][0];
                this.muestra[i][1]=this.poblacion[x][1];
                this.muestra[i][2]=this.poblacion[x][2];
                this.muestra[i][3]=this.poblacion[x][3];
                this.muestra[i][4]=this.poblacion[x][4];
                this.muestra[i][5]=this.poblacion[x][5];
                this.muestra[i][6]=this.poblacion[x][6];
            }else{
                i--;
            }
        }
        
        return this.muestra;
    }
    
    private boolean verificar(int x){
        for (int i = 0; i < vector.length; i++) {
            if(this.vector[i]==x){
                return true;
            }
        }
        
        return false;
    }
    
    
}
