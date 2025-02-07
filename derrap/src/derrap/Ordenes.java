package derrap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ordenes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ordenes frame = new Ordenes();
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
	public Ordenes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 setLocation(400, 100);
		 
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(162, 117, 102));
		panel_1.setBackground(new Color(162, 117, 102));
		panel_1.setBounds(0, 105, 99, 757);
		contentPane.add(panel_1);
		
	     ImageIcon original5 = new ImageIcon("Imagen/añadirMecanico.png");
	        Image scaledImage5 = original5.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
	        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

	       ImageIcon scaledIcon5= new ImageIcon(scaledImage5);
	        
	       JButton btn5 = new JButton("");
	       btn5.setBackground(new Color(162, 117, 104));
	       btn5.setIcon(scaledIcon5);
	       
	       btn5.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               
	           }
	       });
	       
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
		
		
	        
	        
	        
		  ImageIcon original6 = new ImageIcon("Imagen/Facturaas.png");
	        Image scaledImage = original6.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
	        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

	       ImageIcon scaledIcon= new ImageIcon(scaledImage);
	        
	        JButton btn6 = new JButton("");
	        btn6.setIcon(scaledIcon);
	        btn6.setEnabled(false);
	        btn6.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btn6.setForeground(new Color(162, 117, 102));
	        btn6.setBackground(new Color(192, 192, 192));
	        btn6.setBounds(26, 88, 53, 55);
	        panel_1.add(btn6);
	        
		
	        
	        
	        
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("Imagen/logo.png"));
		lblNewLabel_8.setBounds(0, 0, 197, 110);
		contentPane.add(lblNewLabel_8);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(162, 117, 102));
		panel.setBounds(151, 0, 1024, 61);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Facturas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(8, 35, 226, 20);
		panel.add(lblNewLabel);
		
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
	        
	        
	        
		btnNewButton.setForeground(new Color(162, 117, 102));
		btnNewButton.setBackground(new Color(162, 117, 102));
		btnNewButton.setBounds(5, 5, 63, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		 lblNewLabel_1.setIcon(new ImageIcon("Imagen/añadirfactura.png"));
		lblNewLabel_1.setBounds(161, 105, 110, 82);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("Imagen/actualizarfactura.png"));
		lblNewLabel_2.setBounds(362, 105, 110, 82);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("Imagen/eliminarfactura.png"));
		lblNewLabel_3.setBounds(556, 105, 110, 82);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Añadir ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AñadirOrdenes frame = new AñadirOrdenes();
	               frame.setVisible(true);
	               dispose();
	               
				
			}
		});
		btnNewButton_1.setBackground(new Color(162, 117, 104));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(161, 198, 110, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Actualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ActualizarOrdenes frame = new ActualizarOrdenes();
	               frame.setVisible(true);
	               dispose();
			}
		});
		btnNewButton_2.setBackground(new Color(162, 117, 104));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(362, 198, 110, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 EliminarOrdenes frame = new EliminarOrdenes();
	               frame.setVisible(true);
	               dispose();
			}
		});
		btnNewButton_3.setBackground(new Color(162, 117, 104));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(556, 198, 110, 24);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Ver Ordenes");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 VerOrdenes frame = new VerOrdenes();
	               frame.setVisible(true);
	               dispose();
			}
		});
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(162, 117, 104));
		btnNewButton_4.setBounds(747, 199, 110, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("Imagen/BuscarOrden.png"));
		lblNewLabel_4.setBounds(752, 105, 105, 82);
	
		contentPane.add(lblNewLabel_4);
	}
}
