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
        <input type="hidden" name="collectionId" value="${collection.collectionId}"/>
        <div class="layui-form-item">
            <div class="layui-inline">

                <label for="L_collectionName" class="layui-form-label">
                    名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_collectionName" name="collectionName" value="${collection.collectionName}"
                           required
                           class="layui-input"/>
                </div>
            </div>
            <div class="layui-inline">
                <label for="L_parentcollectionId" class="layui-form-label">
                    上级目录
                </label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" style="float: left;width: 190px;" id="L_parentName" name="parentName"
                           value="${parentCollection.collectionName}"
                           readonly
                           onclick="showMenu(); return false;"
                           class="layui-input"/>
                    <button class="layui-btn layui-btn-small" type="button" style="float: left;margin-left: 10px;"
                            onclick="$('#L_parentId').val('');$('#L_parentName').val('');"><i
                            class="layui-icon"></i></button>
                    <input type="hidden" id="L_parentId" name="parentId"
                           value="${parentCollection.collectionId}"/>
                </div>
                <div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 999999;">
                    <ul id="tree" class="ztree"
                        style="  border: 1px solid #617775;background: #f0f6e4 none repeat scroll 0 0;margin-top:0; width:240px;z-index: 999999"></ul>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label for="L_collectionValue" class="layui-form-label">
                    结果集类型
                </label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <select name="collectionType" id="selectCollectionType" lay-filter="collectionType">
                            <option value="">目录</option>
                        <#list collectionTypes as collectionType>
                            <option value="${collectionType.code}"
                                    <#if collectionType.code==collection.collectionType>selected="selected"</#if>>${collectionType.code}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-inline" id="selectSourceId">
                <label for="L_collectionValue" class="layui-form-label">
                    数据源
                </label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline">
                        <select name="sourceId" lay-filter="sourceId">
                        <#list dataSources as dataSource>
                            <option value="${dataSource.sourceId}"
                                    <#if dataSource.sourceId==collection.sourceId>selected="selected"</#if>>${dataSource.sourceName}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            </div>

        </div>

        <div class="layui-form-item" id="selectCollectionValue">
            <label for="L_collectionValue" class="layui-form-label">
                结果集来源
            </label>
            <div class="layui-input-block" id="v_collectionValue">

            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_collectionDescription" class="layui-form-label">
                描述
            </label>
            <div class="layui-input-block">
                <textarea type="text" id="L_collectionDiscription" name="collectionDiscription"
          required class="layui-textarea  ">${collection.collectionDiscription}</textarea>
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
            parent.refreshNode('${collectionId}');
        });
    </#if>
        $ = layui.jquery;
        //监听提交
        form.on('submit(save)', function (data) {
            return true;
        });
        form.on('select(collectionType)', function (data) {
            reloadDivStatus(data.value);
        });
        form.on('select(sourceId)', function (data) {
            loadCollectionValue(data.value, $("#selectCollectionType").val());
        });

    });


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
                    onDblClick: function (e, treeId, treeNode) {
                        if (!treeNode.collectionType) {
                            hideMenu();
                            $("#L_parentId").val(treeNode.collectionId);
                            $("#L_parentName").val(treeNode.collectionName);
                        } else {
                            layer.alert("只能选择目录", {icon: 5});
                        }

                    }
                }
            }
    ;

    function clickNode(e, treeId, treeNode) {
        if (!treeNode.children) $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
    }

    function filter(treeId, parentNode, responseData) {
        $(responseData.data).each(function (index, row) {
            row.id = row.collectionId;
            if (!row.collectionType) row.isParent = true;
            else row.isParent = false;
        });
        var data = responseData.data;
        return data;
    };
    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting);
        var collectionType = $("#selectCollectionType").val();
        reloadDivStatus(collectionType);

    });
    var reloadDivStatus = function (collectionType) {
        if (!collectionType) {
            $("#selectSourceId").hide();
            $("#selectCollectionValue").hide();

        } else {
            $("#selectSourceId").show();
            $("#selectCollectionValue").show();
            loadCollectionValue($("#selectSourceId select").val(), collectionType)
        }
    }
    var loadCollectionValue = function (sourceId, collectionType) {
        $.ajax({
            url: 'getCollections?id=' + sourceId + "&type=" + collectionType,
            type: 'get',
            success: function (result) {
                $("#v_collectionValue").empty();
                if (result && result.code == 0) {
                    var select = $("<select></select>");
                    select.attr("id", "L_collectionValue");
                    select.attr("name", "collectionValue");
                    select.attr("lay-filter", "collectionValue");
                    select.attr("required", "");
                    $(result.data).each(function (index, row) {
                        var option = $("<option></option>");
                        option.attr("value", row.TABLE_NAME);
                        option.text(row.TABLE_NAME);
                    <#if (collection.collectionValue)??>
                        if (row.TABLE_NAME == '${collection.collectionValue}') option.attr("selected", "selected");
                    </#if>
                        select.append(option);
                    });
                    $("#v_collectionValue").append(select);
                    layui.use(['form'], function () {
                        var form = layui.form;
                        form.render('select');
                    });
                } else {
                    var textarea = $("<textarea></textarea>");
                    textarea.attr("id", "L_collectionValue");
                    textarea.attr("name", "collectionValue");
                    textarea.attr("required", "");
                    textarea.attr("class", "layui-textarea");
                    textarea.val('${collection.collectionValue}');
                    $("#v_collectionValue").append(textarea);
                }
            }
        })
    }

    function showMenu() {
        var cityObj = $("#L_parentName");
        var cityObj2 = $("#L_parentName").parent().prev();

        var cityOffset = cityObj.offset();
        var cityOffset2 = cityObj2.offset();

        $("#menuContent").css({
            left: (cityOffset.left - cityOffset2.left) + "px",
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