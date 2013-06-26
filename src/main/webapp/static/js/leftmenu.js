$(
  function()
  {
	  
	$.fn.isTitle = function()
	{
		if (isNull(this) || !$(this).is("div"))
			return false ;
		else{
			if($(this).attr("isTitle")=="true")
				return true ;
			else
				return false ;
		}
	}(jQuery) ;
	  
	$("div#leftmenu li div").hover(function(){
		$("div#leftmenu li div").removeClass("higlight_light_light_gray");
		var current_li_class = $(this).attr("class") ;
		if (isNull(current_li_class)|| current_li_class =="show" || current_li_class =="hide margin_left_20"){
			$(this).addClass("higlight_light_light_gray");
		}
	}).click(function()
	{
		$("div#leftmenu li div").removeClass("higlight_light_light_gray");
		
		var $sub_menu = $(this).parent().find("div[isTitle='false']");
		
		if($(this).attr("isTitle")=="true")
		{
			$("div#leftmenu li div").removeClass("higlight_gray");
			$("div#leftmenu li div[isTitle='false']").hide("slow");
			$(this).addClass("higlight_gray").removeClass("higlight_light_gray");
			if($sub_menu.is(":hidden"))
				$sub_menu.show("slow");
			else
				$sub_menu.hide("slow");
		} else 
		{
			$("div#leftmenu li div").removeClass("higlight_light_gray");
			$(this).addClass("higlight_light_gray");
		}
		
	});
	
  }
 );

