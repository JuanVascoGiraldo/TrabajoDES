
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifrarJava {
    
    public static final String ruta= "C:\\Users\\Juanv\\Desktop\\CifradoDes\\";
    
    public static int Cifrar (String cadena, String clave) throws Exception{
        int sirvio= 0;
        try{
            byte[] encodedKey     = clave.getBytes();
            System.out.println(encodedKey);
            SecretKey subllave= new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
            Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, subllave);
            byte[] buffer = new byte[1000];
            byte[] bufferCifrado;
            FileInputStream in = new FileInputStream(ruta+cadena);
            FileOutputStream outs = new FileOutputStream(ruta + "archivo.txt.cifrado");

            int bytesleidos = in.read(buffer, 0, 1000);
            while(bytesleidos != -1){
                bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
                outs.write(bufferCifrado);
                bytesleidos = in.read(buffer, 0, 1000);
            }
            bufferCifrado = cifrado.doFinal();
            outs.write(bufferCifrado);

            in.close();
            outs.close();
            sirvio = 1;
        }catch(Exception e){
                System.out.println(e.getMessage());
                sirvio=2;
            }
        return sirvio;
    }
}
