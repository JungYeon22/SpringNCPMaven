<%--
  Created by IntelliJ IDEA.
  User: jungyeon
  Date: 2024-03-25
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {border-collapse: collapse;}
        td, th{padding: 5px; width: 250px;}
    </style>
</head>
<body>
<a href="/"><img src="../image/Logo.png" width="30%"/></a>
<form id="uploadListForm">
<table id="uploadListTable" border="1" frame="hsides" rules="rows">
    <tr>
        <th width="100"><input type="checkbox" id="all" >번호</th>
        <th width="100">이미지</th>
        <th width="100">이미지 이름</th>
    </tr>
</table>
</form>

<input type="button" id="uploadDeleteBtn" value="선택 삭제" style="margin-top: 15px;">

<script src="https://code.jQuery.com/jquery-3.7.1.min.js"></script>
<script src="../js/uploadList.js"></script>
<script src="../js/uploadDelete.js"></script>
</body>
</html>
