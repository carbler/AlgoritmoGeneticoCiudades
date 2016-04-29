/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgotimoGenetico;

/**
 *
 * @author Adrian
 */
public class Convertidor {
    
    public static void main(String[] args) {
        Convertidor aplication = new Convertidor();
        double txt;
        String binarionormal="" ;
        String binariotruncado="" ;
        double deci = 1.0;
        int cont = 0;
        /*Binario para el rango decimal de 0 - 2*/
        Object[] binario = aplication.DecDoubleToBin(deci);
        //Distancia y Tiempo
        Object[] binariolargo = aplication.DecDoubleToBinlargo(deci, 10);
        System.out.println("Numero entero: "+deci+"\n"+"Binario: ");
        for(int i=0; i<binario.length;i++){
            binarionormal = binarionormal+binario[i].toString();
        }
        System.out.println(binarionormal+"\n"+"binariorecortado: ");
        for(int i=0; i<binariolargo.length;i++){
            binariotruncado = binariotruncado+binariolargo[i].toString();
        }
        System.out.println(binariotruncado);
        
        Object[] b = aplication.convertEnteroToBinary(1.0,4);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
        }
    }
    
    private static String invert(String str){
        String Ax="";
        for(int k=str.length()-1;k>=0;k--){
            Ax=Ax+str.charAt(k);
        }
        str=Ax;
        return str;

    }
    
    public Object[] DecDoubleToBin(double real){
        double real2=real;
        String s="";
        String signo="";
        int b;
        if(real<0.0){
            real=real*-1;
            signo="-";
        }
        
        int pe=(int)real;
        if(real<pe){
            pe=pe-1;
        }
        
        double pf;
        pf = (real-pe);
        if(pe>0){      
            while(pe>0){
            b=pe%2;
            pe=pe/2;
            s=s+b;
            }
        }else{s=s+""+0;}
        s=signo+Convertidor.invert(s);
        
        if(pf==0){s=s+".0";}else{
            s=s+".";
        while(pf>0 && pf<1.0){
            pf=pf*2;
            String sd=""+pf;
            if(pf>=1.0){
                pf=pf-1.0;
                s=s+"1";
            }else{
                s=s+"0";
            }
            
        }
        }
        String s2=""+s.charAt(s.length()-1);
        if(s2.equals(".")){
            s=s+"0";
        }
        if(Double.valueOf(s) >= 0.0 && Double.valueOf(s) < 2.0){
            s = "0" + s;
        }
        
        Object[] binario = new Object[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            binario[i] = s.charAt(i);
        }
        
        return binario;
    }
    
    public Object[] DecDoubleToBinlargo(double real, int numbinary){
        double real2=real;
        String s="";
        String signo="";
        int b;
        if(real<0.0){
            real=real*-1;
            signo="-";
        }
        
        int pe=(int)real;
        if(real<pe){
            pe=pe-1;
        }
        
        double pf;
        pf = (real-pe);
        if(pe>0){      
            while(pe>0){
            b=pe%2;
            pe=pe/2;
            s=s+b;
            }
        }else{s=s+""+0;}
        s=signo+Convertidor.invert(s);
        
        if(pf==0){s=s+".0";}else{
            s=s+".";
        while(pf>0 && pf<1.0){
            pf=pf*2;
            String sd=""+pf;
            if(pf>=1.0){
                pf=pf-1.0;
                s=s+"1";
            }else{
                s=s+"0";
            }
            
        }
        }
        String s2=""+s.charAt(s.length()-1);
        if(s2.equals(".")){
            s=s+"0";
        }
        if(Double.valueOf(s) >= 0.0 && Double.valueOf(s) < 2.0){
            s = "0" + s;
            
        }
        //String string = "004-034556";
        //String[] parts = string.split("-");
        String[] partdecimal_entera = s.split("\\.");
        String partEntera = partdecimal_entera[0];
        String partDecimal = partdecimal_entera[1];
        String sumceroentero = "";
        String sumcerodecimal = "";
        String decimaltemporal = "";
        String resultado;
        //Object[] numBinaryentero = Object[]
        if(partEntera.length()<numbinary){
            int p = numbinary-partEntera.length();
            for(int i = 1; i <= p; i++){
                sumceroentero = "0" + sumceroentero;
            }
            partEntera = sumceroentero + partEntera;
        }
        
        if(partDecimal.length() > 10){
            
            for(int i = 0; i <=9; i++){
                sumcerodecimal = sumcerodecimal + partDecimal.charAt(i);
            }
        }else if(partDecimal.length() < 10){
            
            for(int i = 0; i < 10 - partDecimal.length() ; i++){
                sumcerodecimal += "0";
            }
            
            for(int i = 0; i < partDecimal.length(); i++){
                decimaltemporal = decimaltemporal + partDecimal.charAt(i);
            }
            
          sumcerodecimal = sumcerodecimal + decimaltemporal;
        }
        
        resultado = partEntera + "." + sumcerodecimal;
        
        Object[] binario = new Object[resultado.length()];
        
        for(int i = 0; i < resultado.length(); i++){
            binario[i] = resultado.charAt(i);
        }
        
        
        return binario;
    }
    
    private String invertB(String str){
        String Ax="";
        for(int k=str.length()-1;k>=0;k--){
            Ax=Ax+str.charAt(k);
        }
        str=Ax;
        return str;

    }
    
    public double BinToDecDouble(Object[] binary){
        String bin = "";
        for(int i = 0; i < binary.length; i++){
            bin = bin + binary[i];    
        }
        
        if(bin.equals("")) return Double.NaN;
        int signo=1;
        double dec=0;
        double fr=0;
        String dec2="";
        int pos=0;
        int b;
     
        if(bin.contains(".")){
            dec2=bin.substring(bin.lastIndexOf(".")+1,bin.length());
            bin=bin.substring(0,bin.lastIndexOf("."));
            int m=0;
            while(pos<dec2.length()){
                double n=Integer.parseInt(""+dec2.charAt(pos));
                if(n==1){
                    fr=fr+Math.pow(2,-(pos+1) );
                }
                pos++;
            }
            pos=0;
        }else{
            bin=bin+".0";
            return BinToDecDouble(binary);
        }
        String bs=""+bin.charAt(0);
        if(bs.equals("-")){
            bin=bin.substring(1,bin.length());
            signo=-1;
        }
        bin=Convertidor.invert(bin);
        while(pos<bin.length()){
            b=Integer.parseInt(""+bin.charAt(pos));
            if(b==1){
            dec=(dec+Math.pow(2, pos));
            }
            pos++;
        }
        return (dec+fr)*signo;
    }
    
    public Object[] convertEnteroToBinary(double real, int numbinary){
        double real2=real;
        String s="";
        String signo="";
        int b;
        if(real<0.0){
            real=real*-1;
            signo="-";
        }
        
        int pe=(int)real;
        if(real<pe){
            pe=pe-1;
        }
        
        double pf;
        pf = (real-pe);
        if(pe>0){      
            while(pe>0){
            b=pe%2;
            pe=pe/2;
            s=s+b;
            }
        }else{s=s+""+0;}
        s=signo+Convertidor.invert(s);
        
        if(pf==0){s=s+".0";}else{
            s=s+".";
        while(pf>0 && pf<1.0){
            pf=pf*2;
            String sd=""+pf;
            if(pf>=1.0){
                pf=pf-1.0;
                s=s+"1";
            }else{
                s=s+"0";
            }
            
        }
        }
        String s2=""+s.charAt(s.length()-1);
        if(s2.equals(".")){
            s=s+"0";
        }
        
        if(Double.valueOf(s) >= 0.0 && Double.valueOf(s) < 2.0){
            s = "0" + s;
            
        }
        
        String[] partdecimal_entera = s.split("\\.");
        String partEntera = partdecimal_entera[0];
        String partDecimal = partdecimal_entera[1];
        String sumceroentero = "";
        String sumcerodecimal = "";
        String decimaltemporal = "";
        String resultado;
  
        if(partEntera.length()<numbinary){
            int p = numbinary-partEntera.length();
            for(int i = 1; i <= p; i++){
                sumceroentero = "0" + sumceroentero;
            }
            partEntera = sumceroentero + partEntera;
        }
        
        
        //resultado = partEntera + ".0";
        
        Object[] binario = new Object[partEntera.length()];
        
        for(int i = 0; i < partEntera.length(); i++){
            binario[i] = partEntera.charAt(i);
        }
        
        
        return binario;
    }
}
