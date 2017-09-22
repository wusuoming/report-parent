<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-机构保存</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../static/css/font.css">
    <link rel="stylesheet" href="../static/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="../static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../static/lib/ztree/js/jquery.ztree.core.js" charset="utf-8"></script>

    <script type="text/javascript" src="../static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body  ">
    <div class="layui-row">
        <div class="layui-col-md3">
            <ul id="tree" class="ztree"></ul>
        </div>
        <div class="layui-col-md9">
            <xblock>
                <button class="layui-btn" onclick="x_admin_show('添加机构','region.html')"><i class="layui-icon"></i>添加
                </button>
            </xblock>
            <table class="layui-hide" id="table_rule" lay-filter="rule"></table>
        </div>
    </div>
</div>
<SCRIPT type="text/javascript">
    var setting = {
        async: {
            enable: true,
            url: "queryRegion",
            autoParam: ["id"],
            dataFilter: filter
        }, view: {
            dblClickExpand: false//屏蔽掉双击事件
        }, data: {
            key: {
                title: "regionName", name: "regionName"

            }
        },
        callback: {
            onClick: clickNode
        }
    };

    function clickNode(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.expandNode(treeNode);
        loadTableData(treeNode.children, treeNode);
    }

    var loadTableData = function (datas, node) {
        var array = new Array();
//        if (node) {
//            array.push(node);
//        }
        $(datas).each(function (index, data) {
            array.push(data);
        });
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            table.reload("ruleReload", { //其它参数在此省略
                data: array //赋值数据
            });
        });
    }

    function filter(treeId, parentNode, responseData) {
        $(responseData.data).each(function (index, row) {
            row.id = row.commonRegionId;
            row.isParent = true;
        });
        var data = responseData.data;
        loadTableData(data, parentNode);
        return data;
    };

    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting);
    });


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
            , cols: [[
                {field: 'commonRegionId', title: 'ID', width: 60}
                , {field: 'regionName', title: '名称', width: 120}
                , {field: 'regionDescription', title: '描述', width: 220}
                , {field: 'createTime', title: '创建时间', width: 160}
                , {title: '操作', width: 160, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'ruleReload'
            , where: $("form").serializeJson()
            , height: 'auto'
        })
        ;
        //监听工具条
        table.on('tool(rule)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        url: 'region?id=' + data.commonRegionId,
                        type: 'DELETE',
                        success: function (result) {
                            if (result && result.code != 0) {
                                layer.alert(result.message, {icon: 5});
                            } else {
                                layer.alert("删除成功", {icon: 6});
                                layer.close(index);
                                refreshNode();
                            }
                        }
                    })
                    ;
                });
            } else if (obj.event === 'edit') {
                x_admin_show('修改机构', 'region.html?id=' + data.commonRegionId);
            }
        });


    });

    /**
     * 刷新当前节点
     */
    function refreshNode(id) {
        /*根据 treeId 获取 zTree 对象*/
        var zTree = $.fn.zTree.getZTreeObj("tree"),
                type = "refresh",
                silent = false,
                /*获取 zTree 当前被选中的节点数据集合*/
                nodes = zTree.getSelectedNodes();
        /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
        try {
            if (id == nodes[0].commonRegionId) {
                zTree.reAsyncChildNodes(nodes[0], type, silent)
            } else {
                location.reload(true);
            }
        } catch (error) {
            location.reload(true);
        }

    }
</SCRIPT>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>
</body>

</html>