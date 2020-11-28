import java.io.*;

/**
* EasyTrain è la classe principale, contenente il metodo <code>main()</code>, il
* quale di fatto istanzia tutti gli oggetti per l'avvio del programma
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class EasyTrain{

	public static void main(String[] args) {

		if(args.length>0) {
			File fileOrari = new File(args[0]);

			if(fileOrari.exists()) {
				LeggiOrari orari = new LeggiOrari(args[0]);

				OrariGUI gui = new OrariGUI("EasyTrain");
				gui.caricaDati(orari.getArrayList(),0);
			} else {
				System.out.println("ERRORE: file inesistente");
				System.exit(0);
			}

		} else {
			System.out.println("ERRORE: inserire il nome di un file in input dopo il comando di esecuzione");
			System.exit(0);
		}
	}
}
