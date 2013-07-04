<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#search").click(function(){
		var id = $("input[name=name]").val();
		if(isNull(id)){
			alert("pleas type userid between 0 and 5");
			return ;
		}
		$.ajax({url:'${pageContext.request.contextPath}/user/findUserByName?name='+id,type:"POST",success:function(user){
			$("div#user").html("<span>username:"+user.name+"  age:"+user.age+"  sex:"+user.sex+"</span>");
		}});
	});
});
</script>
<div><span>用户名称:</span><input name="name" value=""/><input type="button" id="search" value="查询用户"/></div>
<div id="user"></div>
