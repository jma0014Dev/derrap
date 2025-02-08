package derrap;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class EliminarOrdenes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdPedido;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EliminarOrdenes frame = new EliminarOrdenes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EliminarOrdenes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 566, 227);
        setLocation(400, 100);
        
       
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
               
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), 0, height, new Color(220, 200, 190));
                g2.setPaint(gp);
                g2.fillRect(0, 0, width, height);
            }
        };
        backgroundPanel.setLayout(null);
        
      
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104), 2),
                "Eliminar Ordenes",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                new Color(162, 117, 104)
        );
        backgroundPanel.setBorder(titleBorder);
        
        setContentPane(backgroundPanel);
        contentPane = backgroundPanel;
        
     
        Border textFieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        );
        
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(7, 30, 100, 20);
        lblIdPedido.setForeground(new Color(162, 117, 104));
        contentPane.add(lblIdPedido);
        
        txtIdPedido = new JTextField();
        txtIdPedido.setBounds(117, 30, 200, 20);
        txtIdPedido.setBorder(textFieldBorder);
        contentPane.add(txtIdPedido);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(148, 60, 120, 30);
        btnEliminar.setBackground(new Color(164, 117, 104));
        btnEliminar.setForeground(Color.WHITE);
        contentPane.add(btnEliminar);
        
        btnEliminar.addActionListener(e -> eliminarOrden());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(130, 100, 160, 30);
        btnVolver.setBackground(new Color(164, 117, 104));
        btnVolver.setForeground(Color.WHITE);
        contentPane.add(btnVolver);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("Imagen/logo.png"));
        lblNewLabel.setBounds(384, 21, 158, 84);
        backgroundPanel.add(lblNewLabel);
        
        btnVolver.addActionListener(e -> volverAFacturas());
    }
    
    private void eliminarOrden() {
        int idPedido = Integer.parseInt(txtIdPedido.getText());
        
        String query = "DELETE FROM ordenes WHERE id_pedido = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idPedido);
            
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Orden eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la orden con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la orden.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volverAFacturas() {
        this.dispose(); 
        Ordenes ordenes = new Ordenes(); 
        ordenes.setVisible(true);
    }
}
