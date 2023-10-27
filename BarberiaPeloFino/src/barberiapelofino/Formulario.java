package barberiapelofino;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formulario {
    int opcion;
    String cedulaUsuario;
    Crud manipularArchivos = new Crud();
    Barbero barbero = new Barbero();

    public Formulario() {
    }

    public Formulario(int opcion) {
        this.opcion = opcion;
    }
    
     public void menuPrincipal(){
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Bievenidos \n \n \nBarberia Pelo Fino "
                                    + "\n1.Registro \n2.Iniciar Secion \n3.Salir \n\n"));
        
        switch (opcion) {
            case 1:
                menuBaberoOCliente();
                break;
            case 2:
                inicioSecion();
                break; 
             case 3:
                System.exit(0);
                break;
            default:
                throw new AssertionError();
        }
    }
     
    public void menuBaberoOCliente(){
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion"
                                    + "\n1.Babero \n2.Cliente \n3.Volver \n\n"));
        
        switch (opcion) {
            case 1:
                try{
                    manipularArchivos.guardarBarbero();
                    manipularArchivos.crearHorario();
                }catch(Exception e){
                    e.printStackTrace(System.out);
                }
                
                break;
            case 2:
                try{
                    manipularArchivos.guardarCliente();
                }catch(Exception e){
                    e.printStackTrace(System.out);
                }
                break; 
             case 3:
                menuPrincipal();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void inicioSecion(){
        String cedula = JOptionPane.showInputDialog("Ingrese tu cedula");
        cedulaUsuario = cedula;
        consultarUsuario(cedula);
        
    }
    
    public void consultarUsuario(String cedula){
        Crud buscar = new Crud();
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion"
                                    + "\n1.Babero \n2.Cliente \n3.Volver \n\n"));
        
        switch (opcion) {
            case 1:
                buscar.buscarBarbero(cedula);
                menuBarbero();
            case 2:
                menuCliente();
             case 3:
                menuPrincipal();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void menuBarbero(){
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion"
                                    + "\n1.Modificar datos \n2.Ver citas \n3.Volver \n\n"));
        
        switch (opcion) {
            case 1:
            {
                try {
                    manipularArchivos.actualizarBarbero(cedulaUsuario);
                } catch (IOException ex) {
                    
                }
            }
                break;

            case 2:
                manipularArchivos.verCitas();
                break; 
             case 3:
                menuPrincipal();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void menuCliente(){
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion"
                                    + "\n1.Agendar una cita \n2.Ver horarios de barberos \n3.Eliminar cita"
                                    + "\n4.Modificar datos \n5.Modificar datos \n\n"));
        
        switch (opcion) {
            case 1:
                manipularArchivos.creaCita();
                break;

            case 2:
                manipularArchivos.verCitas();
                break; 
             case 3:
                manipularArchivos.eliminarCita();
                break;
            case 4:
            {
                try {
                    manipularArchivos.actualizarCliente(cedulaUsuario);
                } catch (IOException ex) {
                    
                }
            }
                break;

            case 5:
                menuPrincipal();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    
}
