
package Servlet;

import Modelo.CifrarJava;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ProcesoArchivo")
@MultipartConfig
public class Cifrar extends HttpServlet {
    
    public static final String ruta= "C:\\Users\\Juanv\\Desktop\\CifradoDes\\";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
        String llave = request.getParameter("llave1");
        System.out.println("miau");
            System.out.println(llave);
        if(llave.length() == 8){
            System.out.println("miau");
            InputStream inputStream = null;
            int sirvio = 0;
            Part filePart = request.getPart("file_1");
            if (filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }
            FileOutputStream archivo = new FileOutputStream(ruta + "archivo.txt");
            int dato = inputStream.read();
                while(dato != -1){
                        archivo.write(dato);
                        dato = inputStream.read();
                }
            archivo.close();
            inputStream.close();
            System.out.println("miau");
            sirvio = CifrarJava.Cifrar("archivo.txt", llave);
            System.out.println("miau");
            if(sirvio == 1){
                FileInputStream inn = new FileInputStream(ruta+"archivo.txt.cifrado");
                PrintWriter bufferr = response.getWriter(); 
                String filename = "Cifrado.txt";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
                int read;
                while ((read = inn.read()) != -1) {
                    bufferr.write(read);
                }
                inn.close();
                bufferr.close(); 
                System.out.println("miau");
            }else{
                response.sendRedirect("index.html");
            }
        }else{
            response.sendRedirect("index.html");
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
