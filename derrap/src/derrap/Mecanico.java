package derrap;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

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
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Etiqueta en la parte superior
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        contentPane.add(topPanel, BorderLayout.CENTER);
        topPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(142, 0, 900, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Menu Principal Mecánico ");
        lblNewLabel.setBounds(8, 35, 300, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        // Botón para regresar al Login
        JButton atrasButton = new JButton("<--");
        atrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal loginFrame = new principal();
                loginFrame.setVisible(true);
                dispose(); // Cierra el frame actual
            }
        });
        atrasButton.setBounds(5, 5, 63, 23);
        panel.add(atrasButton);
        
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(162, 117, 102));
        panel_1.setBounds(-7, 111, 99, 651);
        panel_1.setBackground(new Color(162, 117, 102));
        topPanel.add(panel_1);
        panel_1.setLayout(null);
        
        JButton facturasButton = new JButton("Facturas");
        facturasButton.setBounds(26, 53, 57, 57);
        facturasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción del botón facturas
            }
        });
        panel_1.add(facturasButton);
        
        JButton agregarCocheButton = new JButton("Agregar Coche");
        agregarCocheButton.setBounds(26, 195, 57, 57);
        panel_1.add(agregarCocheButton);
        agregarCocheButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarCoche agregarCoche = new AgregarCoche();
                agregarCoche.setVisible(true);
            }
        });
        
        JButton almacenButton = new JButton("Almacén");
        almacenButton.setBounds(26, 127, 57, 57);
        panel_1.add(almacenButton);
        
        JButton verCocheButton = new JButton("Ver coches en taller");
        verCocheButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerCoches VerCoches2 = new VerCoches();
                VerCoches2.setVisible(true);
            }
        });
        verCocheButton.setBounds(142, 243, 194, 34);
        topPanel.add(verCocheButton);
        
        JButton verOrdenButton = new JButton("Ver órdenes asignadas");
        verOrdenButton.setBounds(416, 243, 194, 34);
        topPanel.add(verOrdenButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\coche.png"));
        lblNewLabel_1.setBounds(142, 98, 194, 134);
        topPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\historial.png"));
        lblNewLabel_2.setBounds(416, 89, 194, 143);
        topPanel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\logo.png"));
        lblNewLabel_3.setBounds(0, 0, 143, 112);
        topPanel.add(lblNewLabel_3);
        verOrdenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdenMecanico ordenMecanico = new OrdenMecanico();
                ordenMecanico.setVisible(true);
            }
        });
    }
}


