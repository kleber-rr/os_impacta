package com.impacta.os.resources.exceptions;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandardError implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long timeStamp;
	private Integer status;
	private String error;
	public StandardError(Long timeStamp, Integer status, String error) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
	}
	public StandardError() {
		super();
	}

	
	
}
