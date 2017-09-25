<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-机构编辑</title>
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
<div class="x-body">
    <form class="layui-form" method="post">
        <input type="hidden" name="commonRegionId" value="${region.commonRegionId}"/>
        <div class="layui-form-item">
            <label for="L_regionName" class="layui-form-label">
                名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_regionName" name="regionName" value="${region.regionName}" required
                       class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_parentRegionId" class="layui-form-label">
                上级机构
            </label>
            <div class="layui-input-inline" style="width: 250px;">
                <input type="text" style="float: left;width: 190px;" id="L_parentRegionName" name="parentRegionName"
                       value="${parentRegion.regionName}"
                       readonly
                       onclick="showMenu(); return false;"
                       class="layui-input"/>
                <button class="layui-btn layui-btn-small" type="button" style="float: left;margin-left: 10px;"
                        onclick="$('#L_parentRegionId').val('');$('#L_parentRegionName').val('');"><i
                        class="layui-icon"></i></button>
                <input type="hidden" id="L_parentRegionId" name="parentRegionId"
                       value="${parentRegion.commonRegionId}"/>
            </div>
            <div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 999999;">
                <ul id="tree" class="ztree"
                    style="  border: 1px solid #617775;background: #f0f6e4 none repeat scroll 0 0;margin-top:0; width:240px;z-index: 999999"></ul>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_regionDescription" class="layui-form-label">
                描述
            </label>
            <div class="layui-input-inline">
                <textarea type="text" id="L_regionDescription" name="regionDescription"
                          required class="layui-textarea  "
                >${region.regionDescription}</textarea>
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
            parent.refreshNode('${commonRegionId}');
        });
    </#if>
        $ = layui.jquery;
        //监听提交
        form.on('submit(save)', function (data) {
            return true;
        });


    });


    var setting = {
                async: {
                    enable: true,
                    url: "queryRegion",
                    autoParam: ["id"],
                    dataFilter: filter
                }, data: {
                    key: {
                        title: "regionName", name: "regionName"

                    }
                },
                callback: {
                    onClick: clickNode,
                    onDblClick: function (e, treeId, treeNode) {
                        hideMenu();
                        $("#L_parentRegionId").val(treeNode.commonRegionId);
                        $("#L_parentRegionName").val(treeNode.regionName);
                    }
                }
            }
    ;

    function clickNode(e, treeId, treeNode) {
        if (!treeNode.children) $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);

    }

    function filter(treeId, parentNode, responseData) {
        $(responseData.data).each(function (index, row) {
            row.id = row.commonRegionId;
            row.isParent = true;
        });
        var data = responseData.data;

        return data;
    };
    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting);
    });


    function showMenu() {
        var cityObj = $("#L_parentRegionName");
        var cityOffset = cityObj.offset();
        $("#menuContent").css({
            left: cityOffset.left + "px",
            top: cityOffset.top + cityObj.outerHeight() + "px"
        }).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }

</script>
<style>
    ul.ztree {
        background: #f0f6e4 none repeat scroll 0 0;
        border: 1px solid #617775;
        height: 360px;
        margin-top: 10px;
        overflow-x: auto;
        overflow-y: scroll;
        width: 300px;
    }
</style>
</body>

</html>