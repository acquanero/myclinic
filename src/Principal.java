import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal implements Runnable{
	
	public static gui ventana;

	public void run() {
		
		if (CheckFolder() =="vacio") {
			
			JOptionPane.showConfirmDialog(null, "Elige una carpeta donde guardar los pacientes", "Directorio faltante", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null);
			
			JFileChooser ventanaEligeFolder = new JFileChooser();
			
			ventanaEligeFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int eleccion = ventanaEligeFolder.showOpenDialog(null);
			
			if (eleccion == JFileChooser.APPROVE_OPTION) {
				
				EscribiendoRuta(ventanaEligeFolder.getSelectedFile().getAbsolutePath());
				
			}
			
			ventana = new gui();
			
			ventana.setVisible(true);
			
			
		} else {
			
			File almacenaje = new File(CheckFolder());
			
			if (almacenaje.exists() == true) {
				
				ventana = new gui();
				
				ventana.setVisible(true);
				
			} else {
				
				JOptionPane.showConfirmDialog(null, CheckFolder() + " \n no existe. Elige otro directorio", "Directorio erroeno", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null);
				
				JFileChooser ventanaEligeFolder = new JFileChooser();
				
				ventanaEligeFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int eleccion = ventanaEligeFolder.showOpenDialog(null);
				
				if (eleccion == JFileChooser.APPROVE_OPTION) {
					
					EscribiendoRuta(ventanaEligeFolder.getSelectedFile().getAbsolutePath());
					
				}
				
				ventana = new gui();
				
				ventana.setVisible(true);
				
			}
	
			
		}
		
	}
	
	public static String CheckFolder() {
		
		String line;
		
		File almacen = new File("." + File.separator + "carpeta_almacen.dat");
		
		if (almacen.exists() == true) {
			
			 try {	
				 	
				 	FileReader carpeta_almacen = new FileReader(almacen);
				 	
				 	BufferedReader bufferreader = new BufferedReader(carpeta_almacen);
				 	
				 	//Descomentar las dos lineas de abajo y comentar las 2 de arriba para crear el JAR
				 	
				 	//InputStream in = Principal.class.getResourceAsStream("/carpeta_almacen.dat");
				 
				 	//BufferedReader bufferreader = new BufferedReader(new InputStreamReader(in));
				 	
			        
			        line = bufferreader.readLine();
			        
			        bufferreader.close();
			        
			        return line;

			    } catch (FileNotFoundException ex) {
			    	
			        ex.printStackTrace();
			        
			        return "vacio";
			        
			    } catch (IOException ex) {
			    	
			        ex.printStackTrace();
			        
			        return "vacio";
			        
			    }
			
		} else {
			
			return "vacio";
			
		}
		 
	}

	public static void EscribiendoRuta(String path) {
		
		String ruta = path;
		
		try {
			
			FileWriter escritor = new FileWriter("." + File.separator + "carpeta_almacen.dat", false);
			
			for (int i=0; i<ruta.length(); i++) {
				
				escritor.write(ruta.charAt(i));

			}
			
			escritor.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
}
