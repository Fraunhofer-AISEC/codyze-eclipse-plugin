package de.fraunhofer.aisec.codyze.eclipse.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.fraunhofer.aisec.codyze.eclipse.Activator;

import org.eclipse.ui.IWorkbench;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class CodyzePreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public CodyzePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Eclipse connects via LSP (Language Server Protocol) to a local Codyze LSP server. The path to the Codyze LSP server binary must be set here.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new FileFieldEditor(PreferenceConstants.P_PATH, "&Path to Codyze LSP server:", getFieldEditorParent()));
		String[][] entryNamesAndValues = new String[][] {new String[] {"4G", "4G"}, new String[] {"6G", "6G"},new String[] {"8G", "8G"},new String[] {"12G", "12G"},};
		addField(new ComboFieldEditor(PreferenceConstants.P_MEMORY, "&Max. memory for analysis server\n(6 GB or more is recommended)", entryNamesAndValues, getFieldEditorParent()));
		addField(new DirectoryFieldEditor(PreferenceConstants.P_MARK, "&Folder containing MARK policy files", getFieldEditorParent()));
		
		addField(new ComboFieldEditor(PreferenceConstants.P_TSA, "&Type State Analysis", new String[][] { {"Within functions only (faster)", "NFA"}, {"Whole program (more precise)", "WPDS"} } , getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}