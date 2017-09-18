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
        <form class="layui-form layui-col-md12 x-so layui-form-pane" id="ruleQuery">
            <input type="text" name="ruleName" placeholder="权限名称" autocomplete="off" class="layui-input">
            <div class="layui-input-inline">
                <select name="ruleCate">
                    <option value="">权限分类</option>
                    <#list ruleCates as ruleCate>
                        <option value="${ruleCate.cateId}">${ruleCate.cateName}</option>
                    </#list>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="ruleType">
                    <option value="">权限类型</option>
                    <option value="1">菜单</option>
                    <option value="2">功能</option>
                </select>
            </div>
            <button type="submit" class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加权限','rule.html')"><i class="layui-icon"></i>添加</button>
    </xblock>
    <table class="layui-hide" id="table_rule" lay-filter="user"></table>
</div>

<script>
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
            elem: '#table_rule'
            , url: 'queryRule'
            , cols: [[
                {field: 'ruleId', checkbox: true}
                , {field: 'ruleName', title: '权限名', width: 180}
                , {field: 'ruleCate', title: '所属分类', width: 120, sort: true, templet: '#ruleCates'}
                , {field: 'ruleType', title: '权限类型', width: 120, sort: true, templet: '#ruleType'}
                , {field: 'ruleExpression', title: '权限表达式', sort: true, width: 180}
                , {field: 'createTime', title: '创建时间', sort: true, width: 160}
                , {title: '操作', width: 160, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'ruleReload'
            , page: true
            , where: $("form").serializeJson()
            , height: 'auto'
        })
        ;
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        url: 'rule?id=' + data.ruleId,
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
                x_admin_show('修改权限', 'rule.html?id=' + data.ruleId);
            }
        });


    });
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


    function delAll(argument) {

        var data = tableCheck.getSelectTableData('ruleReload')
        layer.confirm('确认要删除吗？', function (index) {
            url = "rule?1=1";
            data.forEach(function (val, index, arr) {
                url += "&ids=" + val.ruleId;
            });
            layui.use(['table', 'form'], function () {
                var table = layui.table;
                //捉到所有被选中的，发异步进行删除
                $.ajax({
                    url: url,
                    type: 'DELETE',
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
</script>
<script type="text/html" id="ruleType">
    {{#  if(d.ruleType == 1){ }}菜单{{#  } else { }}接口{{#  } }}
</script>
<script type="text/html" id="ruleCates">
    <#list ruleCates as ruleCate>
         {{#  if(d.ruleCate == ${ruleCate.cateId}){ }}${ruleCate.cateName}{{#  } }}
    </#list>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>


</body>

</html>