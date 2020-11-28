/**
* Ora è la classe che si occupa di salvare un dato orario dividendolo in ore e minuti per poterli gestire separatamente
*
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class Ora implements Comparable<Ora> {
	private int ore;
	private int min;

	/**
	* Costruttore che assegna i valori ricevuti a tutti gli attributi dichiarati
	*
	* @param o parametro che definisce l'ora di un orario
	* @param m parametro che definisce i minuti di un orario
	*/
	public Ora(int o, int m) {
		ore = o;
		min = m;
	}

	/**
	* Costruttore che assegna valori nulli/azzerati a tutti gli attributi dichiarati
	*
	*/
	public Ora() {
		this(0, 0);
	}

	/**
	* Metodo che ritorna l'ora dell'orario
	*
	* @return ora dell'orario
	*/
	public int getOra() { return ore; }

	/**
	* Metodo che ritorna i minuti dell'orario
	*
	* @return minuti dell'orario
	*/
	public int getMin() { return min; }

	/**
	* Metodo che setta l'ora dell'orario
	*
	* @param o ora
	*/
	public void setOra(int o) { ore = o; }

	/**
	* Metodo che setta i minuti dell'orario
	*
	* @param m minuti
	*/
	public void setMin(int m) { min = m; }

	/**
	* Metodo che stampa l'orario nel formato (HH:mm)
	*
	* @return ritorna la string <code>ore</code>:<code>min</code>
	*/
	public String toString() { return (ore < 10 ? "0" + ore : ore) + ":" + (min < 10 ? "0" + min : min); }

	/**
	* Metodo ereditato dalla classe <code>Comparable</code> e sovrascritto.&nbsp;Si occupa di comparare due oggetti di tipo
	* {@link Ora} 
	*
	* @param o oggetto {@link Ora} da confrontare
	* @return 1 se l'oggetto l'ora che ha richiamato il metodo è più grande del parametro {@link Ora} passato, 0 se l'oggetto l'ora che ha richiamato il metodo è uguale al parametro {@link Ora} passato, -1 se l'oggetto l'ora che ha richiamato il metodo è più piccolo del parametro {@link Ora} passato
	*/
	public int compareTo(Ora o) {
		if(ore>o.ore) {
			return 1;
		} else if(ore==o.ore) {
			if(min>o.min) {
				return 1;
			} else if(min<o.min) {
				return -1;
			}
			return 0;
		}
		return -1;
	}

	/**
	* Metodo che ha la funzione di sottrarre due oggetti di tipo {@link Ora}, ottendendone un terzo che verrà infine ritornato
	*
	* @param o oggetto di tipo {@link Ora} da sottrarre all'istanza chiamante
	*/
	public Ora sottrai(Ora o) {
		int tempMin = 0,
			tempOra = 0;

		tempOra = o.getOra() - getOra();
		if(o.getMin()<getMin()) {
			tempMin = o.getMin() - getMin() + 60;
			tempOra -= 1;
		} else {
			tempMin = o.getMin() - getMin();
		}

		return new Ora(tempOra, tempMin);
	}
}
