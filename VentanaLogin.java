package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import Controlador.Coordinador;
import Modelo.Logica;
import Modelo.PersonaVo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textContraseña;
	private Coordinador miCoordinador;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VentanaLogin frame = new VentanaLogin();

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
	public VentanaLogin() {
		setResizable(false);
		setOpacity(1.0f);
		setBackground(Color.BLACK);
		setForeground(Color.BLUE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Login");

		setBounds(100, 100, 215, 397);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Usuario o Contrase\u00F1a incorrectos");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(26, 235, 174, 23);
		contentPane.add(lblNewLabel_1);
	

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(82, 325, 89, 23);
		contentPane.add(btnEntrar);

		btnEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					@SuppressWarnings("deprecation")
					PersonaVo miPersona = miCoordinador.btnBuscarUsuario(textUsuario.getText(), textContraseña.getText());
					if (miPersona != null) {
						
						miCoordinador.CerrarVentanaLogin();
						JOptionPane.showMessageDialog(null, "bienvenido " + textUsuario.getText() + " Conectado",
								"Advertencia", JOptionPane.WARNING_MESSAGE);
						
						miCoordinador.MostrarVentanaPrincipal();
						miCoordinador.pasarusu(miPersona);
						//String usuario= miPersona.getUsuario();
					

					} else {
						
						lblNewLabel_1.setVisible(true);
						
					}
				}
			}
		});

		

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/candado-cerrado(1).png")));
		label_2.setBounds(26, 290, 30, 24);
		contentPane.add(label_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/descargeterasombra.png")));
		label.setBounds(0, 11, 225, 225);
		contentPane.add(label);

		textUsuario = new JTextField();
		textUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textContraseña.requestFocus();
				}
			}
		});
		textUsuario.setBackground(Color.WHITE);
		textUsuario.setBounds(62, 259, 129, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		textContraseña = new JPasswordField();
		textContraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.requestFocus();

				}
			}
		});
		textContraseña.setEditable(true);
		textContraseña.setEnabled(true);
		textContraseña.setToolTipText("");
		textContraseña.setText("");
		textContraseña.setBounds(62, 294, 129, 20);
		contentPane.add(textContraseña);
		textContraseña.setColumns(10);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/silueta-de-usuario.png")));
		label_1.setBounds(26, 249, 43, 34);
		contentPane.add(label_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				VentanaLogin.class.getResource("/Imagenes/Blue-Wallpaper-random-35944327-500-313 copia.jpg")));
		lblNewLabel.setBounds(0, 0, 211, 369);
		contentPane.add(lblNewLabel);

	}

	public Coordinador getCoordinador() {
		return miCoordinador;
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public void limpiarVL() {
		// TODO Apéndice de método generado automáticame
		textUsuario.setText("");
		textContraseña.setText("");
	}

	public String pasarusu(String usu) {
		// TODO Auto-generated method stub
		return usu;
	}
}
