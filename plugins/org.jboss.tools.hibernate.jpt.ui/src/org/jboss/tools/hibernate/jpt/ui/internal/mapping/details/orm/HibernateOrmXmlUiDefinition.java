/*******************************************************************************
  * Copyright (c) 2010 Red Hat, Inc.
  * Distributed under license by Red Hat, Inc. All rights reserved.
  * This program is made available under the terms of the
  * Eclipse Public License v1.0 which accompanies this distribution,
  * and is available at http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributor:
  *     Red Hat, Inc. - initial API and implementation
  ******************************************************************************/
package org.jboss.tools.hibernate.jpt.ui.internal.mapping.details.orm;

import java.util.List;

import org.eclipse.jpt.common.core.JptResourceType;
import org.eclipse.jpt.common.ui.internal.jface.SimpleItemTreeStateProviderFactoryProvider;
import org.eclipse.jpt.common.ui.jface.ItemTreeStateProviderFactoryProvider;
import org.eclipse.jpt.jpa.core.JptJpaCorePlugin;
import org.eclipse.jpt.jpa.core.context.AttributeMapping;
import org.eclipse.jpt.jpa.core.context.TypeMapping;
import org.eclipse.jpt.jpa.ui.ResourceUiDefinition;
import org.eclipse.jpt.jpa.ui.details.orm.OrmAttributeMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.details.orm.OrmTypeMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.details.orm.OrmXmlUiFactory;
import org.eclipse.jpt.jpa.ui.internal.details.orm.AbstractOrmXmlResourceUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmBasicMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmEmbeddableUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmEmbeddedIdMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmEmbeddedMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmEntityUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmIdMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmManyToManyMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmManyToOneMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmMappedSuperclassUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmOneToManyMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmOneToOneMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmTransientMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.details.orm.OrmVersionMappingUiDefinition;
import org.eclipse.jpt.jpa.ui.internal.structure.OrmStructureItemContentProviderFactory;
import org.eclipse.jpt.jpa.ui.internal.structure.OrmStructureItemLabelProviderFactory;


/**
 * @author Dmitry Geraskov
 *
 */
public class HibernateOrmXmlUiDefinition extends AbstractOrmXmlResourceUiDefinition {
	
	// singleton
	private static final ResourceUiDefinition INSTANCE = new HibernateOrmXmlUiDefinition();
	
	
	/**
	 * Return the singleton
	 */
	public static ResourceUiDefinition instance() {
		return INSTANCE;
	}
	
	
	/**
	 * Enforce singleton usage
	 */
	private HibernateOrmXmlUiDefinition() {
		super();
	}
	

	@Override
	protected OrmXmlUiFactory buildOrmXmlUiFactory() {
		return new HibernateOrmXmlUiFactory();
	}
	
	public boolean providesUi(JptResourceType resourceType) {
		return resourceType.equals(JptJpaCorePlugin.ORM_XML_1_0_RESOURCE_TYPE);
	}
	
	public ItemTreeStateProviderFactoryProvider getStructureViewFactoryProvider() {
		return STRUCTURE_VIEW_FACTORY_PROVIDER;
	}
	
	public static final ItemTreeStateProviderFactoryProvider STRUCTURE_VIEW_FACTORY_PROVIDER =
			new SimpleItemTreeStateProviderFactoryProvider(
					OrmStructureItemContentProviderFactory.instance(),
					OrmStructureItemLabelProviderFactory.instance()
				);
	
	@Override
	protected void addOrmAttributeMappingUiDefinitionsTo(List<OrmAttributeMappingUiDefinition<? extends AttributeMapping>> definitions) {
		definitions.add(OrmIdMappingUiDefinition.instance());
		definitions.add(OrmEmbeddedIdMappingUiDefinition.instance());
		definitions.add(OrmBasicMappingUiDefinition.instance());
		definitions.add(OrmVersionMappingUiDefinition.instance());
		definitions.add(OrmManyToOneMappingUiDefinition.instance());
		definitions.add(OrmOneToManyMappingUiDefinition.instance());
		definitions.add(OrmOneToOneMappingUiDefinition.instance());
		definitions.add(OrmManyToManyMappingUiDefinition.instance());
		definitions.add(OrmEmbeddedMappingUiDefinition.instance());
		definitions.add(OrmTransientMappingUiDefinition.instance());
	}
	
	@Override
	protected void addOrmTypeMappingUiDefinitionsTo(List<OrmTypeMappingUiDefinition<? extends TypeMapping>> definitions) {
		definitions.add(OrmEntityUiDefinition.instance());
		definitions.add(OrmMappedSuperclassUiDefinition.instance());
		definitions.add(OrmEmbeddableUiDefinition.instance());
	}
	
}
