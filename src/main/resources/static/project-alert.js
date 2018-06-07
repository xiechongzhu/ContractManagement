$(document).ready(function () {
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'json',
        url: '/project/alert/get',
        colModel: [
            {label: '项目名称', name: 'contractName', index: 'contractName', width: 150, sortable: false},
            {label: '技术通知单号', name: 'alertNumber', index: 'alertNumber', width: 120, sortable: false},
            {
                label: '技术通知单文件', name: 'alertFile', index: 'alertFile', width: 120, sortable: false,
                formatter: function (x, v, r) {
                    var wordName = r['alertFile'];
                    if (wordName != '') {
                        var wordLink = '\'/download-project-alert/' + wordName + '\'';
                        var pdfLink = '\'/pdf.js/web/viewer.html?file=/view-project-alert/' + wordName.split('.')[0] + '.pdf\'';
                        console.log(pdfLink);
                        return '<a href=' + wordLink + ' style=\'color:blue\'>下载</a><span>  </span><a href='
                            + pdfLink + ' style=\'color:blue\'  target=\'_blank\'>预览</a>';
                    } else {
                        return '<a style=\'color:red\'>未上传</a>';
                    }
                }
            },
            {
                label: '变更日期', name: 'alertDate', index: 'alertDate', width: 80, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
            {label: '确认分析单号', name: 'confirmNumber', index: 'confirmNumber', width: 120, sortable: false},
            {
                label: '确认分析单文件', name: 'confirmFile', index: 'confirmFile', width: 120, sortable: false,
                formatter: function (x, v, r) {
                    var wordName = r['confirmFile'];
                    if (wordName != '') {
                        var wordLink = '\'/download-project-alert/' + wordName + '\'';
                        var pdfLink = '\'/pdf.js/web/viewer.html?file=/view-project-alert/' + wordName.split('.')[0] + '.pdf\'';
                        console.log(pdfLink);
                        return '<a href=' + wordLink + ' style=\'color:blue\'>下载</a><span>  </span><a href='
                            + pdfLink + ' style=\'color:blue\'  target=\'_blank\'>预览</a>';
                    } else {
                        return '<a style=\'color:red\'>未上传</a>';
                    }
                }
            },
            {
                label: '确认日期', name: 'confirmDate', index: 'confirmDate', width: 80, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
        ],
        height: 'auto',
        shrinkToFit: false,
        autoScroll: true,
        autowidth: false,
        autoheight: true,
        rowNum: 20,
        rownumbers: true,
        viewrecords: true,
        pager: '#page1_jDataGrid1_pager',
        ondblClickRow: function (id) {
            modify_item();
        }
    });

    jQuery('#alert_date_input').datepicker();
    jQuery('#confirm_date_input').datepicker();
    jQuery('#upload_alert_file').button();
    jQuery('#upload_confirm_file').button();
    jQuery('#btn_ok').button();
    jQuery('#btn_cancel').button();
    jQuery('#file_alert_upload').change(alert_upload_file_change);
    jQuery('#file_confirm_upload').change(confirm_upload_file_change);

    $.ajax({
        url: '/contract/get-all',
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; ++i) {
                var item = data[i];
                var option = $('<option>').val(item.id).text(item.name);
                $('#project_select').append(option);
            }
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

function add_item() {
    set_pop_dialog_value({
        alertNumber: '',
        alertFile: '',
        alertDate: '',
        confirmNumber: '',
        confirmFile: '',
        confirmDate: ''

    });
    $('#item_dialog').attr('method', 'add');
    $('#item_dialog').dialog({
        modal: true,
        resizable: false,
        title: '新增变更',
        width: 810
    });
}

function modify_item() {
    var id = $('#page1_jDataGrid1_table').jqGrid('getGridParam', 'selrow');
    if (id == null) {
        return;
    }
    $.ajax({
        url: '/project/alert/get/' + id,
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            set_pop_dialog_value(data);
            $('#item_dialog').attr('method', 'modify');
            $('#item_dialog').attr('project_alert_id', id);
            $('#item_dialog').dialog({
                modal: true,
                resizable: false,
                title: '修改变更',
                width: 810
            });
        },
        error: function () {
            messagebox('信息', '从服务器获取数据失败', 'error');
        }
    });
}

function search_item() {
    findProjectAlert();
}

function pop_okClick() {
    var method = $('#item_dialog').attr('method');
    var id = $('#item_dialog').attr('project_alert_id');
    if (method == 'add') {
        ajax_add_project_alert();
    } else {
        ajax_modify_project_alert(id);
    }
}

function build_form_data(addItemInfo) {
    var formData = new FormData;
    formData.append('contractId', addItemInfo.contractId);
    formData.append('alertNumber', addItemInfo.alertNumber);
    formData.append('alertFile', addItemInfo.alertFile);
    formData.append('alertDate', addItemInfo.alertDate);
    formData.append('confirmNumber', addItemInfo.confirmNumber);
    formData.append('confirmFile', addItemInfo.confirmFile);
    formData.append('confirmDate', addItemInfo.confirmDate);
    return formData;
}

function ajax_add_project_alert() {
    var addItemInfo = get_pop_dialog_value();
    var formData = build_form_data(addItemInfo);
    $.ajax({
        url: '/project/alert/add',
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '添加项目变更信息成功', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
                $('#item_dialog').dialog('close');
            });
        },
        error: function () {
            messagebox('信息', '添加项目变更信息失败', 'error');
        }
    });
}

function ajax_modify_project_alert(id) {
    var newValue = get_pop_dialog_value();
    console.log(newValue);
    var formData = build_form_data(newValue);
    $.ajax({
        url: "/project/alert/modify/" + id,
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '变更信息修改成功', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
                $('#item_dialog').dialog('close');
            })
        },
        error: function () {
            messagebox('信息', '变更信息修改失败', 'error');
        }
    });
}

function findProjectAlert() {
    var contractName = $('#search_project').val();
    var alertType = $('#search_alert').val();
    var confirmType = $('#search_confirm').val();

    $('#page1_jDataGrid1_table').jqGrid('setGridParam', {
        postData: {
            contractName: contractName,
            alertType: alertType,
            confirmType: confirmType
        }
    }).trigger("reloadGrid");
}

function pop_cancelClick() {
    $('#item_dialog').dialog('close');
}

function set_pop_dialog_value(value) {
    var count = $('#project_select option').length;
    for (var i = 0; i < count; i++) {
        if ($('#project_select').get(0).options[i].text == value.contractName) {
            $('#project_select').get(0).options[i].selected = true;
            break;
        }
    }
    $('#alert_number_input').val(value.alertNumber);
    $('#alert_file_input').val(value.alertFile);
    $('#alert_date_input').val(value.alertDate);
    $('#confirm_number_input').val(value.confirmNumber);
    $('#confirm_file_input').val(value.confirmFile);
    $('#confirm_date_input').val(value.confirmDate);
}

function get_pop_dialog_value() {
    return {
        contractId: $('#project_select').val(),
        alertNumber: $('#alert_number_input').val(),
        alertFile: $('#alert_file_input').val(),
        alertDate: $('#alert_date_input').val(),
        confirmNumber: $('#confirm_number_input').val(),
        confirmFile: $('#confirm_file_input').val(),
        confirmDate: $('#confirm_date_input').val()
    };
}

function upload_alert_file() {
    $('#file_alert_upload').click();
}

function upload_confirm_file() {
    $('#file_confirm_upload').click();
}

function alert_upload_file_change() {
    if ($("#file_alert_upload").val() == '') {
        return;
    }
    var formData = new FormData;
    formData.append("file", $("#file_alert_upload")[0].files[0]);
    $.ajax({
        url: "/upload-project-alert",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            $("#file_alert_upload").val('');
            $('#alert_file_input').val(data);
        },
        error: function () {
            $("#file_alert_upload").val('');
            messagebox('信息', '上传文件失败!', 'error');
        }
    });
}

function confirm_upload_file_change() {
    if ($('#file_confirm_upload').val() == '') {
        return;
    }
    var formData = new FormData;
    formData.append("file", $("#file_confirm_upload")[0].files[0]);
    $.ajax({
        url: "/upload-project-alert",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            $('#file_confirm_upload').val('');
            $('#confirm_file_input').val(data);
        },
        error: function () {
            $('#file_confirm_upload').val('');
            messagebox('信息', '上传文件失败!', 'error');
        }
    });
}