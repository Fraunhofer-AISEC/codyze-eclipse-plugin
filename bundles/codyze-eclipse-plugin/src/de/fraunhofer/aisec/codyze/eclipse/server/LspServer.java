package de.fraunhofer.aisec.codyze.eclipse.server;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.osgi.framework.Bundle;

import de.fraunhofer.aisec.codyze.eclipse.Activator;
import de.fraunhofer.aisec.codyze.eclipse.preferences.PreferenceConstants;

/**
 * Launches and connects to the LSP server when needed.
 */
public class LspServer extends ProcessStreamConnectionProvider {

	private static final String ID = "Codyze Language Server";
	
	private String xmx;
	
	public LspServer() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		String codyzePath = store.getString(PreferenceConstants.P_PATH);
		xmx = store.getString(PreferenceConstants.P_MEMORY);
		String markFolder = store.getString(PreferenceConstants.P_MARK);
		String tsa = store.getString(PreferenceConstants.P_TSA);

		try {
			Bundle bundle = Activator.getDefault().getBundle();
			Path workingDir = new Path(FileLocator.toFileURL(FileLocator.find(bundle, new Path("/"), null)).getPath());

			// Start on Linux/Mac: Launch Java process and bind to its standard input/output
			// stream.
			List<String> commands = Arrays.asList(codyzePath, "-l", "-m", markFolder, "--typestate=" + tsa);

			String osSpecificPath = workingDir.toOSString();
			if (Platform.OS_WIN32.equals(Platform.getOS())) {
				osSpecificPath = "\"" + osSpecificPath + "\"";
			}

			setCommands(commands);
			setWorkingDirectory(workingDir.toOSString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Need to override createProcessBuilder() to include JAVA_OPTS in environment
	 * variables.
	 */
	@Override
	protected ProcessBuilder createProcessBuilder() {
		ProcessBuilder builder = new ProcessBuilder(getCommands());
		if (getWorkingDirectory() != null) {
			builder.directory(new File(getWorkingDirectory()));
		}
		builder.redirectError(ProcessBuilder.Redirect.INHERIT);
		
		// Add Xmx command line option
		builder.environment().put("JAVA_OPTS", "-Xmx" + xmx);
		
		return builder;
	}
	
	@Override
	public void start() throws IOException {
		System.out.println("Starting LSP server");
		super.start();
		System.out.println("  LSP server started");
	}

	@Override
	public void stop() {
		System.out.println("  Stopping LSP server");
		super.stop();
		System.out.println("LSP server stopped");
	}

	@Override
	public String toString() {
		return ID + " [commands=" + this.getCommands() + ", workingDir=" + this.getWorkingDirectory() + "]";
	}

}