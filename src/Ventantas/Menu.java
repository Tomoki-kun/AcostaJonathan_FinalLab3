package Ventantas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Ingreso;
import Clases.Persona;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JButton btnPersonas;
	protected JButton btnIngresos;
	protected JButton btnNewButton_2;
	private Map<Integer, Persona> personas;
	private Map<Integer, Ingreso> ingresos;
	private final String serPersonas = "personas.ser";
	private final String serIngresos = "ingresos.ser";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		// DESERIALIZACIÓN
		deserializarPersonas();
		deserializarIngresos();

		setResizable(false);
		setTitle("Menú Principal");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 308, 172);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.GREEN);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.btnPersonas = new JButton("Personas");
		this.btnPersonas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FPersonas vPersonas = new FPersonas(personas);
				vPersonas.setVisible(true);
			}
		});
		this.btnPersonas.setBounds(36, 33, 90, 36);
		this.contentPane.add(this.btnPersonas);

		this.btnIngresos = new JButton("Ingresos");
		this.btnIngresos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FIngresos vIngresos = new FIngresos(ingresos, personas);
				vIngresos.setVisible(true);
			}
		});
		this.btnIngresos.setBounds(182, 33, 90, 36);
		this.contentPane.add(this.btnIngresos);

		this.btnNewButton_2 = new JButton("Salir");
		this.btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(rootPane, "¡Que tenga un buen día!", "Saliendo", 2);

				// SERIALIZACIÓN
				serializarPersonas();
				serializarIngresos();

				System.exit(0);
			}
		});
		this.btnNewButton_2.setBounds(108, 79, 85, 36);
		this.contentPane.add(this.btnNewButton_2);

	}

	// MÉTODOS DE SERIALIZACIÓN/DESERIALIZACIÓN

	// PERSONAS
	private void serializarPersonas() {
		try (FileOutputStream fosPersona = new FileOutputStream(serPersonas);
				ObjectOutputStream oosPersona = new ObjectOutputStream(fosPersona)) {
			oosPersona.writeObject(personas);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	private void deserializarPersonas() {
		try (FileInputStream fisPersona = new FileInputStream(serPersonas);
				ObjectInputStream oisPersona = new ObjectInputStream(fisPersona)) {
			personas = (Map<Integer, Persona>) oisPersona.readObject();
			int id = 0;
			for (Iterator<Entry<Integer, Persona>> it = personas.entrySet().iterator(); it.hasNext();) {
				id = ((Entry<Integer, Persona>) it.next()).getValue().getID();
			}
			Persona.setIdStatic(id + 1);
		} catch (FileNotFoundException e) {
			personas = new HashMap<Integer, Persona>();
		} catch (IOException | ClassNotFoundException e) {
			personas = new HashMap<Integer, Persona>();
			e.printStackTrace();
		}
	}

	// INGRESOS
	private void serializarIngresos() {
		try (FileOutputStream fosIngreso = new FileOutputStream(serIngresos);
				ObjectOutputStream oosIngreso = new ObjectOutputStream(fosIngreso)) {
			oosIngreso.writeObject(ingresos);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	private void deserializarIngresos() {
		try (FileInputStream fisIngreso = new FileInputStream(serIngresos);
				ObjectInputStream oisIngreso = new ObjectInputStream(fisIngreso)) {
			ingresos = (Map<Integer, Ingreso>) oisIngreso.readObject();
			int id = 0;
			for (Iterator<Entry<Integer, Ingreso>> it = ingresos.entrySet().iterator(); it.hasNext();) {
				id = ((Entry<Integer, Ingreso>) it.next()).getValue().getIDIngreso();
			}
			Ingreso.setIdStatic(id + 1);
		} catch (FileNotFoundException e) {
			ingresos = new HashMap<Integer, Ingreso>();
		} catch (IOException | ClassNotFoundException e) {
			ingresos = new HashMap<Integer, Ingreso>();
			e.printStackTrace();
		}
	}
}
