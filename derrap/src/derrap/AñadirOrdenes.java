package derrap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AñadirOrdenes extends JFrame {
    // Declaración de los campos de la interfaz
    private JTextField tfIdPedido, tfPrecio, tfPiezaSolicitada, tfFechaVisita, tfMatriculaVehiculo, tfMecanicoDni, tfFacturaId, tfAdministradorDni, tfAdministradorMecanicoDni;

    public AñadirOrdenes() {
        // Configurar la ventana
        setTitle("Insertar Nueva Orden");
        setSize(556, 504);
        getContentPane().setLayout(null);  // Usamos AbsoluteLayout (null layout)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear los campos de texto
        tfIdPedido = new JTextField(10);
        tfPrecio = new JTextField(10);
        tfPiezaSolicitada = new JTextField(10);
        tfFechaVisita = new JTextField(10); // Campo de texto para la fecha
        tfMatriculaVehiculo = new JTextField(10); // Campo de texto para la matrícula del vehículo
        tfMecanicoDni = new JTextField(10);
        tfFacturaId = new JTextField(10);
        tfAdministradorDni = new JTextField(10);
        tfAdministradorMecanicoDni = new JTextField(10);

        // Botón para insertar la orden
        JButton btnInsertar = new JButton("Insertar Orden");

        // Posicionar los componentes en la ventana
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(20, 20, 100, 25);
        tfIdPedido.setBounds(120, 20, 150, 25);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 60, 100, 25);
        tfPrecio.setBounds(120, 60, 150, 25);

        JLabel lblPiezaSolicitada = new JLabel("Pieza solicitada:");
        lblPiezaSolicitada.setBounds(20, 100, 120, 25);
        tfPiezaSolicitada.setBounds(140, 100, 150, 25);

        JLabel lblFechaVisita = new JLabel("Fecha visita (DD/MM/YYYY):");
        lblFechaVisita.setBounds(20, 140, 180, 25);
        tfFechaVisita.setBounds(200, 140, 150, 25);

        JLabel lblMatriculaVehiculo = new JLabel("Matrícula Vehículo:");
        lblMatriculaVehiculo.setBounds(20, 180, 120, 25);
        tfMatriculaVehiculo.setBounds(140, 180, 150, 25);

        JLabel lblMecanicoDni = new JLabel("Mecánico DNI:");
        lblMecanicoDni.setBounds(20, 220, 120, 25);
        tfMecanicoDni.setBounds(140, 220, 150, 25);

        JLabel lblFacturaId = new JLabel("Factura ID:");
        lblFacturaId.setBounds(20, 260, 120, 25);
        tfFacturaId.setBounds(140, 260, 150, 25);

        JLabel lblAdministradorDni = new JLabel("Administrador DNI:");
        lblAdministradorDni.setBounds(20, 300, 120, 25);
        tfAdministradorDni.setBounds(140, 300, 150, 25);

        JLabel lblAdministradorMecanicoDni = new JLabel("Admin Mecánico DNI:");
        lblAdministradorMecanicoDni.setBounds(20, 340, 140, 25);
        tfAdministradorMecanicoDni.setBounds(160, 340, 150, 25);

        btnInsertar.setBounds(120, 380, 150, 30);

        // Añadir los componentes a la ventana
        getContentPane().add(lblIdPedido);
        getContentPane().add(tfIdPedido);
        getContentPane().add(lblPrecio);
        getContentPane().add(tfPrecio);
        getContentPane().add(lblPiezaSolicitada);
        getContentPane().add(tfPiezaSolicitada);
        getContentPane().add(lblFechaVisita);
        getContentPane().add(tfFechaVisita);
        getContentPane().add(lblMatriculaVehiculo);
        getContentPane().add(tfMatriculaVehiculo);
        getContentPane().add(lblMecanicoDni);
        getContentPane().add(tfMecanicoDni);
        getContentPane().add(lblFacturaId);
        getContentPane().add(tfFacturaId);
        getContentPane().add(lblAdministradorDni);
        getContentPane().add(tfAdministradorDni);
        getContentPane().add(lblAdministradorMecanicoDni);
        getContentPane().add(tfAdministradorMecanicoDni);
        getContentPane().add(btnInsertar);

        // Acción al presionar el botón
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar datos de entrada
                    int idPedido = Integer.parseInt(tfIdPedido.getText());
                    int precio = Integer.parseInt(tfPrecio.getText());
                    String piezaSolicitada = tfPiezaSolicitada.getText();
                    String matriculaVehiculo = tfMatriculaVehiculo.getText();
                    int mecanicoDni = Integer.parseInt(tfMecanicoDni.getText());
                    int facturaId = Integer.parseInt(tfFacturaId.getText());
                    int administradorDni = Integer.parseInt(tfAdministradorDni.getText());
                    int administradorMecanicoDni = Integer.parseInt(tfAdministradorMecanicoDni.getText());

                    // Obtener la fecha como string (DD/MM/YYYY)
                    String fechaStr = tfFechaVisita.getText();

                    // Insertar la orden en la base de datos
                    if (insertarOrden(idPedido, precio, fechaStr, piezaSolicitada, matriculaVehiculo, mecanicoDni, facturaId, administradorDni, administradorMecanicoDni)) {
                        JOptionPane.showMessageDialog(null, "Orden insertada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar la orden");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
                }
            }
        });
    }

    // Método para insertar una nueva orden en la base de datos
    public boolean insertarOrden(int idPedido, int precio, String fecha, String piezaSolicitada, String matriculaVehiculo, 
                                 int mecanicoDni, int facturaId, int administradorDni, int administradorMecanicoDni) {
        String sql = "INSERT INTO ordenes (id_pedido, precio, Fecha, Pieza_solicitada, vehiculo_Matricula, mecanico_dni, " +
                     "factura_id_factura, administrador_dni, administrador_mecanico_dni) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/derrap", "root", "root"); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, precio);
            stmt.setString(3, fecha); // Fecha como cadena de texto
            stmt.setString(4, piezaSolicitada);
            stmt.setString(5, matriculaVehiculo);
            stmt.setInt(6, mecanicoDni);
            stmt.setInt(7, facturaId);
            stmt.setInt(8, administradorDni);
            stmt.setInt(9, administradorMecanicoDni);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar la orden: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AñadirOrdenes().setVisible(true);
            }
        });
    }
}


