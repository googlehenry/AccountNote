package com.demo.api.account;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.demo.api.account.viewhelper.DateUtil;

public class Test {

	public static void main(String[] args) {
		System.out.println(DateUtil.generateDurationOfMonth(5).toString());
		System.out.println(DateUtil.generateDurationOfWeek(24).toString());
		Map<String, Integer> map = new LinkedHashMap<>();
		List<String> allDate = new ArrayList<String>();
		int size = allDate.size();
		for (int i = 0; i < size; i++) {

		}
	}

}
