package ar.com.mercadolibre.exam.covid19.christianzelaya.util;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

	private int status;
	private String message;
	private String method;
	private List<ApiSubError> subErrors;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}
	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}
	
}
