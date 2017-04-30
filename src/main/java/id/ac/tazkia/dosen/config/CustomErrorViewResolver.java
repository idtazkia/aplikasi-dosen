package id.ac.tazkia.dosen.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class CustomErrorViewResolver implements ErrorViewResolver {

	 @Override
	 public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
			 Map<String, Object> model) {
		  Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		  Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		  String exceptionMessage = getExceptionMessage(throwable, statusCode);
		 
		 ModelAndView modelv = new ModelAndView("error");
		 modelv.addObject("status", request.getAttribute("javax.servlet.error.status_code"));
		 modelv.addObject("message", exceptionMessage);
		 return modelv;
	 }
	 
	 private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		 if (throwable != null) {
			 return throwable.getMessage();
		 }
		 
		 HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		 return httpStatus.getReasonPhrase();
	 }
}