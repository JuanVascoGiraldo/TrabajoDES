/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DescifrarJava {
    public static final String ruta= "C:\\Users\\Juanv\\Desktop\\CifradoDes\\";
    public static int Descifrar (String cadena, String clave) throws Exception{
        int sirvio= 0;
        try{
            byte[] encodedKey = clave.getBytes();
            SecretKey subllave= new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
            Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            byte[] buffer = new byte[1000];
            cifrado.init(Cipher.DECRYPT_MODE, subllave);
            byte[] bufferPlano;
            FileInputStream in = new FileInputStream(ruta+"archivo.txt");
            FileOutputStream outs = new FileOutputStream(ruta+"archivo.txt.descifrado");
            int bytesleidos = in.read(buffer, 0, 1000);
            while(bytesleidos != -1){
                bufferPlano = cifrado.update(buffer, 0, bytesleidos);
                outs.write(bufferPlano);
                bytesleidos = in.read(buffer, 0, 1000);
            }
            bufferPlano = cifrado.doFinal();
            outs.write(bufferPlano);
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
