package com.demo.api.account.viewhelper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.api.account.entity.Account;
import com.demo.api.account.entity.Category;
import com.demo.api.account.service.CategoryService;
import com.demo.api.account.vo.AccountSummary;
import com.demo.api.account.vo.AccountVO;
import com.demo.api.account.vo.ChartVO;
import com.demo.api.account.vo.Duration;
import com.demo.api.account.vo.Item;
import com.demo.api.account.vo.MonthWeekLineChildPoint;
import com.demo.api.account.vo.ParentItem;
import com.demo.api.account.vo.Point;
import com.demo.api.account.vo.YearLineChildPoint;
import com.demo.api.account.vo.YearPieChildPoint;
import static com.demo.api.account.viewhelper.DateUtil.mdDateFormat;

public class AccountUtil {
	static Logger log = LoggerFactory.getLogger(AccountUtil.class);

	public static AccountVO generteAccountVO(List<Account> accounts) {
		Double outcome = accounts.stream().filter(a -> a.getCategory().getType().equals("out"))
				.map(account -> new Double(account.getAmount())).reduce((double) 0, (a, b) -> a + b);
		Double income = accounts.stream().filter(a -> a.getCategory().getType().equals("in"))
				.map(account -> new Double(account.getAmount())).reduce((double) 0, (a, b) -> a + b);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");

		@SuppressWarnings("unchecked")
		Map<String, List<Item>> itemMap = (Map) accounts.stream()
				.map(a -> new Item(String.valueOf(a.getId()), dateFormatter.format(a.getCreateDay()),
						timeFormatter.format(a.getCreateTime()), a.getCategory().getImagePath(),
						a.getCategory().getCode(), a.getCategory().getDescription(), a.getCategory().getType(),
						a.getAmount()))
				.collect(Collectors.groupingBy(Item::getCreateDay, LinkedHashMap::new, Collectors.toList()));
		List<ParentItem> items = new ArrayList<ParentItem>();
		for (Entry<String, List<Item>> entry : itemMap.entrySet()) {
			ParentItem parentItem = new ParentItem(entry.getKey(), entry.getValue());
			items.add(parentItem);
		}

		log.info("outcome: " + outcome.toString());
		log.info("income: " + income.toString());
		log.info("items: " + items);

		AccountSummary accountSummary = new AccountSummary(String.valueOf(income), String.valueOf(outcome));
		AccountVO accountVo = new AccountVO(accountSummary, items);
		return accountVo;
	}

	public static Account generateAccount(Item item, String customerId, CategoryService categoryService) {
		Category category = categoryService.getCategoryById(item.getCategoryCode());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

		Account account = null;
		try {
			java.util.Date date = dateFormat.parse(item.getCreateDay());
			java.util.Date time = timeFormat.parse(item.getCreateTime());
			account = new Account(new java.sql.Date(date.getTime()), new java.sql.Time(time.getTime()),
					item.getAmount(), customerId, category);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	// *********chart start**********//
	public static ChartVO generateYearChartVO(List<Account> accounts, String accountType) {
		ChartVO chartVo = new ChartVO();
		List<Point> linePoints = generateYearLinePoints(accounts, accountType);
		List<Point> piePoints = generatePiePoints(accounts, accountType);
		String totalAmount=generateTotalAmount(accounts, accountType);
		chartVo.setLinePoints(linePoints);
		chartVo.setPiePoints(piePoints);
		chartVo.setTotalAmount(totalAmount);
		return chartVo;
	}
	
	public static ChartVO generateMonthWeekChartVO(List<Account> accounts, String accountType,Duration duration) {
		ChartVO chartVo = new ChartVO();
		List<Point> linePoints = generateMonthWeekLinePoints(accounts, accountType,duration);
		List<Point> piePoints = generatePiePoints(accounts, accountType);
		String totalAmount=generateTotalAmount(accounts, accountType);
		chartVo.setLinePoints(linePoints);
		chartVo.setPiePoints(piePoints);
		chartVo.setTotalAmount(totalAmount);
		return chartVo;
	}

	private static List<Point> generateYearLinePoints(List<Account> accounts, String accountType) {
		Map<Integer, Integer> map = accounts.stream()
				.filter(account -> account.getCategory().getType().equals(accountType))
				.map(account -> new YearLineChildPoint(getIntFromAmount(account.getAmount()),
						account.getCreateDay().getMonth() + 1))
				.collect(Collectors.groupingBy(YearLineChildPoint::getMonth,
						Collectors.reducing(0, YearLineChildPoint::getAmount, Integer::sum)));

		// sort
		// LinkedHashMap<Integer, Integer> orderedMap = new LinkedHashMap<>();
		// map.entrySet().stream()
		// .sorted(Map.Entry.<Integer, Integer> comparingByKey())
		// .forEachOrdered(e -> orderedMap.put(e.getKey(), e.getValue()));

		LinkedHashMap<Integer, Integer> yearMap = (LinkedHashMap<Integer, Integer>) DateUtil.getYearMap();
		map.entrySet().stream()
		 .forEach(e -> yearMap.put(e.getKey(), e.getValue()));
		
		List<Point> points = yearMap.entrySet().stream()
				.map(entry -> new Point(entry.getKey() + "月", entry.getValue())).collect(Collectors.toList());

		return points;
	}
	
	public static List<Point> generateMonthWeekLinePoints(List<Account> accounts, String accountType, Duration duration) {
		Map<String, Integer> map = accounts.stream()
				.filter(account -> account.getCategory().getType().equals(accountType))
				.map(account->new MonthWeekLineChildPoint(getIntFromAmount(account.getAmount())
														,mdDateFormat.format(account.getCreateDay())))
				.collect(Collectors.groupingBy(MonthWeekLineChildPoint::getDay,
						Collectors.reducing(0,MonthWeekLineChildPoint::getAmount,Integer::sum)));
		LinkedHashMap<String, Integer> monthWeekMap = (LinkedHashMap<String, Integer>) DateUtil.getMonthWeekMap(duration);
		log.info("monthWeekMap: " + monthWeekMap.toString());
		map.entrySet().stream()
		 .forEach(e -> monthWeekMap.put(e.getKey(), e.getValue()));
		
		List<Point> points = monthWeekMap.entrySet().stream()
				.map(entry -> new Point(entry.getKey(), entry.getValue())).collect(Collectors.toList());
		return points;
	}

	private static List<Point> generatePiePoints(List<Account> accounts, String accountType) {
		Map<String, Integer> map = accounts.stream()
				.filter(account -> account.getCategory().getType().equals(accountType))
				.map(account -> new YearPieChildPoint(getIntFromAmount(account.getAmount()),
						account.getCategory().getDescription()))
				.collect(Collectors.groupingBy(YearPieChildPoint::getCategoryDesc,
						Collectors.reducing(0, YearPieChildPoint::getAmount, Integer::sum)));

		LinkedHashMap<String, Integer> orderedMap = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
				.forEachOrdered(e -> orderedMap.put(e.getKey(), e.getValue()));

		List<Point> allPoints = orderedMap.entrySet().stream()
				.map(entry -> new Point(entry.getKey() + ": " + entry.getValue(), entry.getValue()))
				.collect(Collectors.toList());
		int maxLength = allPoints.size() >= 5 ? 5 : allPoints.size();
		List<Point> points = allPoints.subList(0, maxLength);
		return points;
	}
	
	private static String generateTotalAmount(List<Account> accounts, String accountType){
		int totalAmount = accounts.stream()
				.filter(account -> account.getCategory().getType().equals(accountType))
				.map(account -> getIntFromAmount(account.getAmount()))
				.collect(Collectors.reducing(0,Integer::sum));
		return String.valueOf(totalAmount);
	}
	


	public static Integer getIntFromAmount(String amount) {
		int intAmount = new BigDecimal(amount).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		return Integer.valueOf(intAmount);
	}
	// *********chart end**********//
}
