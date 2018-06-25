package com.TechTheEasyWay.bill.StaticHelpers;

public class ValidationHelper {

	public static boolean hasText(final String p_strData) {
		return !isEmpty(p_strData);
	}

	public static boolean isEmpty(final String p_strData) {
		if (null == p_strData) {
			return true;
		}

		return p_strData.trim().isEmpty();
	}

	public static boolean isNumeric(final String p_strData) {
		return isNumeric(p_strData, true);
	}

	public static boolean isNumeric(final String p_strData, final boolean p_bWholeNumber) {

		if (isEmpty(p_strData)) {
			return false;
		}

		if (!p_bWholeNumber) {
			/**
			 * there should only be one decimal point if it is a decimal number.
			 **/
			if (p_strData.indexOf('.') != p_strData.lastIndexOf('.')) {
				return false;
			}
		}

		for (char c : p_strData.toCharArray()) {
			if (!Character.isDigit(c)) {
				if (p_bWholeNumber) {
					return false;
				} else if (!(c == '.')) {
					return false;
				}
			}
		}

		return true;
	}
}
