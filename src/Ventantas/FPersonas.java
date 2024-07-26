package Ventantas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Persona;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.Color;

public class FPersonas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JButton btnAgregar;
	protected JButton btnQuitar;
	protected JButton btnModificar;
	protected JLabel lblNewLabel;
	protected JLabel lblApellido;
	protected JLabel lblNombre;
	protected JLabel lblPatente;
	protected JTextField txtID;
	protected JTextField txtApellido;
	protected JTextField txtNombre;
	protected JTextField txtPatente;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JButton btnCerrar;

	public FPersonas(Map<Integer, Persona> personas) {
		setTitle("Personas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 817, 305);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.PINK);
		this.contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtApellido.getText().isBlank() && !txtNombre.getText().isBlank()
						&& !txtPatente.getText().isBlank()) {
					try {
						Persona persona = new Persona(txtApellido.getText(), txtNombre.getText(),
								txtPatente.getText().toUpperCase());
						if (personas.put(persona.getID(), persona) == null) {
							CargarTabla(personas);
							limpiarCampos();
							JOptionPane.showMessageDialog(rootPane, "Agregado correctamente");
						} else
							JOptionPane.showMessageDialog(rootPane, "No pudo agregarse");
					} catch (Exception ex) {
						ex.getMessage();
					}
				} else
					JOptionPane.showMessageDialog(rootPane, "Campos incompletos");
			}
		});
		this.btnAgregar.setBounds(10, 198, 85, 21);
		this.contentPane.add(this.btnAgregar);

		this.btnQuitar = new JButton("Quitar");
		this.btnQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					int id = Integer.parseInt(txtID.getText());
					personas.remove(id);
					CargarTabla(personas);
					limpiarCampos();
					JOptionPane.showMessageDialog(rootPane, "Eliminado correctamente");
				} else
					JOptionPane.showMessageDialog(rootPane, "Seleccione una persona de la lista");
			}
		});
		this.btnQuitar.setBounds(105, 198, 85, 21);
		this.contentPane.add(this.btnQuitar);

		this.btnModificar = new JButton("Modificar");
		this.btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (table.getSelectedRow() != -1) {
					int id = Integer.parseInt(txtID.getText());
					Persona modificar = personas.get(id);
					if (modificar != null) {
						modificar.setApellido(txtApellido.getText());
						modificar.setNombre(txtNombre.getText());
						modificar.setPatente(txtPatente.getText().toUpperCase());
						CargarTabla(personas);
						limpiarCampos();
						JOptionPane.showMessageDialog(rootPane, "Modificado correctamente");
					} else
						JOptionPane.showMessageDialog(rootPane, "No se encontró en la lista");

				} else
					JOptionPane.showMessageDialog(rootPane, "Seleccione un producto del carrito");
			}
		});
		this.btnModificar.setBounds(196, 198, 85, 21);
		this.contentPane.add(this.btnModificar);

		this.lblNewLabel = new JLabel("ID:");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		this.lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.lblNewLabel.setBounds(10, 31, 85, 13);
		this.contentPane.add(this.lblNewLabel);

		this.lblApellido = new JLabel("Apellido:");
		this.lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblApellido.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.lblApellido.setBounds(10, 72, 85, 13);
		this.contentPane.add(this.lblApellido);

		this.lblNombre = new JLabel("Nombre:");
		this.lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNombre.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.lblNombre.setBounds(20, 115, 75, 13);
		this.contentPane.add(this.lblNombre);

		this.lblPatente = new JLabel("Patente:");
		this.lblPatente.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblPatente.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.lblPatente.setBounds(20, 161, 75, 13);
		this.contentPane.add(this.lblPatente);

		this.txtID = new JTextField();
		this.txtID.setBounds(109, 25, 96, 19);
		this.contentPane.add(this.txtID);
		this.txtID.setColumns(10);

		this.txtApellido = new JTextField();
		this.txtApellido.setColumns(10);
		this.txtApellido.setBounds(109, 66, 96, 19);
		this.contentPane.add(this.txtApellido);

		this.txtNombre = new JTextField();
		this.txtNombre.setColumns(10);
		this.txtNombre.setBounds(109, 109, 96, 19);
		this.contentPane.add(this.txtNombre);

		this.txtPatente = new JTextField();
		this.txtPatente.setColumns(10);
		this.txtPatente.setBounds(109, 155, 96, 19);
		this.contentPane.add(this.txtPatente);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(291, 10, 508, 209);
		this.contentPane.add(this.scrollPane);

		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = table.getSelectedRow();
				if (fila != -1) {
					txtID.setText(table.getValueAt(fila, 0).toString());
					txtApellido.setText(table.getValueAt(fila, 1).toString());
					txtNombre.setText(table.getValueAt(fila, 2).toString());
					txtPatente.setText(table.getValueAt(fila, 3).toString());
				}
			}
		});
		this.table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "ID", "Apellido", "Nombre", "Patente" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.scrollPane.setViewportView(this.table);

		this.btnCerrar = new JButton("Cerrar");
		this.btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		this.btnCerrar.setBounds(10, 227, 271, 21);
		this.contentPane.add(this.btnCerrar);

		CargarTabla(personas);
	}

	private void CargarTabla(Map<Integer, Persona> personas) {
		Object[][] aux = (Object[][]) new Object[personas.size()][4];
		int renglon = 0;
		for (Iterator<Entry<Integer, Persona>> it = personas.entrySet().iterator(); it.hasNext();) {
			Persona persona = ((Entry<Integer, Persona>) it.next()).getValue();
			aux[renglon][0] = persona.getID();
			aux[renglon][1] = persona.getApellido();
			aux[renglon][2] = persona.getNombre();
			aux[renglon][3] = persona.getPatente();
			renglon++;
		}
		table.setModel(new DefaultTableModel(aux, new String[] { "ID", "Apellido", "Nombre", "Patente" }));
	}

	private void limpiarCampos() {
		txtID.setText("");
		txtApellido.setText("");
		txtNombre.setText("");
		txtPatente.setText("");
	}
}
