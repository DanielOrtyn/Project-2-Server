package com.revature.util;

import com.revature.model.User;

public class RedactUtil {

	public static void redactUser(User userToRedact) {
		if(userToRedact==null) {
			return;
		}
		userToRedact.setPassword(null);
	}
}
