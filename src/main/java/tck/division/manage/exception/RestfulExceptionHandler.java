/**
 * Copyright 2018 trans-cosmos korea Co., All Rights Reserved.
 *   http://www.trans-cosmos.co.kr
 *
 * This source code is digitised property of trans-cosmos korea  Company Limited ("tsKo") and
 * protected by copyright under the law of Republic of Korea and other foreign laws.
 * Reproduction and/or redistribution of the source code without written permission of
 * tsKo is not allowed. Also, it is severely prohibited to register whole or specific
 * part of the source code to any sort of information retrieval system.
 */
package tck.division.manage.exception;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tck.division.manage.model.APIResult;


@RestControllerAdvice
public class RestfulExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RestfulExceptionHandler.class);
	
	
	
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(BoardNotFoundException.class)
//    public String handle(BoardNotFoundException e, Model model, HttpServletRequest request) {
//        log.error(e.getMessage(), e);
//        model.addAttribute("timestamp", LocalDateTime.now());
//        model.addAttribute("error", "BOARD_NOT_FOUND");
//        model.addAttribute("path", request.getRequestURI());
//        model.addAttribute("message", e.getMessage());
//        return "/error/404";
//    }
//	
//	@ExceptionHandler(MyBatisSystemException.class)
//	public BaseResponse handleMyBatisSystemException(MyBatisSystemException e) {
//		
//		log.info("handleMyBatisSystemException()");
//		
//		BaseResponse res = new BaseResponse();
//		
//		res.setResult(Constant.Response.RESULT_CODE.ERROR);
//		res.setError_message(e.getMessage());
//		
//		return res;
//	}
//	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity handleException(CommonException e) {
		
		log.info("handleException() :");
		e.printStackTrace();
		
		APIResult result = new APIResult();
		
		result.setCode(e.getCode());
		result.setMessage(e.getMessage());
		result.setUrl(e.getUrl());
		
		return new ResponseEntity<APIResult>(result, HttpStatus.OK);
	}
	
}
