import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class imagenes extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static JList<String> listado_d_imagenes;
	
	static DefaultListModel<String> modeloListaImagenes = new DefaultListModel<String>();

	public imagenes() {
		
		setLayout(new BorderLayout());
		
		add(new BoxSuperior(), BorderLayout.NORTH);
		add(new BoxCentral(), BorderLayout.CENTER);
		
		
	}
	
	class BoxSuperior extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public BoxSuperior() {
			
			setBorder(BorderFactory.createTitledBorder("Agregar / Borrar"));
			
			Dimension mytamanio = new Dimension(120,28);
			
			JButton openimage = new JButton("Abrir");
			openimage.setPreferredSize(mytamanio);
			openimage.addActionListener(new OyenteBotoAbrir());
			
			JButton addimage = new JButton("Agregar");
			addimage.setPreferredSize(mytamanio);
			addimage.addActionListener(new OyenteAgregarImg());
			
			JButton removeimage = new JButton("Borrar");
			removeimage.setPreferredSize(mytamanio);
			removeimage.addActionListener(new OyenteBorraImagen());
			
			
			add(openimage);
			add(addimage);
			add(removeimage);
			
		}
		
	}
	
	class BoxCentral extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BoxCentral() {
			
			setBorder(BorderFactory.createTitledBorder("Selecciona una imagen"));
			
			setLayout(new BorderLayout());
			
			listado_d_imagenes = new JList<String>(modeloListaImagenes);
			
			listado_d_imagenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			listado_d_imagenes.setPreferredSize(new Dimension(210, Integer.MAX_VALUE));
			
			JScrollPane scroll_list_proc = new JScrollPane();
			
			scroll_list_proc.setViewportView(listado_d_imagenes);

			add(listado_d_imagenes, BorderLayout.CENTER);
			
			
		}
		
	}
	
	public static void cargandoImagenes(String path) {
		
		modeloListaImagenes.clear();
		
		File path_a_paciente_elegido = new File(path);
		
		String[] arrayImagenes = path_a_paciente_elegido.list();
		
		for (String imagen : arrayImagenes) {
			
			modeloListaImagenes.addElement(imagen);
			
		}
		
		
	}
	
	class VentanaImagen extends JDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		int ancho;
		
		int alto;
		
		public VentanaImagen(String el_path) {
			
			setLayout(new BorderLayout());
			
			setTitle("MyClinic - Imagen");

			try {
				
				BufferedImage myImage = ImageIO.read(new File(el_path));
				
				ancho = myImage.getWidth();
				
				alto = myImage.getHeight();
				
				if (ancho > alto) {
					
					Image ImagenEscalada = myImage.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
					
					setSize(900,600);
					
					JLabel picLabel = new JLabel(new ImageIcon(ImagenEscalada));
					
					add(picLabel, BorderLayout.CENTER);
					
					setLocationRelativeTo(Principal.ventana);
					
				} else if (ancho < alto) {
					
					Image ImagenEscalada = myImage.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
					
					setSize(500,700);
					
					JLabel picLabel = new JLabel(new ImageIcon(ImagenEscalada));
					
					add(picLabel, BorderLayout.CENTER);
					
					setLocationRelativeTo(Principal.ventana);
					
				} else {
					
					Image ImagenEscalada = myImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
					
					setSize(600,600);
					
					JLabel picLabel = new JLabel(new ImageIcon(ImagenEscalada));
					
					add(picLabel, BorderLayout.CENTER);
					
					setLocationRelativeTo(Principal.ventana);
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	class OyenteBotoAbrir implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (listado_d_imagenes.getSelectedValue() == null) {
				
				JOptionPane.showMessageDialog(imagenes.this, "Debes elegir una imagen del lista", "Selecciona imagen", JOptionPane.ERROR_MESSAGE);
				
			} else {
				
				String ruta = baseDatosPanel.path_a_paciente_elegido + File.separator + "imagenes" + File.separator + listado_d_imagenes.getSelectedValue().toString();
				
				VentanaImagen new_window = new VentanaImagen(ruta);
				
				new_window.setVisible(true);
				
			}
			
			
		}
		
		
	}
	
	class OyenteAgregarImg implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			JFileChooser ventanaEligeImagen = new JFileChooser();
			
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imagenes", "JPG", "jpeg", "jpg");
			
			ventanaEligeImagen.setFileFilter(imageFilter);
			
			int eleccion = ventanaEligeImagen.showOpenDialog(imagenes.this);
			
			if (eleccion == JFileChooser.APPROVE_OPTION) {
				
				File origen = new File(ventanaEligeImagen.getSelectedFile().getAbsolutePath());
				
				String name_picture = JOptionPane.showInputDialog(imagenes.this, "Con que nombre quieres guardar la foto?");
				
				String path_destino = baseDatosPanel.path_a_paciente_elegido + File.separator + "imagenes" + File.separator + name_picture + ".jpg";
				
				File destino = new File(path_destino);

				try {
					
					Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				}
				
				imagenes.cargandoImagenes(baseDatosPanel.path_a_paciente_elegido + File.separator + "imagenes");
				
				
			}
			
		}
		
		
		
	}
	
	class OyenteBorraImagen implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (listado_d_imagenes.getSelectedValue() == null) {
				
				
			} else {
				
				int rta = JOptionPane.showConfirmDialog(imagenes.this, "Seguro quieres borrar la imagen?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
				
				if (rta == JOptionPane.OK_OPTION) {
					
					String ruta = baseDatosPanel.path_a_paciente_elegido + File.separator + "imagenes" + File.separator + 
							listado_d_imagenes.getSelectedValue().toString();
					
					File archivo_a_borrar = new File(ruta);
					
					try {
						
						Files.delete(archivo_a_borrar.toPath());
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
						
					}
					
					imagenes.cargandoImagenes(baseDatosPanel.path_a_paciente_elegido + File.separator + "imagenes");
					
				} else {
					
					
				}
				
			}			
			
		}
		
		
	}

}
