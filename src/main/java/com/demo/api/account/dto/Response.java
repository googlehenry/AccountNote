package com.demo.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Response {
	public int statusCode;
	public Object data;
	public String errorCode;
	public String errorMsg;
	public Response(int statusCode, Object data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}
	public Response(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
}
