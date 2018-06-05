$(document).ready(function () {
    var page1_jTreeView1_setting = {};
    var page1_jTreeView1_zNodes = [
        {
            name: '合同', url: '#', target: '_self', open: true,
            children: [
                {name: '合同管理', url: '/contract', target: '_self'},
            ]
        },
        {
            name: '项目', url: '#', target: '_self', open: true,
            children: [
                {name: '变更管理', url: '/project/alert', target: '_self'},
            ]
        },
    ]
    $.fn.zTree.init($('#page1_jTreeView1_ztree'), page1_jTreeView1_setting, page1_jTreeView1_zNodes);
})