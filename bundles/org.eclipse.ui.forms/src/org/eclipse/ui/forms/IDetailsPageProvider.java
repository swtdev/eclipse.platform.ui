/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.forms;

/**
 * The class that implements this interface provides for dynamic
 * computation of page key and the page itself based on the
 * input object. It should be used in situations where
 * using the object class as a static key is not enough
 * i.e. different pages may need to be loaded for objects
 * of the same type depending on their state.
 *
 * @see DetailsPart
 * @see MasterDetailsBlock
 * @since 3.0
 */
public interface IDetailsPageProvider {
/**
 * Returns the page key for the provided object. The assumption is
 * that the provider knows about various object types and
 * is in position to cast the object into a type and call methods
 * on it to determine the matching page key.
 * @param object the input object
 * @return the page key for the provided object
 */
	Object getPageKey(Object object);
/**
 * Returns the page for the provided key. This method is the dynamic
 * alternative to registering pages with the details part directly.
 * @param key the page key
 * @return the matching page for the provided key
 */
	IDetailsPage getPage(Object key);
}
