/**
* Orario è la classe che si occupa di fornire la struttura necessaria per un
* intero orario, costituita da orario di partenza e di arrivo, stazione di
* partenza e di arrivo, prezzo del biglietto, nome del treno e identificativo.
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class Orario{

	private Ora orarioPartenza;
	private Ora orarioArrivo;
	private String stazionePartenza;
	private String stazioneArrivo;
	private double prezzo;
	private String nome;
	private int identificativo;

	/**
	* Costruttore che inizializza un oggetto di tipo Orario senza i dettagli
	* specificati
	*/
	public Orario() {
		this(new Ora(), new Ora(), null, null, 0, 0);
	}

	/**
	* Costruttore che inizializza un oggetto di tipo Orario contenente i dettagli
	* di un viaggio: orario di partenza e d'arrivo, stazione di partenza e d'arrivo,
	* il prezzo del biglietto e l'identificativo.
	*
	* @param orarioPartenza		l'orario di partenza
	* @param orarioArrivo		l'orario d'arrivo
	* @param stazionePartenza	il nome della stazione di partenza
	* @param stazioneArrivo		il nome della stazione d'arrivo
	* @param prezzo				il prezzo del biglietto per il viaggio
	* @param identificativo		l'id univoco
	*/
	public Orario(Ora orarioPartenza, Ora orarioArrivo, String stazionePartenza, String stazioneArrivo, double prezzo, int identificativo) {
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.stazionePartenza = stazionePartenza;
		this.stazioneArrivo = stazioneArrivo;
		this.prezzo = prezzo;
		this.identificativo = identificativo;
	}

	/**
	* Metodo necessario a modificare l'orario di partenza
	*
	* @param o	l'ora della partenza
	* @param m  il minuto esatto relativo all'ora della partenza
	*/
 	public void setOrarioPartenza(int o, int m) {
 		orarioPartenza.setOra(o);
 		orarioPartenza.setMin(m);
 	}

	/**
	* Metodo necessario a modificare l'orario d'arrivo
	*
	* @param o	l'ora dell'arrivo
	* @param m  il minuto esatto relativo all'ora dell'arrivo
	*/
 	public void setOrarioArrivo(int o, int m) {
 		orarioArrivo.setOra(o);
 		orarioArrivo.setMin(m);
 	}

	/**
	* Metodo necessario a modificare il nome della stazione di partenza
	*
	* @param s	il nome della stazione di partenza
	*/
 	public void setStazionePartenza(String s) {
 		stazionePartenza = s;
 	}

	/**
	* Metodo necessario a modificare il nome della stazione d'arrivo
	*
	* @param s  il nome della stazione d'arrivo
	*/
 	public void setStazioneArrivo(String s) {
 		stazioneArrivo = s;
 	}

	/**
	* Metodo necessario a modificare il prezzo del biglietto per il viaggio
	*
	* @param d	il prezzo del biglietto per il viaggio
	*/
 	public void setPrezzo(Double d) {
 		prezzo = d;
 	}

	/**
	* Metodo necessario a modificare l'identificativo
	*
	* @param i 	parametro scelto arbitrariamente per dare un id univoco all'orario. È compito del programmatore assicurarsi
	* 			l'univocità dello stesso
	*/
 	public void setIdentificativo(int i) {
 		identificativo = i;
 	}

	/**
	* Metodo necessario a modificare il nome del treno che opererà il viaggio
	*
	* @param nome  il nome del treno che opererà il viaggio
	*/
 	public void setNome(String nome) {
 		this.nome = nome;
 	}

	/**
	* Metodo che fornisce l'orario di partenza
	*
	* @return	l'orario di partenza
	*/
 	public Ora getOrarioPartenza() { return orarioPartenza; }

	/**
	* Metodo che fornisce l'orario d'arrivo
	*
	* @return	l'orario d'arrivo
	*/
 	public Ora getOrarioArrivo() { return orarioArrivo; }

	/**
	* Metodo che fornisce il nome della stazione di partenza
	*
	* @return	il nome della stazione di partenza
	*/
 	public String getStazionePartenza() { return stazionePartenza; }

	/**
	* Metodo che fornisce il nome della stazione d'arrivo
	*
	* @return	il nome della stazione d'arrivo
	*/
 	public String getStazioneArrivo() { return stazioneArrivo; }

	/**
	* Metodo che fornisce il prezzo del biglietto per il viaggio
	*
	* @return	il prezzo del biglietto per il viaggio
	*/
 	public Double getPrezzo() { return prezzo; }

	/**
	* Metodo che fornisce l'id dell'orario
	*
	* @return   l'id dell'orario
	*/
 	public int getIdentificativo() { return identificativo; }

	/**
	* Metodo che fornisce il nome del treno che opererà il viaggio
	*
	* @return	il nome del treno che opererà il viaggio
	*/
 	public String getNome() { return nome; }

	/**
	* Si occupa di tradurre in stringa i maggiori dettagli del viaggio
	*
	* @return	una stringa contenente i maggiori dettagli del viaggio:
	*			orario di partenza e d'arrivo, stazione di partenza e d'arrivo, prezzo del biglietto
	*/
 	public String toString(){
 		String s = "[\n";

 		s += "orarioPartenza: "+orarioPartenza+"\n";
 		s += "stazionePartenza: "+stazionePartenza+"\n";
 		s += "\norarioArrivo: "+orarioArrivo+"\n";
 		s += "stazioneArrivo: "+stazioneArrivo+"\n";
 		s += "\nprezzo: "+prezzo;

 		s += "\n]";

 		return s;
 	}

}
