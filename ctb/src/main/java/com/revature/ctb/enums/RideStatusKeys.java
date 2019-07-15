package com.revature.ctb.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Enum to keep the primary keys for role table, this way we can query the role
 * table. These are not change by the users
 *
 */
public enum RideStatusKeys {

	Active(1), Completed(2), Canceled(3);

	private int value;
	private static Map<Integer, RideStatusKeys> map = new HashMap<>();

	public int getValue() {
		return value;
	}

	public String getText() {
		return String.join(" ", name().toString().split("_"));
	}

	private RideStatusKeys(int value) {
		this.value = value;
	}

	static {
		for (RideStatusKeys roleKey : RideStatusKeys.values()) {
			map.put(roleKey.value, roleKey);
		}
	}

	public static RideStatusKeys valueOf(int value) {
		return (RideStatusKeys) map.get(value);
	}
}
