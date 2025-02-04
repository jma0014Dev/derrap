package derrap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.LinearGradientPaint;

public class Clientes extends JFrame {

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

    // Clase interna para un panel con degradado personalizado
    class GradientPanel extends JPanel {
        private Color[] colors;
        private float[] fractions;

        /**
         * @param colors    arreglo de colores (por ejemplo: [color1, color2, color3])
         * @param fractions arreglo de paradas, debe tener la misma cantidad de elementos que colors
         */
        public GradientPanel(Color[] colors, float[] fractions) {
            this.colors = colors;
            this.fractions = fractions;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            // Creando un degradado horizontal (de izquierda a derecha)
            LinearGradientPaint paint = new LinearGradientPaint(
                    0, 0, width, 0, fractions, colors);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            super.paintComponent(g);
        }
    }

    public Clientes() {
        // Se reduce el tamaño de la ventana a 1366x768
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        // Content pane con layout absoluto
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        // Conectar a la base de datos
        conectarBaseDatos();
        
        // ===============================
        // Logo de la Empresa (Esquina Superior Izquierda)
        // ===============================
        JLabel logoEmpresa = new JLabel("");
        logoEmpresa.setIcon(new ImageIcon("Imagen/logo.png"));
        logoEmpresa.setBounds(0, 0, 134, 103);
        contentPane.add(logoEmpresa);

        // ===============================
        // Panel Superior (Barra Superior) con degradado
        // ===============================
        // Usamos el GradientPanel en lugar de un JPanel normal
        JPanel barraSuperior = new GradientPanel(
                new Color[]{ new Color(120, 80, 60), new Color(180, 150, 130), new Color(120, 80, 60) },
                new float[]{ 0f, 0.5f, 1f }
        );
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(134, 0, 1216, 58);
        contentPane.add(barraSuperior);

        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitulo.setBounds(8, 25, 226, 20);
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

        // ===============================
        // Panel Lateral (Barra Lateral) con degradado
        // ===============================
        JPanel barraLateral = new GradientPanel(
                new Color[]{ new Color(120, 80, 60), new Color(180, 150, 130), new Color(120, 80, 60) },
                new float[]{ 0f, 0.5f, 1f }
        );
        barraLateral.setLayout(null);
        barraLateral.setBounds(-5, 103, 98, 631);
        contentPane.add(barraLateral);

        // Botón "Añadir Cliente" con imagen (se reescala la imagen al tamaño del botón)
        JButton btnAñadirCliente = new JButton("");
        btnAñadirCliente.setBounds(20, 11, 60, 50);
        ImageIcon originalIcon = new ImageIcon("Imagen\\agregar-usuario.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(btnAñadirCliente.getWidth(), btnAñadirCliente.getHeight(), Image.SCALE_SMOOTH);
        btnAñadirCliente.setIcon(new ImageIcon(scaledImg));
        btnAñadirCliente.addActionListener(e -> {
            new AnadirClienteFrame(connection).setVisible(true);
        });
        barraLateral.add(btnAñadirCliente);

        // Otros íconos en el panel lateral (puedes ajustar si lo deseas)
        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("Imagen/añadir.png"));
        iconoUsuario.setBounds(10, 10, 50, 50);
        barraLateral.add(iconoUsuario);

        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("Imagen/factura.png"));
        iconoFactura.setBounds(10, 70, 50, 35);
        barraLateral.add(iconoFactura);

        // ===============================
        // Panel Principal: Tabla y Botones
        // ===============================
        JPanel panelClientes = new JPanel();
        panelClientes.setLayout(null);
        panelClientes.setBounds(160, 79, 1067, 569);
        contentPane.add(panelClientes);
        


        
        // Configuración del modelo y JTable
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

        // JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        scrollPane.setBounds(29, 31, 1067, 497);
        panelClientes.add(scrollPane);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);
        panelBotones.setBounds(29, 528, 1067, 64);
        panelClientes.add(panelBotones);
        
     // Botón "Asignar Vehículo"
        JButton btnAsignarVehiculo = new JButton("Asignar Vehículo");
        btnAsignarVehiculo.setBounds(760, 11, 120, 28);  // Ajusta la posición y tamaño según tu layout
        btnAsignarVehiculo.addActionListener(e -> {
            int selectedRow = tableClientes.getSelectedRow();
            if (selectedRow != -1) {
                // Se obtienen los datos mínimos del cliente seleccionado:
                int dni = Integer.parseInt(modelClientes.getValueAt(selectedRow, 0).toString());
                String nombre = modelClientes.getValueAt(selectedRow, 1).toString();
                String apellido = modelClientes.getValueAt(selectedRow, 2).toString();
                int currentMatricula = Integer.parseInt(modelClientes.getValueAt(selectedRow, 6).toString());
                // Se crea un objeto ClienteData para pasar los datos a la ventana de asignación
                ClienteData cliente = new ClienteData(dni, nombre, apellido, currentMatricula);
                new AsignarVehiculoFrame(cliente, connection).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para asignar un vehículo.");
            }
        });
        panelBotones.add(btnAsignarVehiculo);

        
        // Botón "Actualizar Tabla"
        JButton btnActualizarTabla = new JButton("Actualizar Tabla");
        btnActualizarTabla.setBounds(323, 11, 107, 28);
        btnActualizarTabla.addActionListener(e -> loadClientData());
        panelBotones.add(btnActualizarTabla);

        // Botón "Modificar Cliente"
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        btnModificarCliente.setBounds(465, 11, 107, 28);
        btnModificarCliente.addActionListener(e -> {
            int selectedRow = tableClientes.getSelectedRow();
            if (selectedRow != -1) {
                int dni = Integer.parseInt(modelClientes.getValueAt(selectedRow, 0).toString());
                String nombre = modelClientes.getValueAt(selectedRow, 1).toString();
                String apellido = modelClientes.getValueAt(selectedRow, 2).toString();
                int matricula = Integer.parseInt(modelClientes.getValueAt(selectedRow, 6).toString());
                ClienteData cliente = new ClienteData(dni, nombre, apellido, matricula);
                new ModificarClienteFrame(cliente, connection).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar.");
            }
        });
        panelBotones.add(btnModificarCliente);

        // Botón "Eliminar Cliente"
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(608, 11, 107, 28);
        btnEliminarCliente.addActionListener(e -> {
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
                        loadClientData();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para eliminar.");
            }
        });
        panelBotones.add(btnEliminarCliente);

        // Cargar los datos iniciales de la tabla
        loadClientData();
    }

    private void conectarBaseDatos() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/derrap";
            String DB_USER = "root";
            String DB_PASSWORD = "Medac123";
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al conectar con la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClientData() {
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
            JOptionPane.showMessageDialog(this,
                    "Error al obtener clientes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clase interna para representar los datos mínimos de un cliente
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
