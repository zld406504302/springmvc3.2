<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#search").click(function(){
		var id = $("input[name=id]").val();
		if(isNull(id)){
			alert("pleas type userid between 0 and 5");
			return ;
		}
		$.ajax({url:'${pageContext.request.contextPath}/user/findUserById?id='+id,type:"POST",success:function(user){
			$("div#user").html("<span>username:"+user.name+"  age:"+user.age+"  sex:"+user.sex+"</span>");
		}});
	});
});
</script>
<div><span>用户编号:</span><input name="id" value=""/><input type="button" id="search" value="查询用户"/></div>
<div id="user"></div>
