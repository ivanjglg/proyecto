package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controlador.Coordinador;
import Modelo.ClienteDAO;
import Modelo.ClienteVO;

/**
 * Programa VentanaAlumnobtnBuscar Paquete vista
 * 
 * @author Edgardo
 *
 */
public class VentanaBusqueda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Coordinador miCoordinador;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnVolver;
	
	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusqueda frame = new VentanaBusqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana
	 */
	public VentanaBusqueda() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnVolver);
		mostrarDatosConTableModel();
	}

	public void mostrarDatosConTableModel() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Id Cliente", "Razon Social",
				"Tipo Doc.", "Documento", "Responsabilidad", "Domicilio", "Teléfono" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Integer.class, String.class,
					String.class, Integer.class,

			};

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				miCoordinador.pasarDatosCliente(pasarDatosCliente(e));
			}
		});

		table.setModel(modelo);
		table.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
		centrar.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
		izquierda.setHorizontalAlignment(SwingConstants.LEFT);

		table.getColumnModel().getColumn(0).setMaxWidth(78);
		table.getColumnModel().getColumn(0).setMinWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(78);
		table.getColumnModel().getColumn(0).setCellRenderer(centrar);

		table.getColumnModel().getColumn(1).setMinWidth(100);

		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(1).setCellRenderer(izquierda);

		table.getColumnModel().getColumn(2).setMinWidth(80);

		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setCellRenderer(centrar);

		table.getColumnModel().getColumn(3).setMinWidth(100);

		table.getTableHeader().getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setCellRenderer(izquierda);

		table.getColumnModel().getColumn(4).setMinWidth(100);

		table.getTableHeader().getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setCellRenderer(izquierda);

		table.getColumnModel().getColumn(5).setMinWidth(100);

		table.getTableHeader().getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setCellRenderer(izquierda);

		table.getColumnModel().getColumn(6).setMinWidth(80);

		table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(80);
		table.getColumnModel().getColumn(6).setCellRenderer(centrar);

		ClienteDAO miClienteDAO = new ClienteDAO();

		miClienteDAO.cargartabla(modelo);
		
		scrollPane.setViewportView(table);
	}

	protected ClienteVO pasarDatosCliente(MouseEvent e) {
		ClienteVO miCliente = new ClienteVO();
		int row = table.rowAtPoint(e.getPoint());
		miCliente.setCodigocliente(Integer.valueOf(table.getValueAt(row, 0).toString()));
		miCliente.setRazonsocial(table.getValueAt(row, 1).toString());
		miCliente.setTipodoc(table.getValueAt(row, 2).toString());
		miCliente.setDocumento(Integer.valueOf(table.getValueAt(row, 3).toString()));
		miCliente.setResponsabilidad(table.getValueAt(row, 4).toString());
		miCliente.setDomicilio(table.getValueAt(row, 5).toString());
		miCliente.setTelefono(Integer.valueOf(table.getValueAt(row, 6).toString()));

		return miCliente;
	}

	public Coordinador getCoordinador() {
		return miCoordinador;
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

}
