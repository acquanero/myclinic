import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProcedure extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField name_doctor = new JTextField(50);
	
	JComboBox<String> list_diagnosis;
	
	JButton add_diagnosis = new JButton("Agregar");
	
	JComboBox<String> list_procedures;
	
	JButton add_a_procedure = new JButton("Agregar");
	
	Dimension tamagnio_boton = new Dimension(70,10);
	
	JTextArea area_procedimiento = new JTextArea();
	
	JButton save_procedure = new JButton("Guardar");
	JButton cancel_window = new JButton("Cancelar");
	
	gui.MiPopUp elpopup = new gui.MiPopUp();
	
	public AddProcedure() {
		
		setTitle("Agregar Procedimiento");
		
		setLayout(new BorderLayout());
		
		setSize(500,500);
		
		setLocationRelativeTo(Principal.ventana);
		
		add(new PanelEncabezado(), BorderLayout.NORTH);
		
		add(new PanelDescripcion(), BorderLayout.CENTER);
		
		add(new PanelInfBotones(), BorderLayout.SOUTH);
		
	}
	
	class PanelEncabezado extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PanelEncabezado() {
			
			setBorder(BorderFactory.createTitledBorder("Encabezado"));
			
			setLayout(new GridBagLayout());
			
			add(new JLabel("Medico"), GenerarMyConstrain(0,0,1,1,0.0,1.0,GridBagConstraints.NONE));
			name_doctor.setComponentPopupMenu(elpopup);
			add(name_doctor, GenerarMyConstrain(1,0,1,1,0.0,1.0,GridBagConstraints.HORIZONTAL));
			add(new JLabel(""), GenerarMyConstrain(2,0,1,1,0.0,1.0,GridBagConstraints.HORIZONTAL));
			
			add(new JLabel("Diagnostico"), GenerarMyConstrain(0,1,1,1,0.0,1.0,GridBagConstraints.NONE));
			
			
			list_diagnosis = new JComboBox<String>(GetLista("diagnosticos.txt"));
			
			
			list_diagnosis.setEditable(true);
			add(list_diagnosis, GenerarMyConstrain(1,1,1,1,1.0,1.0,GridBagConstraints.HORIZONTAL));
			add_diagnosis.setPreferredSize(tamagnio_boton);
			add_diagnosis.addActionListener(new OyenteBotonGrabarCombo(add_diagnosis));
			add(add_diagnosis, GenerarMyConstrain(2,1,1,1,0.0,1.0,GridBagConstraints.HORIZONTAL));
			
			add(new JLabel("Procedimiento"), GenerarMyConstrain(0,2,1,1,0.0,1.0,GridBagConstraints.NONE));
			
			
			list_procedures = new JComboBox<String>(GetLista("procedimientos.txt"));
			
			list_procedures.setEditable(true);
			add(list_procedures, GenerarMyConstrain(1,2,1,1,1.0,1.0,GridBagConstraints.HORIZONTAL));
			add_a_procedure.setPreferredSize(tamagnio_boton);
			add_a_procedure.addActionListener(new OyenteBotonGrabarCombo(add_a_procedure));
			add(add_a_procedure, GenerarMyConstrain(2,2,1,1,0.0,1.0,GridBagConstraints.HORIZONTAL));
			
		}
		
		GridBagConstraints GenerarMyConstrain(int x, int y, int ocupax, int ocupay, double estirax, double estiray, int llenar) {
			
			GridBagConstraints mi_constraint = new GridBagConstraints();
			
			Insets myinset = new Insets(5,5,5,5); //creo un objeto de tipo Insets que define el espacio del objeto con sos 4 bordes
			
			mi_constraint.gridx = x; //columna donde empieza
			
			mi_constraint.gridy = y; //fila donde empieza
			
			mi_constraint.gridwidth = ocupax; //columnas que ocupa
			
			mi_constraint.gridheight = ocupay; //filas que ocupa
			
			mi_constraint.weightx = estirax; //si se estira la columna
			
			mi_constraint.weighty = estiray; //si se estira la fila
			
			mi_constraint.fill = llenar;
			
			mi_constraint.insets = myinset;
			
			return mi_constraint;
			
			
		}
		
	}
	
	class PanelDescripcion extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PanelDescripcion() {
			
			setLayout(new BorderLayout());
			
			setBorder(BorderFactory.createTitledBorder("Descripcion"));
			
			JScrollPane scroll_proced = new JScrollPane();
			
			area_procedimiento.setComponentPopupMenu(elpopup);
			
			scroll_proced.setViewportView(area_procedimiento);
			
			add(scroll_proced, BorderLayout.CENTER);	

		}
		
	}
	
	class PanelInfBotones extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PanelInfBotones() {
			
			save_procedure.addActionListener(new OyenteSaveProcedure());
			add(save_procedure);
			
			cancel_window.addActionListener(new OyenteCancelarDialog());
			add(cancel_window);
			
			
		}
	}
	
	public DefaultComboBoxModel<String> GetLista(String archivo_donde_buscar ){
		
		DefaultComboBoxModel<String> model_list = new DefaultComboBoxModel<String>();
		
		try {
		 	
		 	//InputStream inRead = AddProcedure.class.getResourceAsStream("/" + archivo_donde_buscar);
		 
		 	//BufferedReader bufferLeeListaDiagno = new BufferedReader(new InputStreamReader(inRead));
			
			//Descomentar las dos lineas de arriba y comentar la de abajo para crear el JAR
			
			BufferedReader bufferLeeListaDiagno = new BufferedReader(new FileReader(archivo_donde_buscar));
			
			String lineatxt="";
			
			while (lineatxt != null) {
				
				lineatxt = bufferLeeListaDiagno.readLine();
				
				if (lineatxt == null || lineatxt == "\n") {
					
					
				} else {
					
					model_list.addElement(lineatxt);
					
				}
				
			}
			
			bufferLeeListaDiagno.close();
			
		} catch (FileNotFoundException e) {
			
			return model_list;
			
		} catch (IOException e) {
			
			return model_list;
		}
		
		return model_list;
		
		
	}
	
	class OyenteCancelarDialog implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			dispose();
			
			
		}
		
	}
	
	void AddingToList(JComboBox<String> elcombobox, String path_to_file) {
		
		String ComboTexto = elcombobox.getSelectedItem().toString();
		
		File txt_lista = new File(path_to_file);
			
			try {
				
				BufferedWriter bufferGrabaTexto = new BufferedWriter(new FileWriter(txt_lista, true));
				
				bufferGrabaTexto.write("\n");
				
				bufferGrabaTexto.write(ComboTexto);
				
				bufferGrabaTexto.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}
	
	class OyenteBotonGrabarCombo implements ActionListener{
		
		private JButton elboton;
		
		public OyenteBotonGrabarCombo(JButton elboton) {
			
			this.elboton = elboton;
			
		}

		public void actionPerformed(ActionEvent e) {
			
			if (elboton.equals(add_diagnosis)) {
				
				String path1 = "." + File.separator + "diagnosticos.txt";
				
				AddingToList(list_diagnosis, path1);
				
				list_diagnosis.setModel(GetLista(path1));
				
			} else if (elboton.equals(add_a_procedure)) {
				
				String path2 = "." + File.separator + "procedimientos.txt";
				
				AddingToList(list_procedures, path2);
				
				list_procedures.setModel(GetLista(path2));
				
			} else {
				
				System.out.println("ERROR");
				
			}
			
			
			
		}
		
	}
	
	class OyenteSaveProcedure implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			SimpleDateFormat miformato = new SimpleDateFormat("d-M-YYY-k-m");
			
			String proc_file_name = miformato.format(new Date()) + "-" + name_doctor.getText();
			
			String path_new_proc = baseDatosPanel.path_a_paciente_elegido + File.separator + "procedimientos" + File.separator + proc_file_name;
			
			File file_procedure = new File(path_new_proc);
			
			try {
				
				file_procedure.createNewFile();
				
				String procedure_cuerpo_texto = miformato.format(new Date()) + "\n \n" + "Diagnostico: " + list_diagnosis.getSelectedItem().toString() + 
						"\n" + "Procedimiento: " + list_procedures.getSelectedItem().toString() + "\n" +
						"Medico: " + name_doctor.getText() + "\n \n" + "Descripcion:" + "\n \n" + area_procedimiento.getText();
				
				ManejandoFicheros.GrabarTxtEnArchivo(file_procedure, procedure_cuerpo_texto);
				
				procedimientos.cargandoProcedimientos(baseDatosPanel.path_a_paciente_elegido + File.separator + "procedimientos");
				
				dispose();
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
				
			}
			
		}
		
		
	}

}
