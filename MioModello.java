import javax.swing.*;

/**
* MioModello è la classe che ha il solo scopo di modellare un oggetto che contenga una stringa da stanpare da stampare&nbsp;
* all'interno della lista degli orari (componente grafico JList)
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class MioModello {

	public String s;
	public int id;
	public ImageIcon image;

	/**
	* Metodo
	*
	*/
	public MioModello() {
		this.s = "";
		this.id = -1;
		this.image = null;
	}

	/**
	* Metodo
	*
	* @param s
	* @param id
	*/
	public MioModello(String s, int id) {
		this.s = s;
		this.id = id;
		this.image = null;
	}

	/**
	* Metodo
	*
	* @param s
	* @param id
	* @param path
	*/
	public MioModello(String s, int id, String path) {
		this.s = s;
		this.id = id;
		this.image = new ImageIcon(path);
	}

	/**
	* Metodo
	*
	* @return
	*/
	public String toString() {
		return s;
	}

	/**
	* Metodo
	*
	* @return
	*/
	public ImageIcon getImmagine() {
		return image;
	}

	/**
	* Metodo
	*
	* @return
	*/
	public int getId() {
		return id;
	}

}
