package com.revature.ctb.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleKeys {

	Driver(1), Passenger(2), Administrator(3);

	private int value;
	private static Map<Integer, RoleKeys> map = new HashMap<>();

	public int getValue() {
		return value;
	}

	public String getText() {
		return String.join(" ", name().toString().split("_"));
	}

	private RoleKeys(int value) {
		this.value = value;
	}

	static {
		for (RoleKeys roleKey : RoleKeys.values()) {
			map.put(roleKey.value, roleKey);
		}
	}

	public static RoleKeys valueOf(int value) {
		return (RoleKeys) map.get(value);
	}
}
