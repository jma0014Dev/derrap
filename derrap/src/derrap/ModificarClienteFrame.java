package derrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModificarClienteFrame extends JFrame {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Medac123";

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private String clienteOriginal;

    public ModificarClienteFrame(String clienteSeleccionado) {
        this.clienteOriginal = clienteSeleccionado;

        setTitle("Modificar Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Extraer los nombres y apellidos del cliente seleccionado
        String[] nombres = clienteSeleccionado.split(" ");
        String nombreOriginal = nombres[0];
        String apellidoOriginal = nombres.length > 1 ? nombres[1] : "";
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(nombreOriginal);
        add(txtNombre);

        add(new JLabel("Apellido:"));
        txtApellido = new JTextField(apellidoOriginal);
        add(txtApellido);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        // Botones
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarCliente();
                    JOptionPane.showMessageDialog(null, "Cliente modificado con éxito.");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el cliente: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);

        // Cargar datos actuales del cliente desde la base de datos
        try {
            cargarDatosCliente(nombreOriginal, apellidoOriginal);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cargarDatosCliente(String nombre, String apellido) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "SELECT Telefono, Email FROM Cliente WHERE Nombre = ? AND Apellido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                txtTelefono.setText(rs.getString("Telefono"));
                txtEmail.setText(rs.getString("Email"));
            }
        } finally {
            connection.close();
        }
    }

    private void modificarCliente() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "UPDATE Cliente SET Nombre = ?, Apellido = ?, Telefono = ?, Email = ? WHERE CONCAT(Nombre, ' ', Apellido) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtApellido.getText());
            stmt.setString(3, txtTelefono.getText());
            stmt.setString(4, txtEmail.getText());
            stmt.setString(5, clienteOriginal);
            stmt.executeUpdate();
        } finally {
            connection.close();
        }
    }
}
