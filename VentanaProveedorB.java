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
import javax.swing.table.TableColumn;

import Controlador.Coordinador;
import Modelo.ProductoVO;
import Modelo.ProveedorVO;

/**
 * Programa VentanaAlumnobtnBuscar Paquete vista
 * 
 * @author Edgardo
 *
 */
public class VentanaProveedorB extends JFrame {

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
					VentanaProveedorB frame = new VentanaProveedorB();
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
	public VentanaProveedorB() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 551, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnVolver);
	}

	/**
	 * Método setCoordinador
	 * 
	 * @param miCoordinador
	 */
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Carga y muestra el JTable para seleccionar el alumno
	 * 
	 * @param btn
	 * @param doc
	 * @param ape
	 * @param ventana
	 */

	public void mostrarDatosConTableModel(int btn, int doc, String ape, int ventana) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {

		}, new String[] { "idProveedor", "Cuil", "Razon Social" }) {
			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table = new JTable();
	
		table.setModel(modelo);
		table.getTableHeader().setReorderingAllowed(false);

		// centralizado
		DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
		centrar.setHorizontalAlignment(SwingConstants.CENTER);

		// justificado a izquierda
		DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
		izquierda.setHorizontalAlignment(SwingConstants.LEFT);

		// columna DNI
		table.getColumnModel().getColumn(0).setMaxWidth(78);
		table.getColumnModel().getColumn(0).setMinWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(78);
		table.getColumnModel().getColumn(0).setCellRenderer(centrar);

		// columna Apellido y nombre
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(1).setCellRenderer(izquierda);

		// columna Fecha de nacimiento
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setCellRenderer(centrar);

		

		
		scrollPane.setViewportView(table);
	}

	protected ProveedorVO pasarDatosAlumno(MouseEvent e) {
		ProveedorVO miProveedorVO = new ProveedorVO();
		int row = table.rowAtPoint(e.getPoint());
		miProveedorVO.setIdProveedor(Integer.valueOf(table.getValueAt(row, 0).toString()));
		miProveedorVO.setCuil(Integer.valueOf(table.getValueAt(row, 1).toString()));
		miProveedorVO.setRazonsocial((table.getValueAt(row, 2).toString()));

		return miProveedorVO;
	}

}