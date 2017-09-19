<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-角色编辑</title>
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
    <form action="" method="post" class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="x-red">*</span>角色名
            </label>
            <input value="${role.roleId}" name="roleId" type="hidden">
            <div class="layui-input-inline">
                <input type="text" id="roleName" name="roleName" required="" lay-verify="required"
                       value="${role.roleName}"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">
                拥有权限
            </label>
            <table class="layui-table layui-input-block">
                <tbody>
                <#assign i=0 />
                <#list ruleCates as ruleCate>
                <tr>
                    <td>

                        <input name="id[]" type="checkbox" lay-filter="allChoose" title="${ruleCate.cateName}">
                    </td>
                    <td>
                        <div class="layui-input-block">
                            <#list ruleCate.rules as rule>
                                <input name="roleRules[${i}].ruleId" type="checkbox" value="${rule.ruleId}"
                                       lay-filter="choose"
                                       title="${rule.ruleName}">
                                <#assign i = i+1 >
                            </#list>
                        </div>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="layui-form-item layui-form-text">
            <label for="desc" class="layui-form-label">
                描述
            </label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="desc" name="desc"
                          class="layui-textarea">${role.roleDiscription}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, layer = layui.layer;

        //全选
        form.on('checkbox(allChoose)', function (data) {
            var child = $(data.elem).parents('tr').find('input[type="checkbox"]');
            child.each(function (index, item) {
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });
        form.on('checkbox(choose)', function (data) {
            var child = $(data.elem).parents('tr').find('input[type="checkbox"]');
            var child2 = $(data.elem).parents('td').find('input[type="checkbox"]');
            if (data.elem.checked) {
                var isALlChecked = true;
                child2.each(function (index, item) {
                    if (!item.checked) {
                        console.log(isALlChecked);
                        isALlChecked = false;
                    }
                });
                child[0].checked = isALlChecked;

            } else {
                child[0].checked = data.elem.checked;
            }
            form.render('checkbox');
        });
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