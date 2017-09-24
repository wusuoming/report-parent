<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-权限列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../static/css/font.css">
    <link rel="stylesheet" href="../static/lib/layui/css/layui.css" media="all">
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
        <form class="layui-form layui-col-md12 x-so layui-form-pane" id="sourceQuery">
            <input type="text" name="sourceName" placeholder="数据源名称" autocomplete="off" class="layui-input">
            <div class="layui-input-inline">
                <select name="sourceType">
                    <option value="">数据库类型</option>
                <#list dataSourseTypes as dataSourseType>
                    <option value="${dataSourseType.code}">${dataSourseType.code}</option>
                </#list>
                </select>
            </div>
            <button type="submit" class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加数据源','source.html')"><i class="layui-icon"></i>添加</button>
        <button class="layui-btn" onclick="importLocalDataSource()"><i class="layui-icon">&#xe601;</i>导入当前数据源
        </button>
    </xblock>
    <table class="layui-hide" id="table_source" lay-filter="source"></table>
</div>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;
        form.on('submit(sreach)', function (data) {
            table.reload('sourceReload', {
                where: data.field //设定异步数据接口的额外参数
            });

            return false;
        });
        //方法级渲染
        table.render({
            elem: '#table_source'
            , url: 'querySource'
            , cols: [[
                {field: 'sourceName', title: '数据源名称', width: 180}
                , {field: 'sourceType', title: '类型', width: 80}
                , {field: 'sourceIp', title: '地址', width: 120}
                , {field: 'sourcePort', title: '端口', width: 80}
                , {field: 'dataName', title: '库名', width: 80}
                , {field: 'createTime', title: '创建时间', sort: true, width: 160}
                , {title: '操作', width: 160, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'sourceReload'
            , page: true
            , where: $("form").serializeJson()
            , height: 'auto'
        })
        ;
        //监听工具条
        table.on('tool(source)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        url: 'source?ids=' + data.sourceId,
                        type: 'DELETE',
                        success: function (result) {
                            table.reload('sourceReload', {
                                where: $("form").serializeJson() //设定异步数据接口的额外参数
                            });
                            layer.close(index);
                        }
                    })
                });
            } else if (obj.event === 'edit') {
                x_admin_show('修改数据源', 'source.html?id=' + data.sourceId);
            }
        });
    });

    function importLocalDataSource() {
        layer.confirm('是否导入当前数据源？', function (index) {
                    layui.use(['table', 'form'], function () {
                        var table = layui.table;

                        $.ajax({
                            url: 'importLocalDataSource',
                            type: 'GET',
                            success: function (result) {
                                table.reload('sourceReload', {
                                    where: $("form").serializeJson() //设定异步数据接口的额外参数
                                });
                                layer.close(index);
                            }
                        })

                    });
                }
        )
        ;
    }
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>


</body>

</html>