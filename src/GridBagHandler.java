import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Diese Klasse erleichtert den Umgang mit dem GridBagLayout.
 * <p>
 * 	<code>GridBagHandler.addComponent(this, component)<br />
 * 	<code>GridBagHandler.addComponent(this, component, column, row)<br />
 * 	<code>GridBagHandler.addComponent(this, component, column, row, colspan, rowspan)
 * </p>
 *
 * @author ekleinod
 * @version 0.1
 * @since 0.1
 */
public class GridBagHandler {

    /**
     * Addiert eine Komponente zu einem Panel.
     *
     * @param theAdder addierender Panel
     * @param theAdded zu addierende Komponente
     */
    public static void addComponent(JPanel theAdder, JComponent theAdded) {

        addComponent(theAdder, theAdded, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE);

    }

    /**
     * Addiert eine Komponente zu einem Panel.
     *
     * @param theAdder addierender Panel
     * @param theAdded zu addierende Komponente
     * @param iColumn Spalte
     * @param iRow Zeile
     */
    public static void addComponent(JPanel theAdder, JComponent theAdded, int iColumn, int iRow) {

        addComponent(theAdder, theAdded, iColumn, iRow, 1, 1);

    }

    /**
     * Addiert eine Komponente zu einem Panel.
     *
     * @param theAdder addierender Panel
     * @param theAdded zu addierende Komponente
     * @param iColumn Spalte
     * @param iRow Zeile
     * @param iColumnSpan Spaltenuebergriff
     * @param iRowSpan Zeilenuebergriff
     */
    public static void addComponent(JPanel theAdder, JComponent theAdded, int iColumn, int iRow, int iColumnSpan, int iRowSpan) {

        GridBagConstraints theConstraints = new GridBagConstraints();

        theConstraints.weightx = 100;
        theConstraints.weighty = 100;

        theConstraints.anchor = GridBagConstraints.NORTHWEST;
        theConstraints.fill = GridBagConstraints.BOTH;

        theConstraints.gridx = iColumn;
        theConstraints.gridy = iRow;

        theConstraints.gridwidth = iColumnSpan;
        theConstraints.gridheight = iRowSpan;

        theConstraints.insets = new Insets(0, 0, 0, 0);

        theAdder.add(theAdded, theConstraints);

    }

}
