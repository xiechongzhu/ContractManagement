$(document).ready(function () {
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'json',
        url: '/project/get',
        colModel: [
            {label: '项目名称', name: 'project_name', index: 'project_name', width: 150, sortable: false},
            {label: '合同名称', name: 'contract_number', index: 'contract_number', width: 150, sortable: false},
            {label: '项目状态', name: 'project_status', index: 'project_status', width: 150, sortable: false,
                formatter: function (x, v, r) {
                    switch (r['project_status']) {
                        case 0:
                            return '正常';
                        case 1:
                            return '暂停';
                        case 0:
                            return '已验收';
                        case 1:
                            return '结项';
                    }
                }
            },
            {
                label: '密级', name: 'project_classification', index: 'project_classification', width: 150, sortable: false,
                formatter: function (x, v, r) {
                    switch (r['project_classification']) {
                        case 0:
                            return '非密';
                        case 1:
                            return '秘密';
                    }
                }
            },
            {label: '评审阶段', name: 'project_phases', index: 'project_phases', width: 150, sortable: false,
                formatter: function (x, v, r) {
                    switch (r['project_phases']) {
                        case 0:
                            return '需求阶段';
                        case 1:
                            return '设计阶段';
                        case 2:
                            return '测试阶段';
                        case 3:
                            return '验收阶段';
                    }
                }
            },
            {label: '阶段状态', name: 'project_phasesstauts', index: 'project_phasesstauts', width: 150, sortable: false,
                formatter: function (x, v, r) {
                    switch (r['project_phasesstauts']) {
                        case 0:
                            return '未评审';
                        case 1:
                            return '评审通过，待修改';
                        case 2:
                            return '评审通过，已修改';
                    }
                }
            },
            {
                label: '更新时间', name: 'update_time', index: 'update_time', width: 80, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },

            {label: '甲方单位', name: 'partyA_unit', index: 'partyA_unit', width: 150, sortable: false},
            {label: '甲方接口人', name: 'partyA_infpeople', index: 'partyA_infpeople', width: 150, sortable: false},
            {label: '乙方单位', name: 'partyB_unit', index: 'partyB_unit', width: 150, sortable: false},
            {label: '乙方接口人', name: 'partyB_infpeople', index: 'partyB_infpeople', width: 150, sortable: false},
            {
                label: '计划开始时间', name: 'project_planstarttime', index: 'project_planstarttime', width: 150, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
            {
                label: '计划结束时间', name: 'project_planendtime', index: 'project_planendtime', width: 150, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            },
            {
                label: '结项时间', name: 'project_realendtime', index: 'project_realendtime', width: 150, sortable: false,
                formatter: "date", formatoptions: {srcformat: 'ISO8601Long', newformat: 'Y-m-d'}
            }
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

    jQuery('#project_planstarttime').datepicker();
    jQuery('#project_planendtime').datepicker();
    jQuery('#btn_ok').button();
    jQuery('#btn_cancel').button();
    jQuery('#search_start_date').datepicker();
    jQuery('#search_end_date').datepicker();
    jQuery('#file').change(import_file_change);

    $.ajax({
        url: '/project/get-all',
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            console.log(data);
            var nameTags = new Array();
            var contractTags = new Array();
            var leaderTags = new Array();
            for (var i = 0; i < data.length; ++i) {
                var item = data[i];
                nameTags.push(item.project_name);
                contractTags.push(item.contract_number);
                leaderTags.push(item.partyA_infpeople);
                leaderTags.push(item.partyB_infpeople);
            }
            $('#search_number').autocomplete({
                source: nameTags
            });
            $('#search_name').autocomplete({
                source: contractTags
            });
            $('#search_leader').autocomplete({
                source: leaderTags
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
        , north__size: 62
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
    var now = (new Date()).Format('yyyy-MM-dd');
    set_pop_dialog_value({
        project_name:'',
        contract_number:'',
        project_status:0,
        project_classification:0,
        project_phases:0,
        project_phasesstauts:0,
        partyA_unit:'',
        partyA_infpeople:'',
        partyB_unit:'',
        partyB_infpeople:'',
        project_planstarttime:now,
        project_planendtime:now
    });
    $('#item_dialog').attr('method', 'add');
    $('#item_dialog').dialog({
        modal: true,
        resizable: false,
        title: '添加项目',
        width: 920
    });
}

function modify_item() {
    var id = $('#page1_jDataGrid1_table').jqGrid('getGridParam', 'selrow');
    if (id == null) {
        return;
    }
    $.ajax({
        url: '/project/get/' + id,
        type: 'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            set_pop_dialog_value(data);
            $('#item_dialog').attr('method', 'modify');
            $('#item_dialog').attr('project_id', id);
            $('#item_dialog').dialog({
                modal: true,
                resizable: false,
                title: '修改项目',
                width: 920
            });
        },
        error: function () {
            messagebox('信息', '从服务器获取数据失败', 'error');
        }
    });
}

function search_item() {
    findProjects();
}

function pop_okClick() {
    var method = $('#item_dialog').attr('method');
    var id = $('#item_dialog').attr('project_id');
    if (method == 'add') {
        ajax_add_project();
    } else {
        ajax_modify_project(id);
    }
}

function build_form_data(addItemInfo) {
    var formData = new FormData;
    formData.append('project_name', addItemInfo.project_name);
    formData.append('contract_number', addItemInfo.contract_number);
    formData.append('project_status', addItemInfo.project_status);
    formData.append('project_classification', addItemInfo.project_classification);
    formData.append('project_phases', addItemInfo.project_phases);
    formData.append('project_phasesstauts', addItemInfo.project_phasesstauts);
    formData.append('update_time', addItemInfo.update_time);
    formData.append('partyA_unit', addItemInfo.partyA_unit);
    formData.append('partyA_infpeople', addItemInfo.partyA_infpeople);
    formData.append('partyB_unit', addItemInfo.partyB_unit);
    formData.append('partyB_infpeople', addItemInfo.partyB_infpeople);
    formData.append('project_planstarttime', addItemInfo.project_planstarttime);
    formData.append('project_planendtime', addItemInfo.project_planendtime);
    return formData;
}

function ajax_add_project() {
    var addItemInfo = get_pop_dialog_value();
    var formData = build_form_data(addItemInfo);
    $.ajax({
        url: "/project/add",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '添加新项目信息成功', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
                $('#item_dialog').dialog('close');
            });
        },
        error: function () {
            messagebox('信息', '添加新项目信息失败', 'error');
        }
    });
}

function ajax_modify_project(id) {
    var newValue = get_pop_dialog_value();
    var formData = build_form_data(newValue);
    $.ajax({
        url: "/project/modify/" + id,
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '项目信息修改成功', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
                $('#item_dialog').dialog('close');
            })
        },
        error: function () {
            messagebox('信息', '项目信息修改失败', 'error');
        }
    });
}

function findProjects() {
    var project_name = $('#search_number').val();
    var contract_number = $('#search_name').val();
    var project_status = $('#search_status').val();
    var project_classification = $('#search_classification').val();
    var party_infpeople = $('#search_leader').val();
    var project_planstarttime = $('#search_start_date').val();
    var project_planendtime = $('#search_end_date').val();

    if(project_planendtime <project_planstarttime){
        alert("结束时间不能小于开始时间！");
        return;
    }
    $('#page1_jDataGrid1_table').jqGrid('setGridParam', {
        postData: {
            project_name: project_name,
            contract_number: contract_number,
            project_status: project_status,
            project_classification:project_classification,
            party_infpeople:party_infpeople,
            project_planstarttime: project_planstarttime,
            project_planendtime: project_planendtime
        }
    }).trigger("reloadGrid");
}

function pop_cancelClick() {
    $('#item_dialog').dialog('close');
}

function set_pop_dialog_value(value) {
    console.log(value);
    $('#project_name').val(value.project_name);
    $('#contract_number').val(value.contract_number);
    $('#project_status').val(value.project_status);
    $('#project_classification').val(value.project_classification);
    $('#project_phases').val(value.project_phases);
    $('#project_phasesstauts').val(value.project_phasesstauts);
    $('#partyA_unit').val(value.partyA_unit);
    $('#partyA_infpeople').val(value.partyA_infpeople);
    $('#partyB_unit').val(value.partyB_unit);
    $('#project_planstarttime').val(value.project_planstarttime);
    $('#project_planendtime').val(value.project_planendtime);
    $('#partyB_infpeople').val(value.partyB_infpeople);
}

function get_pop_dialog_value() {
    return {
        project_name: $('#project_name').val(),
        contract_number: $('#contract_number').val(),
        project_status: $('#project_status').val(),
        project_classification: $('#project_classification').val(),
        project_phases: $('#project_phases').val(),
        project_phasesstauts: $('#project_phasesstauts').val(),
        partyA_unit: $('#partyA_unit').val(),
        partyA_infpeople: $('#partyA_infpeople').val(),
        partyB_unit: $('#partyB_unit').val(),
        partyB_infpeople: $('#partyB_infpeople').val(),
        project_planstarttime: $('#project_planstarttime').val(),
        project_planendtime: $('#project_planendtime').val()
    };
}

function export_excel() {
    window.location.href = "/project/export";
}

function import_excel() {
    $('#file').click();
}

function import_file_change() {
    var formData = new FormData;
    formData.append("file", $("#file")[0].files[0]);
    $.ajax({
        url: "/project/import",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '导入项目信息成功!', 'info', function () {
                $('#page1_jDataGrid1_table').trigger('reloadGrid');
            });

        },
        error: function (data) {
            console.log(data)
            messagebox('信息', '导入项目信息失败!', 'error');
        }
    });
    $("#file").val('');
}