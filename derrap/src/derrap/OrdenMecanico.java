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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class OrdenMecanico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	OrdenMecanico frame = new OrdenMecanico();
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
    public OrdenMecanico() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

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
        panel.setBounds(134, 0, 908, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Ordenes");
        lblNewLabel.setBounds(8, 35, 226, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JButton btnNewButton = new JButton("<--");
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
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(26, 5, 30, 22);
        panel_1.add(comboBox);
        
        JButton btn5 = new JButton("Facturas");
        btn5.setBounds(26, 53, 57, 57);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_1.add(btn5);
        
        JButton bt5 = new JButton("Agregar Coche");
        bt5.setBounds(26, 195, 57, 57);
        panel_1.add(bt5);
        
        JButton btn6 = new JButton("Almacen");
        btn6.setBounds(26, 127, 57, 57);
        panel_1.add(btn6);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Downloads\\añadir.png"));
        lblNewLabel_5.setBounds(36, 38, 46, 47);
        panel_1.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Downloads\\facturas.png"));
        lblNewLabel_6.setBounds(33, 127, 64, 35);
        panel_1.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Downloads\\almacen.png"));
        lblNewLabel_7.setBounds(24, 207, 46, 41);
        panel_1.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setBounds(-17, -13, 164, 124);
        topPanel.add(lblNewLabel_8);
        lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\MEDAC\\Desktop\\derrap\\src\\imagen\\logo.png"));
        
        JLabel listaOrdenes = new JLabel("Lista Ordenes");
        listaOrdenes.setFont(new Font("Verdana", Font.PLAIN, 25));
        listaOrdenes.setBounds(452, 72, 173, 61);
        topPanel.add(listaOrdenes);
        
    }
}

