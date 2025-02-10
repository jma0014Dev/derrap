package derrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Mecanico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mecanico frame = new Mecanico("default_dni");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor para el frame del mecánico.
     * @param dni Identificación del mecánico que se pasa desde el login.
     */
    public Mecanico(String dni) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1078, 811);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Panel superior que contiene el encabezado y el botón "<--"
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(null);
        contentPane.add(topPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(142, 0, 900, 61);
        headerPanel.setBackground(new Color(162, 117, 102));
        headerPanel.setLayout(null);
        topPanel.add(headerPanel);

        JLabel lblTitulo = new JLabel("Menu Principal Mecánico");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTitulo.setBounds(8, 35, 300, 20);
        headerPanel.add(lblTitulo);

        // Botón "<--" para volver al login
        JButton atrasButton = new JButton("<--");
        atrasButton.setBounds(5, 5, 63, 23);
        atrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal loginFrame = new principal();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        headerPanel.add(atrasButton);

        // Panel lateral (se muestran los elementos, pero sin funcionalidad activa)
        JPanel lateralPanel = new JPanel();
        lateralPanel.setBounds(-7, 111, 99, 651);
        lateralPanel.setBackground(new Color(162, 117, 102));
        lateralPanel.setLayout(null);
        topPanel.add(lateralPanel);

        // Ejemplo de botón en lateral (sin acción activa)
        JButton btnFacturas = new JButton("Facturas");
        btnFacturas.setBounds(26, 53, 57, 57);
        lateralPanel.add(btnFacturas);

        JButton btnAgregarCoche = new JButton("Agregar Coche");
        btnAgregarCoche.setBounds(26, 195, 57, 57);
        lateralPanel.add(btnAgregarCoche);

        JButton btnAlmacen = new JButton("Almacén");
        btnAlmacen.setBounds(26, 127, 57, 57);
        lateralPanel.add(btnAlmacen);

        // Otros elementos gráficos (imágenes y botones, sin funcionalidad crítica)
        JButton btnVerCoches = new JButton("Ver coches en taller");
        btnVerCoches.setBounds(142, 243, 194, 34);
        topPanel.add(btnVerCoches);

        JButton btnVerOrdenes = new JButton("Ver órdenes asignadas");
        btnVerOrdenes.setBounds(416, 243, 194, 34);
        topPanel.add(btnVerOrdenes);

        JLabel lblCoche = new JLabel("");
        lblCoche.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\coche.png"));
        lblCoche.setBounds(142, 98, 194, 134);
        topPanel.add(lblCoche);

        JLabel lblHistorial = new JLabel("");
        lblHistorial.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\historial.png"));
        lblHistorial.setBounds(416, 89, 194, 143);
        topPanel.add(lblHistorial);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\logo.png"));
        lblLogo.setBounds(0, 0, 143, 112);
        topPanel.add(lblLogo);
    }
}



