package barberiapelofino;

import java.io.*;
import javax.swing.JOptionPane;

public class Crud {
     File archivo;
    
    public void guardarBarbero() throws IOException {
        Barbero barbero = new Barbero();
        barbero.setCedula(JOptionPane.showInputDialog("Ingrese tu cedula"));
        barbero.setNombre(JOptionPane.showInputDialog("Ingrese tu nombre"));
        barbero.setApellido(JOptionPane.showInputDialog("Ingrese tu apellido"));
        barbero.setCorreo(JOptionPane.showInputDialog("Ingrese tu correo"));
        barbero.setEstado("Barbero");
        
        //Gestion de archivo
        try{
            archivo = new File("listabarbero.txt");
            
            if(archivo.exists()){
                FileOutputStream apertura = new FileOutputStream("listabarbero.txt",true);
                //Mandar el objeto sin cabecera
                MiObjectOutputStream salida = new MiObjectOutputStream(apertura);
                salida.writeObject(barbero);
                apertura.close();
                salida.close();
                
            }else{
                FileOutputStream apertura1 = new FileOutputStream("listabarbero.txt",true);
                ObjectOutputStream salida1 = new ObjectOutputStream(apertura1);
                salida1.writeObject(barbero);
                apertura1.close();
                salida1.close();
                
            }
        }catch(FileNotFoundException e){
            e.printStackTrace(System.out);
        }
         
    }
    
    public void guardarCliente() throws IOException {
        Cliente cliente = new Cliente();
        cliente.setCedula(JOptionPane.showInputDialog("Ingrese tu cedula"));
        cliente.setNombre(JOptionPane.showInputDialog("Ingrese tu nombre"));
        cliente.setApellido(JOptionPane.showInputDialog("Ingrese tu apellido"));
        cliente.setCorreo(JOptionPane.showInputDialog("Ingrese tu correo"));
        cliente.setEstado("Cliente");
        
        //Gestion de archivo
        try{
            archivo = new File("listacliente.txt");
            
            if(archivo.exists()){
                FileOutputStream apertura = new FileOutputStream("listacliente.txt",true);
                //Mandar el objeto sin cabecera
                MiObjectOutputStream salida = new MiObjectOutputStream(apertura);
                salida.writeObject(cliente);
                apertura.close();
                salida.close();
                
            }else{
                FileOutputStream apertura1 = new FileOutputStream("listacliente.txt",true);
                ObjectOutputStream salida1 = new ObjectOutputStream(apertura1);
                salida1.writeObject(cliente);
                apertura1.close();
                salida1.close();
                
            }
        }catch(FileNotFoundException e){
            e.printStackTrace(System.out);
        }
       
    }
    
    public String buscarBarbero(cedula){
        try (BufferedReader br = new BufferedReader(new FileReader(listabarbero.txt))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(cedula)) {
                    System.out.println("El string está presente en el archivo.");
                    // Puedes agregar aquí cualquier otra lógica que necesites.
                    break; // Si deseas detener la búsqueda después del primer hallazgo.
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
    }
}
