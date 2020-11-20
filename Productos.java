package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import Controlador.Coordinador;
import Modelo.ProductoDAO;
import Modelo.ProductoVO;

public class Productos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table_1;
	private JTextField textField;
	private JTextField textCantidad;
	private JButton btnAgregar,btnSalir;
	private Coordinador miCoordinador;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productos frame = new Productos();
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
	public Productos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(30, 419, 58, 14);
		contentPane.add(lblCantidad);

		textField = new JTextField();
		textField.setBounds(67, 8, 393, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(366, 409, 139, 34);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				miCoordinador.pasarfila(pasarfila());
				
			}
		});
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(552, 409, 139, 34);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			

			}
		});

		textCantidad = new JTextField();

		textCantidad.setBounds(91, 416, 86, 20);
		contentPane.add(textCantidad);

		mostrarDatosConTableModel();
	}

	public 	Object[] pasarfila() {
		// TODO Auto-generated method stub
		ProductoVO miProductoVO = new ProductoVO();
		int row = table_1.getSelectedRow();
		System.out.println(row);
//		int cantstock = Integer.valueOf(table_1.getValueAt(row, 2).toString());
//		int canti = Integer.valueOf(textCantidad.getText());
//		if (cantstock>canti ) {
//			int num=Integer.valueOf(table_1.getValueAt(row, 0).toString());
//			String letra =table_1.getValueAt(row, 1).toString();
//			Double prec = Double.valueOf(table_1.getValueAt(row, 3).toString());
//			int nu =Integer.valueOf(textCantidad.getText());
//			System.out.println(+num+ letra+prec+ +nu);
			Object lista[] = new Object[4];
			
				
				lista [0]=table_1.getValueAt(row, 0);
				lista [1]=table_1.getValueAt(row, 1);
				lista [2]=table_1.getValueAt(row, 3);
				lista [3]=Integer.parseInt(textCantidad.getText());
			
			for (int i = 0; i < lista.length; i++) {
				System.out.println(lista[i]);
			}
	
//		}else {
//			JOptionPane.showMessageDialog(null, "Stock Insuficiente", "Error", JOptionPane.INFORMATION_MESSAGE);
			
//		}
		return lista;
	}

	public void mostrarDatosConTableModel() {
		DefaultTableModel modelo1 = new DefaultTableModel();
		modelo1.addColumn("Idproducto");
		modelo1.addColumn("Detalle");
		modelo1.addColumn("Cantidad Stock");
		modelo1.addColumn("Precio");
	
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 843, 344);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();

		scrollPane_1.setViewportView(table_1);
		table_1.setModel(modelo1);

		table_1.getTableHeader().setReorderingAllowed(true);

		ProductoDAO.cargarTabla(modelo1);

	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}


	
}
