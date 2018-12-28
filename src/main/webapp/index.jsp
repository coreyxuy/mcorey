<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<h2>文件上传!</h2>
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMvc上传文件">
</form>

<h2>富文本上传文件!</h2>
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMvc上传文件">
</form>

</body>
</html>
