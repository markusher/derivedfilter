package derivedfilter;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Adds a context menu item to mark multiple elements as derived.
 */
public class MarkMultiple extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (MessageDialog.openConfirm(HandlerUtil.getActiveShell(event), "Mark derived", "Sure to mark all files as derived?")) {
		    final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

            final ISelection selection = window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
            final TreeSelection treeSelection = ((TreeSelection) selection);


		    @SuppressWarnings("rawtypes")
			final Iterator it = treeSelection.iterator();
    		try {
			    while (it.hasNext()) {
			    	final Object element = it.next();
			    	if (element instanceof IResource) {
						((IResource) element).setDerived(true, null);
			    	}
			    	else if (element instanceof IPackageFragment) {
			    		final IResource el = ((IPackageFragment) element).getResource();
			    		if (el != null) {
			    			el.setDerived(true, null);
			    		}
			    	}
			    }
			} catch (CoreException e) {
				throw new ExecutionException("Error marking derived", e);
			}

		}
		return null;
	}

}
