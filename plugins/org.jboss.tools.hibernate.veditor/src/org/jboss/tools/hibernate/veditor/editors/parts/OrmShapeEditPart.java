/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.hibernate.veditor.editors.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.jboss.tools.hibernate.core.IDatabaseTable;
import org.jboss.tools.hibernate.core.IPersistentClass;
import org.jboss.tools.hibernate.veditor.editors.figures.RoundLineBorder;
import org.jboss.tools.hibernate.veditor.editors.figures.TitleFigure;
import org.jboss.tools.hibernate.veditor.editors.figures.TitleLabel;
import org.jboss.tools.hibernate.veditor.editors.model.OrmDiagram;
import org.jboss.tools.hibernate.veditor.editors.model.OrmShape;


/**
 * @author Konstantin Mishin
 *
 */
public class OrmShapeEditPart extends ExtendedShapeEditPart{
		
	protected IFigure createFigure() {
		if (getModel() instanceof OrmShape) {
			TitleFigure figure = new TitleFigure();
			figure.setLayoutManager(new ToolbarLayout());
			TitleLabel label = new TitleLabel();
			label.setText(getCastedModel().getOrmElement().getName());
			FontData fontData[] = Display.getCurrent().getSystemFont().getFontData();
			fontData[0].setStyle(SWT.BOLD);
			//fontData[0].height++;
			label.setFont(ResourceManager.getInstance().getFont(fontData[0]));
			label.setBackgroundColor(getColor());
			label.setIcon(ormLabelProvider.getImage(getCastedModel().getOrmElement()));
			label.setLabelAlignment(PositionConstants.LEFT);
			label.setBorder(new MarginBorder(1,2,1,2));
			figure.add(label,-2);
			label.setOpaque(true);
			figure.setBackgroundColor(getBackgroundColor());
			RoundLineBorder border = new RoundLineBorder();
			border.setColor(ResourceManager.getInstance().getColor(new RGB(160, 160, 160)));
			figure.setBorder(border);
			figure.setSize(-1,-1);
			return figure;
		} else {
			throw new IllegalArgumentException();
		}
	}	
		
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (OrmShape.LOCATION_PROP.equals(prop)) {
			refreshVisuals();
			((OrmDiagram)getParent().getModel()).setDirty(true);
		}
		else if (OrmShape.SET_HIDEN.equals(prop)) {
			int i = figure.getPreferredSize().width;
				((TitleFigure)figure).setHiden(((Boolean)evt.getNewValue()).booleanValue());
				((TitleLabel)figure.getChildren().get(0)).setHiden(((Boolean)evt.getNewValue()).booleanValue());
				if(((Boolean)evt.getNewValue()).booleanValue())
					figure.setSize(i,-1);		
				else
					figure.setSize(-1,-1);				
				refresh();
				((OrmDiagram)getParent().getModel()).setDirty(true);
		}
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		Rectangle bounds = new Rectangle(((OrmShape)getModel()).getLocation(),
				getFigure().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
	}
	
	public void performRequest(Request req) {
		if(RequestConstants.REQ_OPEN.equals(req.getType())) {
			((OrmShape)getModel()).refreshHiden();
		}
	}
	
	protected Color getBackgroundColor() {
		if (getCastedModel().getOrmElement() instanceof IPersistentClass) 
			return ResourceManager.getInstance().getColor(new RGB(0,0,0));
		else if (getCastedModel().getOrmElement() instanceof IDatabaseTable) 
			return ResourceManager.getInstance().getColor(new RGB(
					Integer.parseInt(Messages.Colors_DatabaseColumnR),
					Integer.parseInt(Messages.Colors_DatabaseColumnG),
					Integer.parseInt(Messages.Colors_DatabaseColumnB)));
		else
			throw new IllegalArgumentException();
	}

}