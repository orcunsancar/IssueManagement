package com.orcunsancar.issuemanagement.advice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private Date date;
	private String message;
	
}
