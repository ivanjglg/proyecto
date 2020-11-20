package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Controlador.Coordinador;
import Modelo.ClienteVO;
import Modelo.FacturaDAO;
import Modelo.FacturaVO;
import Modelo.ProductoVO;

public class VistaSalidademateriales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCalendar calendar;
	private JPanel contentPane;
	private JTextField textcodcliente;
	private JTextField textRazonsocial;
	private JTextField textDoc;
	private JTextField textField_1;
	private JTextField textNumerofactura;
	private JTextField textField_3;
	private Coordinador miCoordinador;
	private JComboBox<String> cbtipodoc;
	private JComboBox<String> cbresponsa;
	public JTable table1;
	private JScrollPane scrollPane;
	private JComboBox<String> cbbiva;

	private JComboBox<String> cbdescuento;
	private int a;
	private int cantidad;
	private Double precio;
	private Object fila[];
	private JComboBox<String> cbbiva_1;
	private int b;
	private double Total;
	private int subTotal;
	private double descuento;
	private float IVA;
	private FacturaVO miFacturaVO;
	private JComboBox<String> cbbtipo;
	private JDateChooser dateChooser_1;
	private String fecha;
	DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {

	}, new String[] { "Codigo", "Descripcion", "Cantidad", "Precio", "Subtotal" }) {

		private static final long serialVersionUID = 1L;
		Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Integer.class, Integer.class };

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false, true };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

	};
	private JTextField textDescuento;
	private JTextField textIVA;
	private JTextField textTotal;
	private JTextField textSubtotal;
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaSalidademateriales frame = new VistaSalidademateriales();
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
	public VistaSalidademateriales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textcodcliente = new JTextField();

		textcodcliente.setBounds(93, 11, 86, 20);
		contentPane.add(textcodcliente);
		textcodcliente.setColumns(10);

		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 14, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Razon Social");
		lblNewLabel_1.setBounds(10, 43, 60, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(10, 68, 60, 14);
		contentPane.add(lblDocumento);

		JLabel lblNewLabel_3 = new JLabel("IVA");
		lblNewLabel_3.setBounds(10, 93, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Vendedor:");
		lblNewLabel_4.setBounds(10, 118, 50, 14);
		contentPane.add(lblNewLabel_4);

		// Instanciar Componente
		calendar = new JCalendar();
		calendar.setTodayButtonVisible(true);
		calendar.setTodayButtonText("Hoy Día");
		calendar.setNullDateButtonVisible(true);
		// Ubicar y agregar al panel
		calendar.setBounds(363, 130, 230, 162);

		// contentPane.add(calendar);

		textRazonsocial = new JTextField();
		textRazonsocial.setBounds(93, 37, 86, 20);
		contentPane.add(textRazonsocial);
		textRazonsocial.setColumns(10);

		JLabel lblVendedor = new JLabel("New label");
		lblVendedor.setBounds(93, 118, 86, 14);
		contentPane.add(lblVendedor);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Comprobante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(348, 0, 409, 161);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblcomprobante = new JLabel("Tipo");
		lblcomprobante.setBounds(10, 15, 74, 14);
		panel_1.add(lblcomprobante);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(94, 63, 100, 20);
		panel_1.add(dateChooser_1);
		dateChooser_1.getJCalendar();

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(10, 44, 46, 14);
		panel_1.add(lblNumero);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 38, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		textNumerofactura = new JTextField();
		textNumerofactura.setBounds(187, 38, 86, 20);
		panel_1.add(textNumerofactura);
		textNumerofactura.setColumns(10);
		textNumerofactura.setEditable(false);
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(536, 448, 88, 14);
		contentPane.add(lblDescuento);

		textIVA = new JTextField();
		textIVA.setBounds(425, 470, 86, 20);
		contentPane.add(textIVA);
		textIVA.setColumns(10);

		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(425, 448, 86, 14);
		contentPane.add(lblIva);

		textTotal = new JTextField();
		textTotal.setBounds(317, 470, 86, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(317, 448, 78, 14);
		contentPane.add(lblTotal);

		textSubtotal = new JTextField();
		textSubtotal.setBounds(671, 470, 86, 20);
		contentPane.add(textSubtotal);
		textSubtotal.setColumns(10);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(671, 448, 86, 14);
		contentPane.add(lblSubtotal);
		JLabel lblNewLabel_5 = new JLabel("Fecha:");
		lblNewLabel_5.setBounds(10, 69, 46, 14);
		panel_1.add(lblNewLabel_5);

		JLabel lbldescuento = new JLabel("%  Descuento:");
		lbldescuento.setBounds(10, 94, 74, 14);
		panel_1.add(lbldescuento);

		JLabel lblNewLabel_2 = new JLabel("Remito:");
		lblNewLabel_2.setBounds(283, 44, 46, 14);
		panel_1.add(lblNewLabel_2);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 338, 161);
		contentPane.add(panel);
		panel.setLayout(null);

		textDoc = new JTextField();
		textDoc.setBounds(170, 61, 147, 20);
		panel.add(textDoc);
		textDoc.setColumns(10);

		JComboBox<String> cbbtipo = new JComboBox<String>();
		cbbtipo.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Factura A", "Factura B", "Factura C" }));
		cbbtipo.setBounds(94, 11, 210, 22);
		panel_1.add(cbbtipo);
		cbbtipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s = cbbtipo.getSelectedIndex();
				tipoFactura(s);
				totalFactura();
				tipo = (cbbtipo.getSelectedItem().toString());

			}
		});

		JComboBox<String> cbdescuento = new JComboBox<String>();
		cbdescuento.setModel(new DefaultComboBoxModel<String>(new String[] { "", "0.05", "0.12", "0.15", "0.20" }));
		cbdescuento.setBounds(94, 90, 46, 22);
		panel_1.add(cbdescuento);
		cbdescuento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				a = cbdescuento.getSelectedIndex();
				funcionDescuento(a);
				totalFactura();
				funcionIVA(b);
			}
		});

		cbtipodoc = new JComboBox<String>();
		cbtipodoc.setModel(new DefaultComboBoxModel<String>(new String[] { "", "CUIT", "DNI", "Sin identificar" }));
		cbtipodoc.setBounds(93, 60, 67, 22);
		panel.add(cbtipodoc);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(207, 11, 19, 23);
		panel.add(btnNewButton);

		cbresponsa = new JComboBox<String>();
		cbresponsa.setBounds(93, 92, 224, 22);
		panel.add(cbresponsa);
		cbresponsa.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "Exento", "Responsable Inscripto", "Consumidor final", "Responsable Monotributo" }));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarCliente();

			}

			private void btnBuscarCliente() {
				// TODO Auto-generated method stub

				miCoordinador.mostrarVentanaBusqueda();
			}
		});

		textField_3 = new JTextField();
		textField_3.setBounds(325, 38, 74, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		JComboBox<String> cbbiva_1 = new JComboBox<String>();
		cbbiva_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = cbbiva_1.getSelectedIndex();
				funcionIVA(b);
				totalFactura();
			}
		});

		cbbiva_1.setBounds(172, 90, 111, 22);
		panel_1.add(cbbiva_1);
		cbbiva_1.setModel(new DefaultComboBoxModel<String>(new String[] { "", "21.00" }));

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miCoordinador.mostrarVentanaProductos();

			}
		});
		btnNewButton_1.setBounds(0, 162, 89, 23);
		contentPane.add(btnNewButton_1);
		JFormattedTextField textField_3 = new JFormattedTextField();
		textField_3.setValue(new Integer(3));
		textField_3.getValue();

		mostrarDatosConTableModel();

	}

	private void totalFactura() {
		// TODO Auto-generated method stub

		Total = ((subTotal - descuento) + IVA);

		textTotal.setText(String.valueOf(Math.round(Total)));
	}

	protected void tipoFactura(int s) {
		// TODO Auto-generated method stub
		FacturaDAO miFacturaDAO = new FacturaDAO();
		switch (s) {

		case 0:
			textNumerofactura.setText(String.valueOf(miFacturaDAO.contadorfacA()));
			break; // break es opcional

		case 1:
			textNumerofactura.setText(String.valueOf(miFacturaDAO.contadorfacB()));
			break; // break es opcional

		default:
			textNumerofactura.setText(String.valueOf(miFacturaDAO.contadorfacC()));
		}

	}

	public void mostrarDatosConTableModel() {

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		scrollPane.setBounds(10, 196, 754, 241);
		JTable table1 = new JTable();
	
		table1.getTableHeader().setReorderingAllowed(true);
		scrollPane.setViewportView(table1);
		table1.setModel(modelo1);

		textDescuento = new JTextField();
		textDescuento.setBounds(536, 470, 89, 20);
		contentPane.add(textDescuento);
		textDescuento.setColumns(10);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(0, 448, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					miFacturaVO = new FacturaVO();
					miFacturaVO.setNumeroFactura(Integer.valueOf(textNumerofactura.getText()));

					miFacturaVO.setTipoFactura(tipo);

					miFacturaVO.setIdCliente(Integer.valueOf(textcodcliente.getText()));

					miFacturaVO.setTotal(Integer.valueOf(textTotal.getText()));
					fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser_1.getDate());
					miFacturaVO.setFecha(fecha);
					miCoordinador.guardarFactura(miFacturaVO);
					limpiarCampos();
				}
				
			}
		});

	}

	protected void limpiarCampos() {
		// TODO Auto-generated method stub
		
	}

	public void muestraCliente(ClienteVO miClienteVO) {
		textcodcliente.setText(String.valueOf(miClienteVO.getCodigocliente()));
		textRazonsocial.setText(miClienteVO.getRazonsocial());
		textDoc.setText(String.valueOf(miClienteVO.getDocumento()));
		cbtipodoc.setSelectedItem(miClienteVO.getTipodoc());
		cbresponsa.setSelectedItem(miClienteVO.getResponsabilidad());

	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;

	}

	public void tomarProducto(Object[] objects) {
		// TODO Auto-generated method stub
//		double suma1 = 0;
		int cantidad;
		Double precio;

		double suma = 0;

		Object fila[] = new Object[9];
//		fila[0] = objects.getIdproducto();
//		fila[1] = objects.getDetalle();
//		fila[2] = objects.getCantidad();
//		fila[3] = objects.getPrecioventa();
//		cantidad = objects.getCantidad();
//		precio = objects.getPrecioventa();
//		fila[4] = cantidad * precio;
		modelo1.addRow(objects);
		sumarSubTotal();
		funcionDescuento(a);
		funcionIVA(b);
		totalFactura();
	}

	private void sumarSubTotal() { // hacer el subtotal de la suma de los productos precios por cantidad
		// TODO Auto-generated method stub
		int rowCount = modelo1.getRowCount(); // contador de filas
		double sumador[] = new double[rowCount]; // creamos vector
		for (int i = 0; i < rowCount; i++) { // definimos la finalidad del for segun las cantidad de filas agregadas en
												// el modelo1
			sumador[i] = (double) modelo1.getValueAt(i, 4);// tomamos del modelo la fila y columna donde se encuentre la
															// celda de subtotales
			subTotal = (int) (subTotal + sumador[i]); // acumula todos los subtotales

		}
		textSubtotal.setText(String.valueOf(subTotal));// pasamos suma1 tipo string para que lo tome el set text

	}

	protected void funcionDescuento(int a) {
		// TODO Auto-generated method stub
		switch (a) { // a = al index que se encuentra en cbdescuento

		case 1:
			// pasamos el subtotal
			descuento = subTotal * 0.05; // el subtotal * el porcentage seleccionado en el cbbdescuento
			textDescuento.setText(String.valueOf(Math.floor(descuento)));// tomamos x redondeamos y covertimos en string

			break;
		case 2:
			descuento = subTotal * 0.12;

			textDescuento.setText(String.valueOf(Math.floor(descuento)));

			break;
		case 3:
			descuento = subTotal * 0.20;
			textDescuento.setText(String.valueOf(Math.floor(descuento)));

			break;

		default:
			break;
		}

	}

	protected void funcionIVA(int b) {
		// TODO Auto-generated method stub
		// descuento = Double.valueOf(textDescuento.getText());
		switch (b) { // a = al index que se encuentra en cbbIVA
		case 0:

			textIVA.setText(String.valueOf(0));
			IVA = 0;
			break;
		case 1:
			//
			IVA = (float) ((subTotal - descuento) * 0.21);
			textIVA.setText(String.valueOf(Math.floor(IVA)));

			break;

		}

	}
}
