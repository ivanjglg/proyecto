package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Controlador.Coordinador;
import Modelo.ProductoDAO;
import Modelo.ProveedorDAO;
import Modelo.StockDAO;
import Modelo.StockVO;

public class VistaEntradamateriales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Coordinador miCoordinador;
	private JScrollPane scrollPane;
	private JButton btnVolver;
	private JTextField textField;
	private JComboBox<String> cbboxProveedor;
	private JTable table_1;
	private JDateChooser dateChooser;
	private String fecha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEntradamateriales frame = new VistaEntradamateriales();
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

	public VistaEntradamateriales() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 528, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 64, 497, 152);
		contentPane.add(scrollPane);

//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(367, 64, 323, 460);
//		contentPane.add(scrollPane_1);
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(402, 33, 100, 20);
		contentPane.add(dateChooser);
		dateChooser.getJCalendar();
		mostrarDatosConTableModel();

	}

	private void repetidos() {
		// TODO Auto-generated method stub
		for (int i = 0; i < model1.getRowCount(); i++) {
			for (int j = 1; j < model1.getRowCount(); j++) {
				int column = (int) model1.getValueAt(i, 0);
				int column1 = (int) model1.getValueAt(j, 0);
				String column2 = (String) model1.getValueAt(i, 2);
				String column3 = (String) model1.getValueAt(j, 2);
				if (column == column1 && column2 == column3) {
					JOptionPane.showMessageDialog(cbboxProveedor, "error");
				}

			}
		}
	}

	private void eliminarlista() {
		// TODO Auto-generated method stub
		int as = table_1.getSelectedRow();
		model1.removeRow(as);
	}

	/**
	 * Método setCoordinador
	 * 
	 * @param miCoordinador
	 */
	public void setCoordinador(Coordinador miCoordinador) {
		this.setMiCoordinador(miCoordinador);
	}

	DefaultTableModel model1 = new DefaultTableModel(new Object[][] {

	}, new String[] { "idProducto", "Detalle", "Proveedor", "Cantidad" }) {

		private static final long serialVersionUID = 2L;
		Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Integer.class };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		boolean[] columnEditables = new boolean[] { false, false, true, true };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

	};

	private JTextField textField_1;

	public void mostrarDatosConTableModel() {

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					eliminarlista();
					// repetidos();
				}

			}

		});
		table_1.setVisible(true);
		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(5, 247, 504, 277);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(model1);
		JComboBox<String> cbboxProveedor = new JComboBox<String>();

		ArrayList<String> lista = new ArrayList<String>();
		lista = ProveedorDAO.llenar_combo();
		for (int i = 0; i < lista.size(); i++) {
			cbboxProveedor.addItem(lista.get(i));

		}

		TableColumn provColumn = table_1.getColumnModel().getColumn(2);
		provColumn.setCellEditor(new DefaultCellEditor(cbboxProveedor));

		table_1.setAutoCreateRowSorter(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getTableHeader().setReorderingAllowed(false);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {

		}, new String[] { "idProducto", "Detalle" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> str = new TableRowSorter<>(modelo);
		textField = new JTextField();
		textField.setBounds(83, 33, 245, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent jf) {

				str.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText(), 1));
				table.setRowSorter(str);
			}
		});
		textField_1 = new JTextField();
		textField_1.setBounds(5, 33, 68, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				str.setRowFilter(RowFilter.regexFilter("(?i)" + textField_1.getText(), 0));
				table.setRowSorter(str);
			}

		});

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				table_1 = new JTable();
//				scrollPane_1.setViewportView(table_1);

				cargarclick();

			}

			private void cargarclick() {
				// TODO Auto-generated method stub
				int[] ds = table.getSelectedRows();

				try {
					Object[] row = new Object[2];
					for (int i = 0; i < ds.length; i++) {

						row[0] = modelo.getValueAt(ds[i], 0);
						row[1] = modelo.getValueAt(ds[i], 1);

						model1.addRow(row);

					}

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table.setModel(modelo);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		JButton btnAgregar = new JButton("Agregar producto");
		btnAgregar.setBounds(5, 215, 89, 23);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StockVO mistockVO = new StockVO();
					for (int i = 0; i < model1.getRowCount(); i++) {
						StockDAO midao = new StockDAO();
						mistockVO.setIdStock(midao.contadorIdEntradaMateriales());
						mistockVO.setIdproducto((int) model1.getValueAt(i, 0));
						String prov = (String) model1.getValueAt(i, 2);
						mistockVO.setCantidad((int) model1.getValueAt(i, 3));
						int idpr = prov.indexOf("-");
						mistockVO.setIdproveedor(idpr);
						fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
						mistockVO.setFecha(fecha);
						midao.agregarEntrada(mistockVO);
					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showInternalMessageDialog(null, "Error al Cargar los datos","Agregar producto",JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(220, 528, 63, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		ProductoDAO miProducto = new ProductoDAO();
		miProducto.btnBuscarProd(modelo);
		atributostablas();
	}

	private void atributostablas() {

		// centralizado
		DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
		centrar.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
		izquierda.setHorizontalAlignment(SwingConstants.LEFT);

		DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
		derecha.setHorizontalAlignment(SwingConstants.RIGHT);

		table.getColumnModel().getColumn(0).setMaxWidth(78);

		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);

		table.getColumnModel().getColumn(0).setCellRenderer(centrar);

		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(1).setCellRenderer(izquierda);

		table_1.getColumnModel().getColumn(0).setMaxWidth(78);

		table_1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(78);

		table_1.getColumnModel().getColumn(0).setCellRenderer(centrar);

		table_1.getColumnModel().getColumn(1).setMinWidth(200);
		table_1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(200);
		table_1.getColumnModel().getColumn(1).setCellRenderer(izquierda);

		table_1.getColumnModel().getColumn(2).setMinWidth(150);
		table_1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(150);
		table_1.getColumnModel().getColumn(2).setCellRenderer(izquierda);

		table_1.getColumnModel().getColumn(3).setMinWidth(50);
		table_1.getTableHeader().getColumnModel().getColumn(3).setMinWidth(50);
		table_1.getColumnModel().getColumn(3).setCellRenderer(derecha);

	};

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
