/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.commands.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.commands.util.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * <p>
 * Abstract implementation for an undoable operation
 * </p>
 * 
 * @see org.eclipse.core.commands.operations.IUndoableOperation
 * 
 * @since 3.1
 */
public abstract class AbstractOperation implements IUndoableOperation {
	protected List contexts = new ArrayList();

	private String label = ""; //$NON-NLS-1$

	/**
	 * Construct an operation that has the specified label.
	 * 
	 * @param label -
	 *            the label to be used for the operation.
	 */
	public AbstractOperation(String label) {
		this.label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#addContext(org.eclipse.core.commands.operations.IUndoContext)
	 */
	public void addContext(IUndoContext context) {
		if (!contexts.contains(context)) {
			contexts.add(context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#canRedo()
	 */
	public boolean canRedo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#canUndo()
	 */
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#dispose()
	 */
	public void dispose() {
		// nothing to dispose.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.core.runtime.IAdaptable)
	 */
	public abstract IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException;

	public IUndoContext[] getContexts() {
		return (IUndoContext[]) contexts.toArray(new IUndoContext[contexts
				.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#getLabel()
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the label of the operation to the specified name.
	 * 
	 * @param name -
	 *            the string to be used for the label.
	 */
	public void setLabel(String name) {
		label = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#hasContext(org.eclipse.core.commands.operations.IUndoContext)
	 */
	public boolean hasContext(IUndoContext context) {
		Assert.isNotNull(context);
		for (int i = 0; i < contexts.size(); i++) {
			IUndoContext otherContext = (IUndoContext) contexts.get(i);
			// have to check both ways because one context may be more general
			// in
			// its matching rules than another.
			if (context.matches(otherContext) || otherContext.matches(context))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#redo(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.core.runtime.IAdaptable)
	 */
	public abstract IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException;

	public void removeContext(IUndoContext context) {
		contexts.remove(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.operations.IUndoableOperation#undo(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.core.runtime.IAdaptable)
	 */
	public abstract IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException;

	/**
	 * The string representation of this operation. Used for debugging purposes
	 * only. This string should not be shown to an end user.
	 * 
	 * @return The string representation.
	 */
	public String toString() {
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(getLabel());
		stringBuffer.append("("); //$NON-NLS-1$
		IUndoContext[] contexts = getContexts();
		for (int i = 0; i < contexts.length; i++) {
			stringBuffer.append(contexts[i].toString());
			stringBuffer.append(',');
		}
		stringBuffer.append(')');
		return stringBuffer.toString();
	}
}
