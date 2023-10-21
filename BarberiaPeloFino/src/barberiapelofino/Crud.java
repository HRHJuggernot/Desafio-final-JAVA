package barberiapelofino;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    
    public void buscarBarbero(String cedula){
        try (BufferedReader br = new BufferedReader(new FileReader("listabarbero.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(cedula)) {
                    System.out.println("El string está presente en el archivo.");
                    break; // Si deseas detener la búsqueda después del primer hallazgo.
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public void creaCita(){
        Cita cita = new Cita();
        cita.setId(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id")));
        
        // Obtener la fecha de inicio como cadena
        String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la hora de inicio (yyyy-MM-dd):");

        // Validar y convertir la cadena en un objeto LocalDate
        LocalDate fechaInicio = null;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato yyyy-MM-dd.");
            // Puedes manejar el error de otra manera, como mostrar un mensaje de error al usuario.
        }

        if (fechaInicio != null) {
            // Repite el mismo proceso para la fecha final si es necesario
            String fechaFinalStr = JOptionPane.showInputDialog("Ingrese la hora final (yyyy-MM-dd):");
            LocalDate fechaFinal = null;
            try {
                fechaFinal = LocalDate.parse(fechaFinalStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato yyyy-MM-dd.");
                // Puedes manejar el error de otra manera, como mostrar un mensaje de error al usuario.
            }

            if (fechaFinal != null) {
                // Las fechas son válidas, ahora puedes usarlas como sea necesario.
                cita.setFechaInicio(fechaInicio);
                cita.setFechaFinal(fechaFinal);
                // Realiza otras operaciones con la cita si es necesario.
            }
        }
        
        cita.setEstado("En proceso");
        
        //Gestionar el archivo
        try{
            archivo = new File("citas.txt");
            
            if(archivo.exists()){
                FileOutputStream apertura = new FileOutputStream("citas.txt",true);
                //Mandar el objeto sin cabecera
                MiObjectOutputStream salida = new MiObjectOutputStream(apertura);
                salida.writeObject(cita);
                apertura.close();
                salida.close();
                
            }else{
                FileOutputStream apertura1 = new FileOutputStream("citas.txt",true);
                ObjectOutputStream salida1 = new ObjectOutputStream(apertura1);
                salida1.writeObject(cita);
                apertura1.close();
                salida1.close();
            }
        }catch (Exception e){
            
        }
    }
    
    public void actualizarBarbero(HashMap<Integer, Perro> hashSelecionado, String nombreArchivo){
        archivo = new File(nombreArchivo);
            
         try{
            if(archivo.exists()){
                FileOutputStream archivoOut = new FileOutputStream(nombreArchivo, true);
                ObjectOutputStream salida = new ObjectOutputStream(archivoOut);

                for(Perro perro: hashSelecionado.values()){
                    salida.writeObject(perro);
                }
                    
                archivoOut.close();
                salida.close();
                }
        }catch (Exception e){
            
        }
    }
    
}
