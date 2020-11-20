package Vista;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Coordinador;
import Modelo.PersonaVo;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JLabel lblUsu_1;
	private JLabel lblHora_1;
	private JLabel lblFecha_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					  
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public VentanaPrincipal() {
		
		

		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 756, 474);
	

		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu_1 = new JMenu("Menu");
		menuBar.add(mnMenu_1);
		menuBar.setMaximumSize(getMaximumSize());

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.salirVGU();
			}
		});
		mnMenu_1.add(mntmSalir);

		JMenu mnMenu = new JMenu("Usuarios");
		menuBar.add(mnMenu);
		menuBar.resize(getMaximumSize());

		JMenuItem mntmGestionUsuarios = new JMenuItem("Gestion Usuarios");
		mntmGestionUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmGestionUsuarios) {
					miCoordinador.MostrarVentanaGestionUsuarios();

				}

			}
		});
		mnMenu.add(mntmGestionUsuarios);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnEntradamateriales = new JButton("Entrada Materiales");
		btnEntradamateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.MuestraVistaEntradamateriales();
			}
			
		});
		btnEntradamateriales.setBounds(0, 0, 142, 23);
		contentPane.add(btnEntradamateriales);

		JButton btnSalidamateriales = new JButton("Salida Materiales");
		btnSalidamateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.getMiVistaSalidademateriales().setVisible(true);
			}
		});
		btnSalidamateriales.setBounds(0, 22, 142, 23);
		contentPane.add(btnSalidamateriales);

		JButton btnOrdenescompra = new JButton("Ordenes Compra");
		btnOrdenescompra.setBounds(0, 45, 142, 23);
		contentPane.add(btnOrdenescompra);

		JLabel lblUsu = new JLabel("Usuario:");
		lblUsu.setBounds(10, 175, 46, 14);
		contentPane.add(lblUsu);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 196, 46, 14);
		contentPane.add(lblHora);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 225, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblFecha_1 = new JLabel("New label");
		lblFecha_1.setBounds(51, 225, 67, 14);
		contentPane.add(lblFecha_1);
		
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		    	JLabel lblHora_1 = new JLabel("");
				lblHora_1.setBounds(66, 196, 76, 14);
				contentPane.add(lblHora_1);
		        while (true) {
		            try {
		                Thread.sleep(500);
		                lblHora_1.setText(formateador.format(LocalDateTime.now()));
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
		
		// DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		// Date date = new Date();
		//  String hora =dateFormat.format(date);
		
		//  lblHora_1.setText(hora);
		  

	
	
		
		
		
		Calendar c2 = new GregorianCalendar();
		String dia = Integer.toString(c2.get(Calendar.DATE));
		String mes = Integer.toString(c2.get(Calendar.MONTH));
		String annio = Integer.toString(c2.get(Calendar.YEAR));
		
		lblFecha_1.setText(dia+"/"+mes+"/"+annio);
		
		JButton btnStock = new JButton("Stock");
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVentanaStock();
			}
		});
		btnStock.setBounds(0, 66, 142, 23);
		contentPane.add(btnStock);
		
		JButton btnVentanaProducto = new JButton("Productos");
		btnVentanaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVistaProductos();
			}
		});
		btnVentanaProducto.setBounds(0, 89, 142, 23);
		contentPane.add(btnVentanaProducto);
		
		
	}

	public void repasarusu(PersonaVo miPersona) {
		// TODO Auto-generated method stub
		JLabel lblUsu_1 = new JLabel(miPersona.getUsuario());
		lblUsu_1.setBounds(66, 175, 46, 14);
		contentPane.add(lblUsu_1);
		lblUsu_1.setEnabled(true);

	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
