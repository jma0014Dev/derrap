package derrap;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VerCoches extends JFrame {

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
                    VerCoches frame = new VerCoches();
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
    public VerCoches() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1078, 811);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        contentPane.add(topPanel, BorderLayout.CENTER);
        topPanel.setLayout(null);
       
        JPanel panel = new JPanel();
        panel.setBounds(134, 0, 908, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);//nvkfdnkdfknfbdkn
       
        JLabel lblNewLabel = new JLabel("Coches en taller");
        lblNewLabel.setBounds(8, 35, 122, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
       
        JButton btnNewButton = new JButton("");
        btnNewButton.setForeground(new Color(162, 117, 102));
        btnNewButton.setIcon(new ImageIcon("imagen/back.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton.setVerticalAlignment(SwingConstants.CENTER);
        btnNewButton.setPreferredSize(new Dimension(63, 23));
        btnNewButton.setBackground(new Color(162, 117, 102));
        btnNewButton.setBounds(5, 5, 89, 31);
        panel.add(btnNewButton);
       
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(162, 117, 102));
        panel_1.setBounds(-7, 111, 120, 651);
        panel_1.setBackground(new Color(162, 117, 102));
        topPanel.add(panel_1);
        panel_1.setLayout(null);
       
        JButton btn5 = new JButton((String) null);
        btn5.setForeground(new Color(162, 117, 102));
        btn5.setBackground(new Color(162, 117, 102));
        ImageIcon añadirMecanicoIcon = new ImageIcon("imagen/añadirMecanico.PNG");
        Image añadirMecanicoScaledImage = añadirMecanicoIcon.getImage().getScaledInstance(53, 55, Image.SCALE_SMOOTH);
        btn5.setIcon(new ImageIcon(añadirMecanicoScaledImage));
        btn5.setHorizontalAlignment(SwingConstants.CENTER);
        btn5.setVerticalAlignment(SwingConstants.CENTER);
        btn5.setPreferredSize(new Dimension(53, 55));
        btn5.setBounds(38, 23, 53, 55);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AñadirMecanico añadirMecanico = new AñadirMecanico();
                añadirMecanico.setVisible(true);
            }
        });
        panel_1.add(btn5);
       
        JButton bt5 = new JButton("");
        bt5.setBackground(new Color(162, 117, 102));
        bt5.setForeground(new Color(162, 117, 102));
        ImageIcon almacenIcon = new ImageIcon("imagen/almacen.png");
        Image almacenScaledImage = almacenIcon.getImage().getScaledInstance(53, 55, Image.SCALE_SMOOTH);
        bt5.setIcon(new ImageIcon(almacenScaledImage));
        bt5.setHorizontalAlignment(SwingConstants.CENTER);
        bt5.setVerticalAlignment(SwingConstants.CENTER);
        bt5.setPreferredSize(new Dimension(53, 55));
        bt5.setBounds(38, 155, 53, 55);
        bt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_1.add(bt5);

        ImageIcon original6 = new ImageIcon("imagen/Facturaas.png");
        Image scaledImage = original6.getImage().getScaledInstance(50, 55, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
       
        JButton btn6 = new JButton("");
        btn6.setIcon(scaledIcon);
        btn6.setHorizontalAlignment(SwingConstants.CENTER);
        btn6.setVerticalAlignment(SwingConstants.CENTER);
        btn6.setPreferredSize(new Dimension(53, 55));
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn6.setForeground(new Color(162, 117, 102));
        btn6.setBackground(new Color(162, 117, 102));
        btn6.setBounds(38, 89, 53, 55);
        panel_1.add(btn6);
       
        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setBounds(-17, -13, 164, 124);
        topPanel.add(lblNewLabel_8);
        lblNewLabel_8.setIcon(new ImageIcon("imagen/logo.png"));
       
        JPanel panelListas = new JPanel();
        panelListas.setBounds(150, 100, 1500, 800);
        panelListas.setLayout(new GridLayout(1, 3, 20, 0));
        topPanel.add(panelListas);

        String[] columnas = {"Eliminar un coche", "Modificar un coche", "Consultar un coche"};
        for (String columna : columnas) {
       
            JPanel panelColumna = new JPanel();
            panelColumna.setLayout(new BorderLayout());

            JLabel tituloColumna = new JLabel(columna);
            tituloColumna.setFont(new Font("Tahoma", Font.BOLD, 14));
            tituloColumna.setHorizontalAlignment(SwingConstants.CENTER);
            panelColumna.add(tituloColumna, BorderLayout.NORTH);

            JList<String> listaCoches = new JList<>(obtenerCocheDesdeBD());
           
            listaCoches.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                   
                        String cocheSeleccionado = listaCoches.getSelectedValue();
                       
                        if (cocheSeleccionado != null) {
                            switch (columna) {
                           
                                case "Eliminar un coche":
                                    eliminarCoche(cocheSeleccionado);
                                    break;
                                   
                                case "Modificar un coche":
                                    new ModificarClienteFrame(cocheSeleccionado).setVisible(true);
                                    break;
                                   
                                case "Consultar un coche":
                                    mostrarInformacionCoches(cocheSeleccionado);
                                    break;
                            }
                        }
                    }
                }

private void mostrarInformacionCoches(String cocheSeleccionado) {


}

private void eliminarCoche(String cocheSeleccionado) {


}
            });
            panelColumna.add(new JScrollPane(listaCoches), BorderLayout.CENTER);

            panelListas.add(panelColumna);
        }
       
    }

private Object obtenerCocheDesdeBD() {
java.util.List<String> vehiculo = new ArrayList<>();
       
        try {
            String query = "SELECT CONCAT(Matricula, ' ', Marca) AS CocheCompleto FROM vehiculo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            vehiculo.add(resultSet.getString("CocheCompleto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener coches: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return vehiculo.toArray(new String[0]);
}
   
}