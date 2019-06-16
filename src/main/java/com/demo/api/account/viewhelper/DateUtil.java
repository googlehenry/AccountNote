package com.demo.api.account.viewhelper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.api.account.AccountConstants;
import com.demo.api.account.controller.AccountController;
import com.demo.api.account.vo.Duration;

public class DateUtil {
	static Logger log = LoggerFactory.getLogger(DateUtil.class);
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat mdDateFormat = new SimpleDateFormat("MM-dd");

	public static Map<Integer, Integer> getYearMap() {
		Map<Integer, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < 12; i++) {
			map.put(i, 0);
		}
		return map;
	}

	public static Map<String, Integer> getMonthWeekMap(Duration duration) {
		Map<String, Integer> map = new LinkedHashMap<>();
		List<String> allDate = duration.getAllDate();
		int size = allDate.size();
		for (int i = 0; i < size; i++) {
			map.put(allDate.get(i), 0);
		}
		return map;
	}

	public static Duration getDuration(String dateType, int number) {
		Duration duration = new Duration();
		switch (dateType) {
		case AccountConstants.DATE_TYPE_WEEK:
			duration = generateDurationOfWeek(number);
			log.info(number + " week " + duration);
			break;
		case AccountConstants.DATE_TYPE_MONTH:
			duration = generateDurationOfMonth(number);
			log.info(number + " month " + duration);
			break;
		default:
			break;
		}

		return duration;
	}

	public static Duration generateDurationOfWeek(int week) {

		Calendar c = new GregorianCalendar();
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		c.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一周的第一天

		Calendar calendar = (GregorianCalendar) c.clone();
		calendar.add(Calendar.DATE, (week - 1) * 7);
		int firstDayOfWeek = calendar.getFirstDayOfWeek();
		int lastDayOfWeek = calendar.getFirstDayOfWeek() + 6;
		List<String> allDate = new ArrayList<>();

		for (int i = firstDayOfWeek; i <= lastDayOfWeek; i++) {
			calendar.set(Calendar.DAY_OF_WEEK, i);
			allDate.add(mdDateFormat.format(calendar.getTime()));
		}

		calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
		String startDay = simpleDateFormat.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, lastDayOfWeek);
		String endDay = simpleDateFormat.format(calendar.getTime());

		Duration duration = new Duration(Date.valueOf(startDay), Date.valueOf(endDay), allDate);
		return duration;
	}

	public static Duration generateDurationOfMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		List<String> allDate = new ArrayList<>();

		int firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = firstDayOfMonth; i <= lastDayOfMonth; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			allDate.add(mdDateFormat.format(calendar.getTime()));
		}

		calendar.set(Calendar.DAY_OF_MONTH, firstDayOfMonth);
		String startDay = simpleDateFormat.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
		String endDay = simpleDateFormat.format(calendar.getTime());

		Duration duration = new Duration(Date.valueOf(startDay), Date.valueOf(endDay), allDate);
		return duration;

	}

}
