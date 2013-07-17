<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function(){
	
	$("div#link_container ul").each(function(){
		var menuType = $(this).attr("contextmenu");
		$(this).find("li").each(function(index ,el){
			var $menuLi = $(this);
			$("ul#left_menu_ul li").eq(menuType).find("div[isTitle='false']").eq(index).click(function(){
				var url = "${pageContext.request.contextPath}"+$menuLi.html() ;
				
				$("div#content").load(url);
			});
		});
		
	});
});
</script>
<div id="link_container" style="display: none;">
<jsp:include page="leftmenu_link.jsp"/>
</div>
<ul id="left_menu_ul">
<li>
<div class="show" isTitle="true"><span>spring annotation1</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring thymeleafe</span></div>
</li>
<li>
<div class="show" isTitle="true"><span>spring annotation2</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation2.1</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation2.2</span></div>
</li>
<li>
<div class="show" isTitle="true"><span>spring annotation3</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation3.1</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation3.2</span></div>
</li>
<li>
<div class="show" isTitle="true"><span>spring annotation4</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation4.1</span></div>
<div class="hide margin_left_20" isTitle="false"><span>spring annotation4.2</span></div>
</li>
</ul>
