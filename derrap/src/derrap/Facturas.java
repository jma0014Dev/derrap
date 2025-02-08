package derrap;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
        setLocation(400, 100);
        
        // Crear un panel con fondo degradado y borde titulado
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                // Degradado vertical: de un tono claro a un tono marrón suave
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), 0, height, new Color(220, 200, 190));
                g2.setPaint(gp);
                g2.fillRect(0, 0, width, height);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104), 2),
                "Factura",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                new Color(162, 117, 104)
        ));
        setContentPane(backgroundPanel);
        contentPane = backgroundPanel;
        
        // Label para mostrar la información de la factura
        JLabel lblFacturaInfo = new JLabel("<html>Cargando factura...</html>");
        lblFacturaInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFacturaInfo.setBounds(10, 30, 420, 200);
        contentPane.add(lblFacturaInfo);
        
        cargarFactura(idFactura, lblFacturaInfo);
        
        // Botón para volver a las órdenes (cierra esta ventana)
        JButton btnVolver = new JButton("Volver a Ordenes");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setBounds(150, 250, 150, 30);
        btnVolver.setBackground(new Color(164, 117, 104));
        btnVolver.setForeground(Color.WHITE);
        contentPane.add(btnVolver);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
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

