<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../static/lib/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<table class="layui-hide" id="table_collection_structure"
       lay-filter="collectionStructure"></table>

<script type="text/javascript" src="../static/lib/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#table_collection_structure'
            ,
            cols: [[{title: '字段集合', rowspan: 2, align: 'center'}, {
                title: '字段集合',
                colspan: 5,
                align: 'center'
            }], [
                {field: 'COLUMN_NAME', title: '字段名称', align: 'center'}
                , {field: 'REMARKS', title: '字段描述', align: 'center'}
                , {field: 'TYPE_NAME', title: '字段类型', align: 'center'}
                , {field: 'COLUMN_SIZE', title: '字段长度', align: 'center'}
                , {field: 'DECIMAL_DIGITS', title: '字段精度', align: 'center'}
            ]]
            ,
            id: 'collectionStructure'
            ,
            height: 'auto',
            page: false
        });
    })
</script>
</div>
</body>
</html>