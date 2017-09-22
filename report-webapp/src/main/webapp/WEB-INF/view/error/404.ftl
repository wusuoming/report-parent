<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>报表管理系统-错误界面</title>
    <link rel="stylesheet" href="../static/css/error.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="parent">
    <div class="container">
        <div class="container_1">
            <DIV style="FONT-SIZE: 100pt; FILTER: shadow(color=7093DB); HEIGHT: 21px">
                <PRE> <FONT
                        color=6bbaa3><B><#if message??>400<#else>404</#if></B> </FONT></PRE>
            </DIV>
        </div>
        <div class="container_2"><img src="../static/images/3.22.gif"></div>
        <div class="container_3"><!--<img src="images/404_1.png" >-->
            <div class="container_3_1"><span>${message!'SORRY你要访问的页面弄丢了'}</span></div>
            <div class="container_3_2">
                <table style="width: 100%;text-align: center;">
                    <tr>
                        <td><a href="javascript:history.back(-1);"
                               style=" color: #1E9FFF; ">返回上一页</a></td>
                        <td><a href="/index.html" style=" color: #1E9FFF; ">返回首页</a></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
