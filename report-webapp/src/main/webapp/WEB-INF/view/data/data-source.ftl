<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>报表管理系统-数据源编辑</title>
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
        <input type="hidden" name="sourceId" value="${source.sourceId}"/>
        <div class="layui-inline">
            <div class="layui-form-item">
                <label for="L_sourceName" class="layui-form-label">
                    数据源名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_sourceName" name="sourceName" value="${source.sourceName}" required
                           class="layui-input"/>
                </div>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-form-item">
                <label for="L_dataName" class="layui-form-label">
                    数据库名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_dataName" name="dataName" value="${source.dataName}"
                           class="layui-input"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label for="L_sourseType" class="layui-form-label">数据库类型</label>
                <div class="layui-input-inline">
                    <select id="L_sourseType" name="sourceType">
                    <#list dataSourseTypes as dataSourseType>
                        <option value="${dataSourseType.code}"
                                <#if    source.sourceType==dataSourseType.code >selected="selected"</#if>>${dataSourseType.code}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label for="L_characterEncoding" class="layui-form-label">字符集</label>
                <div class="layui-input-inline">
                    <select id="L_characterEncoding" name="characterEncoding">
                        <option value="">字符集</option>
                    <#list encodingTypes as encodingType>
                        <option value="${encodingType.code}"
                                <#if    source.characterEncoding==encodingType.code >selected="selected"</#if>>${encodingType.code}</option>
                    </#list>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label for="L_sourceIp" class="layui-form-label">
                    地址
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_sourceIp" name="sourceIp" value="${source.sourceIp}" required
                           class="layui-input"/>
                </div>
            </div>
            <div class="layui-inline">
                <label for="L_sourcePort" class="layui-form-label">
                    端口
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_sourcePort" name="sourcePort" value="${source.sourcePort}" required
                           class="layui-input"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label for="L_dataUsername" class="layui-form-label">
                    用户名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_dataUsername" name="dataUsername" value="${source.dataUsername}" required
                           class="layui-input"/>
                </div>
            </div>
            <div class="layui-inline">
                <label for="L_dataPassword" class="layui-form-label">
                    密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="L_dataPassword" name="dataPassword" value="${source.dataPassword}"
                           class="layui-input"/>
                </div>
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
            var
                    flag = false;
            $.ajax({
                url: 'testDataSource',
                type: 'POST',
                data: data.field,
                async: false,
                success: function (result) {
                    if (!result.data) {
                        layer.alert("数据源校验失败！", {icon: 5});
                    } else {
                        flag = result.data;
                    }
                }
            });
            return flag;

        });


    });
</script>

</body>

</html>