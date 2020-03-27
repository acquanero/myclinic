import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.text.DefaultEditorKit;

public class gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public gui() {
		
		setTitle("My Clinic");
		
		setBounds(200,200,700,500);
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		setMinimumSize(new Dimension(900,500));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new box_contenedora());
		
		ImageIcon myiconito = new ImageIcon("." + File.separator + "mylogo.gif");
		
		setIconImage(myiconito.getImage());
		
		
	}
	
	class box_contenedora extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public box_contenedora() {
			
			setLayout(new BorderLayout());
			
			BarraDeMenu mimenu = new BarraDeMenu();
			
			setJMenuBar(mimenu);
			
			add(new pestania_main(), BorderLayout.CENTER);
			
		}
		
	}

	class pestania_main extends JTabbedPane{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public pestania_main() {
			
			baseDatosPanel panelBdatos = new baseDatosPanel();
			
			datos_generales mipanel2 = new datos_generales();
			
			evoluciones mipanel3 = new evoluciones();
			
			procedimientos mipanel4 = new procedimientos();
			
			laboratorios mipanel5 = new laboratorios();
			
			imagenes mipanel6 = new imagenes();
			
			addTab("Base de datos", panelBdatos);
			addTab("Datos grales", mipanel2);
			addTab("Evoluciones", mipanel3);
			addTab("Procedimientos", mipanel4);
			addTab("Laboratorios", mipanel5);
			addTab("Imagenes", mipanel6);
	;
			
		}
		
	}

	class cerrar_ventana implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			gui.this.dispose();
			
		}
		
		
	}
	
	class GuardarPaciente implements ActionListener{
		
		ArrayList<String> array_datos_paciente = new ArrayList<String>();

		public void actionPerformed(ActionEvent arg0) {
			
			array_datos_paciente.clear();
			
			for (int j=0; j<9; j++) {
				
				array_datos_paciente.add(datos_generales.ListaDeTextFields.get(j).getText());
				
			}
			
			if (array_datos_paciente.contains("") || datos_generales.areaAntecedentes.getText().equals("")) {
				
				JOptionPane.showMessageDialog(gui.this, "Debes completar todos los campos para guardar\nsi careces de alguno introduce -", "Campos incompletos", 
						JOptionPane.ERROR_MESSAGE, null);
				
			} else {
				
				String apellido = datos_generales.ListaDeTextFields.get(2).getText();
				String nombre = datos_generales.ListaDeTextFields.get(1).getText();
				String dni = datos_generales.ListaDeTextFields.get(0).getText();
				
				File nueva_carpeta = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni);
				
				if (nueva_carpeta.exists() == true) { //maneja el caso de que ya exista una carpeta con el mismo nombre
					
					int rta = JOptionPane.showOptionDialog(gui.this, "El paciente ya existe, deseas sobreescribirlo?", "Paciente existente", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					
					if (rta == 0) {
						
						System.out.println("Sobreescribiendo");
						
						
					} else {
						
						JOptionPane.showMessageDialog(gui.this, "Paciente no guardado", "Sobreescritura cancelada", JOptionPane.INFORMATION_MESSAGE, null);
						
					}
					
				} else { //Si la carpeta no existia, la crea y crea dentro los archivos necesarios
					
					nueva_carpeta.mkdir();
					
					File antecedentes_dat = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "antecedentes.dat");
					File datospersonales_dat = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "datos_personales.dat");
					File evoluciones_dir = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "evoluciones");
					File procedimientos_dir = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "procedimientos");
					File laboratorios_dir = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "laboratorios");
					File imagenes_dir = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni + File.separator + "imagenes");
					
					
					try {
						
						antecedentes_dat.createNewFile();
						datospersonales_dat.createNewFile();
						evoluciones_dir.mkdir();
						procedimientos_dir.mkdir();
						laboratorios_dir.mkdir();
						imagenes_dir.mkdir();
						
						ManejandoFicheros.GrabarEnArchivo(datospersonales_dat, antecedentes_dat);
						
					} catch (IOException e) {
						
						e.printStackTrace();
						
					}
					
					baseDatosPanel.ActualizarLista();
					
				}
				
			}
			
		}
		
	}
	
	class EliminarPaciente implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			String apellido = datos_generales.ListaDeTextFields.get(2).getText();
			String nombre = datos_generales.ListaDeTextFields.get(1).getText();
			String dni = datos_generales.ListaDeTextFields.get(0).getText();
			
			File paciente_elegido = new File(Principal.CheckFolder() + File.separator + apellido + " " + nombre + " " + dni);
			
			ManejandoFicheros.BorrarDirectorio(paciente_elegido);
			
			baseDatosPanel.ActualizarLista();
			
			
			
		}
		
	}
	
	class NuevoPaciente implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			for (int j=0; j<9; j++) {
				
				datos_generales.ListaDeTextFields.get(j).setText("");
				datos_generales.areaAntecedentes.setText("");
				
				evoluciones.modeloListaEvoluciones.clear();
				evoluciones.area_evolucion.setText("");
				
				procedimientos.modeloListaProced.clear();
				procedimientos.area_descrip_proc.setText("");
				
				laboratorios.modeloListaLabo.clear();
				laboratorios.area_descrip_labo.setText("");
				
			}		
			
		}
		
		
	}
	
	class BarraDeMenu extends JMenuBar{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BarraDeMenu() {
			
			JMenu Archivo=new JMenu("Archivo");
			
			JMenuItem nuevoP=new JMenuItem("Nuevo");
			JMenuItem guardarP=new JMenuItem("Guardar");
			JMenuItem eliminarP=new JMenuItem("Eliminar");
			JMenuItem salir=new JMenuItem("Salir");
			
			nuevoP.addActionListener(new NuevoPaciente());
			guardarP.addActionListener(new GuardarPaciente());
			eliminarP.addActionListener(new EliminarPaciente());
			salir.addActionListener(new cerrar_ventana());
			
			Archivo.add(nuevoP);
			Archivo.add(guardarP);
			Archivo.add(eliminarP);
			Archivo.addSeparator();
			Archivo.add(salir);
			
			
			JMenu Edicion=new JMenu("Edicion");
			
			JMenuItem copiarB = new JMenuItem(new DefaultEditorKit.CopyAction());
			copiarB.setText("Copiar");
			JMenuItem cortarB = new JMenuItem(new DefaultEditorKit.CutAction());
			cortarB.setText("Cortar");
			JMenuItem pegarB = new JMenuItem(new DefaultEditorKit.PasteAction());
			pegarB.setText("Pegar");
			
			
			Edicion.add(copiarB);
			Edicion.add(cortarB);
			Edicion.add(pegarB);
			
			add(Archivo);
			add(Edicion);		
			
			
		}
		
	}
	
	static class MiPopUp extends JPopupMenu {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MiPopUp() {
			
			JMenuItem copiarB = new JMenuItem(new DefaultEditorKit.CopyAction());
			copiarB.setText("Copiar");
			JMenuItem cortarB = new JMenuItem(new DefaultEditorKit.CutAction());
			cortarB.setText("Cortar");
			JMenuItem pegarB = new JMenuItem(new DefaultEditorKit.PasteAction());
			pegarB.setText("Pegar");
			
			add(cortarB);
			add(copiarB);
			add(pegarB);
			
			
		}
		
	}

}
