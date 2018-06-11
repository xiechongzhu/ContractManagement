$(document).ready(function () {
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'json',
        url: '/asset/plugins/get',
        colModel: [
            {label: '名称', name: 'name', index: 'name', width: 150, sortable: false},
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
                }
            },
            {label: 'Fusion版本', name: 'fusionVersion', index: 'fusionVersion', width: 120, sortable: false},
            {
                label: '发布日期', name: 'uploadDate', index: 'uploadDate', width: 80, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
            {
                label: '文件', name: 'fileName', index: 'fileName', width: 80, sortable: false,
                formatter: function (x, v, r) {
                    var fileName = r['fileName'];
                    if (fileName != '') {
                        var wordLink = '\'/download-plugin/' + fileName + '\'';
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
        rowList: [5, 10, 20, 50, 100],
        rownumbers: true,
        viewrecords: true,
        pager: '#page1_jDataGrid1_pager'
    });

    jQuery('#btn_ok').button();
    jQuery('#btn_cancel').button();
    jQuery('#platform_select').change(platform_change);
    jQuery('#file_upload').change(upload_file_change);

    $.ajax({
        url: '/asset/plugins/get-all',
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            var nameTags = new Array();
            var versionTags = new Array();
            for (var i = 0; i < data.length; ++i) {
                var item = data[i];
                nameTags.push(item.name);
                versionTags.push(item.version);
            }
            $('#search_name').autocomplete({
                source: nameTags
            });
            $('#search_version').autocomplete({
                source: versionTags
            });
        }
    });

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

function getFusionVersion(platform) {
    var versionList = new Array();
    $.ajax({
        url: '/asset/fusion/get-by-platform/' + platform,
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            for(var i = 0; i < data.length; ++i) {
                versionList.push(data[i].version);
            }
        }
    })
    return versionList;
}

function initFusionSelect(fusionVersionList) {
    $('#fusion_select').empty();
    for(var i = 0; i < fusionVersionList.length; ++i) {
        var version = fusionVersionList[i];
        var option = $('<option>').val(version).text(version);
        $('#fusion_select').append(option);
    }
}

function add_item() {
    var platform = 1; //Windows
    var fusionList = getFusionVersion(platform);
    console.log(fusionList);
    set_pop_dialog_value({
        name: '',
        version: '',
        platform: 1,
        fusionVersion: fusionList,
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
    findPlugin();
}

function pop_okClick() {
    var method = $('#item_dialog').attr('method');
    var id = $('#item_dialog').attr('project_alert_id');
    if (method == 'add') {
        ajax_add_plugin();
    } else {
        ajax_modify_fusion(id);
    }
}

function build_form_data(addItemInfo) {
    var formData = new FormData;
    formData.append('name', addItemInfo.name);
    formData.append('version', addItemInfo.version);
    formData.append('platform', addItemInfo.platform);
    formData.append('fusionVersion', addItemInfo.fusionVersion);
    formData.append('fileName', addItemInfo.fileName);
    return formData;
}

function ajax_add_plugin() {
    var addItemInfo = get_pop_dialog_value();
    var formData = build_form_data(addItemInfo);
    $.ajax({
        url: '/asset/plugins/add',
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

function findPlugin() {
    var name = $('#search_name').val();
    var version = $('#search_version').val();
    var platform = $('#search_platform').val();

    $('#page1_jDataGrid1_table').jqGrid('setGridParam', {
        postData: {
            name: name,
            version: version,
            platform: platform
        }
    }).trigger("reloadGrid");
}

function pop_cancelClick() {
    $('#item_dialog').dialog('close');
}

function set_pop_dialog_value(value) {
    $('#name_input').val(value.name);
    $('#version_input').val(value.version);
    $('#platform_select').val(value.platform);
    initFusionSelect(value.fusionVersion);
    $('#file_input').val(value.fileName);
}

function get_pop_dialog_value() {
    return {
        name: $('#name_input').val(),
        version: $('#version_input').val(),
        platform: $('#platform_select').val(),
        fusionVersion: $('#fusion_select').val(),
        fileName: $('#file_input').val()
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
        url: "/upload-fusion-or-plugin/plugin",
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

function platform_change() {
    var fusionList = getFusionVersion($('#platform_select').val());
    initFusionSelect(fusionList);
}