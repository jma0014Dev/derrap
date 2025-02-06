package derrap;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Administrador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection = null;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Administrador frame = new Administrador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Administrador() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1078, 811);
        conectarBaseDatos();
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        setLocation(0, 0);
        

        // Etiqueta en la parte superior
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        contentPane.add(topPanel, BorderLayout.CENTER);
        topPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(134, 0, 908, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Menu Principal-Administrador");
        lblNewLabel.setBounds(8, 35, 226, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        
        
        
        ImageIcon originalback = new ImageIcon("Imagen/back.png");
        Image scaledImageback = originalback.getImage().getScaledInstance(40,-5,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIconback= new ImageIcon(scaledImageback);
        JButton btnNewButton = new JButton("");
        
        btnNewButton.setIcon(scaledIconback);

        btnNewButton.setBackground(new Color(162, 117, 104));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(5, 5, 63, 23);
        panel.add(btnNewButton);
        
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(162, 117, 102));
        panel_1.setBounds(-7, 111, 99, 651);
        panel_1.setBackground(new Color(162, 117, 102));
        topPanel.add(panel_1);
        panel_1.setLayout(null);
        
        
        
        
        
        
        
        ImageIcon original5 = new ImageIcon("Imagen/añadirMecanico.png");
        Image scaledImage5 = original5.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon5= new ImageIcon(scaledImage5);
        
       JButton btn5 = new JButton("");
       btn5.setIcon(scaledIcon5);

       btn5.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               // Crear una nueva instancia de AñadirMecanico y hacerla visible
               AñadirMecanico frame = new AñadirMecanico();
               frame.setVisible(true);
               dispose();
           }
       });
       btn5.setForeground(new Color(255, 255, 255));
       btn5.setBackground(new Color(162, 117, 104));
       btn5.setBounds(26, 11, 53, 55);
       panel_1.add(btn5);

        
        
        
        
        
        
        
        
        ImageIcon original0 = new ImageIcon("Imagen/almacen.png");
        Image scaledImage0 = original0.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon0= new ImageIcon(scaledImage0);
        
        JButton bt5 = new JButton("");
        bt5.setIcon(scaledIcon0);
        
        bt5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        bt5.setForeground(new Color(255, 255, 255));
        bt5.setBackground(new Color(162, 117, 104));
        bt5.setBounds(26, 159, 53, 55);
        panel_1.add(bt5);
        

        
        
        
        
        
      
        ImageIcon original6 = new ImageIcon("Imagen/Facturaas.png");
        Image scaledImage = original6.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon= new ImageIcon(scaledImage);
        
        JButton btn6 = new JButton("");
        btn6.setIcon(scaledIcon);
        
        btn6.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 // Crear una nueva instancia de Factura y hacerla visible
                 Facturas frame = new Facturas();
                 frame.setVisible(true);
                 dispose();
             }
         });
        btn6.setForeground(new Color(255, 255, 255));
        btn6.setBackground(new Color(162, 117, 104));
        btn6.setBounds(26, 88, 53, 55);
        panel_1.add(btn6);
        
       
       
        
        
        
        
        
        
        
        JButton btn1 = new JButton("Ver coches");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn1.setBounds(142, 243, 194, 34);
        topPanel.add(btn1);
        
        JButton btn2 = new JButton("Ver mecanicos");
        btn2.setBounds(416, 243, 194, 34);
        topPanel.add(btn2);
        
        JButton btn4 = new JButton("Clientes");
        btn4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btn4.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        		        Clientes clientesFrame = new Clientes();
        		        clientesFrame.setVisible(true);
        		        dispose();
        		    }
        		});

        	}
        });
        btn4.setBounds(714, 243, 199, 34);
        topPanel.add(btn4);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("Imagen/coche.png"));
        lblNewLabel_1.setBounds(142, 84, 194, 160);
        topPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("Imagen/mecanico.png"));
        lblNewLabel_2.setBounds(416, 72, 194, 160);
        topPanel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon("Imagen/cliente.png"));
        lblNewLabel_4.setBounds(714, 100, 199, 132);
        topPanel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setBounds(-17, -13, 164, 124);
        topPanel.add(lblNewLabel_8);
        lblNewLabel_8.setIcon(new ImageIcon("Imagen/logo.png"));
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
}

