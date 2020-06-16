
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


//Alondra Sánchez Molina

public class ServidorMain {
    public static void main(String[] args) throws IOException {
        int PUERTO = 1234; //Puerto para la conexión
        ServerSocket ss; //Socket del servidor
        Socket sc; //Socket del cliente
        try{
            ss = new ServerSocket(PUERTO);
            System.out.println("-----SERVIDOR-----");
            System.out.println("Esperando conexión...");
            
            while(true){
                sc = ss.accept();
                System.out.println("Conexion aceptada");
                new GestorPeticion(sc).start();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class GestorPeticion extends Thread{
    PrintWriter salida;
    BufferedReader entrada;
    String mensajeRecibido, MA, opc, ID;
    Socket sc;
    
    public GestorPeticion(Socket sc) {
        this.sc = sc;
    }
    
    public void run(){
        try {
            Servidor s = new Servidor();
            s.readDataBase();
            salida = new PrintWriter( new OutputStreamWriter(sc.getOutputStream() ),true );
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            
            opc = s.desencriptar(entrada.readLine());
            if(Integer.valueOf(opc) == 1){
                ID = entrada.readLine();                                            //ID
                System.out.println("Usuario recibido: "+s.desencriptar(ID));

                MA = s.buscarUser(s.desencriptar(ID),1);                            //MA
                salida.println(s.encriptar(MA));          
                if(!"ID no encontrado".equals(MA)){
                    mensajeRecibido = entrada.readLine();                            //MD5
                    
                    String resValidacion = s.compararHuellaDigital(s.desencriptar(mensajeRecibido),s.Mezcla(MA,s.getPass()));
                    salida.println(s.encriptar(resValidacion));                      //ResultadoValidación
                }
            }
            else{
                ID = s.desencriptar(entrada.readLine());
                String pass = s.desencriptar(entrada.readLine());
                
                if("ID no encontrado".equals(s.buscarUser(ID,0))){  
                    s.writeDataBase("\n" + ID + "," + pass);
                    salida.println(s.encriptar("Registro con exito")); 
                }
                else
                    salida.println(s.encriptar("Usuario ya utilizado")); 
            }
            
            salida.close();
            entrada.close();
            sc.close();
            System.out.println("Conexión finalizada");
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
    }

}