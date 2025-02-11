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
        atrasButton.setBackground(new Color(162, 117, 104));
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
        ImageIcon original6 = new ImageIcon("Imagen/Facturaas.png");
        Image scaledImage = original6.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon= new ImageIcon(scaledImage);
        
        JButton btn6 = new JButton("");
        btn6.setIcon(scaledIcon);
        
        btn6.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 // Crear una nueva instancia de Factura y hacerla visible
                 Ordenes frame = new Ordenes();
                 frame.setVisible(true);
                 dispose();
             }
         });
        btn6.setForeground(new Color(255, 255, 255));
        btn6.setBackground(new Color(162, 117, 104));
        btn6.setBounds(26, 88, 53, 55);
        lateralPanel.add(btn6);
//fotos cambiadas
        ImageIcon original0 = new ImageIcon("Imagen/almacen.png");
        Image scaledImage0 = original0.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon0= new ImageIcon(scaledImage0);
        
        JButton btnAlmacen = new JButton("");
        btnAlmacen.setIcon(scaledIcon0);
        
        btnAlmacen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Stock frame = new Stock();
                frame.setVisible(true);
                dispose();
        	}
        });
        btnAlmacen.setForeground(new Color(255, 255, 255));
        btnAlmacen.setBackground(new Color(162, 117, 104));
        btnAlmacen.setBounds(26, 159, 53, 55);
        lateralPanel.add(btnAlmacen);

        // Otros elementos gráficos (imágenes y botones, sin funcionalidad crítica)
        JButton btnVerCoches = new JButton("Ver coches en taller");
        btnVerCoches.setForeground(new Color(255, 255, 255));
        btnVerCoches.setBackground(new Color(162, 117, 104));
        btnVerCoches.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnVerCoches.setBounds(142, 243, 194, 34);
        topPanel.add(btnVerCoches);

        JButton btnVerOrdenes = new JButton("Ver órdenes asignadas");
        btnVerOrdenes.setForeground(new Color(255, 255, 255));
        btnVerOrdenes.setBackground(new Color(162, 117, 104));
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



