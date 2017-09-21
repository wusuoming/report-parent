<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报表管理系统-首页</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/lib/contextjs/context.css">
    <link rel="stylesheet" href="./static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./static/lib/contextjs/context.js" charset="utf-8"></script>
    <script type="text/javascript" src="./static/js/xadmin.js" charset="utf-8"></script>
    <script type="text/javascript" src="./static/js/index/index.js" charset="utf-8"></script>

</head>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="./index.html">报表管理系统</a>
    </div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${user.nikeName}</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
            <#--<dd>-->
            <#--<a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a>-->
            <#--</dd>-->
                <dd>
                    <a onclick="x_admin_show('修改密码','password.html')">修改密码</a>
                </dd>
                <dd>
                    <a href="./logout.html">退出</a>
                </dd>
            </dl>
        </li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a _href="./welcome.html">
                    <i class="layui-icon">&#xe62a;</i>
                    <cite>我的桌面</cite>
                </a>
            </li>
        <#list menu as ruleCate>
            <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe62a;</i>
                    <cite>${ruleCate.cateName}</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <#list ruleCate.rules as rule>
                        <li>
                            <a _href="${rule.ruleExpression}">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>${rule.ruleName}</cite>
                            </a>
                        </li>
                    </#list>
                </ul>
            </li>
        </#list>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="layui-this" lay-id="1">我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./welcome.html' tab-id="1" frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2017 x-admin v2.3 All Rights Reserved</div>
</div>
</body>

</html>