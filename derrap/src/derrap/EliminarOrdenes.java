package derrap;

import java.awt.EventQueue;
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

public class EliminarOrdenes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdPedido;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; // Cambiar si es necesario

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
        setBounds(100, 100, 450, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocation(400, 100);
        
        
        JLabel lblIdPedido = new JLabel("ID Pedido:");
        lblIdPedido.setBounds(10, 10, 100, 20);
        contentPane.add(lblIdPedido);
        
        txtIdPedido = new JTextField();
        txtIdPedido.setBounds(120, 10, 200, 20);
        contentPane.add(txtIdPedido);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(150, 50, 120, 30);
        contentPane.add(btnEliminar);
        
        btnEliminar.addActionListener(e -> eliminarOrden());
        
        JButton btnVolver = new JButton("Volver ");
        btnVolver.setBounds(130, 100, 160, 30);
        contentPane.add(btnVolver);
        
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