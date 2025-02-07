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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActualizarOrdenes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdPedido;
    private JTextField txtPrecio;
    private JTextField txtFecha;
    private JTextField txtPieza;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; // Cambiar si es necesario

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
        setBounds(100, 100, 450, 300);
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
        
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 40, 100, 20);
        contentPane.add(lblPrecio);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 40, 200, 20);
        contentPane.add(txtPrecio);
        
        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(10, 70, 150, 20);
        contentPane.add(lblFecha);
        
        txtFecha = new JTextField();
        txtFecha.setBounds(160, 70, 160, 20);
        contentPane.add(txtFecha);
        
        JLabel lblPieza = new JLabel("Pieza Solicitada:");
        lblPieza.setBounds(10, 100, 100, 20);
        contentPane.add(lblPieza);
        
        txtPieza = new JTextField();
        txtPieza.setBounds(120, 100, 200, 20);
        contentPane.add(txtPieza);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(150, 140, 120, 30);
        contentPane.add(btnActualizar);
        
        JButton btnNewButton = new JButton("Atras");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 Ordenes frame = new Ordenes();
	               frame.setVisible(true);
	               dispose();
        	}
        });
        btnNewButton.setBounds(160, 181, 89, 23);
        contentPane.add(btnNewButton);
        
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

