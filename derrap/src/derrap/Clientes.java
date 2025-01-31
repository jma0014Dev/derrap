package derrap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Clientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Conexión a la base de datos
        conectarBaseDatos();

        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        contentPane.add(topPanel, BorderLayout.CENTER);
        
        topPanel.setLayout(null);

        JPanel barraSuperior = new JPanel();
        barraSuperior.setBounds(134, 0, 1760, 61);
        barraSuperior.setBackground(new Color(162, 117, 102));
        topPanel.add(barraSuperior);
        barraSuperior.setLayout(null);
        
        

        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setBounds(8, 35, 226, 20);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        barraSuperior.add(lblTitulo);
        
        

        JButton btnVolver = new JButton("<--");
        btnVolver.addActionListener(e -> {
            Administrador administradorFrame = new Administrador();
            administradorFrame.setVisible(true);
            dispose();
            });
        btnVolver.setBounds(5, 5, 63, 23);
        barraSuperior.add(btnVolver);


        // Barra lateral
        JPanel barraLateral = new JPanel();
        barraLateral.setBounds(-7, 111, 99, 920);
        barraLateral.setBackground(new Color(162, 117, 102));
        topPanel.add(barraLateral);
        barraLateral.setLayout(null);

        JButton btnAñadirMecanico = new JButton("Añadir cliente");
        btnAñadirMecanico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ventana para ingresar datos del cliente
                JFrame formulario = new JFrame("Añadir Cliente");
                formulario.setSize(400, 400);
                formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formulario.getContentPane().setLayout(new GridLayout(7, 2));

                JLabel lblDNI = new JLabel("DNI:");
                JTextField txtDNI = new JTextField();
                
                JLabel lblNombre = new JLabel("Nombre:");
                JTextField txtNombre = new JTextField();

                JLabel lblApellido = new JLabel("Apellido:");
                JTextField txtApellido = new JTextField();

                JLabel lblEmail = new JLabel("Email:");
                JTextField txtEmail = new JTextField();

                JLabel lblTelefono = new JLabel("Teléfono:");
                JTextField txtTelefono = new JTextField();

                JLabel lblDireccion = new JLabel("Dirección:");
                JTextField txtDireccion = new JTextField();


                JButton btnGuardar = new JButton("Guardar");
                btnGuardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String dni = txtDNI.getText();
                        String nombre = txtNombre.getText();
                        String apellido = txtApellido.getText();
                        String email = txtEmail.getText();
                        String telefono = txtTelefono.getText();
                        String direccion = txtDireccion.getText();

                        // Insertar en la base de datos
                        try {
                            String query = "INSERT INTO Cliente (DNI, Nombre, Apellido, Email, Telefono, Direccion) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = connection.prepareStatement(query);
                            statement.setInt(1, Integer.parseInt(dni));
                            statement.setString(2, nombre);
                            statement.setString(3, apellido);
                            statement.setString(4, email);
                            statement.setInt(5, Integer.parseInt(telefono));
                            statement.setString(6, direccion);

                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(formulario, "Cliente añadido con éxito.");
                            formulario.dispose(); 
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(formulario, "Error al añadir cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


                formulario.getContentPane().add(lblDNI);
                formulario.getContentPane().add(txtDNI);
                formulario.getContentPane().add(lblNombre);
                formulario.getContentPane().add(txtNombre);
                formulario.getContentPane().add(lblApellido);
                formulario.getContentPane().add(txtApellido);
                formulario.getContentPane().add(lblEmail);
                formulario.getContentPane().add(txtEmail);
                formulario.getContentPane().add(lblTelefono);
                formulario.getContentPane().add(txtTelefono);
                formulario.getContentPane().add(lblDireccion);
                formulario.getContentPane().add(txtDireccion);
                formulario.getContentPane().add(btnGuardar);


                formulario.setVisible(true);
            }
        });

        btnAñadirMecanico.setBounds(0, 83, 111, 23);
        barraLateral.add(btnAñadirMecanico);
        

        JButton btnAlmacen = new JButton("Almacen");
        
        btnAlmacen.setBounds(20, 259, 73, 23);
        barraLateral.add(btnAlmacen);

        JButton btnFacturas = new JButton("Factura");
        btnFacturas.setBounds(20, 173, 69, 23);
        
        barraLateral.add(btnFacturas);

        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\añadir.png"));
        iconoUsuario.setBounds(33, 31, 53, 41);
        barraLateral.add(iconoUsuario);
        
        

        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\factura.png"));
        iconoFactura.setBounds(33, 127, 64, 35);
        barraLateral.add(iconoFactura);
        

        JLabel iconoAlmacen = new JLabel("");
        iconoAlmacen.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\almacen.png"));
        iconoAlmacen.setBounds(30, 207, 46, 41);
        barraLateral.add(iconoAlmacen);

        
        
        
        JLabel logoEmpresa = new JLabel("");
        logoEmpresa.setBounds(-17, -13, 164, 124);
        logoEmpresa.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\logo.png"));
        topPanel.add(logoEmpresa);

        // Sección de Listas
        JPanel panelListas = new JPanel();
        panelListas.setBounds(150, 100, 1500, 800);
        panelListas.setLayout(new GridLayout(1, 3, 20, 0));
        topPanel.add(panelListas);

        String[] columnas = {"Dar de alta a cliente", "Modificar un cliente", "Consultar un cliente"};
        for (String columna : columnas) {
        	
            JPanel panelColumna = new JPanel();
            panelColumna.setLayout(new BorderLayout());

            JLabel tituloColumna = new JLabel(columna);
            tituloColumna.setFont(new Font("Tahoma", Font.BOLD, 14));
            tituloColumna.setHorizontalAlignment(SwingConstants.CENTER);
            panelColumna.add(tituloColumna, BorderLayout.NORTH);

            JList<String> listaClientes = new JList<>(obtenerClientesDesdeBD());
            
            listaClientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                    	
                        String clienteSeleccionado = listaClientes.getSelectedValue();
                        
                        if (clienteSeleccionado != null) {
                            switch (columna) {
                            
                                case "Dar de alta a cliente":
                                    eliminarCliente(clienteSeleccionado);
                                    break;
                                    
                                case "Modificar un cliente":
                                    new ModificarClienteFrame(clienteSeleccionado).setVisible(true);
                                    break;
                                    
                                case "Consultar un cliente":
                                    mostrarInformacionCliente(clienteSeleccionado);
                                    break;
                            }
                        }
                    }
                }
            });
            panelColumna.add(new JScrollPane(listaClientes), BorderLayout.CENTER);

            panelListas.add(panelColumna);
        }
    }

    private void conectarBaseDatos() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/derrap";
            String DB_USER = "root";
            String DB_PASSWORD = "Medac123";

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
        	
        	
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] obtenerClientesDesdeBD() {
    	
        java.util.List<String> clientes = new ArrayList<>();
        
        try {
            String query = "SELECT CONCAT(Nombre, ' ', Apellido) AS NombreCompleto FROM Cliente";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                clientes.add(resultSet.getString("NombreCompleto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return clientes.toArray(new String[0]);
    }


    private void eliminarCliente(String clienteSeleccionado) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar al cliente: " +clienteSeleccionado+ "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                String[] partes = clienteSeleccionado.split(" ");
                String query = "DELETE FROM Cliente WHERE Nombre = ? AND Apellido = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, partes[0]);
                statement.setString(2, partes[1]);
                
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.");
                dispose();
                new Clientes().setVisible(true);
            } catch (SQLException e) {
            	
                JOptionPane.showMessageDialog(this, "Error al eliminar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarInformacionCliente(String clienteSeleccionado) {
        try {
            String[] partes = clienteSeleccionado.split(" ");
            String query = "SELECT * FROM Cliente WHERE Nombre = ? AND Apellido = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, partes[0]);
            statement.setString(2, partes[1]);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String info = "Nombre: " + resultSet.getString("Nombre") +"\n" +
                              "Apellido: " + resultSet.getString("Apellido") + "\n" +
                              "Email: " + resultSet.getString("Email") + "\n"+
                              "Teléfono: " + resultSet.getString("Telefono");
                
                

                JOptionPane.showMessageDialog(this, info, "Información del Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
