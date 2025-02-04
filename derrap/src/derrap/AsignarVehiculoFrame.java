package derrap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AsignarVehiculoFrame extends JFrame {

    private JPanel contentPane;
    private Connection connection;
    private Clientes.ClienteData clienteData;
    private JTextField txtNuevaMatricula;

    public AsignarVehiculoFrame(Clientes.ClienteData clienteData, Connection connection) {
        this.clienteData = clienteData;
        this.connection = connection;
        
        setTitle("Asignar Vehículo a Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        
        contentPane = new JPanel();
        contentPane.setLayout(null);  // Layout absoluto para este ejemplo
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        
        // Información del cliente
        JLabel lblInfo = new JLabel("Cliente: " + clienteData.nombre + " " + clienteData.apellido + " (DNI: " + clienteData.dni + ")");
        lblInfo.setBounds(20, 20, 360, 25);
        contentPane.add(lblInfo);
        
        JLabel lblActual = new JLabel("Vehículo Actual: " + clienteData.vehiculoMatricula);
        lblActual.setBounds(20, 50, 360, 25);
        contentPane.add(lblActual);
        
        // Campo para ingresar la nueva matrícula
        JLabel lblNueva = new JLabel("Nueva Matrícula:");
        lblNueva.setBounds(20, 90, 120, 25);
        contentPane.add(lblNueva);
        
        txtNuevaMatricula = new JTextField();
        txtNuevaMatricula.setBounds(150, 90, 200, 25);
        contentPane.add(txtNuevaMatricula);
        
        // Botón para asignar el nuevo vehículo
        JButton btnAsignar = new JButton("Asignar");
        btnAsignar.setBounds(150, 140, 100, 30);
        btnAsignar.addActionListener(e -> asignarVehiculo());
        contentPane.add(btnAsignar);
    }

    private void asignarVehiculo() {
        try {
            int nuevaMatricula = Integer.parseInt(txtNuevaMatricula.getText());
            
            // Verificar si la nueva matrícula existe en la tabla 'vehiculo'
            String consulta = "SELECT COUNT(*) FROM vehiculo WHERE Matricula = ?";
            PreparedStatement psConsulta = connection.prepareStatement(consulta);
            psConsulta.setInt(1, nuevaMatricula);
            ResultSet rs = psConsulta.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            if (count == 0) {
                // La matrícula no existe; preguntamos al administrador si desea crear el nuevo vehículo.
                int opcion = JOptionPane.showConfirmDialog(
                        this, 
                        "La matrícula ingresada no existe en la tabla de vehículos.\n¿Desea crear un nuevo vehículo con esta matrícula?",
                        "Crear nuevo vehículo", 
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    // Insertar un nuevo registro en la tabla vehiculo con la nueva matrícula y valores por defecto.
                    // (Asegúrate de que los valores por defecto sean coherentes con tu aplicación y existan registros en la tabla reparacion)
                    String insertVehiculo = "INSERT INTO vehiculo (Matricula, Marca, Modelo, Año_fabricacion, Kilometraje, Estado, Observaciones, reparacion_id_reparacion, reparacion_factura_id_factura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement psInsert = connection.prepareStatement(insertVehiculo);
                    psInsert.setInt(1, nuevaMatricula);
                    psInsert.setString(2, "Desconocido");      // Marca por defecto
                    psInsert.setString(3, "Desconocido");      // Modelo por defecto
                    psInsert.setInt(4, 0);                     // Año_fabricacion por defecto
                    psInsert.setInt(5, 0);                     // Kilometraje por defecto
                    psInsert.setString(6, "Nuevo");            // Estado por defecto
                    psInsert.setString(7, "");                 // Observaciones vacías
                    psInsert.setInt(8, 1);                     // Reparación: usamos 1 (debe existir en reparacion)
                    psInsert.setInt(9, 1);                     // Factura de reparación: usamos 1 (debe existir)
                    
                    int insertCount = psInsert.executeUpdate();
                    if (insertCount > 0) {
                        JOptionPane.showMessageDialog(this, "Nuevo vehículo creado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo crear el nuevo vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    // Si el administrador no desea crear el vehículo, se cancela la operación.
                    return;
                }
            }
            
            // Actualizar el registro del cliente usando los valores originales para identificar la fila
            String query = "UPDATE cliente SET vehiculo_Matricula = ? WHERE dni = ? AND vehiculo_Matricula = ?";
            PreparedStatement psUpdate = connection.prepareStatement(query);
            psUpdate.setInt(1, nuevaMatricula);
            psUpdate.setInt(2, clienteData.dni);
            psUpdate.setInt(3, clienteData.vehiculoMatricula);
            int filasAfectadas = psUpdate.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Vehículo asignado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo asignar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa una matrícula válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al asignar el vehículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
