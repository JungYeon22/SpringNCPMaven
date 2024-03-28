<%--
  Created by IntelliJ IDEA.
  User: jungyeon
  Date: 2024-03-21
  Time: 오후 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    table {border-collapse: collapse;}
    td, th{padding: 5px; width: 250px;}
    #userPagingDiv {
        text-align:center;
        margin-top: 10px;
        padding: 10px;
    }
    span {
        padding: 10px;
        width: 20px;
    }
    #pagingDiv span{text-decoration: none; padding:3px 10px; border-radius: 5px; margin: 0 10px;}
    #currentPaging{background-color : #4CAF50; color:white;}
    #paging:hover{background-color : #ddd; text-decoration:underline;}
    span:Link{color: black; text-decoration: none;}
    span:visited{color: black; text-decoration: none;}
    span:hover{color: #4CAF50; text-decoration: underline;}
    span:active{color: black; text-decoration: none;}
  </style>
</head>
<body>
pg = <input type="text" id="pg" value="${pg }" readonly/>
<a href="/"><img src="../image/Logo.png"  width="30%"/></a>
    <table border="1" frame="hsides" role="rows" id="userListTable">
      <tr>
        <th >이름</th>
        <th>아이디</th>
        <th>비밀번호</th>
      </tr>
      <%-- 동적 처리 --%>
    </table>

    <%-- 페이징 처리 --%>
    <div id="userPagingDiv">

    </div>
<script src="https://code.jQuery.com/jquery-3.7.1.min.js"></script>
<script src="../js/list.js"></script>
<script>
    function userPaging(pg){
        location.href= '/user/list?pg=' + pg;
    }
</script>
</body>
</html>
