$(document).ready(function () {

    context.init({preventDoubleContext: false});
    context.attach('.layui-tab-title li', [
        {
            text: '刷新', action: function (e) {
            if ($(openMenuTarget).hasClass("layui-this")) {
                var iframe = $("iframe[tab-id$='" + layId + "']");
                iframe[0].contentWindow.location.reload(true);
            } else {
                layer.alert('只能在当前选中节点刷新', {icon: 5});
            }
        }
        },
        {divider: true},
        {
            text: '关闭', action: function (e) {
            if (openMenuTarget) {
                element.tabDelete('xbs_tab', $(openMenuTarget).attr("lay-id"));
            }
        }
        },
        {
            text: '全部关闭', action: function (e) {
            if (openMenuTarget) {
                $(openMenuTarget).siblings().each(function () {
                    element.tabDelete('xbs_tab', $(this).attr("lay-id"));
                });
                element.tabDelete('xbs_tab', $(openMenuTarget).attr("lay-id"));
            }
        }
        },
        {
            text: '除此之外全部关闭', action: function (e) {
            if (openMenuTarget) {
                $(openMenuTarget).siblings().each(function () {
                    element.tabDelete('xbs_tab', $(this).attr("lay-id"));
                });
            }
        }
        },
        {
            text: '当前页右侧全部关闭', action: function (e) {
            if (openMenuTarget) {
                $(openMenuTarget).nextAll().each(function () {
                    element.tabDelete('xbs_tab', $(this).attr("lay-id"));
                });
            }
        }
        },
        {
            text: '当前页左侧全部关闭', action: function (e) {
            if (openMenuTarget) {
                $(openMenuTarget).prevAll().each(function () {
                    element.tabDelete('xbs_tab', $(this).attr("lay-id"));
                });
            }
        }
        },
        {divider: true},
        {text: '退出', href: '#'}
    ]);
    $(document).on('mouseover', '.me-codesta', function () {
        $('.finale h1:first').css({opacity: 0});
        $('.finale h1:last').css({opacity: 1});
    });

    $(document).on('mouseout', '.me-codesta', function () {
        $('.finale h1:last').css({opacity: 0});
        $('.finale h1:first').css({opacity: 1});
    });


});