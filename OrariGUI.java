import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.*;

/**
* OrariGUI è la classe che si occupa di modellare la finesra grafica per l'interazione con l'utente, attraverso l'uso delle 
* librerie <code>java.awt</code> e <code>javax.swing</code>, inoltre implementa anche qualche metodo per lo scambio di dati
* tra la gli array di memorizzazione e i componenti grafici adibiti alla loro visualizzazione
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
*/
public class OrariGUI extends JFrame {

	/* DICHIARAZIONE di tutti i componenti */
	public ArrayList<Orario> arrayOrariOriginale;
	public ArrayList<Orario> arrayOrari;

	public JPanel 	panelPrincipale,
					panelAcquistati;

	public JTabbedPane tabbedPane;

	public JLabel 	labelViaggi,
					labelDettagli;

	public JButton buttonAcquista;

	public JList<MioModello> listOrari;
	public DefaultListModel<MioModello> model;

	public JSeparator separatore;

	public JScrollPane 	scrollConsole,
						scrollDettagli,
						scrollList;

	public JTable tableAcquistati;
	public DefaultTableModel modelTableAcquistati;

	public JTextArea textAreaConsole;

	public JTextPane textPaneDettagli;

	public JComboBox<String> boxOrdina;

	public JTextField textFieldCerca;

	public GridBagConstraints 	gridCon,
								gridCon2;

	public GridBagLayout 	gridBL,
							gridBL2;

	public ArrayList<Integer> acquistati;

	/**
	* Costruttore unico al quale viene passato solo il titolo della finestra.&nbsp;In quanto non necessita di altri parametri.&nbsp;
	* Il costruttore della classe OrariGUI contiene buona parte dell'intero codice cella classe, in quanto ha il compito di
	* istanziare e aggiungere alla finestra tutti i componenti grafici necessari
	*
	* @param title il titolo della finestra
	*/
	public OrariGUI(String title) {
		
		/* FRAME - settaggi iniziali */
		super(title);
		setMinimumSize(new Dimension(705,500));

		tabbedPane = new JTabbedPane();

	    panelPrincipale = new JPanel();
	    panelAcquistati = new JPanel();
	    tabbedPane.addTab("Disponibilit\u00E0",panelPrincipale);
	    tabbedPane.addTab("Acquistati",panelAcquistati);

	    setContentPane(tabbedPane);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    /* ARRAY biglietti acquistati */
	    acquistati = new ArrayList<Integer>();

	    /* GridBagLayout  */

	    	//GridBagLayout per la tab "VIAGGI DISPONIBILI"
	    gridBL = new GridBagLayout();	
	    gridCon = new GridBagConstraints();	
	    reset();
		panelPrincipale.setLayout(gridBL);
	   
	    	//GridBagLayout per la tab "ACQUISTATI"
	    gridBL2 = new GridBagLayout();	
	    gridCon2 = new GridBagConstraints();
	    gridCon2.insets.top = 5;
	    gridCon2.insets.bottom = 5;
	    gridCon2.insets.left = 5;
	    gridCon2.insets.right = 5;
		panelAcquistati.setLayout(gridBL2);


		/******************** COMPONENTI ********************/


		/*---------
		|		  |
		|  TAB_1  |  (VIAGGI DISPONIBILI)
		|		  |
		---------*/
		/* -------------------------------------------------- RIGA 1 -------------------------------------------------- */

		/* JComboBox */
	 	boxOrdina = new JComboBox<>(new String[] {"","orario di partenza","orario di arrivo","stazione di partenza (A-Z)","stazione di arrivo (A-Z)"});
	 	/* border */
	   	TitledBorder titoloOrdina;
	   	titoloOrdina = BorderFactory.createTitledBorder("Ordina per");
	   	boxOrdina.setBorder(titoloOrdina);

	 	reset();
	 	gridCon.gridy = 0;
	 	gridCon.gridx = 1;
	 	gridCon.anchor = GridBagConstraints.EAST;
	 	gridCon.fill = GridBagConstraints.NONE;
	    gridBL.setConstraints(boxOrdina, gridCon);
	    panelPrincipale.add(boxOrdina);


	    /* JSeparator */
	    separatore = new JSeparator(JSeparator.VERTICAL);
	    reset();
	    gridCon.fill = GridBagConstraints.VERTICAL;
	    gridCon.gridy = 1;
	 	gridCon.gridx = 2;
		gridCon.gridheight = 3;
	    gridBL.setConstraints(separatore, gridCon);
	    panelPrincipale.add(separatore);


	    /* JTextField - cerca */
	    textFieldCerca =  new JTextField();
	    textFieldCerca.setPreferredSize(new Dimension(170,45));
	    textFieldCerca.setMinimumSize(new Dimension(170,45));
	    textFieldCerca.setOpaque(false);
	    textFieldCerca.addActionListener(new Ascoltatore(this));
	    textFieldCerca.getDocument().addDocumentListener(new Ascoltatore(this));
	    /* border */
	   	TitledBorder titoloCerca;
	   	titoloCerca = BorderFactory.createTitledBorder("Cerca");
	   	textFieldCerca.setBorder(titoloCerca);

	  	reset();
		gridCon.gridy = 0;
	 	gridCon.gridx = 4;
	 	gridCon.fill = GridBagConstraints.NONE;
	 	gridCon.anchor = GridBagConstraints.LINE_END;
	    gridBL.setConstraints(textFieldCerca, gridCon);
	    panelPrincipale.add(textFieldCerca);


	    /* -------------------------------------------------- RIGA 2 -------------------------------------------------- */
	    
	    /* JLabel */
		labelViaggi = new JLabel("VIAGGI DISPONIBILI");
		reset();
	 	gridCon.gridy = 1;
	 	gridCon.gridx = 0;
	    gridBL.setConstraints(labelViaggi, gridCon);
	    panelPrincipale.add(labelViaggi);


	    labelDettagli = new JLabel("DETTAGLI");
		reset();
	 	gridCon.gridy = 1;
	 	gridCon.gridx = 3;
	    gridBL.setConstraints(labelDettagli, gridCon);
	    panelPrincipale.add(labelDettagli);


	    /* -------------------------------------------------- RIGA 3 -------------------------------------------------- */

	    /* JList */
	    model = new DefaultListModel<MioModello>();
		listOrari = new JList<MioModello>(model);
		listOrari.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOrari.setCellRenderer(new MyCellRenderer());

		scrollList = new JScrollPane(listOrari);
	    scrollList.setPreferredSize(new Dimension(480, 260));
		scrollList.setMinimumSize(new Dimension(480, 260));

		reset();
	    gridCon.gridy = 2;
	    gridCon.gridx = 0;
	    gridCon.gridwidth = 2;
		gridCon.gridheight = 2;
	    gridCon.anchor = GridBagConstraints.NORTHWEST;
	    gridCon.fill = GridBagConstraints.HORIZONTAL;
	    gridBL.setConstraints(scrollList, gridCon);
	    panelPrincipale.add(scrollList);


	    /* JTextPane */
	    textPaneDettagli = new JTextPane();
	    textPaneDettagli.setContentType("text/html");
	    textPaneDettagli.setEditable(false);

	    scrollDettagli = new JScrollPane(textPaneDettagli);
	    scrollDettagli.setPreferredSize(new Dimension(270,210));
		scrollDettagli.setMinimumSize(new Dimension(270,210));

	 	reset();
	 	gridCon.gridy = 2;
	 	gridCon.gridx = 3;
	 	gridCon.gridwidth = 2;
	 	gridCon.fill = GridBagConstraints.HORIZONTAL;
	    gridBL.setConstraints(scrollDettagli, gridCon);
	    panelPrincipale.add(scrollDettagli);


	    /* -------------------------------------------------- RIGA 4 -------------------------------------------------- */

		/* JButton */
	    buttonAcquista = new JButton("Acquista");

	 	reset();
	 	gridCon.gridy = 3;
	 	gridCon.gridx = 3;
		gridCon.gridwidth = 2;
	 	gridCon.anchor = GridBagConstraints.NORTHWEST;
	 	gridCon.fill = GridBagConstraints.BOTH;
	    gridBL.setConstraints(buttonAcquista, gridCon);
	    panelPrincipale.add(buttonAcquista);



	    /* -------------------------------------------------- RIGA 5 -------------------------------------------------- */

	    /* JTextArea */
	    textAreaConsole = new JTextArea();
	    textAreaConsole.setEditable(false);

	    scrollConsole = new JScrollPane(textAreaConsole);
	    scrollConsole.setPreferredSize(new Dimension(700,120));
		scrollConsole.setMinimumSize(new Dimension(700,120));

	 	reset();
		gridCon.insets.top = 50;

	 	gridCon.gridy = 4;
	 	gridCon.gridx = 0;
	 	gridCon.gridwidth = 5;
	    gridBL.setConstraints(scrollConsole, gridCon);
	    panelPrincipale.add(scrollConsole);


	    /* aggiunta degli ascoltatori */
		boxOrdina.addActionListener(new Ascoltatore(this));
		listOrari.addListSelectionListener(new Ascoltatore(this));
		buttonAcquista.addActionListener(new Ascoltatore(this));



		/*---------
		|		  |
		|  TAB_2  | (ACQUISTATI)
		|		  |
		---------*/
		/* -------------------------------------------------- RIGA 1 -------------------------------------------------- */
		
		/* JTable */
		modelTableAcquistati = new DefaultTableModel(new MioModello[][] {}, new String[] {"Treno","Partenza","Stazione di partenza","Arrivo","Stazione di arrivo","Durata","Prezzo"}) {
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		tableAcquistati = new JTable(modelTableAcquistati);
		for (int i=0; i<=6; i++) {	// 6 è il numero delle colonne della tabella
			if(i==0 || i==2 | i==4){
				tableAcquistati.getColumnModel().getColumn(i).setPreferredWidth(120);
			}else{
				tableAcquistati.getColumnModel().getColumn(i).setMinWidth(60);
				tableAcquistati.getColumnModel().getColumn(i).setMaxWidth(60);	
			}
		}
		
		JScrollPane contenitore = new JScrollPane(tableAcquistati);
	 	gridCon2.gridy = 0;
	 	gridCon2.gridx = 0;
	 	gridCon2.weightx = 1;
	 	gridCon2.weighty = 1;
	 	gridCon2.anchor = GridBagConstraints.WEST;
	 	gridCon2.fill = GridBagConstraints.BOTH;
	    gridBL2.setConstraints(contenitore, gridCon2);

	    panelAcquistati.add(contenitore);
	    


	    /* istruzioni finali */

	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);

	}

	/**
	* Metodo che si occupa di riportare tutti i parametri dell'oggetto <code>GridBagConstraints</code> allo stato iniziale
	* voluto dal programmatore
	*/
	public void reset(){

		gridCon.fill = GridBagConstraints.BOTH;
	    gridCon.gridheight = 1;
	    gridCon.gridwidth = 1;
	    gridCon.weightx = 1;
	    gridCon.weighty = 1;
	    gridCon.anchor = GridBagConstraints.PAGE_START;
		gridCon.insets.top = 5;
	    gridCon.insets.bottom = 5;
	    gridCon.insets.left = 5;
	    gridCon.insets.right = 5;

	}

	/**
	* Metodo che si occupa di riempire un array model da inserire nel componente grafico JList per popolare la lista degli orari.&nbsp;
	*
	* @param lista l'array di oggetti di tipo {@link Orario} (lista degli orari)
	* @param check parametro necessario a riconoscere da chi è richiamato il metodo, e gestire di conseguenza istruzioni differenti
	*/
	public void caricaDati(ArrayList<Orario> lista, int check) {

		if(check==0) {	//se 0, arriva dal MAIN -> salvo su lista originale, e non tocco più
			arrayOrariOriginale = lista;
		}

		model.removeAllElements();
		arrayOrari = lista;

		for ( int i = 0; i < arrayOrari.size(); i++ ) {	//per ogni orario, creo una stringa da inserire nel modello della JList

			String s = "<html><pre>"+"    "+arrayOrari.get(i).getOrarioPartenza().toString();
			s += "   "+arrayOrari.get(i).getStazionePartenza();
			s += "\t\t"+arrayOrari.get(i).getOrarioArrivo().toString();
			s += "   "+arrayOrari.get(i).getStazioneArrivo()+"</pre></html>";

			/* inserisco il la stringa nel modello e gestiscto il logo */
			if ((arrayOrari.get(i).getNome()).contains("Italo")) {
				model.addElement(new MioModello(s,arrayOrari.get(i).getIdentificativo(),"icons/logo_italo.png"));
			} else {
				model.addElement(new MioModello(s,arrayOrari.get(i).getIdentificativo(),"icons/logo_fs.png"));
			}

		}

	}

	/**
	* Metodo con lo scopo di ordinare l'array di orari, che verrà di volta in volta reinserito nel componente grafico JList
	*
	* @param scelta parametro in base al quale viene decretato quale metodo di ordinamento effettuare
	*/
	public void ordinaModello(int scelta) {

		switch(scelta) {
			case 0:
					//no sorting
					break;

			case 1:
					Collections.sort(arrayOrari, new Comparatore("orario","partenza"));
					break;

			case 2:
					Collections.sort(arrayOrari, new Comparatore("orario","arrivo"));
					break;

			case 3:
					Collections.sort(arrayOrari, new Comparatore("stazione","partenza"));
					break;

			case 4:
					Collections.sort(arrayOrari, new Comparatore("stazione","arrivo"));
					break;

			default:
					Collections.sort(arrayOrari, new Comparatore("orario","partenza"));
					break;
		}

		/* ...dopo aver ordinato l'array, lo reinserisco nella JList */
		caricaDati(arrayOrari,1);
	}

}
