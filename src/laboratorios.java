import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class laboratorios extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton add_labo = new JButton("Agregar laboratorio");
	
	static DefaultListModel<String> modeloListaLabo = new DefaultListModel<String>();
	
	public static JList<String> lista_d_labos;
	
	public static JTextArea area_descrip_labo = new JTextArea();

	public laboratorios() {
		
		setLayout(new BorderLayout());
		
		add(new caja_lista_labos(), BorderLayout.WEST);
		
		add(new caja_descripcion(), BorderLayout.CENTER);
		
	}
	
	class caja_lista_labos extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public caja_lista_labos() {
			
			setLayout(new BorderLayout());
			
			setBorder(BorderFactory.createTitledBorder("Seleccionar / Agregar"));
			
			add_labo.addActionListener(new OyenteBotonAdd());
			
			add(add_labo, BorderLayout.NORTH);
			
			lista_d_labos = new JList<String>(modeloListaLabo);
			
			lista_d_labos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			lista_d_labos.setPreferredSize(new Dimension(210, Integer.MAX_VALUE));
			
			JScrollPane scroll_list_proc = new JScrollPane();
			
			scroll_list_proc.setViewportView(lista_d_labos);
			
			lista_d_labos.addListSelectionListener(new oyenteListaLabos());

			add(lista_d_labos, BorderLayout.CENTER);
			
		}
		
	}
	
	class caja_descripcion extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public caja_descripcion() {
			
			setLayout(new BorderLayout());
			
			setBorder(BorderFactory.createTitledBorder("Descripcion"));
			
			JScrollPane scroll_proced = new JScrollPane();
			
			scroll_proced.setViewportView(area_descrip_labo);
			
			area_descrip_labo.setEditable(false);
			
			add(area_descrip_labo, BorderLayout.CENTER);
			
		}
		
	}
	
	public static void cargandoLaboratorios(String path) {
		
		modeloListaLabo.clear();
		
		File path_a_paciente_elegido = new File(path);
		
		String[] arrayLabos = path_a_paciente_elegido.list();
		
		for (String laboratori : arrayLabos) {
			
			modeloListaLabo.addElement(laboratori);
			
		}
		
		
	}
	
	class oyenteListaLabos implements ListSelectionListener{
		
		ArrayList<String> array_laboratorios = new ArrayList<String>();

		public void valueChanged(ListSelectionEvent e) {
			
			if (e.getValueIsAdjusting() == true) {
				
				
			} else {
				
				
				if(lista_d_labos.getSelectedValue() == null) {
					
					
					
				} else {
					
					String ruta_labos = baseDatosPanel.path_a_paciente_elegido + File.separator + "laboratorios" + File.separator + lista_d_labos.getSelectedValue();
					
					try {
						
						BufferedReader bufferLeeProced = new BufferedReader(new FileReader(ruta_labos));
						
						String lineatxt="";
						
						while (lineatxt != null) {
							
							lineatxt = bufferLeeProced.readLine();
							
							array_laboratorios.add(lineatxt);
							
						}
						
						bufferLeeProced.close();
						
						area_descrip_labo.setText("");
						
						for (int m=0; m<array_laboratorios.size(); m++) {
							
							area_descrip_labo.append(array_laboratorios.get(m));
							area_descrip_labo.append("\n");
							
						}
						
						array_laboratorios.clear();
						
						
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
			}
			
			
			
		}
		
		
	}
	
	class OyenteBotonAdd implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			AddLaboratorio new_window = new AddLaboratorio();
			
			new_window.setVisible(true);
			
			
		}
		
		
	}

}
