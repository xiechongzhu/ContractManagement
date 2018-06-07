$(document).ready(function () {
    var page1_jTreeView1_setting = {};
    var page1_jTreeView1_zNodes = [
        {
            name: '合同', url: '#', target: '_self', open: true,
            children: [
                {name: '合同管理', url: '/contract', target: '_self'}
            ]
        },
        {
            name: '项目', url: '#', target: '_self', open: true,
            children: [
                {name: '项目管理', url: '/projectmanagement', target: '_self'},
                {name: '变更管理', url: '/project/alert', target: '_self'}
            ]
        },
        {
            name: '资产管理', url: '#' ,target: '_self', open: true,
            children: [
                {name: 'Fusion框架', url: '/asset/fusion', target: '_self'},
                {name: '通用插件', url: '/asset/plugins', target: '_self'}
            ]
        },
        {
            name: '文档模板下载', url: '#' ,target: '_self', open: true,
            children: [
                {name: '技术通知单', url: '/word-template/requisition', target: '_self'},
                {name: '变更申请分析及确认单', url: '/word-template/confirm', target: '_self'}
            ]
        }
    ]
    $.fn.zTree.init($('#page1_jTreeView1_ztree'), page1_jTreeView1_setting, page1_jTreeView1_zNodes);
})