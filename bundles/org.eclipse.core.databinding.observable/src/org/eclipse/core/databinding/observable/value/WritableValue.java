/*******************************************************************************
 * Copyright (c) 2006, 2015 IBM Corporation and others.
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
 *     Brad Reynolds - bug 158687
 *     Brad Reynolds - bug 164653, 147515
 *     Boris Bokowski - bug 256422
 *     Matthew Hall - bug 256422
 *     Stefan Xenos <sxenos@gmail.com> - Bug 335792
 *******************************************************************************/

package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;

/**
 * Mutable (writable) implementation of {@link IObservableValue} that will
 * maintain a value and fire change events when the value changes.
 * <p>
 * This class is thread safe. All state accessing methods must be invoked from
 * the {@link Realm#isCurrent() current realm}. Methods for adding and removing
 * listeners may be invoked from any thread.
 * </p>
 *
 * @param <T>
 *            the type of value being observed
 * @since 1.0
 */
public class WritableValue<T> extends AbstractObservableValue<T> {

	private final Object valueType;

	/**
	 * Constructs a new instance with the default realm, a <code>null</code>
	 * value type, and a <code>null</code> value.
	 */
	public WritableValue() {
		this(null, null);
	}

	/**
	 * Constructs a new instance with the default realm.
	 *
	 * @param initialValue
	 *            can be <code>null</code>
	 * @param valueType
	 *            can be <code>null</code>
	 */
	public WritableValue(T initialValue, Object valueType) {
		this(Realm.getDefault(), initialValue, valueType);
	}

	/**
	 * Constructs a new instance with the provided <code>realm</code>, a
	 * <code>null</code> value type, and a <code>null</code> initial value.
	 *
	 * @param realm
	 */
	public WritableValue(Realm realm) {
		this(realm, null, null);
	}

	/**
	 * Constructs a new instance.
	 *
	 * @param realm
	 * @param initialValue
	 *            can be <code>null</code>
	 * @param valueType
	 *            can be <code>null</code>
	 */
	public WritableValue(Realm realm, T initialValue, Object valueType) {
		super(realm);
		this.valueType = valueType;
		this.value = initialValue;
	}

	private T value = null;

	@Override
	public T doGetValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	@Override
	public void doSetValue(T value) {
		if (this.value != value) {
			fireValueChange(Diffs.createValueDiff(this.value,
					this.value = value));
		}
	}

	@Override
	public Object getValueType() {
		return valueType;
	}

	/**
	 * @param <T2>
	 * @param elementType
	 *            can be <code>null</code>
	 * @return new instance with the default realm and a value of
	 *         <code>null</code>
	 */
	public static <T2> WritableValue<T2> withValueType(Object elementType) {
		return new WritableValue<>(Realm.getDefault(), null, elementType);
	}
}
