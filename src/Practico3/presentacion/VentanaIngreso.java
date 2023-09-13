package Practico3.presentacion;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Practico3.Examen;
import Practico3.Resultado;
import Practico3.accesoBD.AccesoBD;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIngreso {
	
	private static Connection con;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static DefaultListModel<String> examenesDLM;
	private Examen seleccionado;
	private JList<String> list;
	private static List<Examen> examenes = new ArrayList<Examen>();

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		examenesDLM = new DefaultListModel<String>();
		con = crearConexion();
		examenes.addAll(AccesoBD.listarExamenes(con));
		for(Examen examen : examenes) {
			examenesDLM.addElement(String.format("%s - %s - %s", examen.codigo, examen.materia, examen.periodo));
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIngreso window = new VentanaIngreso();
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
	public VentanaIngreso() {
		initialize();

		list.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent me) {
		      if (me.getClickCount() == 1) {
		         JList target = (JList)me.getSource();
		         int index = target.locationToIndex(me.getPoint());
		         if (index >= 0) {
		        	 seleccionado = examenes.get(index);
		        }
		      }
		   }
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 221, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 221, 214);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<String>();
		list.setModel(examenesDLM);
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		textField.setBounds(338, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(338, 47, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(241, 11, 87, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(241, 50, 87, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AccesoBD.ingresarResultado(con, new Resultado(Integer.parseInt(textField.getText()), seleccionado.codigo, Integer.parseInt(textField_1.getText())));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(241, 78, 183, 23);
		frame.getContentPane().add(btnNewButton);
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
}
