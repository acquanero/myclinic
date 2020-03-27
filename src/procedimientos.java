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

public class procedimientos extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton add_proced = new JButton("Agregar procedimiento");
	
	static DefaultListModel<String> modeloListaProced = new DefaultListModel<String>();
	
	public static JList<String> lista_d_procedimientos;
	
	public static JTextArea area_descrip_proc = new JTextArea();
	
	public procedimientos() {
		
		setLayout(new BorderLayout());
		
		add(new caja_lista_procedimientos(), BorderLayout.WEST);
		
		add(new caja_descripcion(), BorderLayout.CENTER);
		
		
	}
	
	class caja_lista_procedimientos extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public caja_lista_procedimientos() {
			
			setLayout(new BorderLayout());
			
			setBorder(BorderFactory.createTitledBorder("Seleccionar / Agregar"));
			
			add_proced.addActionListener(new OyenteBotonAdd());
			
			add(add_proced, BorderLayout.NORTH);
			
			lista_d_procedimientos = new JList<String>(modeloListaProced);
			
			lista_d_procedimientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			lista_d_procedimientos.setPreferredSize(new Dimension(210, Integer.MAX_VALUE));
			
			JScrollPane scroll_list_proc = new JScrollPane();
			
			scroll_list_proc.setViewportView(lista_d_procedimientos);
			
			lista_d_procedimientos.addListSelectionListener(new oyenteListaProced());

			add(lista_d_procedimientos, BorderLayout.CENTER);
			
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
			
			scroll_proced.setViewportView(area_descrip_proc);
			
			area_descrip_proc.setEditable(false);
			
			add(area_descrip_proc, BorderLayout.CENTER);
			
		}
		
	}
	
	public static void cargandoProcedimientos(String path) {
		
		modeloListaProced.clear();
		
		File path_a_paciente_elegido = new File(path);
		
		String[] arrayProcedimientos = path_a_paciente_elegido.list();
		
		for (String procedure : arrayProcedimientos) {
			
			modeloListaProced.addElement(procedure);
			
		}
		
		
	}
	
	class oyenteListaProced implements ListSelectionListener{
		
		ArrayList<String> array_procedure = new ArrayList<String>();

		public void valueChanged(ListSelectionEvent e) {
			
			if (e.getValueIsAdjusting() == true) {
				
				
			} else {
				
				
				if(lista_d_procedimientos.getSelectedValue() == null) {
					
					
					
				} else {
					
					String ruta_proc = baseDatosPanel.path_a_paciente_elegido + File.separator + "procedimientos" + File.separator + lista_d_procedimientos.getSelectedValue();
					
					try {
						
						BufferedReader bufferLeeProced = new BufferedReader(new FileReader(ruta_proc));
						
						String lineatxt="";
						
						while (lineatxt != null) {
							
							lineatxt = bufferLeeProced.readLine();
							
							array_procedure.add(lineatxt);
							
						}
						
						bufferLeeProced.close();
						
						area_descrip_proc.setText("");
						
						for (int m=0; m<array_procedure.size(); m++) {
							
							area_descrip_proc.append(array_procedure.get(m));
							area_descrip_proc.append("\n");
							
						}
						
						array_procedure.clear();
						
						
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
			
			AddProcedure new_window = new AddProcedure();
			
			new_window.setVisible(true);
			
			
		}
		
		
	}

}
