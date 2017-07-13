<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search page!</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Comment</th>
    </tr>
<#list model.commentModel as comment>
    <tr>
        <td>${comment.highlight}</td>
    </tr>
</#list>
</table>
</body>
</html>