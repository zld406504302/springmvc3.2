package com.cn.ld.framework.web.tiles;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

public class CustomerPreparer implements ViewPreparer {

	@Override
	public void execute(Request request, AttributeContext context) {
		context.putAttribute("body", new Attribute(
				"This is the value added by the CustomerPreparer"));
	}

}
