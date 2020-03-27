import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class evoluciones extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static DefaultListModel<String> modeloListaEvoluciones = new DefaultListModel<String>();
	
	public static JList<String> lista_d_evoluciones;
	
	public static JTextArea area_evolucion = new JTextArea();
	
	gui.MiPopUp elpopup = new gui.MiPopUp();
	
	JTextField nome_medico = new JTextField();
	
	String[] array_especilidades = {
			
			"Cirugia",
			"Traumatologia",
			"Ginecologia",
			"Urologia",
			"Clinica Medica",
			"Otra"
			
	};
	
	JComboBox<String> lista_especialidades = new JComboBox<String>(array_especilidades);

	public evoluciones() {
		
		setLayout(new BorderLayout());
		
		add(new caja_encabezado(), BorderLayout.NORTH);
		add(new caja_navegacion_izq(), BorderLayout.WEST);
		add(new caja_evolucion(), BorderLayout.CENTER);
		
		
	}
	
	class caja_encabezado extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JButton guardar_evol = new JButton("Guardar");
		JButton new_evol = new JButton("Nueva");
		
		public caja_encabezado() {
			
			setBorder(BorderFactory.createTitledBorder(""));
			
			add(new JLabel("Medico que evoluciona:"));
			nome_medico.setPreferredSize(new Dimension(177,22));
			nome_medico.setComponentPopupMenu(elpopup);
			add(nome_medico);
			
			add(new JLabel("Especialidad:"));
			add(lista_especialidades);
			
			guardar_evol.setPreferredSize(new Dimension(93,22));
			new_evol.setPreferredSize(new Dimension(93,22));
			
			guardar_evol.addActionListener(new OyenteGuardarEvol());
			new_evol.addActionListener(new OyenteNuevaEvol());
			
			add(guardar_evol);
			add(new_evol);
			
			
		}
		
	}
	
	class caja_navegacion_izq extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public caja_navegacion_izq() {
			
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createTitledBorder("Seleccione evolucion:"));
			
			lista_d_evoluciones = new JList<String>(modeloListaEvoluciones);
			lista_d_evoluciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista_d_evoluciones.setPreferredSize(new Dimension(210, Integer.MAX_VALUE));
			lista_d_evoluciones.addListSelectionListener(new oyenteListaEvoluciones());
			
			JScrollPane scroll_evol = new JScrollPane();
			
			scroll_evol.setViewportView(lista_d_evoluciones);

			add(lista_d_evoluciones, BorderLayout.CENTER);
			
		}
		
	}
	
	class caja_evolucion extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public caja_evolucion() {
			
			setBorder(BorderFactory.createTitledBorder("Evolucion:"));
			setLayout(new BorderLayout());
			
			area_evolucion.setEditable(false);
			
			area_evolucion.setComponentPopupMenu(elpopup);
			
			JScrollPane scroll_evol = new JScrollPane();
			
			scroll_evol.setViewportView(area_evolucion);
			
			add(scroll_evol, BorderLayout.CENTER);			
		}
		
		
	}
	
	public static void cargandoEvoluciones(String path) {
		
		modeloListaEvoluciones.clear();
		
		File path_a_paciente_elegido = new File(path);
		
		String[] arrayEvoluciones = path_a_paciente_elegido.list();
		
		for (String evolution : arrayEvoluciones) {
			
			modeloListaEvoluciones.addElement(evolution);
			
		}
		
		
	}
	
	class oyenteListaEvoluciones implements ListSelectionListener{
		
		ArrayList<String> array_evolucion = new ArrayList<String>();

		public void valueChanged(ListSelectionEvent e) {
			
			if (e.getValueIsAdjusting() == true) {
				
				
			} else {
				
				
				if(lista_d_evoluciones.getSelectedValue() == null) {
					
					
				} else {
					
					String ruta_evol = baseDatosPanel.path_a_paciente_elegido + File.separator + "evoluciones" + File.separator + lista_d_evoluciones.getSelectedValue();
					
					try {
						
						BufferedReader bufferLeeEvolucion = new BufferedReader(new FileReader(ruta_evol));
						
						String lineatxt="";
						
						while (lineatxt != null) {
							
							lineatxt = bufferLeeEvolucion.readLine();
							
							array_evolucion.add(lineatxt);
							
						}
						
						bufferLeeEvolucion.close();
						
						area_evolucion.setText(""); //limpio el JTextArea de cualquier texto que quedara previamente
						
						for (int m=0; m<array_evolucion.size(); m++) {
							
							area_evolucion.append(array_evolucion.get(m));
							area_evolucion.append("\n");
							
						}
						
						array_evolucion.clear();
						
						area_evolucion.setEditable(false);
						
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					
				}
		
				
			}
			
		}
		
		
	}
	
	class OyenteNuevaEvol implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			area_evolucion.setEditable(true);
			area_evolucion.setText("");
			
		}
		
		
	}
	
	class OyenteGuardarEvol implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (nome_medico.getText().equals("")) {
				
				JOptionPane.showMessageDialog(evoluciones.this, "Debes ingresar tu nombre", "Nombre del médico", JOptionPane.ERROR_MESSAGE, null);
				
			} else {
				
				SimpleDateFormat miformato = new SimpleDateFormat("d-M-YYY-k-m");
				
				String evol_file_name = miformato.format(new Date()) + "-" + nome_medico.getText();
				
				String path_new_evol = baseDatosPanel.path_a_paciente_elegido + File.separator + "evoluciones" + File.separator + evol_file_name;
				
				File new_evolucion = new File(path_new_evol);
				
				try {
					
					new_evolucion.createNewFile();
					
					String evolucion_string = miformato.format(new Date()) + "\n \n" + "Medico: " + nome_medico.getText() + "\n" + 
					"Servicio: " + lista_especialidades.getSelectedItem().toString() + "\n \n" + area_evolucion.getText();
					
					ManejandoFicheros.GrabarTxtEnArchivo(new_evolucion, evolucion_string);
					
					cargandoEvoluciones(baseDatosPanel.path_a_paciente_elegido + File.separator + "evoluciones");
					
					area_evolucion.setEditable(false);
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
			
		}
		
		
	}

}
