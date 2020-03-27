
public class MainClass {

	public static void main(String[] args) {
		
		Runnable MiHilo = new Principal();
		
		Thread instanciaMiHilo = new Thread(MiHilo);
		
		instanciaMiHilo.start();

	}

}
