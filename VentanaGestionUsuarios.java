package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Coordinador;
import Modelo.Logica;
import Modelo.PersonaDao;
import Modelo.PersonaVo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaGestionUsuarios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnComprobar, btnEliminar_1,btnLimpiar,btnModificar_1,btnAgregar;
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTextField textNombre;
	private JTextField textEdad;
	private JTextField textLocalidad;
	private JTextField textApellido;
	private JTextField textSector;
	private JTextField textUsuario;
	private JTextField textContraseña;
	private JComboBox<String> comboBox_1;
	private JTextField textDni;
	private JTable table_1;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionUsuarios frame = new VentanaGestionUsuarios();

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaGestionUsuarios() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 476);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mostrarDatosConTableModel();

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 100, 434, 125);
		panel_1.setBorder(
		new TitledBorder(null, "Datos de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 25, 46, 14);
		panel_1.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setBounds(10, 50, 46, 14);
		panel_1.add(lblApellido);

		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setBounds(10, 78, 46, 14);
		panel_1.add(lblEdad);

		JLabel lblLocalidad = new JLabel("Localidad :");
		lblLocalidad.setBounds(195, 25, 61, 14);
		panel_1.add(lblLocalidad);

		textNombre = new JTextField();
		textNombre.setBounds(75, 22, 100, 20);
		panel_1.add(textNombre);
		textNombre.setColumns(10);

		textEdad = new JTextField();
		textEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				}
			}
		});
		textEdad.setColumns(10);
		textEdad.setBounds(75, 75, 100, 20);
		panel_1.add(textEdad);

		textLocalidad = new JTextField();
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(266, 22, 100, 20);
		panel_1.add(textLocalidad);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(75, 47, 100, 20);
		panel_1.add(textApellido);

		JLabel lblSector = new JLabel("Sector");
		lblSector.setBounds(195, 50, 46, 14);
		panel_1.add(lblSector);

		textSector = new JTextField();
		textSector.setBounds(266, 47, 100, 20);
		panel_1.add(textSector);
		textSector.setColumns(10);

		JLabel lblAcceso = new JLabel("Acceso :");
		lblAcceso.setBounds(195, 78, 46, 14);
		panel_1.add(lblAcceso);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "", "Invitado", "Administrador" }));
		comboBox_1.setBounds(266, 74, 100, 18);
		panel_1.add(comboBox_1);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 101);
		contentPane.add(panel);
		panel.setBorder(new TitledBorder(null, "Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setLayout(null);

		JLabel label = new JLabel("Usuario :");
		label.setBounds(10, 52, 80, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("Contrase\u00F1a :");
		label_1.setBounds(10, 77, 80, 14);
		panel.add(label_1);

		textUsuario = new JTextField();
		textUsuario.setBounds(76, 49, 86, 20);
		textUsuario.setColumns(10);
		panel.add(textUsuario);

		textContraseña = new JTextField();
		textContraseña.setBounds(76, 74, 86, 20);
		textContraseña.setColumns(10);
		panel.add(textContraseña);

		btnComprobar = new JButton("");
		btnComprobar.setIcon(new ImageIcon(VentanaGestionUsuarios.class.getResource("/Imagenes/busqueda.png")));
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PersonaVo miPersona = miCoordinador.btnBuscarDniGestion(textDni.getText());
				if (miPersona != null) {
					muestraPersona(miPersona);
					habilita(false, false,false, false, false,false, false, false, false, true, true, false, true, false);
					
				} else  {
					int respuesta = JOptionPane.showConfirmDialog(null, "La persona no Existe ¿Desea agregarla?", "Advertencia",
							JOptionPane.YES_NO_OPTION);
					if (respuesta== JOptionPane.YES_NO_OPTION) {
						habilita(true, true, true, true, true, true, true,true, true, false, false, true, false, false);
						
					}
						
				}
					
				
			}

		});
		btnComprobar.setBounds(187, 20, 142, 23);
		panel.add(btnComprobar);

		JLabel label_2 = new JLabel("Dni :");
		label_2.setBounds(10, 24, 46, 14);
		panel.add(label_2);

		textDni = new JTextField();
		textDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				}
			}
		});
		textDni.setBounds(76, 21, 86, 20);
		panel.add(textDni);
		textDni.setColumns(10);

		 btnAgregar = new JButton("");
		btnAgregar.setToolTipText("Agregar o Guardar");
		btnAgregar.setBounds(187, 52, 29, 33);
		panel.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(VentanaGestionUsuarios.class.getResource("/Imagenes/agregar-usuario.png")));

		btnEliminar_1 = new JButton("");
		btnEliminar_1.setToolTipText("Eliminar");
		btnEliminar_1.setBounds(222, 52, 29, 33);
		panel.add(btnEliminar_1);
		btnEliminar_1.setIcon(new ImageIcon(VentanaGestionUsuarios.class.getResource("/Imagenes/borrar (1).png")));

		btnLimpiar = new JButton("");
		btnLimpiar.setToolTipText("Limpiar");
		btnLimpiar.setBounds(261, 52, 29, 33);
		panel.add(btnLimpiar);
		btnLimpiar.setIcon(new ImageIcon(VentanaGestionUsuarios.class.getResource("/Imagenes/escoba.png")));

		btnModificar_1 = new JButton("");
		btnModificar_1.setToolTipText("Modificar");
		btnModificar_1.setBounds(300, 52, 29, 33);
		panel.add(btnModificar_1);
		btnModificar_1.setIcon(new ImageIcon(VentanaGestionUsuarios.class.getResource("/Imagenes/escala.png")));
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				habilita(true, true, true, true, true, true, true, true, true, true,false, false, false,true);
				
			}
		});
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(337, 52, 29, 33);
		panel.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PersonaVo miPersona = new PersonaVo();
					miPersona.setDni(Integer.parseInt(textDni.getText()));
					miPersona.setUsuario(textUsuario.getText());
					miPersona.setContraseña(textContraseña.getText());
					miPersona.setNombre(textNombre.getText());
					miPersona.setApellido(textApellido.getText());
					miPersona.setEdad(Integer.parseInt(textEdad.getText()));
					miPersona.setLocalidad(textLocalidad.getText());
					miPersona.setSector(textSector.getText());
					miPersona.setAcceso((String) comboBox_1.getSelectedItem());
					miCoordinador.ModificarUsuario(miPersona);
					mostrarDatosConTableModel();
					Limpiar();
					habilita(true, false,false, false, false,false, false, false, false, true, false, false, false, false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println(e2);
				}
			}
		});
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}

		});
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textDni.getText().equals("")) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la Persona?",
							"Confirmación", JOptionPane.YES_NO_OPTION);

					if (respuesta == JOptionPane.YES_NO_OPTION) {
						miCoordinador.eliminarUsuario(Integer.parseInt(textDni.getText()));
						Limpiar();
						mostrarDatosConTableModel();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un numero de Documento", "Información",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					PersonaVo miPersona = new PersonaVo();
					miPersona.setDni(Integer.parseInt(textDni.getText()));
					miPersona.setUsuario(textUsuario.getText());
					miPersona.setContraseña(textContraseña.getText());
					miPersona.setNombre(textNombre.getText());
					miPersona.setApellido(textApellido.getText());
					miPersona.setEdad(Integer.parseInt(textEdad.getText()));
					miPersona.setLocalidad(textLocalidad.getText());
					miPersona.setSector(textSector.getText());
					miPersona.setAcceso((String) comboBox_1.getSelectedItem());
					miCoordinador.registrarUsuario(miPersona);
					mostrarDatosConTableModel();
					JOptionPane.showMessageDialog(null, "Se Ha Registrado Usuario", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					habilita(true,false,false,false,false,false,false,false,false,true,false,false,true,false);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos, AGREGAR", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println(e2);
				}

			}
		});
		habilita(true,false,false,false,false,false,false,false,false,true,false,false,false,false);

	}

	// llamamos al método mostrarDatosConTableModel

	private void mostrarDatosConTableModel() {
		DefaultTableModel model = new DefaultTableModel();
		table_1 = new JTable();
		table_1.setVisible(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 236, 425, 201);
		contentPane.add(scrollPane);
		

		table_1.setModel(model);
		model.addColumn("Dni");
		model.addColumn("Usuario");
		model.addColumn("Contraseña");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Edad");
		model.addColumn("Localidad");
		model.addColumn("Sector");
		model.addColumn("Acceso");

		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getTableHeader().setReorderingAllowed(false);

		PersonaDao miPersonaDao = new PersonaDao();
		miPersonaDao.btnBuscarDatosTabla(model);
		scrollPane.setViewportView(table_1);
	}

	public void muestraPersona(PersonaVo miPersona) {
		textUsuario.setText(miPersona.getUsuario());
		textContraseña.setText(miPersona.getContraseña());
		textNombre.setText(miPersona.getNombre());
		textApellido.setText(miPersona.getApellido());
		textEdad.setText(Integer.toString(miPersona.getEdad()));
		textLocalidad.setText(miPersona.getLocalidad());
		textSector.setText(miPersona.getSector());
		comboBox_1.setSelectedItem(miPersona.getAcceso());

	}

	public void habilita(boolean dni, boolean usu, boolean con, boolean nom, boolean ape, boolean edad, boolean loc,
			boolean sec, boolean acc, boolean bbtnBuscar, boolean eli, boolean Agregar, boolean bmodi, boolean Guardar) {
		textDni.setEditable(dni);
		textUsuario.setEditable(usu);
		textContraseña.setEditable(con);
		textNombre.setEditable(nom);
		textApellido.setEditable(ape);
		textEdad.setEditable(edad);
		textLocalidad.setEditable(loc);
		textSector.setEditable(sec);
		comboBox_1.setEnabled(acc);
		btnComprobar.setEnabled(bbtnBuscar);
		btnEliminar_1.setEnabled(eli);
		btnAgregar.setEnabled(Agregar);
		btnModificar_1.setEnabled(bmodi);
		btnGuardar.setEnabled(Guardar);

	}

	private void Limpiar() {
		// TODO Apéndice de método generado automáticamente
		textDni.setText("");
		textUsuario.setText("");
		textContraseña.setText("");
		textNombre.setText("");
		textApellido.setText("");
		textEdad.setText("");
		textLocalidad.setText("");
		textSector.setText("");
		comboBox_1.setSelectedIndex(0);
		habilita(true,false, false, false, false, false, false, false, false, true, false, false, false, false);
	}

	public Coordinador getCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
