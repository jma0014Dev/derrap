package derrap;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Facturas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; // Cambiar si es necesario

    public Facturas(int idFactura) {
        setTitle("Factura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocation(400, 100);
        
        // Label para mostrar la información de la factura con formato HTML
        JLabel lblFacturaInfo = new JLabel("<html>Cargando factura...</html>");
        lblFacturaInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFacturaInfo.setBounds(10, 10, 420, 200);
        contentPane.add(lblFacturaInfo);
        
        cargarFactura(idFactura, lblFacturaInfo);
        
        // Botón para volver a las órdenes (cierra esta ventana)
        JButton btnVolver = new JButton("Volver a Ordenes");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setBounds(150, 250, 150, 30);
        contentPane.add(btnVolver);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual y regresa a la ventana de órdenes (que debe estar abierta)
            }
        });
    }
    
    private void cargarFactura(int idFactura, JLabel label) {
        String query = "SELECT * FROM factura WHERE id_factura = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String fecha = rs.getString("Fecha");
                int precio = rs.getInt("Precio");
                String fechaPago = rs.getString("Fecha_pago");
                String metodoPago = rs.getString("Metodo_pago");
                
                String facturaInfo = "<html>"
                        + "<h2>Factura ID: " + idFactura + "</h2>"
                        + "<p><strong>Fecha:</strong> " + fecha + "<br>"
                        + "<strong>Precio:</strong> " + precio + "€<br>"
                        + "<strong>Fecha de Pago:</strong> " + fechaPago + "<br>"
                        + "<strong>Método de Pago:</strong> " + metodoPago + "</p>"
                        + "</html>";
                
                label.setText(facturaInfo);
            } else {
                label.setText("<html><h2>Factura no encontrada.</h2></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            label.setText("<html><h2>Error al cargar la factura.</h2></html>");
        }
    }
}

