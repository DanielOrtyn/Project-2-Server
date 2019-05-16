package com.revature.util;

import java.util.Date;

public class TimeUtil {

	public static long GetTimeCount() {
		return (new Date()).getTime();
	}
}
