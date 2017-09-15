<html>
<head>
    <meta charset="UTF-8">
    <title>报表管理系统-欢迎界面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/xadmin.css">
</head>
<body>
<div class="x-body">
    <fieldset class="layui-elem-field">
        <legend>信息统计</legend>
        <div class="layui-field-box">
            <table class="layui-table" lay-even>
                <thead>
                <tr>
                    <th>统计</th>
                    <th>管理员</th>
                    <th>登录成功</th>
                    <th>登录失败</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>总数</td>
                    <#list informationStatistics as informationStatistic>
                        <td>${informationStatistic.allCount}</td>
                    </#list>
                </tr>
                <tr>
                    <td>今日</td>
                <#list informationStatistics as informationStatistic>
                    <td>${informationStatistic.todayCount}</td>
                </#list>

                </tr>
                <tr>
                    <td>昨日</td>
                <#list informationStatistics as informationStatistic>
                    <td>${informationStatistic.yesterdayCount}</td>
                </#list>

                </tr>
                <tr>
                    <td>本周</td>
                <#list informationStatistics as informationStatistic>
                    <td>${informationStatistic.weekCount}</td>
                </#list>

                </tr>
                <tr>
                    <td>本月</td>
                <#list informationStatistics as informationStatistic>
                    <td>${informationStatistic.monthCount}</td>
                </#list>
                </tr>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>
</body>
</html>