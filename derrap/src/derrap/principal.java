package derrap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private ConectarDB_mysql conexion = new ConectarDB_mysql();
    private JTextField dniField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    principal frame = new principal();
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
    public principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 681, 514);
        setLocationRelativeTo(null);
        contentPane = new JPanel() {
        	
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(getClass().getResource("Imagen/fondo1.png")).getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

    
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 128));
        panel.setBounds(247, 162, 153, 166);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("DNI");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 33, 46, 14);
        panel.add(lblNewLabel);

        dniField = new JTextField();
        dniField.setBackground(new Color(192, 192, 192));
        dniField.setBounds(10, 48, 133, 20);
        panel.add(dniField);
        dniField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("CONTRASEÑA");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(10, 79, 68, 14);
        panel.add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setBounds(10, 104, 133, 20);
        panel.add(passwordField);

        JButton btnNewButton = new JButton("INICIO");
        btnNewButton.setBackground(Color.DARK_GRAY);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBounds(31, 135, 89, 23);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText();
                String pass = new String(passwordField.getPassword());

                if (dni.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }

                try (Connection cn = conexion.conectar()) {
                    // Verificar si es Administrador
                    String adminQuery = "SELECT * FROM Administrador WHERE dni = ? AND Contraseña = ?";
                    try (PreparedStatement adminStmt = cn.prepareStatement(adminQuery)) {
                        adminStmt.setString(1, dni);
                        adminStmt.setString(2, pass);
                        ResultSet adminRs = adminStmt.executeQuery();

                        if (adminRs.next()) {
                            System.out.println("Acceso permitido para Administrador.");
                            Administrador adminFrame = new Administrador();
                            adminFrame.setVisible(true);
                            dispose();
                        } else {
                            // Verificar si es Mecánico
                            String mecanicoQuery = "SELECT * FROM Mecanico WHERE dni = ? AND Contraseña = ?";
                            try (PreparedStatement mecanicoStmt = cn.prepareStatement(mecanicoQuery)) {
                                mecanicoStmt.setString(1, dni);
                                mecanicoStmt.setString(2, pass);
                                ResultSet mecanicoRs = mecanicoStmt.executeQuery();

                                if (mecanicoRs.next()) {
                                    System.out.println("Acceso permitido para Mecánico: " + dni);
                                    Mecanico mecanicoFrame = new Mecanico(dni);
                                    mecanicoFrame.setVisible(true);
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error de credenciales. Intente nuevamente.");
                                }

                                mecanicoRs.close();
                            }
                        }

                        adminRs.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                }
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        panel_1.setBounds(247, 116, 153, 35);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("LOGIN");
        lblNewLabel_2.setBounds(35, 5, 108, 29);
        lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 23));
        lblNewLabel_2.setForeground(Color.WHITE);
        panel_1.add(lblNewLabel_2);
    }
}

