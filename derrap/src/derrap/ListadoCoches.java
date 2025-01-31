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
import javax.swing.JList;

public class ListadoCoches extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	ListadoCoches frame = new ListadoCoches();
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
    public ListadoCoches() {
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
        panel.setBounds(134, 0, 908, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Listado Coches");
        lblNewLabel.setBounds(8, 35, 156, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        
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
        
        JLabel falloVehiculoLabel = new JLabel("Fallo del vehiculo:");
        falloVehiculoLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
        falloVehiculoLabel.setBounds(493, 178, 113, 14);
        topPanel.add(falloVehiculoLabel);
        
        JLabel matriculaLabel = new JLabel("Matricula:");
        matriculaLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
        matriculaLabel.setBounds(493, 217, 113, 14);
        topPanel.add(matriculaLabel);
        
        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
        modeloLabel.setBounds(493, 255, 113, 14);
        topPanel.add(modeloLabel);
        
        JLabel anoCompraLabel = new JLabel("Año de compra:");
        anoCompraLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
        anoCompraLabel.setBounds(493, 293, 113, 14);
        topPanel.add(anoCompraLabel);
        
        textField = new JTextField();
        textField.setBounds(616, 176, 180, 20);
        topPanel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(616, 215, 180, 20);
        topPanel.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(616, 253, 180, 20);
        topPanel.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(616, 291, 180, 20);
        topPanel.add(textField_3);
        
        JLabel lblNewLabel_1 = new JLabel("Imagen Coche");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(325, 231, 99, 38);
        topPanel.add(lblNewLabel_1);
        
        JList cochesLista = new JList();
        cochesLista.setBounds(102, 122, 197, 300);
        topPanel.add(cochesLista);
    }
}

