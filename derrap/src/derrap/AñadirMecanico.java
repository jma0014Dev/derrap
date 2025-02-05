package derrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AñadirMecanico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txtDni;
    private JTextField txtelefono;
    private JPasswordField passwordField;
    private Connection connection = null;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AñadirMecanico frame = new AñadirMecanico();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AñadirMecanico() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 900);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        setLocation(400, 100);

        // Etiqueta en la parte superior
        JPanel topPanel = new JPanel();
        topPanel.setToolTipText("");
        topPanel.setBackground(Color.WHITE);
        contentPane.add(topPanel, BorderLayout.CENTER);
        topPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(150, 0, 1024, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Añadir Mecanico");
        lblNewLabel.setBounds(8, 35, 226, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        
        ImageIcon originalback = new ImageIcon("Imagen/back.png");
        Image scaledImageback = originalback.getImage().getScaledInstance(40,-5,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIconback= new ImageIcon(scaledImageback);
        JButton btnNewButton = new JButton("");
        btnNewButton.setForeground(new Color(162, 117, 102));
        
        btnNewButton.setIcon(scaledIconback);

        btnNewButton.setBackground(new Color(162, 117, 102));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	   Administrador adminFrame = new Administrador();
                   adminFrame.setVisible(true);
                   
                 
                   dispose();
            }
        });
        btnNewButton.setBounds(5, 5, 63, 23);
        panel.add(btnNewButton);
        
        

        JLabel label1 = new JLabel("Nombre");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        label1.setBounds(134, 121, 99, 37);
        topPanel.add(label1);

        txt1 = new JTextField();
        txt1.setText("");
        txt1.setBounds(122, 168, 260, 61);
        topPanel.add(txt1);
        txt1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Apellido");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel_1.setBounds(134, 262, 99, 37);
        topPanel.add(lblNewLabel_1);

        txt2 = new JTextField();
        txt2.setBounds(122, 307, 260, 61);
        topPanel.add(txt2);
        txt2.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("DNI");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel_2.setBounds(134, 415, 99, 27);
        topPanel.add(lblNewLabel_2);

        JTextField txtDni = new JTextField();
        txtDni.setText("Debe incluir 8 números y una letra");
        txtDni.setForeground(Color.GRAY);
        txtDni.setBounds(122, 464, 258, 61);
        txtDni.setColumns(10);

        txtDni.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDni.getText().equals("Debe incluir 8 números y una letra")) {
                    txtDni.setText("");
                    txtDni.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtDni.getText().isEmpty()) {
                    txtDni.setText("Debe incluir 8 números y una letra");
                    txtDni.setForeground(Color.GRAY);
                }
            }
        });
        

        topPanel.add(txtDni);
        
        
        

        JLabel lblNewLabel_3 = new JLabel("Nº de Telefono");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel_3.setBounds(728, 124, 208, 31);
        topPanel.add(lblNewLabel_3);

        JTextField txtelefono = new JTextField();
        txtelefono.setText("Debe incluir 9 digitos");
        txtelefono.setForeground(Color.GRAY);
        txtelefono.setBounds(705, 168, 295, 61);
        txtelefono.setColumns(10);

        txtelefono.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtelefono.getText().equals("Debe incluir 9 digitos")) {
                	txtelefono.setText("");
                	txtelefono.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtelefono.getText().isEmpty()) {
                	txtelefono.setText("Debe incluir 9 digitos");
                	txtelefono.setForeground(Color.GRAY);
                }
            }
        });

        topPanel.add(txtelefono);

        
        
        
      
        
        
        JLabel lblNewLabel_4 = new JLabel("Contraseña");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel_4.setBounds(728, 262, 151, 37);
        topPanel.add(lblNewLabel_4);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setBounds(705, 307, 295, 61);
      
        topPanel.add(passwordField);

        JButton btn1 = new JButton("Insertar mecanico");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	conectarBaseDatos();
                insertarMecanico();
                JOptionPane.showMessageDialog(null, "Mecánico añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        btn1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btn1.setBackground(new Color(0, 0, 0));
        btn1.setForeground(new Color(255, 255, 255));
        btn1.setBounds(120, 561, 260, 37);
        topPanel.add(btn1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setForeground(new Color(162, 117, 102));
        panel_1.setBackground(new Color(162, 117, 102));
        panel_1.setBounds(0, 109, 99, 757);
        topPanel.add(panel_1);
        
        ImageIcon original5 = new ImageIcon("Imagen/añadirMecanico.png");
        Image scaledImage5 = original5.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon5= new ImageIcon(scaledImage5);
        
       JButton btn5 = new JButton("");
       btn5.setIcon(scaledIcon5);
       btn5.setEnabled(false);
       btn5.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               
           }
       });
       
       btn5.setBounds(26, 11, 53, 55);
       panel_1.add(btn5);
       
       
       
       
       
       
        btn5.setForeground(Color.WHITE);
        btn5.setBackground(new Color(192, 192, 192));
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
        bt5.setForeground(new Color(162, 117, 102));
        bt5.setBackground(new Color(162, 117, 102));
        bt5.setBounds(26, 159, 53, 55);
        panel_1.add(bt5);
        
        
        bt5.setForeground(Color.WHITE);
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
        	}
        });
        btn6.setForeground(new Color(162, 117, 102));
        btn6.setBackground(new Color(162, 117, 102));
        btn6.setBounds(26, 88, 53, 55);
        panel_1.add(btn6);
        
        
        
        
        
        btn6.setForeground(Color.WHITE);
        btn6.setBounds(26, 88, 53, 55);
        panel_1.add(btn6);
        
        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setIcon(new ImageIcon("Imagen/logo.png"));
        lblNewLabel_8.setBounds(0, 0, 197, 110);
        topPanel.add(lblNewLabel_8);
        
        
    }

    private void insertarMecanico() {
        String nombre = txt1.getText();
        String apellido = txt2.getText();
        String dni = txtDni.getText();
        String telefono = txtelefono.getText();
      
        String contraseña = new String(passwordField.getPassword());
        

        // Validar campos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || contraseña.isEmpty()) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }

        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/derrap";
        String user = "root";
        String password = "Medac123";

        String query = "INSERT INTO mecanico (nombre, apellido, dni, contraseña) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, dni);
            stmt.setString(4, contraseña);
        

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Mecánico insertado exitosamente.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar mecánico.");
        }
    }
    private void conectarBaseDatos() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/derrap";
            String DB_USER = "root";
            String DB_PASSWORD = "root";

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
        	
        	
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

