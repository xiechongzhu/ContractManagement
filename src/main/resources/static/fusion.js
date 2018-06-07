$(document).ready(function () {
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'json',
        url: '/asset/fusion/get',
        colModel: [
            {label: '版本', name: 'version', index: 'version', width: 150, sortable: false},
            {label: '平台', name: 'platform', index: 'platform', width: 120, sortable: false, formatter: function (x, v, r) {
                    switch (r['platform']) {
                        case 1:
                            return "Windows";
                        case 2:
                            return "中标麒麟";
                        default:
                            return "未知";
                    }
                }},
            {
                label: '发布日期', name: 'uploadDate', index: 'uploadDate', width: 80, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
            {
                label: '文件', name: 'fileName', index: 'fileName', width: 80, sortable: false,
                formatter: function (x, v, r) {
                    var fileName = r['fileName'];
                    if (fileName != '') {
                        var wordLink = '\'/download-fusion/' + fileName + '\'';
                        return '<a href=' + wordLink + ' style=\'color:blue\'>下载</a>';
                    } else {
                        return '<a style=\'color:red\'>未上传</a>';
                    }
                }
            },
            {label: 'MD5码', name: 'md5', index: 'md5', width: 240, sortable: false}
        ],
        height: 'auto',
        shrinkToFit: false,
        autoScroll: true,
        autowidth: false,
        autoheight: true,
        rowNum: 20,
        rownumbers: true,
        viewrecords: true,
        pager: '#page1_jDataGrid1_pager'
    });

    jQuery('#btn_ok').button();
    jQuery('#btn_cancel').button();
    jQuery('#file_upload').change(upload_file_change);

    page1_jContainer1_obj = $('#page1_jContainer1_container').layout({
        onresize: function () {
            page1_jContainer2_obj.resizeAll();
            page1_jContainer3_obj.resizeAll();
        },
        center__paneSelector: '.page1_jContainer1_center'
        , north__paneSelector: '.page1_jContainer1_north'
        , north__size: 88
        , north__spacing_open: 0
        , west__paneSelector: '.page1_jContainer1_west'
        , west__size: 200
        , maskIframesOnResize: true
    });

    page1_jContainer2_DataGrids = new Array("page1_jDataGrid1", "49");
    page1_jContainer2_obj = $('#page1_jContainer2_container').layout({
        onresize: function () {
            Vjjq.resizegrid(page1_jContainer2_DataGrids);
        },
        center__paneSelector: '.page1_jContainer2_center'
        , north__paneSelector: '.page1_jContainer2_north'
        , north__size: 31
        , north__spacing_open: 0
        , maskIframesOnResize: true
    });


    page1_jContainer3_obj = $('#page1_jContainer3_container').layout({
        onresize: function () {
        },
        center__paneSelector: '.page1_jContainer3_center'
        , maskIframesOnResize: true
    });
    page1_jContainer1_obj.resizeAll();
});

function add_item() {
    set_pop_dialog_value({
        version: '',
        platform: 1,
        fileName: ''
    });
    $('#item_dialog').attr('method', 'add');
    $('#item_dialog').dialog({
        modal: true,
        resizable: false,
        title: '新增',
        width: 730
    });
}

function search_item() {
    findFusion();
}

function pop_okClick() {
    var method = $('#item_dialog').attr('method');
    var id = $('#item_dialog').attr('project_alert_id');
    if (method == 'add') {
        ajax_add_fusion();
    } else {
        ajax_modify_fusion(id);
    }
}

function build_form_data(addItemInfo) {
    var formData = new FormData;
    formData.append('version', addItemInfo.version);
    formData.append('platform', addItemInfo.platform);
    formData.append('fileName', addItemInfo.fileName);
    return formData;
}

function ajax_add_fusion() {
    var addItemInfo = get_pop_dialog_value();
    var formData = build_form_data(addItemInfo);
    $.ajax({
        url: '/asset/fusion/add',
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '添加Fusion框架版本信息成功', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
                $('#item_dialog').dialog('close');
            });
        },
        error: function () {
            messagebox('信息', '添加Fusion框架版本信息失败', 'error');
        }
    });
}

function ajax_modify_fusion(id) {
}

function findFusion() {
    var version = $('#search_version').val();
    var platform = $('#search_platform').val();

    $('#page1_jDataGrid1_table').jqGrid('setGridParam', {
        postData: {
            version: version,
            platform: platform
        }
    }).trigger("reloadGrid");
}

function pop_cancelClick() {
    $('#item_dialog').dialog('close');
}

function set_pop_dialog_value(value) {
    $('#version_input').val(value.version);
    $('#platform_select').val(value.platform);
    $('#file_input').val(value.fileName);
}

function get_pop_dialog_value() {
    return {
        version: $('#version_input').val(),
        platform: $('#platform_select').val(),
        fileName: $('#file_input').val(),
    };
}

function upload_file() {
    $('#file_upload').click();
}

function upload_file_change() {
    if ($("#file_upload").val() == '') {
        return;
    }
    var formData = new FormData;
    formData.append('file', $('#file_upload')[0].files[0]);
    $.ajax({
        url: "/upload-fusion",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            $("#file_upload").val('');
            $('#file_input').val(data);
        },
        error: function () {
            $("#file_upload").val('');
            messagebox('信息', '上传文件失败!', 'error');
        }
    });
}