package Ventantas;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Ingreso;
import Clases.Persona;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class FIngresos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel lblNewLabel;
	protected JTextField txtPatente;
	protected JButton btnIngreso;
	protected JButton btnCerrar;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JLabel lblNewLabel_1;

	public FIngresos(Map<Integer, Ingreso> ingresos, Map<Integer, Persona> personas) {
		setTitle("Registrar Ingreso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 617);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.ORANGE);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBackground(new Color(255, 204, 153));
		this.scrollPane.setBounds(10, 178, 357, 381);
		this.contentPane.add(this.scrollPane);

		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "ID", "Patente", "Fecha y Hora" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.scrollPane.setViewportView(this.table);

		this.lblNewLabel_1 = new JLabel("Ingresos registrados");
		this.lblNewLabel_1.setBounds(39, 141, 133, 27);
		this.contentPane.add(this.lblNewLabel_1);
		CargarTabla(ingresos);
		this.lblNewLabel = new JLabel("Patente:");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNewLabel.setBounds(39, 35, 99, 13);
		this.contentPane.add(this.lblNewLabel);

		this.txtPatente = new JTextField();
		this.txtPatente.setBounds(148, 32, 96, 19);
		this.contentPane.add(this.txtPatente);
		this.txtPatente.setColumns(10);

		this.btnIngreso = new JButton("Registar Ingreso");
		this.btnIngreso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtPatente.getText().isBlank()) {
					String patente = txtPatente.getText().toUpperCase();
					Iterator<Entry<Integer, Persona>> it = personas.entrySet().iterator();
					Persona encontrado = null;
					while (it.hasNext() && encontrado == null) {
						Persona persona = it.next().getValue();
						if (persona.getPatente().equals(patente))
							encontrado = persona;
					}
					if (encontrado != null) {
						String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
						Ingreso ingreso = new Ingreso(patente, fechaHora);
						ingresos.put(ingreso.getIDIngreso(), ingreso);
						CargarTabla(ingresos);
						txtPatente.setText("");
						JOptionPane.showMessageDialog(rootPane,
								"Ingreso registrado.\nFecha y Hora:" + ingreso.getFechaHora());

					} else
						JOptionPane.showMessageDialog(rootPane,
								"La patente ingresada no conocida.\nIngrese una patente válida", "Patente no válida",
								2);
				} else
					JOptionPane.showMessageDialog(rootPane, "Campo vacio");
			}
		});
		this.btnIngreso.setBounds(28, 79, 140, 28);
		this.contentPane.add(this.btnIngreso);

		this.btnCerrar = new JButton("Cerrar");
		this.btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		this.btnCerrar.setBounds(207, 79, 133, 28);
		this.contentPane.add(this.btnCerrar);

	}

	private void CargarTabla(Map<Integer, Ingreso> ingresos) {
		Object[][] aux = (Object[][]) new Object[ingresos.size()][3];
		int renglon = 0;
		for (Iterator<Entry<Integer, Ingreso>> it = ingresos.entrySet().iterator(); it.hasNext();) {
			Ingreso ingreso = ((Entry<Integer, Ingreso>) it.next()).getValue();
			aux[renglon][0] = ingreso.getIDIngreso();
			aux[renglon][1] = ingreso.getPatente();
			aux[renglon][2] = ingreso.getFechaHora();
			renglon++;
		}
		table.setModel(new DefaultTableModel(aux, new String[] { "ID", "Patente", "Fecha y Hora" }));
	}
}
