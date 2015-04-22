package com.lottery.rotary.mobile.android.tools;

public class DoubleClickUtils {
	private static long lastClickTime;

	/**
	 *  判断是不是快速点击
	 * 
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long cuurentTime = System.currentTimeMillis();
		long timeD = cuurentTime - lastClickTime;
		// 两秒以内的连续点击都当做快速点击了
		if (0 < timeD && timeD < 2000) {
			return true;
		}
		lastClickTime = cuurentTime;
		return false;
	}

}
