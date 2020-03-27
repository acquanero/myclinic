import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class datos_generales extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<JTextField> ListaDeTextFields = new ArrayList<JTextField>();
	
	public static JTextArea areaAntecedentes;
	
	gui.MiPopUp elpopup = new gui.MiPopUp();
	
	public datos_generales() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new ConstructorPaneles("Datos personales", "DNI", "Nombres", "Apellido"));
		add(new ConstructorPaneles("Datos de contacto", "Telefono", "Direccion", "e-mail"));
		add(new ConstructorPaneles("Datos de prepaga", "Prepaga", "Plan", "N Afiliado"));
		add(new ConstructorPanelAntecedentes());
		
		
	}
	
	class ConstructorPaneles extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		ConstructorPaneles(String titolo, String etiq1, String etiq2, String etiq3){
			
			setBorder(BorderFactory.createTitledBorder(titolo));
			
			setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
			
			setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JLabel label1 = new JLabel(etiq1);
			label1.setPreferredSize(new Dimension(90,20));
			JTextField textfield1 = new JTextField(20);
			textfield1.setComponentPopupMenu(elpopup);
			
			JLabel label2 = new JLabel(etiq2);
			label2.setPreferredSize(new Dimension(90,20));
			JTextField textfield2 = new JTextField(20);
			textfield2.setComponentPopupMenu(elpopup);
			
			JLabel label3 = new JLabel(etiq3);
			label3.setPreferredSize(new Dimension(90,20));
			JTextField textfield3 = new JTextField(20);
			textfield3.setComponentPopupMenu(elpopup);
			
			add(label1);
			add(textfield1);
			
			add(label2);
			add(textfield2);
			
			add(label3);
			add(textfield3);
			
			ListaDeTextFields.add(textfield1);
			ListaDeTextFields.add(textfield2);
			ListaDeTextFields.add(textfield3);
			


		}
	}
	
	class ConstructorPanelAntecedentes extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		ConstructorPanelAntecedentes(){
			
			setBorder(BorderFactory.createTitledBorder("Antecedentes"));
			
			setLayout(new BorderLayout());
			
			areaAntecedentes = new JTextArea();
			
			areaAntecedentes.setLineWrap(true);
			
			areaAntecedentes.setComponentPopupMenu(elpopup);
			
			JScrollPane scrollantecedentes = new JScrollPane();
			
			scrollantecedentes.setViewportView(areaAntecedentes);
			
			add(scrollantecedentes, BorderLayout.CENTER);
			
			
		}
		
	}

}
