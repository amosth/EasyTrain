import java.util.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

/**
* Ascoltatore è la classe che si occupa di notificare e di reagire agli
* eventi generati dall'utente nella GUI
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class Ascoltatore implements ActionListener, ListSelectionListener, DocumentListener {

	private OrariGUI gui;

	/**
	* Costruttore assegna all'attributo dichiarato di tipo {@link OrariGUI} il parametro ottenuto
	*
	* @param gui	un'istanza della classe {@link OrariGUI} che gestisce l'interfaccia grafica
	*/
	public Ascoltatore(OrariGUI gui) {
		this.gui = gui;
	}

	/**
	* Metodo adibito a catturare l'evento ({@link ActionEvent}) e a gestirne
	* tutti i vari casi
	*
	* @param e	l'evento generato da un'azione dell'utente
	*/
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source instanceof JComboBox) {
			JComboBox box = (JComboBox)source;
			gui.textPaneDettagli.setText("");
			gui.ordinaModello(box.getSelectedIndex());
		}else if(source instanceof JButton) {

			JButton button = (JButton)source;
			Date data = new Date();
			SimpleDateFormat outputDate = new SimpleDateFormat("H:mm:ss", new Locale("it"));
			JList list = (JList)gui.listOrari;
			MioModello temp = (MioModello)list.getSelectedValue();
			Boolean giaAcquistato = false;

			try{
				for(Orario orario : gui.arrayOrariOriginale) {
					if(orario.getIdentificativo()==temp.id) {
						for(int j=0; j<gui.acquistati.size(); j++) {
							if(gui.acquistati.get(j)==temp.id) {
								giaAcquistato = true;
							}
						}
						if(!giaAcquistato) {
							int choice = JOptionPane.showOptionDialog(null,
      							"Sei sicuro di voler acquistare questo viaggio?",
      							"Conferma Acquisto",
      							JOptionPane.YES_NO_OPTION,
      							JOptionPane.QUESTION_MESSAGE,
      							null, null, null);

  							if (choice == JOptionPane.YES_OPTION) {
								gui.textAreaConsole.append(outputDate.format(data)+" - Hai acquistato il biglietto per il treno "+orario.getNome()+" da "+orario.getStazionePartenza()+" ("+orario.getOrarioPartenza()+") a "+orario.getStazioneArrivo()+" ("+orario.getOrarioArrivo()+") al prezzo di "+orario.getPrezzo()+" \u20ac.\n");

								gui.acquistati.add(temp.id);
								gui.modelTableAcquistati.addRow(new MioModello[] {
									new MioModello(orario.getNome(),temp.id),
									new MioModello(orario.getOrarioPartenza().toString(),temp.id),
									new MioModello(orario.getStazionePartenza(),temp.id),
									new MioModello(orario.getOrarioArrivo().toString(),temp.id),
									new MioModello(orario.getStazioneArrivo(),temp.id),
									new MioModello(orario.getOrarioPartenza().sottrai(orario.getOrarioArrivo()).toString(),temp.id),
									new MioModello(orario.getPrezzo().toString()+" \u20ac",temp.id)
								});

								gui.textAreaConsole.append("Fino ad ora hai speso: "+calcolaPrezzo()+" \u20ac \n\n");
  							}
						} else {
							String warning = "Hai gi\u00E0 acquistato questo biglietto!";
							System.out.println(warning);
							JOptionPane.showMessageDialog(null, warning, "Attenzione", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}catch(NullPointerException exc) {
				String error = "Non \u00E8 stato selezionato alcun viaggio!";
				System.out.println(error);
				JOptionPane.showMessageDialog(null, error, "Errore", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	* Metodo che richiama il metodo {@link #ricerca(DocumentEvent)}
	*
	* @param e l'evento generato dal campo di ricerca {@link JTextField}
	*/
	public void changedUpdate(DocumentEvent e) {
		ricerca(e);
	}

	/**
	* Metodo che richiama il metodo {@link #ricerca(DocumentEvent)}
	*
	* @param e l'evento generato dal campo di ricerca {@link JTextField}
	*/
	public void insertUpdate(DocumentEvent e) {
		ricerca(e);
	}

	/**
	* Metodo che richiama il metodo {@link #ricerca(DocumentEvent)}
	*
	* @param e l'evento generato dal campo di ricerca {@link JTextField}
	*/
	public void removeUpdate(DocumentEvent e) {
		ricerca(e);
	}

	/**
	* Metodo che cattura gli eventi generati dalla lista orari {@link JList} e in
	* base al tipo di evento gestisce le casistiche
	*
	* @param e	l'evento generato dalla lista orari {@link JList}
	*/
	public void valueChanged(ListSelectionEvent e) {

		Object source = e.getSource();
		int id = 0;

		if(source instanceof JList) {
			//if(e.getValueIsAdjusting()){	//entra SOLO se l'elemento della lista è stato cliccato, NON se perde il focus
				try{
					JList list = (JList)source;
					MioModello temp = (MioModello)list.getSelectedValue();
					id = temp.id;

					String stringaDettagli = "<html><body><pre style='font-size: 10px'>";

					for(Orario orario : gui.arrayOrariOriginale) {
						if(orario.getIdentificativo()==id) {
							stringaDettagli += "<b>Treno: </b>"+orario.getNome()+"<br/><br/>";
							stringaDettagli += "<b>Orario di partenza: </b>"+orario.getOrarioPartenza()+"<br/>";
							stringaDettagli += "<b>Stazione di partenza: </b>"+orario.getStazionePartenza()+"<br/><br/>";
							stringaDettagli += "<b>Orario di arrivo: </b>"+orario.getOrarioArrivo()+"<br/>";
							stringaDettagli += "<b>Stazione di arrivo: </b>"+orario.getStazioneArrivo()+"<br/><br/>";
							stringaDettagli += "<b>Durata: </b>"+(orario.getOrarioPartenza().sottrai(orario.getOrarioArrivo()))+"<br/>";
							stringaDettagli += "<b>Prezzo: </b>"+orario.getPrezzo()+" &euro;<br/>";
							stringaDettagli += "</pre></body></html>";

							gui.textPaneDettagli.setText(stringaDettagli);
						}
					}
				}catch(NullPointerException exc) {
					//do nothing
				}
			//}
		}

	}

	/**
	* Metodo che calcola il prezzo complessivo di tutti i biglietti acquistati
	*
	* @return l'ammontare del prezzo totale comprensivo di tutti i biglietti acquistati
	*/
	public double calcolaPrezzo() {

		double prezzo = 0d;

		for (int id : gui.acquistati) {
			for (Orario orTemp : gui.arrayOrariOriginale ) {
				if(orTemp.getIdentificativo()==id) {
					prezzo += orTemp.getPrezzo();
				}
			}
		}

		prezzo = Math.round(prezzo * 100.0) / 100.0;

		return prezzo;

	}

	/**
	* Metodo che si occupa di ripopolare la lista orari filtrata in base al cambiamento
	* del campo di ricerca
	*
	* @param e	l'evento generato dal campo di ricerca {@link JTextField}, passato dai metodi ascoltatori
	*/
	public void ricerca(DocumentEvent e) {
		try{
			String testo = e.getDocument().getText(0,e.getDocument().getLength());
			ArrayList<Orario> arrayOrariCercati = new ArrayList<>();

			for(Orario orario : gui.arrayOrariOriginale) {
				if(orario.getStazionePartenza().toLowerCase().contains(testo.toLowerCase()) || orario.getStazioneArrivo().toLowerCase().contains(testo.toLowerCase())){
					arrayOrariCercati.add(orario);
				}
			}

			gui.caricaDati(arrayOrariCercati,1);
		}catch(BadLocationException exc){
			exc.printStackTrace();
		}

	}

}
