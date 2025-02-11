package derrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Mecanico_Stock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection;
    private JTabbedPane tabbedPaneStock;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mecanico_Stock frame = new Mecanico_Stock();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class GradientPanel extends JPanel {
        private Color[] colors;
        private float[] fractions;
        public GradientPanel(Color[] colors, float[] fractions) {
            this.colors = colors;
            this.fractions = fractions;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            LinearGradientPaint paint = new LinearGradientPaint(0, 0, width, 0, fractions, colors);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            super.paintComponent(g);
        }
    }

    class DefaultStockTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public Mecanico_Stock() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        conectarBaseDatos();
        JLabel logoEmpresa = new JLabel("");
        logoEmpresa.setIcon(new ImageIcon("Imagen/logo.png"));
        logoEmpresa.setBounds(0, 0, 134, 103);
        contentPane.add(logoEmpresa);
        JPanel barraSuperior = new GradientPanel(new Color[]{ new Color(120,80,60), new Color(180,150,130), new Color(120,80,60) }, new float[]{0f,0.5f,1f});
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(134, 0, 1216, 58);
        contentPane.add(barraSuperior);
        JLabel lblTitulo = new JLabel("Stock");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitulo.setBounds(8, 25, 226, 20);
        barraSuperior.add(lblTitulo);
        JButton btnVolver = new JButton("<--");
        btnVolver.setBounds(5, 5, 63, 23);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mecanico mecanicoFrame = new Mecanico("default_dni");
                mecanicoFrame.setVisible(true);
                dispose();
            }
        });

        barraSuperior.add(btnVolver);
        JPanel barraLateral = new GradientPanel(new Color[]{ new Color(120,80,60), new Color(180,150,130), new Color(120,80,60) }, new float[]{0f,0.5f,1f});
        barraLateral.setLayout(null);
        barraLateral.setBounds(-5, 103, 98, 631);
        contentPane.add(barraLateral);
        JButton btnAñadirCliente = new JButton("");
        btnAñadirCliente.setBounds(20, 11, 60, 50);
        ImageIcon originalIcon = new ImageIcon("Imagen\\agregar-usuario.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(btnAñadirCliente.getWidth(), btnAñadirCliente.getHeight(), Image.SCALE_SMOOTH);
        btnAñadirCliente.setIcon(new ImageIcon(scaledImg));
        btnAñadirCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AnadirClienteFrame(connection).setVisible(true);
            }
        });
        barraLateral.add(btnAñadirCliente);
        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("Imagen/añadir.png"));
        iconoUsuario.setBounds(10, 10, 50, 50);
        barraLateral.add(iconoUsuario);
        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("Imagen/factura.png"));
        iconoFactura.setBounds(10, 70, 50, 35);
        barraLateral.add(iconoFactura);
        tabbedPaneStock = new JTabbedPane();
        tabbedPaneStock.setBounds(160, 70, 1067, 600);
        contentPane.add(tabbedPaneStock);
        loadTabs();
        setLocationRelativeTo(null);
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

    private void loadTabs() {
        tabbedPaneStock.removeAll();
        try {
            String query = "SELECT DISTINCT Categoria FROM piezas_reparacion";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String categoria = rs.getString("Categoria");
                JPanel panel = new JPanel();
                panel.setLayout(null);
                DefaultStockTableModel model = new DefaultStockTableModel();
                model.addColumn("ID Pieza");
                model.addColumn("Precio");
                model.addColumn("Nombre");
                model.addColumn("Stock");
                model.addColumn("Marca");
                JTable table = new JTable(model);
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 0, 1050, 550);
                panel.add(scrollPane);
                loadDataForCategory(model, categoria);
                tabbedPaneStock.addTab(categoria, panel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + ex.getMessage());
        }
    }

    private void loadDataForCategory(DefaultTableModel model, String categoria) {
        try {
            String query = "SELECT id_pieza, Precio, Nombre, Stock, Marca FROM piezas_reparacion WHERE Categoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("id_pieza");
                row[1] = rs.getInt("Precio");
                row[2] = rs.getString("Nombre");
                row[3] = rs.getInt("Stock");
                row[4] = rs.getString("Marca");
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la categoría " + categoria + ": " + ex.getMessage());
        }
    }
}


