package com.cn.ld.base.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
	
	/**
	 * 将分页查询结果对象转换为映射表对象（用于界面表格展示）
	 * @param pagingResult
	 * @return
	 */
	protected Map<String, Object> toMapPagingResult(DataTableResult<?> dataTableResult)
	{
		if(dataTableResult==null)
			return null;
		
		Map<String, Object> re=new HashMap<String, Object>();
		re.put("aaData", dataTableResult.aaData);
		re.put("displayStart", dataTableResult.getDisplayStart());
		re.put("displaySize", dataTableResult.getDisplaySize());
		re.put("internalCounter", dataTableResult.getInternalCounter());
		return re;
	}

}
