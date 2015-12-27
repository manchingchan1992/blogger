package com.blogger.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;


@ControllerAdvice
public class MainExceptionHandler{
	private static final Logger logger = LoggerFactory.getLogger(MainExceptionHandler.class);

	private static MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	
	@ExceptionHandler(HandlerException.class)
	public ResponseEntity<HandlerException> handleCustomException(HandlerException ex) {
		return new ResponseEntity<HandlerException>(ex, HttpStatus.BAD_REQUEST);
	}
	
	public static void handleJsonHandlerError(Model model,HttpStatusCodeException e){
		try {
			HandlerException ex = jsonConverter.getObjectMapper().readValue(e.getResponseBodyAsByteArray(),HandlerException.class);
			logger.info("Exception:"+e.getResponseBodyAsString());
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Error Code:"+ex.getErrCode()+" Description:"+ ex.getErrMsg());
        } catch (Exception e1){
        	e1.printStackTrace();
        	model.addAttribute("css", "danger");
        	model.addAttribute("msg", "Failed!");
		}
	}
}