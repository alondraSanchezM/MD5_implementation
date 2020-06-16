
//Alondra Sánchez Molina

public class MD5 {
    long A= 0x01234567;
    long B= 0x89abcdef;
    long C= 0xfedcba98;
    long D= 0x76543210;
    
    long[] T =  new long[64];
    
    private void initT(){
        for (int i = 0; i < 64; i++)             //Math.sin recibe en radianes
            T[i] = (long) (((double)4294967296L) * (Math.abs(Math.sin(i+1)))); 
    }
    
    private long F(long X,long Y,long Z){
        return  (X & Y) | ((~X) & Z);
    }
    
    private long G(long X,long Y,long Z) {
        return (X & Z) | (Y & (~Z));
    }
    
    private long H(long X,long Y,long Z){
            return X ^ Y ^ Z;                
    }
    
    private long I(long X,long Y,long Z){
        return Y ^ (X | (~Z)); 
    }
    
    private String mPrima(String m){
        String bin, tamBinM;
        String mBinario="";
        String relleno="";
        int valorAscii, tamBinOrigm, tamPrim;
        char[] arrayM = m.toCharArray();
        
        for(int i=0; i<m.length(); i++){
        	valorAscii = (int)arrayM[i];
        	bin=Integer.toBinaryString(valorAscii);
        	if(bin.length()!=8){
                    do{
                        bin="0"+bin;
                    }while(bin.length()!=8);
                mBinario = mBinario+bin;
        	}
        }
        tamBinOrigm = mBinario.length();
        
        //La contraseña no será mayor de 64bytes, podemos usar el modulo como resta
        if ((tamBinOrigm+64) % 512 == 0) {
            tamBinM = Integer.toBinaryString(tamBinOrigm+64);   //No necesita relleno
            if(tamBinM.length()!=64){
                do{
                    tamBinM="0"+tamBinM;
                }while(tamBinM.length()!=64);
            }
        }
        else{
            int residuo = (tamBinOrigm+64) % 512;
            relleno += 1;
            for (int i = residuo; i < 512-1; i++) 
                relleno += "0";
            tamPrim = tamBinOrigm + relleno.length() + 64;
            tamBinM =Integer.toBinaryString(tamPrim); //Mensaje + relleno + 64
            if(tamBinM.length()!=64){
                    do{
                        tamBinM="0"+tamBinM;
                    }while(tamBinM.length()!=64);
            }
        }
        
        mBinario = mBinario+relleno+tamBinM.substring(32)+tamBinM.substring(0,32); //Agrega tamaño, primer B luego A
        
        return mBinario;
    }
    
    private String[][] bloques(String m){
        String bloqueMP = mPrima(m);
        String[] bloques512 = new String[(bloqueMP.length()/512)]; 
        String[][] bloques16= new String[(bloqueMP.length()/512)][512/16];
        int index = 0;
        
        for (int i = 0; i < bloqueMP.length() ; i+=512) {   //de 512
            if((i+512)<bloqueMP.length())
                bloques512[index]=bloqueMP.substring(i, i+512);
            else
                bloques512[index]=bloqueMP.substring(i);
            index++;
        }
        
        for (int i = 0; i <bloques512.length ; i++) {       //de 16
            index = 0;
            for(int j = 0; j < 32 ; j++) {
                if((index+16) < bloques512[i].length())
                    bloques16[i][j]=bloques512[i].substring(index, index+16);
                else
                    bloques16[i][j]=bloques512[i].substring(index);
                index+=16;
            }
        }
        return bloques16;
    }
    
    private long etapa1(long a, long b, long c, long d, int k, int s, int i){
        return  b + ((a + F(b,c,d) + k + T[i]) << s);
    }
    
    private long etapa2(long a, long b, long c, long d, int k, int s, int i){
        return  b + ((a + G(b,c,d) + k + T[i]) << s);
    }
    
    private long etapa3(long a, long b, long c, long d, int k, int s, int i){
        return  b + ((a + H(b,c,d) + k + T[i]) << s);
    }
    
    private long etapa4(long a, long b, long c, long d, int k, int s, int i){
        return  b + ((a + I(b,c,d) + k + T[i]) << s);
    }
    
    private void controlIterativo(String m){
        String[][] bloques = bloques(m).clone();
        long AA, BB, CC, DD;
       
        AA = BB = CC = DD = 0;
       
        for (int i = 0; i <bloques.length ; i++) {
            for(int j = 0; j <bloques[i].length ; j++) {
                AA = A;
                BB = B;
                CC = C;
                DD = D;
                
                A=etapa1(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(0))), 7, 0);
                D=etapa1(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(1))), 12, 1);
                C=etapa1(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(2))), 17, 2);
                B=etapa1(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(3))), 22, 3);
                
                A=etapa1(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(4))), 7, 4);
                D=etapa1(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(5))), 12, 5);
                C=etapa1(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(6))), 17, 6);
                B=etapa1(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(7))), 22, 7);
                
                A=etapa1(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(8))), 7, 8);
                D=etapa1(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(9))), 12, 9);
                C=etapa1(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(10))), 17, 10);
                B=etapa1(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(11))), 22, 11);
                
                A=etapa1(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(12))), 7, 12);
                D=etapa1(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(13))), 12, 12);
                C=etapa1(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(14))), 17, 14);
                B=etapa1(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(15))), 22, 15);
                
                
                
                A=etapa2(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(1))), 5, 16);
                D=etapa2(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(6))), 9, 17);
                C=etapa2(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(11))), 14, 18);
                B=etapa2(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(0))), 20, 19);
                
                A=etapa2(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(5))), 5, 20);
                D=etapa2(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(10))), 9, 21);
                C=etapa2(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(15))), 14, 22);
                B=etapa2(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(4))), 20, 23);
                
                A=etapa2(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(9))), 5, 24);
                D=etapa2(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(14))), 9, 25);
                C=etapa2(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(3))), 14, 26);
                B=etapa2(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(8))), 20, 27);
                
                A=etapa2(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(13))), 5, 28);
                D=etapa2(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(2))), 9, 29);
                C=etapa2(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(7))), 14, 30);
                B=etapa2(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(12))), 20, 31);
                
               
                
                A=etapa3(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(5))), 4, 32);
                D=etapa3(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(8))), 11, 33);
                C=etapa3(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(11))), 16, 34);
                B=etapa3(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(14))), 23, 35);
                
                A=etapa3(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(1))), 4, 36);
                D=etapa3(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(4))), 11, 37);
                C=etapa3(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(7))), 16, 38);
                B=etapa3(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(10))), 23, 39);
                
                A=etapa3(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(13))), 4, 40);
                D=etapa3(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(0))), 11, 41);
                C=etapa3(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(3))), 16, 42);
                B=etapa3(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(6))), 23, 43);
                
                A=etapa3(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(9))), 4, 44);
                D=etapa3(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(12))), 11, 45);
                C=etapa3(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(15))), 16, 46);
                B=etapa3(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(2))), 23, 47);
                
                
                
                A=etapa4(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(0))), 6, 48);
                D=etapa4(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(7))), 10, 49);
                C=etapa4(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(14))), 15, 50);
                B=etapa4(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(5))), 21, 51);
                
                A=etapa4(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(12))), 6, 52);
                D=etapa4(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(3))), 10, 53);
                C=etapa4(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(10))), 15, 54);
                B=etapa4(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(1))), 21, 55);
                
                A=etapa4(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(8))), 6, 56);
                D=etapa4(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(15))), 10, 57);
                C=etapa4(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(6))), 15, 58);
                B=etapa4(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(13))), 21, 59);
                
                A=etapa4(A, B, C, D, Integer.valueOf(String.valueOf(bloques[i][j].charAt(4))), 6, 60);
                D=etapa4(D, A, B, C, Integer.valueOf(String.valueOf(bloques[i][j].charAt(11))), 10, 61);
                C=etapa4(C, D, A, B, Integer.valueOf(String.valueOf(bloques[i][j].charAt(2))), 15, 62);
                B=etapa4(B, C, D, A, Integer.valueOf(String.valueOf(bloques[i][j].charAt(9))), 21, 63);
                
                A = A + AA;
                B = B + BB;
                C = C + CC;
                D = D + DD;
            }
        }
    }
    
    private String invertirCadena(String v){
        String cad = "";
        for (int i=v.length()-1;i>=0;i--)
            cad = cad + v.charAt(i);
        
        return cad;
    }
    
    public String construccionHuella(String m){
        initT();
        controlIterativo(m);
        
        String huellaDigital="";
        String binA, binB, binC, binD, bAP, bBP, bCP, bDP;

        binA = Long.toBinaryString(A);
        binB = Long.toBinaryString(B);
        binC = Long.toBinaryString(C);
        binD = Long.toBinaryString(D);
        
        bAP = invertirCadena(binA.substring(binA.length()-32));
        bBP = invertirCadena(binB.substring(binB.length()-32));
        bCP = invertirCadena(binC.substring(binC.length()-32));
        bDP = invertirCadena(binD.substring(binD.length()-32));
        
        //Parte 5 se agregan a la huella los 32 bits menos significativos de cada uno invertidos
        huellaDigital = huellaDigital.concat(bDP).concat(bCP).concat(bBP).concat(bAP);
        
        return huellaDigital;
    }
    
}
