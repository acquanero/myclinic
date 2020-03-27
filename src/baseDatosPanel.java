import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class baseDatosPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JList<String> listado_paciente;
	
	static DefaultListModel<String> modeloLista;
	
	public static File ruta_mother = new File(Principal.CheckFolder());
	
	public static String path_a_paciente_elegido;
	
	public baseDatosPanel(){
		
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createTitledBorder("Elije un paciente"));
		
		JLabel label_madre = new JLabel("Carpeta origen: " + ruta_mother.getAbsolutePath());
		
		
		//Creando el Jlist de pacientes a partir de un DefaultListModel
		
		modeloLista = new DefaultListModel<String>();
		
		try {
		
			for (String pacientito : pacientesArray()) {
			
				modeloLista.addElement(pacientito);
				
			}
			
			} catch (NullPointerException ex) {
				
				ex.printStackTrace();
				
				System.out.println("Aca esta el error");
				
			}
		
		listado_paciente = new JList<String>(modeloLista);
		
		listado_paciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listado_paciente.addListSelectionListener(new OyendeEventosLista());
		
		
		
		
		JScrollPane panellista = new JScrollPane();
		
		panellista.setViewportView(listado_paciente);
		
		add(label_madre,  BorderLayout.NORTH);
		add(panellista, BorderLayout.CENTER);
		
		
	}
	
	public static String[] pacientesArray(){
		
		String[] arrayPacientes = ruta_mother.list();
		
		return arrayPacientes;
		
		
		
	}
	
	class OyendeEventosLista implements ListSelectionListener{
		
		String paciente_elegido;
		//String path_a_paciente_elegido;
		
		ArrayList<String> array_datos_paciente = new ArrayList<String>();
		ArrayList<String> array_antecedentes = new ArrayList<String>();
		
		
		public void valueChanged(ListSelectionEvent e){
			
			if (e.getValueIsAdjusting() == true) {
				
				
			} else {
				
				if(listado_paciente.getSelectedValue() == null) {
					
					//si no hay paciente seleccionado no hacer nada. If necesario para evitar un error de null pointer
					
				} else {
					
					paciente_elegido = (String)listado_paciente.getSelectedValue();
					
					path_a_paciente_elegido = ruta_mother.getAbsolutePath() + File.separator + paciente_elegido;
					
					try {
						
						BufferedReader bufferLeeDatos = new BufferedReader(new FileReader(path_a_paciente_elegido + File.separator + "datos_personales.dat"));
						
						BufferedReader bufferLeeAntecedentes = new BufferedReader(new FileReader(path_a_paciente_elegido + File.separator + "antecedentes.dat"));
						
						String lineatxt="";
						
						while (lineatxt != null) {
							
							lineatxt = bufferLeeDatos.readLine();
							
							array_datos_paciente.add(lineatxt);
							
						}
						
						bufferLeeDatos.close();
						
						for (int j=0; j<9; j++) {
							
							datos_generales.ListaDeTextFields.get(j).setText(array_datos_paciente.get(j));
							
						}
						
						array_datos_paciente.clear();
						
						lineatxt="";
						
						while (lineatxt != null) {
							
							lineatxt = bufferLeeAntecedentes.readLine();
							
							array_antecedentes.add(lineatxt);
							
						}
						
						bufferLeeDatos.close();
						
						datos_generales.areaAntecedentes.setText(""); //limpio el JTextArea de cualquier texto que quedara previamente
						
						for (int m=0; m<array_antecedentes.size(); m++) {
							
							datos_generales.areaAntecedentes.append(array_antecedentes.get(m));
							datos_generales.areaAntecedentes.append("\n");
							
						}
						
						array_antecedentes.clear(); //limpio el array del texto que haya almacenado previamente
						
						bufferLeeAntecedentes.close();
						
						evoluciones.modeloListaEvoluciones.clear();
						
						evoluciones.cargandoEvoluciones(path_a_paciente_elegido + File.separator + "evoluciones");
						
						evoluciones.area_evolucion.setText("");
						
						procedimientos.modeloListaProced.clear();
						
						procedimientos.cargandoProcedimientos(path_a_paciente_elegido + File.separator + "procedimientos");
						
						procedimientos.area_descrip_proc.setText("");
						
						laboratorios.modeloListaLabo.clear();
						
						laboratorios.cargandoLaboratorios(path_a_paciente_elegido + File.separator + "laboratorios");
						
						laboratorios.area_descrip_labo.setText("");
						
						imagenes.modeloListaImagenes.clear();
						
						imagenes.cargandoImagenes(path_a_paciente_elegido + File.separator + "imagenes");
						
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					
				}
					
					
				}

		}
		
	}
	
	public static final void ActualizarLista() {
		
		modeloLista = new DefaultListModel<String>();
		
		for (String pacientito : pacientesArray()) {
				
			modeloLista.addElement(pacientito);
			
		}
		
		listado_paciente.setModel(modeloLista);

		
	}
	

}
