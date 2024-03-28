<%--
  Created by IntelliJ IDEA.
  User: jungyeon
  Date: 2024-03-25
  Time: 오전 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadFormAJax</title>
  <style>
    th, td{
      padding: 10px;
    }
  </style>
</head>
<body>
<form id="uploadAJaxForm">
  <table border="1">
    <tr>
      <th>상품명</th>
      <td>
        <input type="text" name="imageName" size="35">
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <textarea name="imageContent" id="imageContent" cols="50" rows="10"></textarea>
      </td>
    </tr>

    <tr>
      <td colspan="2">
        <span id="showImageList">파일 이미지</span>
        <img src="../image/camera.png" id="camera" alt="camera" width="30px">
        <input type="file" id="img" name="img[]" multiple="multiple" hidden>
      </td>
    </tr>

    <tr>
      <td colspan="2" align="center">
        <input type="button" value="이미지 업로드" id="uploadAJaxBtn">
        <input type="reset" value="취소">
      </td>
    </tr>
  </table>
</form>
<script src="https://code.jQuery.com/jquery-3.7.1.min.js"></script>
<script src="../js/uploadAJax.js"></script>
<script>
  $('#camera').click(function(){
    //강제 이벤트 발생 -> 트리거
    $('#img').trigger('click');
  })

  // 여러개의 이미지 미리보기
  $('#img').change(function(){
    $('#showImageList').empty();

    for(var i=0;i < this.files.length;i++){
      readURL(this.files[i])
    }
  });

  function readURL(file){
    var reader = new FileReader();

    var show;
    reader.onload = function(e){
      var img = document.createElement('img');
      img.src = e.target.result;
      img.width = 70;
      img.height = 70;
      $('#showImageList').append(img);
    }
    reader.readAsDataURL(file);
  }
</script>

</body>
</html>
