
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

//Alondra Sánchez Molina
public class Servidor {

    private final String clave = "secreto!";
    private final ArrayList<DataBase> datos = new ArrayList();;
    private String passSelect;

    //-------------------------------------------------EncriptadoAES
    private SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
         
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
         
        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
         
        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");
 
        return secretKey;
    }
    
    public String encriptar(String datos) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = this.crearClave(clave);
        
        //-----Encriptar
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
 
        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
 
        return encriptado;
    }
    
    public String desencriptar(String datosEncriptados) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = this.crearClave(clave);

        //----Desencriptar
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datosRecuperados = new String(datosDesencriptados);

        return datosRecuperados;
    }
    //-------------------------------------------------

    public void readDataBase() throws FileNotFoundException, IOException {
        String cadena;
        BufferedReader bf = new BufferedReader(new FileReader("dataBase.txt"));
        while ((cadena = bf.readLine()) != null) { 
            String[] split = cadena.split(",");
            DataBase d = new DataBase(split[0], split[1]);
            datos.add(d);
        }
        bf.close();
    }
    
    public void writeDataBase (String cadena) throws IOException{
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        File file = new File("dataBase.txt");
        
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        
        bw.write(cadena);
        
        bw.close();
        fw.close();
    }
    
    public String buscarUser(String user, int opc){
        for (DataBase dato : datos)
            if(user.equals(dato.getUser()))
                if (opc == 1) {
                    passSelect =  dato.getPassword();
                    return generarMensajeAleatorio();
                }else
                    return "ID encontrado";
        return "ID no encontrado";
    }
    
    public String generarMensajeAleatorio(){
        String mensajeAleatorio = "";
        int in = 32;  //Usando la tabla Ascii
        int f = 126;
        
        for (int i = 0; i < 4096; i++) {
            int numAleatorio = (int)Math.floor(Math.random()*(f-in)+in);
            char c = (char)numAleatorio;
            mensajeAleatorio+=c;
        }
        
        return mensajeAleatorio;
    }
    
    public String getPass() {
        return passSelect;
    }
    
    public String Mezcla(String MA, String Pass){
        String mAux;
        String m="";
        char aux[];
        char maAux[] = MA.toCharArray();
        char pasAux[] = Pass.toCharArray();
        int tam, des, mas, num=0;
        int f=0, g=0;   //banderas
        int j,k,l;      //indices
        
        j = k = l = 0;
        aux = new char[MA.length()+Pass.length()];
        
        for (int i = 0; i < aux.length; i++) {
            if(j==pasAux.length)
                g=1;
            else
                num = (int)pasAux[j];        //marca  error por que el tamaño de la cadena ya termino, por lo tanto no puede crear el numero 
            
            mas=(num%2==0)?2:1;              //Si  la posición es par se agrega 2, si no se agrega 1
            
            if (num+mas == l && g==0) {
                aux[i] = pasAux[j];          //se agrega caracter del pass
                j++;
                l=0;
            }
            else{   
                aux[i] = maAux[k];          //se agrega caracter del ma
                k++;
            }
            
            l++;
        }
        
        mAux = new String(aux);
        tam = mAux.length();
        des = tam;
        
        if(tam%2 == 0){  
            f=1;
            for (int i = 0; i <= (tam/2)-1; i++) 
                m = m.concat(String.valueOf(mAux.charAt(i)).concat(String.valueOf(mAux.charAt(--des)))); 
        }
        else
            for (int i = 0; i <= (tam/2); i++) 
                if(i==(tam/2))
                    m = m.concat(String.valueOf(mAux.charAt(i))); 
                else
                    m = m.concat(String.valueOf(mAux.charAt(i)).concat(String.valueOf(mAux.charAt(--des)))); 
            
        return m;
    }
    
    public String compararHuellaDigital(String huellaCliente, String m){
        String huellaServidor, flag;
        MD5 md5 =  new MD5();
        
        System.out.println("Mezcla del servidor: "+ m);
        huellaServidor = md5.construccionHuella(m);
        
        System.out.println("Huella del servidor: "+ huellaServidor);
        System.out.println("Huella del cliente recibida: "+ huellaCliente);
        
        if(huellaCliente.equals(huellaServidor)){
            flag = "Bienvenido al sistema";
        }else{
            flag = "Contraseña incorrecta";
        }
        
        return flag;
    }
   
}
