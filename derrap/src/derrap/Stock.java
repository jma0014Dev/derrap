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
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import derrap.Clientes.GradientPanel;

public class Stock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock frame = new Stock();
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
            // Creando un degradado horizontal (de izquierda a derecha)
            LinearGradientPaint paint = new LinearGradientPaint(
                    0, 0, width, 0, fractions, colors);
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            super.paintComponent(g);
        }
    }
	
	
	public Stock() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        // Content pane con layout absoluto
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
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
        // Usamos el GradientPanel en lugar de un JPanel normal
        JPanel barraSuperior = new GradientPanel(
                new Color[]{ new Color(120, 80, 60), new Color(180, 150, 130), new Color(120, 80, 60) },
                new float[]{ 0f, 0.5f, 1f }
        );
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(134, 0, 1216, 58);
        contentPane.add(barraSuperior);

        JLabel lblTitulo = new JLabel("Clientes");
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

        // Botón "Añadir Cliente" con imagen (se reescala la imagen al tamaño del botón)
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

        // Otros íconos en el panel lateral (puedes ajustar si lo deseas)
        JLabel iconoUsuario = new JLabel("");
        iconoUsuario.setIcon(new ImageIcon("Imagen/añadir.png"));
        iconoUsuario.setBounds(10, 10, 50, 50);
        barraLateral.add(iconoUsuario);

        JLabel iconoFactura = new JLabel("");
        iconoFactura.setIcon(new ImageIcon("Imagen/factura.png"));
        iconoFactura.setBounds(10, 70, 50, 35);
        barraLateral.add(iconoFactura);
	}
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
}
