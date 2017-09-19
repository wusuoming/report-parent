<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-权限分类列表</title>
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
            <input class="layui-input" placeholder="分类名" name="cateName">
            <button type="submit" class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
        </form>
    </div>
    <xblock>
        <button class="layui-btn" onclick="add_cate()"><i class="layui-icon"></i>增加
        </button>
    </xblock>
    <table class="layui-hide" id="table_rule_cate" lay-filter="ruleCate"></table>
</div>
<script>

    function add_cate() {
        layui.use(['table', 'form'], function () {
            var table = layui.table;

            //prompt层
            layer.prompt({title: '新增分类', formType: 3}, function (name, index) {
                $.ajax({
                    url: "ruleCate?name=" + name,
                    type: 'PUT',
                    success: function (result) {
                        table.reload('ruleReload', {
                            where: $("form").serializeJson() //设定异步数据接口的额外参数
                        });
                        layer.close(index);
                    }
                })
            });
        });
    }

    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;
        form.on('submit(sreach)', function (data) {
            table.reload('ruleReload', {
                where: data.field //设定异步数据接口的额外参数
            });

            return false;
        });
        //方法级渲染
        table.render({
            elem: '#table_rule_cate'
            , url: 'queryRuleCate'
            , cols: [[
                {field: 'cateName', title: '权限分类', width: 180}
                , {field: 'createTime', title: '创建时间', sort: true, width: 160}
                , {title: '操作', width: 160, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'ruleReload'
            , where: $("form").serializeJson()
            , height: 'auto'
        })

        //监听工具条
        table.on('tool(ruleCate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        url: 'ruleCate?ids=' + data.cateId,
                        type: 'DELETE',
                        success: function (result) {
                            table.reload('ruleReload', {
                                where: $("form").serializeJson() //设定异步数据接口的额外参数
                            });
                            layer.close(index);
                        }
                    })
                    ;
                });
            } else if (obj.event === 'edit') {
                //prompt层
                layer.prompt({title: '修改分类', formType: 3, value: data.cateName}, function (name, index) {
                    $.ajax({
                        url: 'ruleCate?id=' + data.cateId + "&name=" + name,
                        type: 'PUT',
                        success: function (result) {
                            table.reload('ruleReload', {
                                where: $("form").serializeJson() //设定异步数据接口的额外参数
                            });
                            layer.close(index);
                        }
                    })
                });
            }
        })
    });


</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
</body>

</html>