<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-权限编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../static/css/font.css">
    <link rel="stylesheet" href="../static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body">
    <form class="layui-form" method="post">
        <input type="hidden" name="ruleId" value="${rule.ruleId}"/>
        <div class="layui-form-item">
            <label for="L_ruleName" class="layui-form-label">
                权限名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_ruleName" name="ruleName" value="${rule.ruleName}" required
                       class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_ruleCate" class="layui-form-label">所属分类</label>
            <div class="layui-input-inline">
                <select id="L_ruleCate" name="ruleCate">
                    <#list ruleCates as ruleCate>
                        <option value="${ruleCate.cateId}"  <#if rule.ruleCate=ruleCate.cateId> selected="selected"</#if>>${ruleCate.cateName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="L_ruleType">权限类型</label>
            <div class="layui-input-inline">
                <select id="L_ruleType" name="ruleType">
                    <option value="1" <#if rule.ruleCate=1> selected="selected"</#if>>菜单</option>
                    <option value="2" <#if rule.ruleCate=2> selected="selected"</#if>>接口</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_ruleExpression" class="layui-form-label">
                权限表达式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_ruleExpression" name="ruleExpression" value="${rule.ruleExpression}" required
                       class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="save" lay-submit="">
                保存
            </button>
        </div>
    </form>
</div>
<script>
    //发异步，把数据提交给php
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer;
        var form = layui.form;
    <#if error_message??>
        layer.msg('${error_message}');
    </#if>
    <#if success?? >
        layer.alert("修改成功", {icon: 6}, function () {
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
            parent.location.reload(true);
        });
    </#if>
        $ = layui.jquery;
        //监听提交
        form.on('submit(save)', function (data) {
            return true;
        });


    });
</script>

</body>

</html>