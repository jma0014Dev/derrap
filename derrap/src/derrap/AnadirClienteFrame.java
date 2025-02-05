package derrap;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AnadirClienteFrame extends JFrame {

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

    public AnadirClienteFrame(Connection connection) {
        this.connection = connection;
        
        setTitle("Añadir Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centrar la ventana

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Título superior
        JLabel lblTitle = new JLabel("Añadir Cliente", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // Panel central con formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(new TitledBorder(new EtchedBorder(), "Datos del Cliente"));
        contentPane.add(panelForm, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: DNI
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("DNI:"), gbc);
        txtDNI = new JTextField();
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

        // Fila 7: Matrícula del Vehículo
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        panelForm.add(new JLabel("Matrícula:"), gbc);
        txtVehiculo = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelForm.add(txtVehiculo, gbc);

        // Panel inferior para el botón "Guardar"
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });
        panelButtons.add(btnGuardar);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
    }

    // Método para insertar el nuevo cliente en la base de datos
    private void agregarCliente() {
        try {
            String query = "INSERT INTO cliente (dni, Nombre, Apellido, email, Telefono, Direccion, vehiculo_Matricula) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(txtDNI.getText()));
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtApellido.getText());
            ps.setString(4, txtEmail.getText());
            ps.setInt(5, Integer.parseInt(txtTelefono.getText()));
            ps.setString(6, txtDireccion.getText());
            ps.setInt(7, Integer.parseInt(txtVehiculo.getText()));
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente añadido con éxito.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para DNI, Teléfono y Matrícula.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
