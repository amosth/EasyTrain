import java.util.*;
/**
* Comparatore è la classe che, implementando l'API {@link Comparator} permette di confrontare due oggetti di tipo {@link Orario}
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/

public class Comparatore implements Comparator<Orario> {

    private String campo, tipo;

	/**
	* Unico costruttore.&nbsp;Obbligatorio in quanto per l'esigenza del programma vanno specificati i parametri <code>campo</code>
	* e <code>tipo</code> per capire in che modo va fatta la comparazione tra due oggetti {@link Orario}
	*
	* @param campo specifica se la comparazione andrà fatta in base all'orario o alla stazione
	* @param tipo specifica se la comparazione andrà fatta basandosi sulla partenza o sull'arrivo
	*/
    public Comparatore(String campo, String tipo) {
    	this.campo = campo;
    	this.tipo = tipo;
    }

	/**
	* Metodo che si occupa del confronto di due oggetti di tipo {@link Orario}
	*
	* @param o1 primo oggetto {@link Orario}
	* @param o2 secondo oggetto {@link Orario}
	*/
	@Override
	public int compare(Orario o1, Orario o2) {

		int res = 0;

		if(campo == "orario") {
			if(tipo == "partenza")
				res = o1.getOrarioPartenza().compareTo(o2.getOrarioPartenza());
			else if(tipo == "arrivo")
				res = o1.getOrarioArrivo().compareTo(o2.getOrarioArrivo());
		} else if(campo=="stazione") {
			if(tipo == "partenza")
				res = o1.getStazionePartenza().compareTo(o2.getStazionePartenza());
			else if(tipo == "arrivo")
				res = o1.getStazioneArrivo().compareTo(o2.getStazioneArrivo());
		}

		return res;

	};

}