/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.protocol.msg;

import java.util.List;

import org.spout.api.protocol.Message;
import org.spout.api.util.Parameter;

public final class EntityMetadataMessage extends Message {
	public static enum Parameters {
		//Entity flags
		META_INFLAMED(0, (byte) 0x01),
		META_CROUCHED(0, (byte) 0x02),
		META_MOBRIDER(0, (byte) 0x04),
		META_SPRINTING(0, (byte) 0x08),
		META_RIGHTCLICKACTION(0, (byte) 0x10),
		/**
		 * Drowning counter for entities. This should be sent when an entity spawns and be decremented
		 * per tick. If the value hits -19, send a 0x26 and reset this to 0
		 */
		META_FULLDROWNINGCOUNTER(1, (short) 300),
		META_DROWNINGCOUNTEDDEPLETED(1, (short) -19),
		META_STARTDROWNING(1, (short) 0),
		/**
		 * Potion color for effect. The value is composed of RRGGBB (in that order).
		 */
		META_NOPOTIONEFFECT(8, (int) 000000),
		/**
		 * Animal growth and control. -23999 is the value for babies, 6000 is the value for adults. When set
		 * to 6000, decrease over time (to determine when to breed again). Value of 0 means the adult can breed.
		 */
		META_BABYANIMALSTAGE(12, (int) -23999),
		META_PARENTANIMALSTAGE(12, (int) 6000),
		META_BREEDANIMALSTAGE(12, (int) 0);
		private Parameter<?> parameter;

		private Parameters(int index, int value) {
			this.parameter = new Parameter<Integer>(Parameter.TYPE_INT, index, value);
		}

		private Parameters(int index, short value) {
			this.parameter = new Parameter<Short>(Parameter.TYPE_SHORT, index, value);
		}

		private Parameters(int index, byte value) {
			this.parameter = new Parameter<Byte>(Parameter.TYPE_BYTE, index, value);
		}

		public Parameter<?> get() {
			return this.parameter;
		}
	}

	private final int id;
	private final List<Parameter<?>> parameters;

	public EntityMetadataMessage(int id, List<Parameter<?>> parameters) {
		this.id = id;
		this.parameters = parameters;
	}

	public int getId() {
		return id;
	}

	public List<Parameter<?>> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder("EntityMetadataMessage{id=");
		build.append(id).append(",metadata=[");

		for (Parameter<?> param : parameters) {
			build.append(param.toString()).append(",");
		}

		build.append("]}");
		return build.toString();
	}
}
