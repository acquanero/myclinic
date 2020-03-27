import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManejandoFicheros {
	
	public static void GrabarEnArchivo(File datospersonales, File antecedentes) {
		
		try {
			
			//Escribiendo datos personales en el archivo
			
			FileWriter miescribidor = new FileWriter(datospersonales);
			
			for (int i=0; i < datos_generales.ListaDeTextFields.size() ; i++) {
				
				miescribidor.write(datos_generales.ListaDeTextFields.get(i).getText() + "\n");
				
			}
			
			miescribidor.close();
			
			//Escribiendo los antecedentes en el archivo 
			
			BufferedWriter bufferAntecedentes = new BufferedWriter(new FileWriter(antecedentes));
			
			bufferAntecedentes.write(datos_generales.areaAntecedentes.getText());
			
			bufferAntecedentes.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void BorrarDirectorio(File el_directorio){
		
		if(el_directorio.exists() == true) {
			
			if (el_directorio.isDirectory() == true) {
				
				if (el_directorio.list().length == 0) { //si el directorio esta vacio , lo borra
					
					el_directorio.delete();
					
				} else {
					
					String archivos[] = el_directorio.list(); // listo el contenido del directorio
					
					for (String archivo : archivos) {
						
						File archivoaborrar = new File(el_directorio, archivo); //construyo los objetos File
						
						BorrarDirectorio(archivoaborrar); //borrado recursivo llamandose el metodo a si mismo, para volver a entrar en todas las subcarpetas
						
					}
					
					if(el_directorio.list().length == 0) {
						
						el_directorio.delete();
						
					}
					
				}
				
				
			} else { //Si no es undirectorio sino un archivo, lo borra
				
				el_directorio.delete();
				
			}
			
		} else {
			
			
		}
		
	}
	
	public static void GrabarTxtEnArchivo(File file, String el_texto) throws IOException {
		
		try {
		
			BufferedWriter bufferGraba = new BufferedWriter(new FileWriter(file, true));
		
			bufferGraba.write(el_texto);
			
			bufferGraba.close();
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
		
	}

}