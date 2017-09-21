<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-角色列表</title>
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
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so layui-form-pane">
            <input class="layui-input" placeholder="用户名" name="username">
            <input class="layui-input" placeholder="昵称" name="nikeName">
            <input class="layui-input" type="tel" placeholder="手机" name="phone"/>
            <input class="layui-input" type="email" placeholder="邮箱" name="email"/>
            <div class="layui-input-inline">
                <select name="status">
                    <option value="">状态</option>
                    <option value="1">已启用</option>
                    <option value="2">已停用</option>
                </select>
            </div>
            <button type="submit" class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
        </form>
    </div>
    <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加角色','user.html')"><i class="layui-icon"></i>添加</button>
    </xblock>

    <table class="layui-hide" id="table_user" lay-filter="role"></table>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        form.on('submit(sreach)', function (data) {
            table.reload('roleReload', {
                where: data.field //设定异步数据接口的额外参数
            });

            return false;
        });
        //方法级渲染
        table.render({
            elem: '#table_user'
            , url: 'queryAdminUser'
            , cols: [[
                {field: 'username', title: '登录名', width: 120},
                {field: 'nikeName', title: '昵称', width: 120},
                {field: 'phone', title: '手机号码', width: 120},
                {field: 'email', title: '邮箱', width: 180},
                {field: 'createTime', title: '加入时间', width: 180, sort: true},
                {field: 'status', title: '状态', width: 80, toolbar: '#barDemo2'},
                {title: '操作', width: 220, align: 'center', toolbar: '#barDemo'}
            ]],
            page: true
            , id: 'roleReload'
            , where: $("form").serializeJson()
            , height: 'auto'
        })

        //监听工具条
        table.on('tool(role)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        url: 'user?ids=' + data.id,
                        type: 'DELETE',
                        success: function (result) {
                            table.reload('roleReload', {
                                where: $("form").serializeJson() //设定异步数据接口的额外参数
                            });
                            layer.close(index);
                        }
                    })
                });
            } else if (obj.event === 'edit') {
                x_admin_show('修改角色', 'user.html?id=' + data.id);
            } else if (obj.event === 'stop') {
                //prompt层
                if (data.status == '0') {
                    layer.confirm('确认停用吗？', function (index) {
                        $.ajax({
                            url: 'stopAdminUser?status=1&id=' + data.id,
                            type: 'PUT',
                            success: function (result) {
                                layer.msg('已停用!', {icon: 5, time: 1000});
                                table.reload('roleReload', {
                                    where: $("form").serializeJson() //设定异步数据接口的额外参数
                                });
                                layer.close(index);
                            }
                        })
                    });
                } else {
                    layer.confirm('确认启用吗？', function (index) {

                        $.ajax({
                            url: 'stopAdminUser?status=0&id=' + data.id,
                            type: 'PUT',
                            success: function (result) {
                                layer.msg('已启用!', {icon: 5, time: 1000});
                                table.reload('roleReload', {
                                    where: $("form").serializeJson() //设定异步数据接口的额外参数
                                });
                                layer.close(index);
                            }
                        })
                    });

                }

            }
        })
    });


</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
    {{#  if(d.status == 0){ }}<a class="layui-btn layui-btn-warm layui-btn-mini" lay-event="stop"><i
            class="layui-icon">&#xe601;</i>停用</a>{{#  } else { }}<a class="layui-btn layui-btn-normal layui-btn-mini"
                                                                    lay-event="stop"><i class="layui-icon">&#xe62f;</i>启用</a>{{#  } }}
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>
<script type="text/html" id="barDemo2">
    {{#  if(d.status == 0){ }}<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>{{#  } else { }}<span
            class="layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled">已停用</span>{{#  } }}
</script>
</body>

</html>