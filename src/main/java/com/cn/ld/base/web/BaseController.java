package com.cn.ld.base.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public abstract class BaseController {

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initDateBinder(final WebDataBinder dataBinder,final Locale locale) {
		final String dateformat = this.messageSource.getMessage("date.format",null, locale);
		final SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		sdf.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,false));
	}

}
