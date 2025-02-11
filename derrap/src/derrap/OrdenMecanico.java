package derrap;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
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
        setSize(1062, 801);
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
        
        JButton btn5 = new JButton("Facturas");
        btn5.setBounds(26, 38, 57, 57);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_1.add(btn5);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("Imagen/añadir.png"));
        lblNewLabel_5.setBounds(36, 38, 46, 47);
        panel_1.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("Imagen/facturas.png"));
        lblNewLabel_6.setBounds(33, 127, 64, 35);
        panel_1.add(lblNewLabel_6);
        
        ImageIcon original0 = new ImageIcon("Imagen/almacen.png");
        Image scaledImage0 = original0.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon0= new ImageIcon(scaledImage0);
        
        JButton bt5 = new JButton("");
        bt5.setIcon(scaledIcon0);
        
        bt5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Stock frame = new Stock();
                frame.setVisible(true);
                dispose();
        	}
        });
        bt5.setForeground(new Color(255, 255, 255));
        bt5.setBackground(new Color(162, 117, 104));
        bt5.setBounds(26, 126, 53, 55);
        panel_1.add(bt5);
        
        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setBounds(-17, -13, 164, 124);
        topPanel.add(lblNewLabel_8);
        lblNewLabel_8.setIcon(new ImageIcon("Imagen/logo.png"));
        
        JLabel listaOrdenes = new JLabel("Lista Ordenes");
        listaOrdenes.setFont(new Font("Verdana", Font.PLAIN, 25));
        listaOrdenes.setBounds(452, 72, 173, 61);
        topPanel.add(listaOrdenes);
        
    }
}

