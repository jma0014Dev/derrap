package derrap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Stock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection;
    private JTabbedPane tabbedPaneStock;  // Pestañas para cada categoría
    // Campo para reutilizar el logo, barra superior y lateral (ya definidos en tu código base)

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Stock frame = new Stock();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Clase para un panel con degradado (usado en las barras superior y lateral)
    class GradientPanel extends JPanel {
        private Color[] colors;
        private float[] fractions;

        /**
         * @param colors    arreglo de colores (por ejemplo: [color1, color2, color3])
         * @param fractions arreglo de paradas, debe tener la misma cantidad de elementos que colors
         */
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
            // Degradado horizontal (de izquierda a derecha)
            LinearGradientPaint paint = new LinearGradientPaint(0, 0, width, 0, fractions, colors);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            super.paintComponent(g);
        }
    }

    // Modelo de tabla que permite editar únicamente la columna "Precio" (índice 1)
    class PriceEditableTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 1; // Solo la columna "Precio" es editable
        }
    }

    public Stock() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Layout absoluto
        setContentPane(contentPane);

        // Conectar a la base de datos
        conectarBaseDatos();

        // ===============================
        // Logo de la Empresa (Esquina Superior Izquierda)
        // ===============================
        JLabel logoEmpresa = new JLabel("");
        logoEmpresa.setIcon(new ImageIcon("Imagen/logo.png"));
        logoEmpresa.setBounds(0, 0, 134, 103);
        contentPane.add(logoEmpresa);

        // ===============================
        // Panel Superior (Barra Superior) con degradado
        // ===============================
        JPanel barraSuperior = new GradientPanel(
                new Color[]{ new Color(120, 80, 60), new Color(180, 150, 130), new Color(120, 80, 60) },
                new float[]{ 0f, 0.5f, 1f }
        );
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(134, 0, 1216, 58);
        contentPane.add(barraSuperior);

        JLabel lblTitulo = new JLabel("Stock");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitulo.setBounds(8, 25, 226, 20);
        barraSuperior.add(lblTitulo);

        JButton btnVolver = new JButton("<--");
        btnVolver.setBounds(5, 5, 63, 23);
        btnVolver.addActionListener(e -> {
            // Se asume que tienes la ventana Administrador implementada
            Administrador administradorFrame = new Administrador();
            administradorFrame.setVisible(true);
            dispose();
        });
        barraSuperior.add(btnVolver);

        // ===============================
        // Panel Lateral (Barra Lateral) con degradado
        // ===============================
        JPanel barraLateral = new GradientPanel(
                new Color[]{ new Color(120, 80, 60), new Color(180, 150, 130), new Color(120, 80, 60) },
                new float[]{ 0f, 0.5f, 1f }
        );
        barraLateral.setLayout(null);
        barraLateral.setBounds(-5, 103, 98, 631);
        contentPane.add(barraLateral);

        // Botón "Añadir Cliente" (lo dejamos para navegación, tal como en tu código base)
        JButton btnAñadirCliente = new JButton("");
        btnAñadirCliente.setBounds(20, 11, 60, 50);
        ImageIcon originalIcon = new ImageIcon("Imagen\\agregar-usuario.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(btnAñadirCliente.getWidth(), btnAñadirCliente.getHeight(), Image.SCALE_SMOOTH);
        btnAñadirCliente.setIcon(new ImageIcon(scaledImg));
        btnAñadirCliente.addActionListener(e -> {
            new AnadirClienteFrame(connection).setVisible(true);
        });
        barraLateral.add(btnAñadirCliente);

        // Otros íconos en el panel lateral (opcional)
        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("Imagen/añadir.png"));
        iconoUsuario.setBounds(10, 10, 50, 50);
        barraLateral.add(iconoUsuario);

        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("Imagen/factura.png"));
        iconoFactura.setBounds(10, 70, 50, 35);
        barraLateral.add(iconoFactura);

        // ===============================
        // Panel Principal: Pestañas de Stock
        // ===============================
        tabbedPaneStock = new JTabbedPane();
        tabbedPaneStock.setBounds(160, 70, 1067, 600);
        contentPane.add(tabbedPaneStock);
        loadTabs();  // Carga las pestañas según las categorías

        // ===============================
        // Panel de Control (botones "Agregar Pieza" y "Guardar Precio")
        // ===============================
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(160, 680, 1067, 50);
        contentPane.add(controlPanel);

        JButton btnAgregarPieza = new JButton("Agregar Pieza");
        btnAgregarPieza.setBounds(100, 10, 150, 30);
        btnAgregarPieza.addActionListener(e -> {
            int index = tabbedPaneStock.getSelectedIndex();
            if (index != -1) {
                String categoria = tabbedPaneStock.getTitleAt(index);
                new AddPieceDialog(connection, categoria).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una categoría en la pestaña.");
            }
        });
        controlPanel.add(btnAgregarPieza);

        JButton btnGuardarPrecio = new JButton("Guardar Precio");
        btnGuardarPrecio.setBounds(300, 10, 150, 30);
        btnGuardarPrecio.addActionListener(e -> {
            int index = tabbedPaneStock.getSelectedIndex();
            if (index != -1) {
                JPanel panel = (JPanel) tabbedPaneStock.getComponentAt(index);
                // Se asume que el JScrollPane es el primer (y único) componente del panel
                JScrollPane sp = (JScrollPane) panel.getComponent(0);
                JTable table = (JTable) sp.getViewport().getView();
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int idPieza = (int) table.getValueAt(selectedRow, 0);
                    int newPrice = Integer.parseInt(table.getValueAt(selectedRow, 1).toString());
                    updatePriceForPiece(idPieza, newPrice);
                    JOptionPane.showMessageDialog(this, "Precio actualizado.");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una pieza para actualizar el precio.");
                }
            }
        });
        controlPanel.add(btnGuardarPrecio);

        // Centrar la ventana
        setLocationRelativeTo(null);
    }

    // Método para conectar a la base de datos
    private void conectarBaseDatos() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/derrap";
            String DB_USER = "root";
            String DB_PASSWORD = "Medac123";
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al conectar con la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga las pestañas de stock, una por cada categoría existente
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
                PriceEditableTableModel model = new PriceEditableTableModel();
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

    // Carga los datos de una categoría en el modelo de la tabla
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

    // Actualiza el precio de una pieza en la base de datos
    private void updatePriceForPiece(int idPieza, int newPrice) {
        try {
            String query = "UPDATE piezas_reparacion SET Precio = ? WHERE id_pieza = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newPrice);
            ps.setInt(2, idPieza);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el precio: " + ex.getMessage());
        }
    }

    // Método para refrescar las pestañas (por ejemplo, después de agregar una pieza nueva)
    private void refreshTabs() {
        loadTabs();
    }

    // Diálogo para agregar una nueva pieza (solo se piden los datos básicos; se usa la categoría de la pestaña actual)
    class AddPieceDialog extends javax.swing.JDialog {
        private Connection connection;
        private String categoria;
        private JTextField txtIdPieza;
        private JTextField txtPrecio;
        private JTextField txtNombre;
        private JTextField txtStock;
        private JTextField txtMarca;

        public AddPieceDialog(Connection connection, String categoria) {
            this.connection = connection;
            this.categoria = categoria;
            setTitle("Agregar Nueva Pieza - Categoría: " + categoria);
            setModal(true);
            setBounds(100, 100, 400, 300);
            getContentPane().setLayout(null);
            setLocationRelativeTo(null);

            JLabel lblId = new JLabel("ID Pieza:");
            lblId.setBounds(20, 20, 100, 25);
            getContentPane().add(lblId);

            txtIdPieza = new JTextField();
            txtIdPieza.setBounds(130, 20, 200, 25);
            getContentPane().add(txtIdPieza);

            JLabel lblPrecio = new JLabel("Precio:");
            lblPrecio.setBounds(20, 60, 100, 25);
            getContentPane().add(lblPrecio);

            txtPrecio = new JTextField();
            txtPrecio.setBounds(130, 60, 200, 25);
            getContentPane().add(txtPrecio);

            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(20, 100, 100, 25);
            getContentPane().add(lblNombre);

            txtNombre = new JTextField();
            txtNombre.setBounds(130, 100, 200, 25);
            getContentPane().add(txtNombre);

            JLabel lblStock = new JLabel("Stock:");
            lblStock.setBounds(20, 140, 100, 25);
            getContentPane().add(lblStock);

            txtStock = new JTextField();
            txtStock.setBounds(130, 140, 200, 25);
            getContentPane().add(txtStock);

            JLabel lblMarca = new JLabel("Marca:");
            lblMarca.setBounds(20, 180, 100, 25);
            getContentPane().add(lblMarca);

            txtMarca = new JTextField();
            txtMarca.setBounds(130, 180, 200, 25);
            getContentPane().add(txtMarca);

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(130, 220, 100, 30);
            btnGuardar.addActionListener(e -> {
                guardarNuevaPieza();
                dispose();
                refreshTabs();
            });
            getContentPane().add(btnGuardar);
        }

        private void guardarNuevaPieza() {
            try {
                int idPieza = Integer.parseInt(txtIdPieza.getText());
                int precio = Integer.parseInt(txtPrecio.getText());
                String nombre = txtNombre.getText();
                int stock = Integer.parseInt(txtStock.getText());
                String marca = txtMarca.getText();

                // Valores predeterminados para las claves foráneas
                int idPedido = 1;
                int mecanicoDni = 87654321;
                String vehiculoMatricula = "123567";
                int facturaId = 1;

                // Insertar la nueva pieza
                String query = "INSERT INTO piezas_reparacion (id_pieza, Precio, Nombre, Stock, Marca, Categoria, ordenes_id_pedido, ordenes_mecanico_dni, ordenes_vehiculo_Matricula, ordenes_factura_id_factura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, idPieza);
                ps.setInt(2, precio);
                ps.setString(3, nombre);
                ps.setInt(4, stock);
                ps.setString(5, marca);
                ps.setString(6, categoria);
                ps.setInt(7, idPedido);
                ps.setInt(8, mecanicoDni);
                ps.setString(9, vehiculoMatricula);
                ps.setInt(10, facturaId);
                int count = ps.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Nueva pieza agregada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar la pieza.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}
