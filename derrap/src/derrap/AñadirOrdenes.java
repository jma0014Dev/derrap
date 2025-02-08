package derrap;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AñadirOrdenes extends JFrame {

    private JTextField tfIdPedido, tfPrecio, tfPiezaSolicitada, tfFechaVisita, tfMatriculaVehiculo,
                       tfMecanicoDni, tfFacturaId, tfAdministradorDni, tfAdministradorMecanicoDni;

    public AñadirOrdenes() {
        setTitle("Insertar Nueva Orden");
        setSize(556, 504);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);

       
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
               
                Color startColor = new Color(245, 245, 245);
                Color endColor = new Color(220, 200, 190);
                GradientPaint gp = new GradientPaint(0, 0, startColor, 0, height, endColor);
                g2.setPaint(gp);
                g2.fillRect(0, 0, width, height);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

       
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104), 2),
                "Insertar Nueva Orden",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                new Color(162, 117, 104)
        );
        backgroundPanel.setBorder(titleBorder);

      
        tfIdPedido = new JTextField(10);
        tfPrecio = new JTextField(10);
        tfPiezaSolicitada = new JTextField(10);
        tfFechaVisita = new JTextField(10);
        tfFechaVisita.setText("(YYY-MM-DD)");
        tfMatriculaVehiculo = new JTextField(10);
        tfMecanicoDni = new JTextField(10);
        tfFacturaId = new JTextField(10);
        tfAdministradorDni = new JTextField(10);
        tfAdministradorMecanicoDni = new JTextField(10);

        
        Border textFieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        );
        tfIdPedido.setBorder(textFieldBorder);
        tfPrecio.setBorder(textFieldBorder);
        tfPiezaSolicitada.setBorder(textFieldBorder);
        tfFechaVisita.setBorder(textFieldBorder);
        tfMatriculaVehiculo.setBorder(textFieldBorder);
        tfMecanicoDni.setBorder(textFieldBorder);
        tfFacturaId.setBorder(textFieldBorder);
        tfAdministradorDni.setBorder(textFieldBorder);
        tfAdministradorMecanicoDni.setBorder(textFieldBorder);

        
        JButton btnInsertar = new JButton("Insertar Orden");
        btnInsertar.setBackground(new Color(162, 117, 104));
        btnInsertar.setForeground(Color.WHITE);

        
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(20, 20, 100, 25);
        lblIdPedido.setForeground(new Color(162, 117, 104));
        tfIdPedido.setBounds(140, 20, 150, 25);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 60, 100, 25);
        lblPrecio.setForeground(new Color(162, 117, 104));
        tfPrecio.setBounds(140, 60, 150, 25);

        JLabel lblPiezaSolicitada = new JLabel("Pieza solicitada:");
        lblPiezaSolicitada.setBounds(20, 100, 120, 25);
        lblPiezaSolicitada.setForeground(new Color(162, 117, 104));
        tfPiezaSolicitada.setBounds(140, 100, 150, 25);

        JLabel lblFechaVisita = new JLabel("Fecha visita:");
        lblFechaVisita.setBounds(20, 140, 180, 25);
        lblFechaVisita.setForeground(new Color(162, 117, 104));
        tfFechaVisita.setBounds(140, 140, 150, 25);

        JLabel lblMatriculaVehiculo = new JLabel("Matrícula Vehículo:");
        lblMatriculaVehiculo.setBounds(20, 180, 120, 25);
        lblMatriculaVehiculo.setForeground(new Color(162, 117, 104));
        tfMatriculaVehiculo.setBounds(140, 180, 150, 25);

        JLabel lblMecanicoDni = new JLabel("Mecánico DNI:");
        lblMecanicoDni.setBounds(20, 220, 120, 25);
        lblMecanicoDni.setForeground(new Color(162, 117, 104));
        tfMecanicoDni.setBounds(140, 220, 150, 25);

        JLabel lblFacturaId = new JLabel("Factura ID:");
        lblFacturaId.setBounds(20, 260, 120, 25);
        lblFacturaId.setForeground(new Color(162, 117, 104));
        tfFacturaId.setBounds(140, 260, 150, 25);

        JLabel lblAdministradorDni = new JLabel("Administrador DNI:");
        lblAdministradorDni.setBounds(20, 300, 120, 25);
        lblAdministradorDni.setForeground(new Color(162, 117, 104));
        tfAdministradorDni.setBounds(140, 300, 150, 25);

        JLabel lblAdministradorMecanicoDni = new JLabel("Admin Mecánico DNI:");
        lblAdministradorMecanicoDni.setBounds(20, 340, 140, 25);
        lblAdministradorMecanicoDni.setForeground(new Color(162, 117, 104));
        tfAdministradorMecanicoDni.setBounds(140, 340, 150, 25);

        btnInsertar.setBounds(120, 380, 150, 30);

        
        backgroundPanel.add(lblIdPedido);
        backgroundPanel.add(tfIdPedido);
        backgroundPanel.add(lblPrecio);
        backgroundPanel.add(tfPrecio);
        backgroundPanel.add(lblPiezaSolicitada);
        backgroundPanel.add(tfPiezaSolicitada);
        backgroundPanel.add(lblFechaVisita);
        backgroundPanel.add(tfFechaVisita);
        backgroundPanel.add(lblMatriculaVehiculo);
        backgroundPanel.add(tfMatriculaVehiculo);
        backgroundPanel.add(lblMecanicoDni);
        backgroundPanel.add(tfMecanicoDni);
        backgroundPanel.add(lblFacturaId);
        backgroundPanel.add(tfFacturaId);
        backgroundPanel.add(lblAdministradorDni);
        backgroundPanel.add(tfAdministradorDni);
        backgroundPanel.add(lblAdministradorMecanicoDni);
        backgroundPanel.add(tfAdministradorMecanicoDni);
        backgroundPanel.add(btnInsertar);
        
        JButton btnAtras = new JButton("Volver");
        btnAtras.setBounds(317, 384, 89, 23);
        btnAtras.setBackground(new Color(162, 117, 104));
        btnAtras.setForeground(Color.WHITE);
        backgroundPanel.add(btnAtras);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("Imagen/logo.png"));
        lblNewLabel.setBounds(375, 20, 157, 96);
        backgroundPanel.add(lblNewLabel);

        
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ordenes frame = new Ordenes();
                frame.setVisible(true);
                dispose();
            }
        });

        
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idPedido = Integer.parseInt(tfIdPedido.getText());
                    int precio = Integer.parseInt(tfPrecio.getText());
                    String piezaSolicitada = tfPiezaSolicitada.getText();
                    String matriculaVehiculo = tfMatriculaVehiculo.getText();
                    int mecanicoDni = Integer.parseInt(tfMecanicoDni.getText());
                    int facturaId = Integer.parseInt(tfFacturaId.getText());
                    int administradorDni = Integer.parseInt(tfAdministradorDni.getText());
                    int administradorMecanicoDni = Integer.parseInt(tfAdministradorMecanicoDni.getText());
                    String fechaStr = tfFechaVisita.getText();

                    if (insertarOrden(idPedido, precio, fechaStr, piezaSolicitada, matriculaVehiculo,
                                      mecanicoDni, facturaId, administradorDni, administradorMecanicoDni)) {
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

  
    public boolean insertarOrden(int idPedido, int precio, String fecha, String piezaSolicitada,
                                 String matriculaVehiculo, int mecanicoDni, int facturaId,
                                 int administradorDni, int administradorMecanicoDni) {
        String sql = "INSERT INTO ordenes (id_pedido, precio, Fecha, Pieza_solicitada, vehiculo_Matricula, " +
                     "mecanico_dni, factura_id_factura, administrador_dni, administrador_mecanico_dni) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhOst:3306/derrap", "root", "Medac123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, precio);
            stmt.setString(3, fecha);
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
        SwingUtilities.invokeLater(() -> new AñadirOrdenes().setVisible(true));
    }
}
