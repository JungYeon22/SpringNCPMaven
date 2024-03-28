<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        /* 메뉴 리스트 스타일 */
        .menu {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex;
        }

        /* 메뉴 아이템 스타일 */
        .menu-item {
            margin-right: 20px;
        }

        /* 링크 스타일 */
        .menu-item a {
            text-decoration: none;
            color: #333;
            transition: color 0.3s ease;
        }

        /* 링크 호버 스타일 */
        .menu-item a:hover {
            color: #ff6347; /* 호버 시 색 변경 */
        }
    </style>
</head>
<body>
<h1>*** 메인 화면 ***</h1>
<br/>
Hello Jung Yeon Park!!
<h3>
    <p class="menu"><a class="menu-item" href="/user/writeForm">입력</a></p>
    <p class="menu"><a class="menu-item" href="/user/list">출력</a></p>
    <br>
    <p class="menu"><a class="menu-item" href="/user/uploadForm">파일 업로드</a></p>
    <p class="menu"><a class="menu-item" href="/user/uploadFormAJax">파일 업로드_AJax</a></p>
    <br>
    <p class="menu"><a class="menu-item" href="/user/uploadList">이미지 출력</a></p>
</h3>
</body>
</html>