import java.util.*;
import java.io.*;


/**
* LeggiOrari è la classe che si occupa di eseguire la lettura da file degli orari, e conseguentemente il salvataggio
* degli stessi in memoria, attraverso una struttura dati che nel metodo "main" verrà poi passata come parametro alla
* classe che gestirà l'interfaccia grafica
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class LeggiOrari{

	private ArrayList<Orario> listaOrari;

	/**
	* Non esistono altri tipi di costruttori, in quanto il programma può essere eseguito solamente indicando tramite
	* linea di comando il nome di un file esistente da leggere che poi verrà passato a questo costruttore dal metodo "main"
	*
	* @param file nome del file che deve essere letto
	*/
	public LeggiOrari(String file) {
		leggiFile(file);
	}

	/**
	* Metodo principale di questa classe.&nbsp;Si occupa di andare ad effettuare la lettura vera e propria da file servendosi
	* delle API FileReader e BufferedReader.&nbsp;Richiama il metodo {@link #creaOrario creaOrario} passandogli
	* l'array delle righe lette da file
	*
	* @param file nome del file che deve essere letto
	*/
	public void leggiFile(String file) {

		listaOrari = new ArrayList<Orario>();
		File fileDaLeggere = new File(file);
		ArrayList<String> stringList = new ArrayList<String>();
		String tempString;

		try {
			FileReader reader = new FileReader(fileDaLeggere);
			BufferedReader buffReader = new BufferedReader(reader);

			while ((tempString = buffReader.readLine()) != null) {
		        stringList.add(tempString);
		    }

			buffReader.close();

		}catch(IOException exc) {
			System.out.println(exc);
		}

		creaOrario(stringList);

	}

	/**
	* Si occupa di tradurre l'array delle righe lette da file in un insieme di singoli orari, inserendoli in un array di oggetti
	* di tipo {@link Orario}
	*
	* @param stringList un array contentente le linee lette da file
	*/
	public void creaOrario(ArrayList<String> stringList) {

		Orario orarioTemp;

		int iterazioni = Integer.parseInt(stringList.get(0));
		int identificativo = 0;

		stringList.remove(0);
		for (int i=0; i<iterazioni ; i++) {

			orarioTemp = new Orario();

			orarioTemp.setNome(stringList.remove(0));	//nome treno

			String[] splittato = stringList.remove(0).split(":");	//orario partenza
			orarioTemp.setOrarioPartenza(Integer.parseInt(splittato[0]),Integer.parseInt(splittato[1]));

			orarioTemp.setStazionePartenza(stringList.remove(0));	//stazione partenza

			splittato = stringList.remove(0).split(":");	//orario arrivo
			orarioTemp.setOrarioArrivo(Integer.parseInt(splittato[0]),Integer.parseInt(splittato[1]));

			orarioTemp.setStazioneArrivo(stringList.remove(0));	//stazione arrivo

			orarioTemp.setPrezzo(Double.parseDouble(stringList.remove(0)));	//prezzo

			orarioTemp.setIdentificativo(identificativo);

			listaOrari.add(i, orarioTemp);
			identificativo++;
		}

	}

	/**
	* Metodo che ritorna l'array di tipo {@link Orario}, in quanto parametro dichiarato privatamente
	*
	* @return array degli orari
	*/
	public ArrayList<Orario> getArrayList() {
		return listaOrari;
	}

}

