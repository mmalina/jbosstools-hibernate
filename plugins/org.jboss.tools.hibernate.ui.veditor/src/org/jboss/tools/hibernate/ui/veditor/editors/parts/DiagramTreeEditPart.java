package org.jboss.tools.hibernate.ui.veditor.editors.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.jboss.tools.hibernate.ui.veditor.editors.model.OrmDiagram;

public class DiagramTreeEditPart extends org.eclipse.gef.editparts.AbstractTreeEditPart
	implements PropertyChangeListener
{

	/**
	 * Constructor initializes this with the given model.
	 *
	 * @param model  Model for this.
	 */
	public DiagramTreeEditPart(Object model) {
		super (model);
	}

	/**
	 * Returns the model of this as a OrmDiagram.
	 *
	 * @return  Model of this.
	 */
	protected OrmDiagram getOrmDiagram() {
		return (OrmDiagram)getModel();
	}

	/**
	 * Returns <code>null</code> as a Tree EditPart holds
	 * no children under it.
	 *
	 * @return <code>null</code>
	 */
	protected List getModelChildren() {
		return getOrmDiagram().getChildren();
	}

	public void propertyChange(PropertyChangeEvent change){
		refreshVisuals();
	}

	/**
	 * Refreshes the visual properties of the TreeItem for this part.
	 */
	protected void refreshVisuals(){
		if (getWidget() instanceof Tree)
			return;
	}
	
}