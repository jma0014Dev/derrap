package derrap;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ActualizarOrdenes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdPedido;
    private JTextField txtPrecio;
    private JTextField txtFecha;
    private JTextField txtPieza;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ActualizarOrdenes frame = new ActualizarOrdenes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ActualizarOrdenes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 496, 367);
        setLocation(400, 100);

        // Crear un panel con fondo degradado
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

        // Agregar un borde titulado decorativo al panel
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104), 2),
                "Actualizar Ordenes",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                new Color(162, 117, 104)
        );
        backgroundPanel.setBorder(titleBorder);

        // Establecer el panel como contentPane
        setContentPane(backgroundPanel);
        contentPane = backgroundPanel;  // para compatibilidad

        // Borde decorativo para los JTextField
        Border textFieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        );
        
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(10, 36, 100, 20);
        lblIdPedido.setForeground(new Color(162, 117, 104));
        contentPane.add(lblIdPedido);
        
        txtIdPedido = new JTextField();
        txtIdPedido.setBounds(120, 36, 200, 20);
        txtIdPedido.setBorder(textFieldBorder);
        contentPane.add(txtIdPedido);
        
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 66, 100, 20);
        lblPrecio.setForeground(new Color(162, 117, 104));
        contentPane.add(lblPrecio);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 66, 200, 20);
        txtPrecio.setBorder(textFieldBorder);
        contentPane.add(txtPrecio);
        
        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(10, 96, 150, 20);
        lblFecha.setForeground(new Color(162, 117, 104));
        contentPane.add(lblFecha);
        
        txtFecha = new JTextField();
        txtFecha.setBounds(141, 96, 160, 20);
        txtFecha.setBorder(textFieldBorder);
        contentPane.add(txtFecha);
        
        JLabel lblPieza = new JLabel("Pieza Solicitada:");
        lblPieza.setBounds(10, 126, 100, 20);
        lblPieza.setForeground(new Color(162, 117, 104));
        contentPane.add(lblPieza);
        
        txtPieza = new JTextField();
        txtPieza.setBounds(120, 126, 200, 20);
        txtPieza.setBorder(textFieldBorder);
        contentPane.add(txtPieza);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(164, 117, 104));
        btnActualizar.setBounds(153, 162, 120, 30);
        contentPane.add(btnActualizar);
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(164, 117, 104));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ordenes frame = new Ordenes();
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(163, 202, 89, 23);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(330, 10, 142, 61);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("Imagen/logo.png"));
        lblNewLabel_1.setBounds(330, 15, 142, 88);
        contentPane.add(lblNewLabel_1);
        
        btnActualizar.addActionListener(e -> actualizarOrden());
    }
    
    private void actualizarOrden() {
        int idPedido = Integer.parseInt(txtIdPedido.getText());
        int precio = Integer.parseInt(txtPrecio.getText());
        String fecha = txtFecha.getText();
        String pieza = txtPieza.getText();
        
        String query = "UPDATE ordenes SET precio = ?, Fecha = ?, Pieza_solicitada = ? WHERE id_pedido = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, precio);
            stmt.setString(2, fecha);
            stmt.setString(3, pieza);
            stmt.setInt(4, idPedido);
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Orden actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la orden con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la orden.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


