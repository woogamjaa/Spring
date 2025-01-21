<%@ page import="com.bs.spring.common.error.MyException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bs.spring.common.error.MyException" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3><%=exception.getMessage()%></h3>
    <p> 발생시간 : <%=((MyException)exception).getDate()%></p>
</body>
</html>
