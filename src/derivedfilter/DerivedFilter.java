package derivedfilter;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * A filter that filters out derived files from the package explorer.
 */
public class DerivedFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		try {
			if (element instanceof IResource) {
				IResource res = (IResource) element;
				return !res.isDerived();
			}
			else if (element instanceof IPackageFragment) {
				final IResource res = ((IPackageFragment) element).getResource();
				if (res != null) {
					return !res.isDerived();
				}
			}
		}
		catch (Throwable e) {
			return true;
		}
		return true;
	}

}
