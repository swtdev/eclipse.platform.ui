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
package org.eclipse.ui;

/**
 * Interface for import wizards.
 * <p>
 * Clients should implement this interface and include the name of their class
 * in a wizard contributed to the workbench's import wizard extension point
 * (named <code>"org.eclipse.ui.importWizards"</code>).
 * For example, the plug-in's XML markup might contain:
 * <pre>
 * &LT;extension point="org.eclipse.ui.importWizards"&GT;
 *   &LT;wizard
 *       id="com.example.myplugin.blob"
 *       name="Blob File"
 *       class="com.example.myplugin.BlobFileImporter"
 *       icon="icons/import_blob_wiz.gif"&GT;
 *     &LT;description&GT;Import resources from a BLOB file&LT;/description&GT;
 *     &LT;selection class="org.eclipse.core.resources.IResource" /&GT;
 *   &LT;/wizard&GT;
 * &LT;/extension&GT;
 * </pre>
 * </p>
 *
 * @see org.eclipse.jface.wizard.IWizard
 */
public interface IImportWizard extends IWorkbenchWizard {
}
