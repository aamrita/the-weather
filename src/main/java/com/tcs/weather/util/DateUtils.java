package com.tcs.weather.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;

/**
 * @author amrutha
 *
 *         For performing operations up on the input date like
 *         formatting,checking if the input date is a future date, getting the
 *         month from input date, fetching the previous two months of the input
 *         month.
 */
public class DateUtils {
	static Logger LOGGER = Logger.getLogger(DateUtils.class);

	/**
	 * @param date
	 *            : user input date in the form of String.
	 * @return : an object of Date in the format MM/dd/yyyy.
	 * @throws WeatherException
	 */
	public static Date formatDate(String date) throws WeatherException {
		DateFormat dateformat = new SimpleDateFormat(ConstantParam.DATE_FORMAT);
		Date referenceDate = null;
		try {
			referenceDate = dateformat.parse(date);
		} catch (ParseException e) {
			throw new WeatherException(ConstantParam.VALID_DATE_MSG
					+ ConstantParam.DATE_FORMAT);
		}
		return referenceDate;
	}

	/**
	 * @param inpDate
	 *            : user input date in the form of String.
	 * @return : boolean, 'true' is the date is after the current date.'false'
	 *         otherwise.
	 * @throws WeatherException
	 */
	public static boolean checkIfFutureDate(String inpDate)
			throws WeatherException {
		boolean isfurute = true;
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern(ConstantParam.DATE_FORMAT);
		DateFormat df = new SimpleDateFormat(ConstantParam.DATE_FORMAT);
		LocalDateTime now = LocalDateTime.now();
		Date currDate = null;
		Date inputDate = null;
		try {
			currDate = df.parse(dtf.format(now));
			inputDate = df.parse(inpDate);
		} catch (ParseException e) {
			throw new WeatherException(
					"Error in parsing the dates. Enter a date in MM/dd/yyyy as input and try again"
							+ ConstantParam.DATE_FORMAT);
		}
		if (!(inputDate == null)) {
			if (!(inputDate.after(currDate))) {
				return !isfurute;
			}
		}
		return isfurute;
	}

	/**
	 * @param refDate
	 *            : user input date in the form of String.
	 * @return : first two characters of the input date, which denotes the
	 *         month.
	 */
	public static String sliceMonth(String refDate) {
		String outputMonth = new String();
		outputMonth = refDate.substring(0, 2);
		return outputMonth;
	}

	/**
	 * @param refMonthInt
	 *            : the sequence number of the month in the user-input date (10
	 *            in case of October).
	 * @return : a list of 3 integers, which correspond to sequence number of
	 *         three months.
	 */
	public static List<Integer> getPreviousMonths(int refMonthInt) {
		List<Integer> mnthList = new ArrayList<Integer>();
		for (int i = 0; i < ConstantParam.DEF_NUM_MNTH; i++) {
			if (refMonthInt == 0) {
				mnthList.add(ConstantParam.DEF_DEC_MNTH);
			} else if (refMonthInt < 0) {
				mnthList.add(ConstantParam.DEF_NOV_MNTH);
			}else{
			mnthList.add(refMonthInt);
			}
			refMonthInt--;
		}
		return mnthList;
	}

	/**
	 * @param inpRefDate
	 *            : input date in MM/dd/yyyy format.
	 * @return : date in ISO8601 format
	 */
	public static String convertToIso(Date inpRefDate) {
		SimpleDateFormat outSDF = new SimpleDateFormat(ConstantParam.ISO_DATE);
		String outDateIso = outSDF.format(inpRefDate);
		return outDateIso;
	}

}
