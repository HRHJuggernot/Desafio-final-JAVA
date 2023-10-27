package barberiapelofino;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
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
    
    public void crearHorario(){
        Horarios horario = new Horarios();
        horario.setId(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del horario")));
        
        // Obtener la fecha de inicio como cadena
        String horaInicioStr = JOptionPane.showInputDialog("Ingrese la hora de inicio (yyyy-MM-dd):");

        // Validar y convertir la cadena en un objeto LocalDate
        LocalDate horaInicio = null;
        try {
            horaInicio = LocalDate.parse(horaInicioStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato yyyy-MM-dd.");
            // Puedes manejar el error de otra manera, como mostrar un mensaje de error al usuario.
        }

        if (horaInicio != null) {
            // Repite el mismo proceso para la fecha final si es necesario
            String horaFinalStr = JOptionPane.showInputDialog("Ingrese la hora final (yyyy-MM-dd):");
            LocalDate horaFinal = null;
            try {
                horaFinal = LocalDate.parse(horaFinalStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato yyyy-MM-dd.");
                // Puedes manejar el error de otra manera, como mostrar un mensaje de error al usuario.
            }

            if (horaFinal != null) {
                // Las fechas son válidas, ahora puedes usarlas como sea necesario.
                horario.setHoraInicio(horaInicio);
                horario.setHoraFinal(horaFinal);
                // Realiza otras operaciones con la cita si es necesario.
            }
        }
        
        //Gestionar el archivo
        try{
            archivo = new File("horarios.txt");
            
            if(archivo.exists()){
                FileOutputStream apertura = new FileOutputStream("horarios.txt",true);
                //Mandar el objeto sin cabecera
                MiObjectOutputStream salida = new MiObjectOutputStream(apertura);
                salida.writeObject(horario);
                apertura.close();
                salida.close();
                
            }else{
                FileOutputStream apertura1 = new FileOutputStream("horarios.txt",true);
                ObjectOutputStream salida1 = new ObjectOutputStream(apertura1);
                salida1.writeObject(horario);
                apertura1.close();
                salida1.close();
            }
        }catch (Exception e){
            
        }
    }
    
    public void eliminarCita(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Id de la cita"));
        Crud eliminar = new Crud();
        eliminar.deleteCita(id, "citas.txt");    
        
        System.out.println("Cita eliminada");
    }
    
    public void deleteCita(int id, String nombreArchivo){
        archivo = new File(nombreArchivo);
        HashMap<Integer,Cita> hashCitas = new HashMap<>();
        Cita cita = new Cita();
        try{
            if (archivo.exists()) {
                FileInputStream archivoIn = new FileInputStream(nombreArchivo);
                ObjectInputStream entrada = new ObjectInputStream(archivoIn);
                
                while(true){
                    try{
                        cita = (Cita) entrada.readObject();
                        hashCitas.put(cita.getId(), cita);
                    }catch (Exception e){
                        break;
                    }
                }
                archivoIn.close();
                entrada.close();
            }
                
            if(hashCitas.containsKey(id)){
                hashCitas.remove(id);
                updateCita(hashCitas, nombreArchivo);
            }
        }catch (Exception e){
            
        }
    }
    
    public void updateCita(HashMap<Integer, Cita> hashSelecionado, String nombreArchivo){
        archivo = new File(nombreArchivo);
            
         try{
            if(archivo.exists()){
                FileOutputStream archivoOut = new FileOutputStream(nombreArchivo, true);
                ObjectOutputStream salida = new ObjectOutputStream(archivoOut);

                for(Cita cita: hashSelecionado.values()){
                    salida.writeObject(cita);
                }
                    
                archivoOut.close();
                salida.close();
                }
        }catch (Exception e){
            
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
        }

        if (fechaInicio != null) {
            // Repite el mismo proceso para la fecha final si es necesario
            String fechaFinalStr = JOptionPane.showInputDialog("Ingrese la hora final (yyyy-MM-dd):");
            LocalDate fechaFinal = null;
            try {
                fechaFinal = LocalDate.parse(fechaFinalStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Utilice el formato yyyy-MM-dd.");
            }

            if (fechaFinal != null) {
                // Las fechas son válidas, ahora puedes usarlas como sea necesario.
                cita.setFechaInicio(fechaInicio);
                cita.setFechaFinal(fechaFinal);
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
    
    public void verCitas(){
        File fichero = new File("citas.txt");
        
        try{
            BufferedReader lectura = new BufferedReader(new FileReader(fichero));
            String linea;
            
            while((linea = lectura.readLine().trim()) != null){
                String datos[] = linea.split(",");
                
                System.out.println(linea);
            }
            
            lectura.close();
        }catch (Exception e){
            // Manejo de excepciones, puedes imprimir un mensaje de error si lo deseas.
            e.printStackTrace();
        }
    }
    
    public void actualizarBarbero(String cedula) throws IOException {
        // Crear un nuevo objeto Barbero con la información actualizada
        Barbero barberoActualizado = new Barbero();
        barberoActualizado.setCedula(cedula);
        barberoActualizado.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
        barberoActualizado.setApellido(JOptionPane.showInputDialog("Ingrese el nuevo apellido"));
        barberoActualizado.setCorreo(JOptionPane.showInputDialog("Ingrese el nuevo correo"));
        barberoActualizado.setEstado("Barbero");
    
        // Crear un archivo temporal para almacenar los barberos actualizados
        File archivoTemporal = new File("temporal.txt");

        try {
            FileInputStream entrada = new FileInputStream("listabarbero.txt");
            ObjectInputStream ois = new ObjectInputStream(entrada);
        
            FileOutputStream salida = new FileOutputStream("temporal.txt");
            ObjectOutputStream oos = new ObjectOutputStream(salida);

            Barbero barbero;
            boolean encontrado = false;

            while (entrada.available() > 0) {
                // Leer un objeto Barbero del archivo
                barbero = (Barbero) ois.readObject();

                if (barbero.getCedula().equals(cedula)) {
                    // Reemplazar el objeto antiguo con el objeto actualizado
                    oos.writeObject(barberoActualizado);
                    encontrado = true;
                } else {
                    // Escribir el objeto sin cambios en el archivo temporal
                    oos.writeObject(barbero);
                }
            }

            ois.close();
            oos.close();
            entrada.close();
            salida.close();

            if (encontrado) {
                // Reemplazar el archivo original con el archivo temporal
                File archivoOriginal = new File("listabarbero.txt");
                archivoTemporal.renameTo(archivoOriginal);
                archivoTemporal.delete();
                System.out.println("Barbero actualizado con éxito.");
            } else {
                System.out.println("Barbero no encontrado en el archivo.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void actualizarCliente(String cedula) throws IOException{
        // Crear un nuevo objeto Cliente con la información actualizada
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCedula(cedula);
        clienteActualizado.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
        clienteActualizado.setApellido(JOptionPane.showInputDialog("Ingrese el nuevo apellido"));
        clienteActualizado.setCorreo(JOptionPane.showInputDialog("Ingrese el nuevo correo"));
        clienteActualizado.setEstado("Cliente");
    
        // Crear un archivo temporal para almacenar los barberos actualizados
        File archivoTemporal = new File("temporal.txt");

        try {
            FileInputStream entrada = new FileInputStream("listacliente.txt");
            ObjectInputStream ois = new ObjectInputStream(entrada);
        
            FileOutputStream salida = new FileOutputStream("temporal.txt");
            ObjectOutputStream oos = new ObjectOutputStream(salida);

            Barbero barbero;
            boolean encontrado = false;

            while (entrada.available() > 0) {
                // Leer un objeto Barbero del archivo
                barbero = (Barbero) ois.readObject();

                if (barbero.getCedula().equals(cedula)) {
                    // Reemplazar el objeto antiguo con el objeto actualizado
                    oos.writeObject(clienteActualizado);
                    encontrado = true;
                } else {
                    // Escribir el objeto sin cambios en el archivo temporal
                    oos.writeObject(barbero);
                }
            }

            ois.close();
            oos.close();
            entrada.close();
            salida.close();

            if (encontrado) {
                // Reemplazar el archivo original con el archivo temporal
                File archivoOriginal = new File("listacliente.txt");
                archivoTemporal.renameTo(archivoOriginal);
                archivoTemporal.delete();
                System.out.println("Barbero actualizado con éxito.");
            } else {
                System.out.println("Barbero no encontrado en el archivo.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}