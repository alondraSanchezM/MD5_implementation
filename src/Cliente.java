
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

//Alondra Sánchez Molina

public class Cliente{
    final int PUERTO = 1234; //Puerto para la conexión
    final String HOST = "localhost"; //Host para la conexión
    final String clave = "secreto!";    
    
    
    public int mensajes(String ID, String pass, int opc){
        Socket sc;
        PrintWriter salida;
        BufferedReader entrada;
        String mensajeRecibido;
        int flag = 0;
        
        try{
            sc = new Socket(HOST, PUERTO);
            salida = new PrintWriter( new OutputStreamWriter(sc.getOutputStream() ),true );
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            
            if (opc == 1) {
                System.out.println("-----CLIENTE----");
                salida.println(encriptar("1"));  //opc
                
                salida.println(encriptar(ID));                                                  //ID

                mensajeRecibido = entrada.readLine();                                           //Mensage Aleatorio
                mensajeRecibido = desencriptar(mensajeRecibido);
                System.out.println("Mensaje Aleatorio recibido: "+ mensajeRecibido);
                if("ID no encontrado".equals(mensajeRecibido))
                    flag = clean(mensajeRecibido);
                else{
                    salida.println(encriptar(calcularMD5(Mezcla(mensajeRecibido, pass))));      //MD5

                    mensajeRecibido = entrada.readLine();                                       //Resultado Validación
                    flag = clean(desencriptar(mensajeRecibido));
                }
            }
            else{
                salida.println(encriptar("0"));  //opc
                salida.println(encriptar(ID));
                salida.println(encriptar(pass));
                
                flag = clean(desencriptar(entrada.readLine()));
            }
            
          //  sc.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
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
    
    public int clean(String mensajeRecibido){
        JOptionPane.showMessageDialog(null,mensajeRecibido);
        return 1;
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
                num = (int)pasAux[j];         //marca  error por que el tamaño de la cadena ya termino, por lo tanto no puede crear el numero 
            
            mas=(num%2==0)?2:1;               //Si  la posición es par se agrega 2, si no se agrega 1
            
            if (num+mas == l && g==0) {
                aux[i] = pasAux[j];           //se agrega caracter del pass
                j++;
                l=0;
            }
            else{   
                aux[i] = maAux[k];           //se agrega caracter del ma
                k++;
            }
            l++;
        }
        
        mAux = new String(aux);
        tam = mAux.length();
        des = tam;
        
        //Se reordena la cadena, uno al principio, uno al final
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
    
    public String calcularMD5(String m){
        String huellaDigital;
        MD5 md5 =  new MD5();
        
        System.out.println("Mezcla del cliente: "+ m);
        huellaDigital = md5.construccionHuella(m);
        System.out.println("Huella del cliente: "+ huellaDigital);
        
        return huellaDigital;
    }
    
}