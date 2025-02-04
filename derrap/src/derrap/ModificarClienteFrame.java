package derrap;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ModificarClienteFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtVehiculo;
    private Connection connection;
    private Clientes.ClienteData clienteData;  // Valores originales del cliente

    // Constructor que recibe ClienteData y Connection
    public ModificarClienteFrame(Clientes.ClienteData clienteData, Connection connection) {
        this.clienteData = clienteData;
        this.connection = connection;
        
        setTitle("Modificar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Panel superior con título
        JLabel lblTitle = new JLabel("Modificar Cliente", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // Panel central con formulario (usando GridBagLayout)
        JPanel panelForm = new JPanel();
        panelForm.setBorder(new TitledBorder(new EtchedBorder(), "Datos del Cliente"));
        panelForm.setLayout(new GridBagLayout());
        contentPane.add(panelForm, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Márgenes uniformes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: DNI (ahora editable)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("DNI:"), gbc);
        txtDNI = new JTextField();
        // Se elimina: txtDNI.setEditable(false);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtDNI, gbc);

        // Fila 2: Nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtNombre, gbc);

        // Fila 3: Apellido
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtApellido, gbc);

        // Fila 4: Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtEmail, gbc);

        // Fila 5: Teléfono
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtTelefono, gbc);

        // Fila 6: Dirección
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Dirección:"), gbc);
        txtDireccion = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtDireccion, gbc);

        // Fila 7: Matrícula del Vehículo (ahora editable)
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Matrícula:"), gbc);
        txtVehiculo = new JTextField();
        // Se elimina: txtVehiculo.setEditable(false);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtVehiculo, gbc);

        // Panel inferior para el botón "Guardar"
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
        panelButtons.add(btnGuardar);
        contentPane.add(panelButtons, BorderLayout.SOUTH);

        cargarDatosCliente();
    }

    // Carga los datos originales del cliente en los campos del formulario
    private void cargarDatosCliente() {
        try {
            String query = "SELECT * FROM cliente WHERE dni = ? AND vehiculo_Matricula = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, clienteData.dni);
            ps.setInt(2, clienteData.vehiculoMatricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtDNI.setText(String.valueOf(rs.getInt("dni")));
                txtNombre.setText(rs.getString("Nombre"));
                txtApellido.setText(rs.getString("Apellido"));
                txtEmail.setText(rs.getString("email"));
                txtTelefono.setText(String.valueOf(rs.getInt("Telefono")));
                txtDireccion.setText(rs.getString("Direccion"));
                txtVehiculo.setText(String.valueOf(rs.getInt("vehiculo_Matricula")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Actualiza el cliente en la base de datos, permitiendo modificar DNI y Matrícula
    private void actualizarCliente() {
        try {
            // Se guardan los valores originales (clave primaria original)
            int originalDNI = clienteData.dni;
            int originalMatricula = clienteData.vehiculoMatricula;
            
            // Se obtienen los nuevos valores desde los campos (convertir a int)
            int newDNI = Integer.parseInt(txtDNI.getText());
            int newMatricula = Integer.parseInt(txtVehiculo.getText());
            
            String updateQuery = "UPDATE cliente SET dni = ?, vehiculo_Matricula = ?, Nombre = ?, Apellido = ?, email = ?, Telefono = ?, Direccion = ? "
                    + "WHERE dni = ? AND vehiculo_Matricula = ?";
            PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
            psUpdate.setInt(1, newDNI);
            psUpdate.setInt(2, newMatricula);
            psUpdate.setString(3, txtNombre.getText());
            psUpdate.setString(4, txtApellido.getText());
            psUpdate.setString(5, txtEmail.getText());
            psUpdate.setInt(6, Integer.parseInt(txtTelefono.getText()));
            psUpdate.setString(7, txtDireccion.getText());
            psUpdate.setInt(8, originalDNI);
            psUpdate.setInt(9, originalMatricula);

            int filasAfectadas = psUpdate.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente modificado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún registro que actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para DNI, Matrícula y Teléfono.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
