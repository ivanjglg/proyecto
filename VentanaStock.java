package Vista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.Coordinador;
import Modelo.StockDAO;

import javax.swing.JTable;

public class VentanaStock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane jscrollPane;
	private Coordinador miCoordinador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaStock frame = new VentanaStock();
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
	public VentanaStock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(300, 300,300, 300));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
	
	
		
		mostrarDatosTableModel();
		
		
	}

	private void mostrarDatosTableModel() {
		// TODO Auto-generated method stub
		table = new JTable();
		table.setBounds(180, 111, 1, 1);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Producto");
		model.addColumn("Cantidad");
	
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 61, 414, 201);
		contentPane.add(jscrollPane);
	
		StockDAO miStockDAO = new StockDAO();
		miStockDAO.cargarModelStock(model);
		table.setModel(model);
		jscrollPane.setViewportView(table);
		
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
