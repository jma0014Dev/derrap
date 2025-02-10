package derrap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VerCoches extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Connection connection;
    private DefaultListModel<String> model;
    private JList<String> listCoches;

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

        // Panel superior con título y botón de retroceso
        JPanel panel = new JPanel();
        panel.setBounds(134, 0, 908, 61);
        panel.setBackground(new Color(162, 117, 102));
        topPanel.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setBounds(-17, -13, 164, 124);
        topPanel.add(lblNewLabel_8);
        lblNewLabel_8.setIcon(new ImageIcon("imagen/logo.png"));
        
        JLabel lblNewLabel = new JLabel("Coches en taller");
        lblNewLabel.setBounds(8, 35, 122, 20);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JButton backBtn = new JButton("");
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Administrador frame = new Administrador();
                frame.setVisible(true);
                dispose();
        	}
        });
        backBtn.setBackground(new Color(162, 117, 102));
        backBtn.setForeground(new Color(162, 117, 102));
        backBtn.setIcon(new ImageIcon("imagen/back.png"));
        backBtn.setBounds(5, 5, 89, 31);
        panel.add(backBtn);

        // Panel lateral con botones
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(-7, 111, 120, 651);
        panel_1.setBackground(new Color(162, 117, 102));
        topPanel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnAdd = new JButton("Añadir");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setBackground(new Color(125, 89, 77));
        btnAdd.setBounds(20, 253, 90, 30);
        btnAdd.addActionListener(e -> abrirFormularioAñadir());
        panel_1.add(btnAdd);

        JButton btnModify = new JButton("Modificar");
        btnModify.setForeground(new Color(255, 255, 255));
        btnModify.setBackground(new Color(125, 89, 77));
        btnModify.setBounds(20, 335, 90, 30);
        btnModify.addActionListener(e -> modificarCoche());
        panel_1.add(btnModify);

        JButton btnDelete = new JButton("Eliminar");
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(125, 89, 77));
        btnDelete.setBounds(20, 294, 90, 30);
        btnDelete.addActionListener(e -> eliminarCoche());
        panel_1.add(btnDelete);
        
        ImageIcon original5 = new ImageIcon("Imagen/añadirMecanico.png");
        Image scaledImage5 = original5.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
        //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

       ImageIcon scaledIcon5= new ImageIcon(scaledImage5);
        
       JButton btn5 = new JButton("");
       btn5.setIcon(scaledIcon5);

       btn5.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               
               AñadirMecanico frame = new AñadirMecanico();
               frame.setVisible(true);
               dispose();
           }
       });
       btn5.setForeground(new Color(255, 255, 255));
       btn5.setBackground(new Color(162, 117, 104));
       btn5.setBounds(36, 21, 53, 55);
       panel_1.add(btn5);
       
       btn5.setBounds(26, 11, 53, 55);
       panel_1.add(btn5);
       
       ImageIcon original6 = new ImageIcon("Imagen/Facturaas.png");
       Image scaledImage = original6.getImage().getScaledInstance(50,-1,java.awt.Image.SCALE_DEFAULT);
       //ImageIcon iconoEscala = new ImageIcon(original6.getImage().getScaledInstance(5, 5, java.awt.Image.SCALE_DEFAULT));

      ImageIcon scaledIcon= new ImageIcon(scaledImage);
       
       JButton btn6 = new JButton("");
       btn6.setIcon(scaledIcon);
       btn6.setEnabled(true);
       btn6.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		Ordenes frame = new Ordenes();
            frame.setVisible(true);
            dispose();
       	}
       });
       btn6.setForeground(new Color(162, 117, 102));
       btn6.setBackground(new Color(162, 117, 102));
       btn6.setBounds(26, 89, 53, 55);
       panel_1.add(btn6);
       
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
       bt5.setBounds(26, 164, 53, 55);
       panel_1.add(bt5);

        // Crear la lista de coches
        model = new DefaultListModel<>();
        listCoches = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(listCoches);
        scrollPane.setBounds(134, 72, 908, 500);
        topPanel.add(scrollPane);

        // Botón para cargar los coches desde la base de datos
        JButton btnCargarCoches = new JButton("Cargar Coches");
        btnCargarCoches.setBounds(134, 580, 150, 30);
        btnCargarCoches.addActionListener(e -> cargarCoches());
        topPanel.add(btnCargarCoches);
    }

    // Método para conectar a la base de datos
    private Connection conectarDB() {
        ConectarDB_mysql conector = new ConectarDB_mysql();
        return conector.conectar();
    }

    // Método para cargar los coches desde la base de datos
    private void cargarCoches() {
        try (Connection con = conectarDB()) {
            String query = "SELECT Matricula, Marca, Modelo FROM vehiculo";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            model.clear(); // Limpiar el modelo antes de agregar los nuevos coches

            while (rs.next()) {
                String matricula = rs.getString("Matricula");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                model.addElement(matricula + " - " + marca + " - " + modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para abrir el formulario para añadir un coche
    private void abrirFormularioAñadir() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(5, 2));

        JTextField matriculaField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField añoField = new JTextField();

        addPanel.add(new JLabel("Matrícula:"));
        addPanel.add(matriculaField);
        addPanel.add(new JLabel("Marca:"));
        addPanel.add(marcaField);
        addPanel.add(new JLabel("Modelo:"));
        addPanel.add(modeloField);
        addPanel.add(new JLabel("Año de fabricación:"));
        addPanel.add(añoField);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            // Lógica para guardar el coche en la base de datos
            guardarCoche(matriculaField.getText(), marcaField.getText(), modeloField.getText(), añoField.getText());
        });
        addPanel.add(btnGuardar);

        JOptionPane.showOptionDialog(this, addPanel, "Añadir Coche", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    }

    // Método para guardar un coche en la base de datos
 // Método para guardar un coche en la base de datos
    private void guardarCoche(String matricula, String marca, String modelo, String año) {
        try (Connection con = conectarDB()) {
            // Iniciar transacción para asegurar que ambas inserciones se hagan de forma atómica
            con.setAutoCommit(false);

            try {
                // Primero, insertamos un registro en la tabla 'reparacion'
                String queryReparacion = "INSERT INTO reparacion (factura_id_factura) VALUES (?)";
                PreparedStatement stmtReparacion = con.prepareStatement(queryReparacion, Statement.RETURN_GENERATED_KEYS);
                stmtReparacion.setInt(1, 1); // Usamos un valor de ejemplo para 'factura_id_factura'
                stmtReparacion.executeUpdate();

                // Obtener el ID de la reparación recién insertada
                ResultSet generatedKeys = stmtReparacion.getGeneratedKeys();
                int idReparacion = 0;
                if (generatedKeys.next()) {
                    idReparacion = generatedKeys.getInt(1); // ID generado de la reparación
                }

                // Ahora insertamos el coche en la tabla 'vehiculo', utilizando el idReparacion
                String queryVehiculo = "INSERT INTO vehiculo (Matricula, Marca, Modelo, Año_fabricacion, reparacion_id_reparacion, reparacion_factura_id_factura) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtVehiculo = con.prepareStatement(queryVehiculo);
                stmtVehiculo.setString(1, matricula); // Matrícula
                stmtVehiculo.setString(2, marca);     // Marca
                stmtVehiculo.setString(3, modelo);    // Modelo
                stmtVehiculo.setInt(4, Integer.parseInt(año));  // Año de fabricación
                stmtVehiculo.setInt(5, idReparacion); // Referencia a la reparación
                stmtVehiculo.setInt(6, 1); // ID de la factura, ejemplo: 1

                // Ejecutamos la consulta de inserción del coche
                int rowsAffected = stmtVehiculo.executeUpdate();
                
                if (rowsAffected > 0) {
                    // Confirmamos la transacción
                    con.commit();
                    JOptionPane.showMessageDialog(this, "Coche añadido correctamente.");
                    cargarCoches(); // Recargamos la lista de coches
                } else {
                    // En caso de que no se haya insertado el coche, revertimos la transacción
                    con.rollback();
                    JOptionPane.showMessageDialog(this, "Error al añadir coche.");
                }
            } catch (SQLException e) {
                // Si hay algún error en cualquiera de las inserciones, revertimos la transacción
                con.rollback();
                JOptionPane.showMessageDialog(this, "Error al añadir coche: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Restauramos el autocommit para futuras operaciones
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // En caso de error con la conexión
        }
    }





    // Método para modificar un coche seleccionado
 // Método para modificar un coche seleccionado
    private void modificarCoche() {
        String selectedCar = listCoches.getSelectedValue();
        if (selectedCar != null) {
            String matricula = selectedCar.split(" - ")[0]; // Obtenemos la matrícula del coche seleccionado
            
            // Crear y mostrar la ventana de modificación
            abrirFormularioModificar(matricula);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un coche.");
        }
    }

    // Método para abrir el formulario de modificación con los datos del coche
    private void abrirFormularioModificar(String matricula) {
        // Crear el panel para el formulario de modificación
        JPanel modifyPanel = new JPanel();
        modifyPanel.setLayout(new GridLayout(5, 2));

        // Campos de texto para editar los detalles del coche
        JTextField matriculaField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField añoField = new JTextField();

        // Obtener los detalles del coche de la base de datos
        try (Connection con = conectarDB()) {
            String query = "SELECT Marca, Modelo, Año_fabricacion FROM vehiculo WHERE Matricula = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Rellenar los campos con los valores actuales del coche
                marcaField.setText(rs.getString("Marca"));
                modeloField.setText(rs.getString("Modelo"));
                añoField.setText(rs.getString("Año_fabricacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Agregar los campos al panel
        modifyPanel.add(new JLabel("Matrícula:"));
        modifyPanel.add(matriculaField);
        modifyPanel.add(new JLabel("Marca:"));
        modifyPanel.add(marcaField);
        modifyPanel.add(new JLabel("Modelo:"));
        modifyPanel.add(modeloField);
        modifyPanel.add(new JLabel("Año de fabricación:"));
        modifyPanel.add(añoField);

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            // Lógica para actualizar el coche en la base de datos
            actualizarCoche(matricula, matriculaField.getText(), marcaField.getText(), modeloField.getText(), añoField.getText());
        });
        modifyPanel.add(btnGuardar);

        // Mostrar el formulario en una ventana emergente
        JOptionPane.showOptionDialog(this, modifyPanel, "Modificar Coche", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    }

    // Método para actualizar los detalles del coche en la base de datos
    private void actualizarCoche(String matriculaOriginal, String matricula, String marca, String modelo, String año) {
        try (Connection con = conectarDB()) {
            String query = "UPDATE vehiculo SET Matricula = ?, Marca = ?, Modelo = ?, Año_fabricacion = ? WHERE Matricula = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            // Asignamos los nuevos valores
            stmt.setString(1, matricula);
            stmt.setString(2, marca);
            stmt.setString(3, modelo);
            stmt.setInt(4, Integer.parseInt(año));
            stmt.setString(5, matriculaOriginal); // Matricula original para identificar el coche

            // Ejecutar la actualización
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Coche modificado correctamente.");
                cargarCoches(); // Recargar la lista de coches
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el coche.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Método para eliminar un coche seleccionado
    private void eliminarCoche() {
        String selectedCar = listCoches.getSelectedValue();
        if (selectedCar != null) {
            String matricula = selectedCar.split(" - ")[0];
            try (Connection con = conectarDB()) {
                String query = "DELETE FROM vehiculo WHERE Matricula = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, matricula);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Coche eliminado.");
                    cargarCoches(); // Recargar la lista de coches
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar coche.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un coche.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VerCoches frame = new VerCoches();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
