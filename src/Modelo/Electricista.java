
package Modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Vista.Agregar_informacion;
/**
 *
 * @author Samuel pc
 */
public class Electricista {
    
    private String uuid;
    private String nombre;
    private int edad;
    private double peso;
    private String correo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    //3- Funciones (Insert, Update, delete, select)
     public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbElectricista(UUID_Electricista, Nombre_Electricista, Edad_Electricista, Peso_Electricista, Correo_Electricista) VALUES (?,?,?,?,?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3,getEdad());
            addProducto.setDouble(4, getPeso());
            addProducto.setString(5,getCorreo());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
     
     public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Electricista, Nombre_Electricista, Edad_Electricista, Peso_Electricista, Correo_Electricista"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM  tbElectricista");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Electricista"), 
                    rs.getString("Nombre_Electricista"), 
                    rs.getInt("Edad_Electricista"), 
                    rs.getDouble("Peso_Electricista"),
                    rs.getString("Correo_Electricista")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
     
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteElectricista = conexion.prepareStatement("delete from  tbElectricista where UUID_codigo = ?");
            deleteElectricista.setString(1, miId);
            deleteElectricista.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     
     public void cargarDatosTabla(Agregar_informacion vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbElectricista.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbElectricista.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbElectricista.getValueAt(filaSeleccionada, 1).toString();
            int Edad = (int) vista.jtbElectricista.getValueAt(filaSeleccionada, 2);
            double Peso_Electricista = (double) vista.jtbElectricista.getValueAt(filaSeleccionada, 3);
            String Correo_Electricista = vista.jtbElectricista.getValueAt(filaSeleccionada,4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(String.valueOf(Edad));
            vista.txtPeso.setText(String.valueOf(Peso_Electricista));
            vista.txtCorreoElectronico.setText(Correo_Electricista);
        }
    }
     
     public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update  tbElectricista set nombre_electricista = ?, edad_electricista = ?, peso_electricista = ?, correo_electricista = ? where UUID_electricista = ?");
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
     
    
    
    
}
