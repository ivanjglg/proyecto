package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controlador.Coordinador;
import Modelo.ProductoDAO;
import Modelo.ProductoVO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaProducto extends JFrame {

	private JPanel contentPane;
//	private JTable table;
	private JTextField textIdProducto;
	private JTextField textDescripcion;
	private JTextField textPrecio;
	private Coordinador miCoordinador;
	private JScrollPane jscrollPane;
	private JTable table;
	private JTextField textBid;
	private JTextField textDetalle;
//	private ProductoVO miProducto;
	private DefaultTableModel modelo;
	private JTextField textprecio2;
	private JButton btnAgregar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaProducto frame = new VistaProducto();
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
	public VistaProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdProducto = new JLabel("Idproducto");
		lblIdProducto.setBounds(10, 11, 59, 14);
		contentPane.add(lblIdProducto);

		textIdProducto = new JTextField();
		textIdProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				} // TODO add your handling code here:
			}
		});

		textIdProducto.setBounds(71, 8, 86, 20);
		contentPane.add(textIdProducto);
		textIdProducto.setColumns(10);

		JLabel lblNewLabel = new JLabel("Descripcion");
		lblNewLabel.setBounds(10, 42, 46, 14);
		contentPane.add(lblNewLabel);

		textDescripcion = new JTextField();
		textDescripcion.setBounds(71, 39, 86, 20);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Precio:");
		lblNewLabel_1.setBounds(10, 73, 46, 14);
		contentPane.add(lblNewLabel_1);

		textPrecio = new JTextField();
		textPrecio.setBounds(71, 70, 86, 20);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				} 
			}
		});

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarProducto();
			}
		});
		btnAgregar.setBounds(163, 7, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(163, 38, 89, 23);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				buscarProducto();
				Habilita(true, true,true,true);
			}
		});
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarProductoclick();
			}
		});
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		btnLimpiar.setBounds(163, 69, 89, 23);
		contentPane.add(btnLimpiar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eliminarProducto();
				mostarDatos();
			}
		});
		btnEliminar.setBounds(262, 7, 89, 23);
		contentPane.add(btnEliminar);
		
		textBid = new JTextField();
		textBid.setBounds(10, 109, 130, 20);
		contentPane.add(textBid);
		textBid.setColumns(10);
		textBid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				} 
			}
		});
		textBid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				TableRowSorter<TableModel> str = new TableRowSorter<>(modelo);
				str.setRowFilter(RowFilter.regexFilter("(?i)" + textBid.getText(), 0));
				table.setRowSorter(str);
			}
		});
		
		textDetalle = new JTextField();
		textDetalle.setBounds(152, 109, 130, 20);
		contentPane.add(textDetalle);
		textDetalle.setColumns(10);
		textDetalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				TableRowSorter<TableModel> str = new TableRowSorter<>(modelo);
				str.setRowFilter(RowFilter.regexFilter("(?i)" + textDetalle.getText(), 1));
				table.setRowSorter(str);
			}
		});
		textprecio2 = new JTextField();
		textprecio2.setBounds(292, 109, 132, 20);
		contentPane.add(textprecio2);
		textprecio2.setColumns(10);
		textprecio2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					getToolkit().beep();

					e.consume();

				} 
			}
		});
		textprecio2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				TableRowSorter<TableModel> str = new TableRowSorter<>(modelo);
				str.setRowFilter(RowFilter.regexFilter("(?i)" + textprecio2.getText(), 2));
				table.setRowSorter(str);
			}
		});
		mostarDatos();
		Habilita(false, false,false,false);
	}

		protected void eliminarProducto() {
		// TODO Auto-generated method stub
			
			miCoordinador.eliminarProducto(Integer.parseInt(textIdProducto.getText()));
	}

		protected void Limpiar() {
		// TODO Auto-generated method stub
			textIdProducto.setText("");
			textDescripcion.setText("");;
			textPrecio.setText("");
		
	}

		public void Habilita(boolean IdProducto, boolean Descripcion, boolean Precio, boolean Agregar) {
		// TODO Auto-generated method stub
			textIdProducto.setEditable(IdProducto);
			textDescripcion.setEditable(Descripcion);
			textPrecio.setEditable(Precio);
			btnAgregar.setEnabled(Agregar);
			
	}

//	protected void buscarProducto() {
	protected void buscarProductoclick() {
		try {
			int row = table.getSelectedRow();
			textIdProducto.setText(table.getValueAt(row, 0).toString());
			textDescripcion.setText(table.getValueAt(row, 1).toString());
			textPrecio.setText(table.getValueAt(row, 2).toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Ocurrio un error al seleccionar", "buscarProductoclick",
					JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}

	}

	public void mostarDatos() {
		modelo = new DefaultTableModel();
		modelo.addColumn("IdProducto");
		modelo.addColumn("Detalle");
		modelo.addColumn("Pecio");
		ProductoDAO.cargarTabla1(modelo);
		table.setModel(modelo);
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 129, 414, 122);
		contentPane.add(jscrollPane);
		jscrollPane.setViewportView(table);
		

	}

	protected void registrarProducto() {
		// TODO Auto-generated method stub
		ProductoVO miProducto = new ProductoVO();
		ProductoDAO miDao = new ProductoDAO();
		miProducto.setIdproducto(miDao.contadorIdProducto());
		miProducto.setDetalle(textDescripcion.getText());
		miProducto.setPrecioventa(Double.valueOf(textPrecio.getText()));
		miCoordinador.resgistrarProducto(miProducto);
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
