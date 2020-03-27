import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddLaboratorio extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int year = Calendar.getInstance().get(Calendar.YEAR);
	
	JComboBox<Integer> lista_dia = new JComboBox<Integer>(GeneradorDeRangos(1,32));
	JComboBox<String> lista_mes = new JComboBox<String>(GeneradorDeRangoMeses());
	JComboBox<Integer> lista_anio = new JComboBox<Integer>(GeneradorDeRangos(year-20,year+1));
	
	JTextField arrayDeTextFields[] = new JTextField[30];
	
	String mylabels[] = {"HTO","Hb","Glob. Bcos.","Plaquetas","Glucemia","Urea","Creatinina","Bili total","Bili directa","TGO","TGP","FAL",
			"5 gamma GT","Amilasa","KPTT","Qucik/TP","RIN","Colesterol","LDL","HDL","Trigliceridos","Proteinas","Albumina","Na+","K+","Cl-",
			"Otros 1","Otros 2","Otros 3","Otros 4"
	};
	
	gui.MiPopUp elpopup = new gui.MiPopUp();
	
	public AddLaboratorio() {
		
		setTitle("Agregar Laboratorio");
		
		setSize(450,600);
		
		setLocationRelativeTo(Principal.ventana);
		
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		add(new BoxSuperior(), BorderLayout.NORTH);
		add(new BoxMedio(), BorderLayout.CENTER);
		add(new BoxInferior(), BorderLayout.SOUTH);
		
	}
	
	class BoxSuperior extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public BoxSuperior() {
			
			setBorder(BorderFactory.createTitledBorder("Fecha del laboratorio:"));
			
			setLayout(new GridBagLayout());
			
			add(lista_dia, GenerarMyConstrain(0,0,1,1,0,0,GridBagConstraints.NONE));
			add(lista_mes, GenerarMyConstrain(1,0,1,1,0,0,GridBagConstraints.NONE));
			add(lista_anio, GenerarMyConstrain(2,0,1,1,0,0,GridBagConstraints.NONE));
			
			
		}
	}
		
	class BoxMedio extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			
		public BoxMedio() {
				
			setBorder(BorderFactory.createTitledBorder("Valores:"));
				
			setLayout(new GridBagLayout());
				
			for (int i=0; i<mylabels.length; i++) {
				
				JTextField elJTfield = new JTextField(33);
				
				elJTfield.setComponentPopupMenu(elpopup);
				
				arrayDeTextFields[i] = elJTfield;

				if (i<15) {
					
					add(new JLabel(mylabels[i]), GenerarMyConstrain(0,i,1,1,1.0,1.0,GridBagConstraints.NONE));
					
					add(elJTfield, GenerarMyConstrain(1,i,2,1,1.0,1.0,GridBagConstraints.HORIZONTAL));
					
				} else {
					
					int l=i-15;
					
					add(new JLabel(mylabels[i]), GenerarMyConstrain(3,l,1,1,1.0,1.0,GridBagConstraints.NONE));
					
					add(elJTfield, GenerarMyConstrain(4,l,2,1,1.0,1.0,GridBagConstraints.HORIZONTAL));
					
				}
					
			}
				
				
		}
			
			
			
	}
	
	class BoxInferior extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public BoxInferior() {
			
			setBorder(BorderFactory.createTitledBorder(""));
			
			setLayout(new GridBagLayout());
			
			JButton save = new JButton("Guardar");
			save.addActionListener(new OyenteButtonGuardar());
			JButton cancel = new JButton("Cancelar");
			cancel.addActionListener(new OyenteButtonCancelar());
			
			add(save, GenerarMyConstrain(0,0,1,1,0,0,GridBagConstraints.NONE));
			add(cancel, GenerarMyConstrain(1,0,1,1,0,0,GridBagConstraints.NONE));			
			
		}
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
	
	DefaultComboBoxModel<Integer> GeneradorDeRangos(int inicio, int fin){
		
		DefaultComboBoxModel<Integer> aLista = new DefaultComboBoxModel<Integer>();
		
		for (int i=inicio; i<fin; i++) {
			
			aLista.addElement(i);
			
		}
		
		return aLista;
		
	}
	
	DefaultComboBoxModel<String> GeneradorDeRangoMeses(){
		
		DefaultComboBoxModel<String> aLista = new DefaultComboBoxModel<String>();
		
		String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		
		for (int i=0; i<meses.length; i++) {
			
			aLista.addElement(meses[i]);
			
		}
		
		return aLista;
		
	}
	
	class OyenteButtonCancelar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			dispose();
			
			
		}	
		
	}
	
	class OyenteButtonGuardar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			String nome_labo = lista_dia.getSelectedItem().toString() + "-"+
					lista_mes.getSelectedItem().toString() + "-" + lista_anio.getSelectedItem().toString();
			
			String path_new_labo = baseDatosPanel.path_a_paciente_elegido + File.separator + "laboratorios" + File.separator + nome_labo;
			
			File file_labo = new File(path_new_labo);
			
			try {
				
				file_labo.createNewFile();
				
				String detalle_labo = nome_labo;
				
				ManejandoFicheros.GrabarTxtEnArchivo(file_labo, detalle_labo);
				
				ManejandoFicheros.GrabarTxtEnArchivo(file_labo, "\n \n");
				
				for (int i=0; i<30; i++) {
					
					String lineaDeValor = mylabels[i] + ": " + arrayDeTextFields[i].getText();
					
					ManejandoFicheros.GrabarTxtEnArchivo(file_labo, lineaDeValor);
					
					ManejandoFicheros.GrabarTxtEnArchivo(file_labo, "\n");
					
				}
				
				laboratorios.cargandoLaboratorios(baseDatosPanel.path_a_paciente_elegido + File.separator + "laboratorios");
				
				dispose();
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
				
			}
			
			
		}	
		
	}

}
