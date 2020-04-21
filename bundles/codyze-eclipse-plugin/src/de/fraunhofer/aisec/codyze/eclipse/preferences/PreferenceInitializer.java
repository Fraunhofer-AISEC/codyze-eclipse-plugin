package de.fraunhofer.aisec.codyze.eclipse.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.fraunhofer.aisec.codyze.eclipse.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_PATH, "/opt/codyze/bin/codyze");
		store.setDefault(PreferenceConstants.P_MEMORY, "6G");
		store.setDefault(PreferenceConstants.P_MARK, "/opt/codyze/mark");
		store.setDefault(PreferenceConstants.P_TSA, "WPDS");
	}

}
