<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>超时</title>
<style type="text/css">
/* 注意、警告框 */
.attention {
	background: #FFFBCC;
	border: 1px #E6DB55 solid;
	color: #333;
	margin: 10px;
	padding: 8px 8px 8px 35px;
	line-height: 22px;
	font-size: 12px;
}
/* 圆角，CSS3支持 */
</style>

<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}
</script>

</head>

<body>
	<DIV class="attention">
		长时间未操作，请<a href="<%=path%>">重新登陆</a>!
	</div>
</body>
</html>
