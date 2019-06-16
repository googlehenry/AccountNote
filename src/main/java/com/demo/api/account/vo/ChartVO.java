package com.demo.api.account.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChartVO {
	private String totalAmount;
	private List<Point> linePoints;
	private List<Point> piePoints;
}
