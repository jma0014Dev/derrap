package derrap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Clientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection;
    private JTable tableClientes;
    private DefaultTableModel modelClientes;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Clientes frame = new Clientes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Clientes() {
        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        // Usamos un layout nulo para posicionar los paneles como en tu código actual
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Conexión a la base de datos
        conectarBaseDatos();

        // =========================
        // Panel Superior (Barra Superior)
        // =========================
        JPanel barraSuperior = new JPanel();
        barraSuperior.setBounds(134, 0, 1760, 61);
        barraSuperior.setBackground(new Color(162, 117, 102));
        barraSuperior.setLayout(null);
        contentPane.add(barraSuperior);

        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setBounds(8, 35, 226, 20);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        barraSuperior.add(lblTitulo);

        JButton btnVolver = new JButton("<--");
        btnVolver.setBounds(5, 5, 63, 23);
        btnVolver.addActionListener(e -> {
            // Se asume que tienes la ventana Administrador implementada
            Administrador administradorFrame = new Administrador();
            administradorFrame.setVisible(true);
            dispose();
        });
        barraSuperior.add(btnVolver);

        // =========================
        // Panel Lateral (Sin cambios)
        // =========================
        JPanel barraLateral = new JPanel();
        barraLateral.setBounds(-7, 111, 99, 920);
        barraLateral.setBackground(new Color(162, 117, 102));
        barraLateral.setLayout(null);
        contentPane.add(barraLateral);

        
        JButton btnAñadirCliente = new JButton("Añadir cliente");
        btnAñadirCliente.setBounds(0, 83, 111, 23);
        btnAñadirCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AnadirClienteFrame(connection).setVisible(true);
            }
        });
        barraLateral.add(btnAñadirCliente);


        // Íconos en la barra lateral (sin modificaciones)
        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("Imagen/añadir.png"));
        iconoUsuario.setBounds(33, 31, 53, 41);
        barraLateral.add(iconoUsuario);

        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("Imagen/factura.png"));
        iconoFactura.setBounds(33, 127, 64, 35);
        barraLateral.add(iconoFactura);

        JLabel iconoAlmacen = new JLabel("");
        iconoAlmacen.setIcon(new ImageIcon("imagenes/almacen.png"));
        iconoAlmacen.setBounds(30, 207, 46, 41);
        barraLateral.add(iconoAlmacen);

        // =========================
        // Imagen superior izquierda (sin cambios)
        // =========================
        JLabel logoEmpresa = new JLabel("");
        logoEmpresa.setBounds(-17, -13, 164, 124);
        logoEmpresa.setIcon(new ImageIcon("Imagen/logo.png"));
        contentPane.add(logoEmpresa);

        // =========================
        // Panel Principal: Tabla de Clientes y botones inferiores
        // =========================
        JPanel panelClientes = new JPanel();
        panelClientes.setBounds(150, 100, 1500, 800);
        panelClientes.setLayout(new BorderLayout(10, 10));
        contentPane.add(panelClientes);

        // Configuración del modelo y tabla
        modelClientes = new DefaultTableModel();
        modelClientes.addColumn("DNI");
        modelClientes.addColumn("Nombre");
        modelClientes.addColumn("Apellido");
        modelClientes.addColumn("Email");
        modelClientes.addColumn("Teléfono");
        modelClientes.addColumn("Dirección");
        modelClientes.addColumn("Matrícula");

        tableClientes = new JTable(modelClientes);
        tableClientes.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panelClientes.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para los botones de "Modificar" y "Eliminar"
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnActualizarTabla = new JButton("Actualizar Tabla");
        btnActualizarTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadClientData();  // Método que recarga los datos de la tabla
            }
        });
        panelBotones.add(btnActualizarTabla);

        // Botón para modificar el cliente seleccionado
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        btnModificarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableClientes.getSelectedRow();
                if (selectedRow != -1) {
                    // Se obtienen los datos mínimos para identificar al cliente
                    int dni = Integer.parseInt(modelClientes.getValueAt(selectedRow, 0).toString());
                    String nombre = modelClientes.getValueAt(selectedRow, 1).toString();
                    String apellido = modelClientes.getValueAt(selectedRow, 2).toString();
                    int matricula = Integer.parseInt(modelClientes.getValueAt(selectedRow, 6).toString());
                    // Se crea un objeto ClienteData para pasar los datos (se utiliza en la ventana de modificación)
                    ClienteData cliente = new ClienteData(dni, nombre, apellido, matricula);
                    new ModificarClienteFrame(cliente, connection).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar.");
                }
            }
        });
        panelBotones.add(btnModificarCliente);

        // Botón para eliminar el cliente seleccionado
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableClientes.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            int dni = Integer.parseInt(modelClientes.getValueAt(selectedRow, 0).toString());
                            int matricula = Integer.parseInt(modelClientes.getValueAt(selectedRow, 6).toString());
                            String query = "DELETE FROM cliente WHERE dni = ? AND vehiculo_Matricula = ?";
                            PreparedStatement ps = connection.prepareStatement(query);
                            ps.setInt(1, dni);
                            ps.setInt(2, matricula);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
                            loadClientData(); // Actualizar la tabla después de eliminar
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente para eliminar.");
                }
            }
        });
        panelBotones.add(btnEliminarCliente);

        // Se agrega el panel inferior (con los botones) al panel principal que contiene la tabla
        panelClientes.add(panelBotones, BorderLayout.SOUTH);
        panelBotones.add(btnModificarCliente);
        panelBotones.add(btnEliminarCliente);
        panelClientes.add(panelBotones, BorderLayout.SOUTH);

        // Cargar los datos de la base en la tabla
        loadClientData();
    }

    // Método para conectar con la base de datos
    private void conectarBaseDatos() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/derrap";
            String DB_USER = "root";
            String DB_PASSWORD = "Medac123";
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para cargar los datos de clientes en la JTable
    private void loadClientData() {
        // Se limpia el modelo
        modelClientes.setRowCount(0);
        try {
            String query = "SELECT dni, Nombre, Apellido, email, Telefono, Direccion, vehiculo_Matricula FROM cliente";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("dni");
                row[1] = rs.getString("Nombre");
                row[2] = rs.getString("Apellido");
                row[3] = rs.getString("email");
                row[4] = rs.getInt("Telefono");
                row[5] = rs.getString("Direccion");
                row[6] = rs.getInt("vehiculo_Matricula");
                modelClientes.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener clientes: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clase interna para almacenar los datos mínimos de un cliente
    public class ClienteData {
        int dni;
        String nombre;
        String apellido;
        int vehiculoMatricula;

        public ClienteData(int dni, String nombre, String apellido, int vehiculoMatricula) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.vehiculoMatricula = vehiculoMatricula;
        }
    }
}
