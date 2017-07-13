<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search page!</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Text</th>
        <th>Date</th>
    </tr>
<#list model.commentModel as comment>
    <tr>
        <td>${comment.id}</td>
        <td>${comment.text}</td>
        <td>${comment.addingDate}</td>
    </tr>
</#list>
</table>
</body>
</html>