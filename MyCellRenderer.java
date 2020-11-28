import java.awt.*;
import javax.swing.*;

/**
* MyCellRenderer è una classe proprietaria con lo scopo di aggiungere un'icona
* ad ogni elemento della lista di orari
*
* @author      Amos Cappellaro
* @author      Luca Dal Poz
* @author      Marco Castagnera
* @version     1.0, %G%
* @see         <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/ListCellRenderer.html">Java API Library: <code>ListCellRenderer&lt;E&gt;</code></a>
*/
public class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {

 /**
 * Metodo necessario ad aggiungere il logo della compagnia che opererà il viaggio
 * a fianco dell'elemento della lista degli orari
 *
 * @param list          la JList che si sta per stampare
 * @param value         l'oggetto che viene trasformato nella stringa equivalente all'orario
 * @param index         l'indice della cella
 * @param isSelected    vero se la cella specificata è stata selezionata
 * @param cellHasFocus  vero se la cella specificata ha il focus
 * @return              un componente il cui metodo <code>paint()</code> farà il
 *                      render del <code>value</code> specificato
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/ListCellRenderer.html#getListCellRendererComponent(javax.swing.JList,%20E,%20int,%20boolean,%20boolean)">Java API Library: <code>getListCellRendererComponent()</code></a>
 */
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String s = value.toString();
        setText(s);

        setIcon(((MioModello)value).getImmagine());

        if (isSelected) {
           setBackground(list.getSelectionBackground());
           setForeground(list.getSelectionForeground());
        } else {
           setBackground(list.getBackground());
           setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
