package barberiapelofino;

import java.io.*;
import javax.swing.JOptionPane;

public class Crud {
     File archivo;
    
    public void guardarBarbero() throws IOException {
        Barbero barbero = new Barbero();
        barbero.setId((int) (Math.random() * (100 - 1 + 1)) + 1);
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
        cliente.setId((int) (Math.random() * (100 - 1 + 1)) + 1);
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
}
