package Practico3.presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Practico3.DatosTabla;
import Practico3.Resultado;
import Practico3.accesoBD.AccesoBD;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class VentanaResultados {

	private static Connection con;
	private JFrame frame;
	private static JTextField textField;
	private JTable table;
	private static List<DatosTabla> resultados = new ArrayList<DatosTabla>();
	private static DefaultTableModel resultadosDTM = new DefaultTableModel(
			new Object[][] {},
			new String[] {"Codigo", "Materia", "Calificacion"}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		con = crearConexion();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaResultados window = new VentanaResultados();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaResultados() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula");
		lblNewLabel.setBounds(10, 11, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(90, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarResultados();
			}
		});
		btnNewButton.setBounds(10, 36, 166, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 36, 420, 214);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(resultadosDTM);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Resultados");
		lblNewLabel_1.setBounds(186, 11, 238, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	public static Connection crearConexion() throws ClassNotFoundException, SQLException {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			String url = "jdbc:mysql://localhost:3306/Bedelia";
			return DriverManager.getConnection(url, "root", "root");
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void actualizarResultados() throws NumberFormatException{
		try { 
			resultados.clear();
			resultados.addAll(AccesoBD.listarResultados(con, Integer.parseInt(textField.getText())));
			for(int i: new int[resultadosDTM.getRowCount()]){
				resultadosDTM.removeRow(0);
			}
			for(DatosTabla resultado : resultados) {
				resultadosDTM.insertRow(0, new Object[] {resultado.codigo, resultado.materia, resultado.calificacion});
			}
			resultadosDTM.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println(e);
			textField.setText("");
		}
	}
}
