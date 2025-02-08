package derrap;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class VerOrdenes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> listaOrdenes;
    private DefaultListModel<String> modeloLista;
    
    private static final String URL = "jdbc:mysql://localhost:3306/derrap";
    private static final String USER = "root";
    private static final String PASSWORD = "Medac123"; // Cambiar si es necesario

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerOrdenes frame = new VerOrdenes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VerOrdenes() {
        setTitle("Ver Órdenes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 509);
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
        backgroundPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(162, 117, 104), 2),
                "Ver Órdenes",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                new Color(162, 117, 104)
        );
        backgroundPanel.setBorder(titleBorder);
        
        setContentPane(backgroundPanel);
        contentPane = backgroundPanel;
        
        modeloLista = new DefaultListModel<>();
        
        cargarOrdenes();
        
        // Botón para volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBounds(204, 349, 120, 30);
        btnVolver.setBackground(new Color(164, 117, 104));
        btnVolver.setForeground(Color.WHITE);
        contentPane.add(btnVolver);
        listaOrdenes = new JList<>(modeloLista);
        listaOrdenes.setBounds(34, 24, 458, 318);
        backgroundPanel.add(listaOrdenes);
        listaOrdenes.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("Imagen/logo.png"));
        lblNewLabel.setBounds(512, 24, 188, 101);
        backgroundPanel.add(lblNewLabel);
        
        listaOrdenes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedOrder = listaOrdenes.getSelectedValue();
                    if (selectedOrder != null) {
                        mostrarFactura(selectedOrder);
                    }
                }
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ordenes frame = new Ordenes();
                frame.setVisible(true);
                dispose();
            }
        });
    }
    
    private void cargarOrdenes() {
        String query = "SELECT id_pedido FROM ordenes";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                modeloLista.addElement("Orden ID: " + rs.getInt("id_pedido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarFactura(String ordenSeleccionada) {
        int idPedido = Integer.parseInt(ordenSeleccionada.replace("Orden ID: ", ""));
        
        String query = "SELECT factura_id_factura FROM ordenes WHERE id_pedido = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int idFactura = rs.getInt("factura_id_factura");
                // Abre la ventana de factura. Asegúrate de tener la clase Facturas con el constructor adecuado.
                Facturas factura = new Facturas(idFactura);
                factura.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

