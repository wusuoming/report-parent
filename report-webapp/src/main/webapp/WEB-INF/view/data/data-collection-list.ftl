<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-数据集管理</title>
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
<div class="left-menu">
    <ul id="tree" class="ztree"></ul>
</div>
<div class="right-content">
    <div id="collection">
        <xblock>
            <button class="layui-btn" onclick="x_admin_show('添加机构','collection.html')"><i
                    class="layui-icon"></i>添加
            </button>
        </xblock>
        <table class="layui-hide" id="table_collection" lay-filter="collection"></table>
    </div>
    <div id="collectionData" style="display: none;">
        <div class="layui-tab" lay-filter="tab">
            <ul class="layui-tab-title">
                <li class="layui-this">数据结构</li>
                <li>数据内容</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">

                    <table class="layui-hide" id="table_collection_structure"
                           lay-filter="collectionStructure"></table>
                </div>
                <div class="layui-row">
                    <form class="layui-form layui-col-md12 x-so layui-form-pane" id="dataQuery">
                        <div class="layui-input-inline" id="columnName">
                            <select name="columnName">
                                <option value="">字段名</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <select name="fillterType">
                                <option value="">匹配类型</option>
                            <#list fillterTypes as fillterType>
                                <option value="${fillterType.code}">${fillterType.getName()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="queryValue" placeholder="查询条件" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <button type="submit" class="layui-btn" lay-submit lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
                        </button>
                    </form>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-hide" id="table_collection_data" lay-filter="collectionData"></table>
                </div>
            </div>
        </div>

        <script>
            //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
            layui.use('element', function () {
                var element = layui.element;
                element.on('tab(tab)', function () {
                    console.log(this);
                });
            });
        </script>
    </div>
</div>
<SCRIPT type="text/javascript">
    var setting = {
        async: {
            enable: true,
            url: "queryCollection",
            autoParam: ["id"],
            dataFilter: filter
        }, data: {
            key: {
                title: "collectionName", name: "collectionName"
            }
        },
        callback: {
            onClick: clickNode,
            beforeClick: beforeClick
        }
    };

    function clickNode(e, treeId, treeNode) {
        if (!treeNode.children) $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);

        layui.use(['table', "form"], function () {
            var table = layui.table;
            var form = layui.form;

            if (treeNode.isParent) {
                loadTableData(treeNode.children, treeNode);

            } else {

                $.ajax({
                    url: 'getCollectionStructure?id=' + treeNode.collectionId,
                    type: 'get',
                    success: function (result) {
                        table.reload("collectionStructure", { //其它参数在此省略
                            data: result.data //赋值数据
                        });
                        var fields = [];
                        $("#columnName").find("select").empty();
                        $("#columnName").find("select").append("<option value=\"\">字段名</option>");
                        $(result.data).each(function (index, row) {
                            fields.push({
                                field: row.COLUMN_NAME,
                                title: row.COLUMN_NAME,
                                width: 160,
                                align: 'center',
                                sort: true
                            });
                            $("#columnName").find("select").append("<option value=\"" + row.COLUMN_NAME + "\">" + row.COLUMN_NAME + "</option>")
                        });
                        form.render("select");
                        table.render({
                            elem: '#table_collection_data',
                            url: 'getCollectionData?id=' + treeNode.collectionId
                            , cols: [fields]
                            , id: 'collectionData'
                            , where: $("form").serializeJson()
                            , height: 'auto', page: true
                        });
                    }
                })
            }
        });
    }

    var loadTableData = function (datas, node) {
        var array = [];
        $(datas).each(function (index, data) {
            array.push(data);
        });
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            table.reload("collectionReload", { //其它参数在此省略
                data: array //赋值数据
            });
            //监听工具条
            table.on('tool(collection)', function (obj) {
                var data = obj.data;
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            url: 'collection?id=' + data.collectionId,
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
                    x_admin_show('修改数据', 'collection.html?id=' + data.collectionId);
                }
            });
        });
    };

    function filter(treeId, parentNode, responseData) {
        $(responseData.data).each(function (index, row) {
            row.id = row.collectionId;
            if (!row.collectionType) {
                row.isParent = true;
            }
            else {
                row.isParent = false;
            }
        });
        var data = responseData.data;
        loadTableData(data, parentNode);
        if (!parentNode) {
            data = [{"collectionName": "全部", "open": true, children: data}];
        }
        return data;
    };

    function beforeClick(treeId, treeNode) {
        if (treeNode.isParent) {
            $("#collection").show();
            $("#collectionData").hide();
            return true;
        } else {
            $("#collection").hide();
            $("#collectionData").show();
            return true;
        }
    }

    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting);
    });


    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;
        form.on('submit(sreach)', function (data) {
            table.reload('collectionData', {
                where: data.field //设定异步数据接口的额外参数
            });

            return false;
        });
        //方法级渲染
        table.render({
            elem: '#table_collection'
            , cols: [[
                {field: 'collectionName', title: '名称', width: 120}
                , {field: 'createTime', title: '创建时间', width: 160}
                , {title: '操作', width: 160, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'collectionReload'
            , height: 'auto'
        })
        table.render({
            elem: '#table_collection_structure'
            , cols: [[
                {field: 'COLUMN_NAME', title: '字段名称', width: 160}
                , {field: 'REMARKS', title: '字段描述', width: 160}
                , {field: 'TYPE_NAME', title: '字段类型', width: 160}
                , {field: 'COLUMN_SIZE', title: '字段长度', width: 160}
                , {field: 'DECIMAL_DIGITS', title: '字段精度', width: 160}
            ]]
            , id: 'collectionStructure'
            , where: $("form").serializeJson()
            , height: 'auto', page: false
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
            if (id == nodes[0].collectionId) {
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