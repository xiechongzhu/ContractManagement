$(document).ready(function(){
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'json',
        url: '/contract/get',
        colModel:[
            {label:'合同编号', name:'number', index:'number', width:80, sortable:false},
            {label:'合同名称', name:'name', index:'name', width:80, sortable:false},
            {label:'合同状态', name:'status', index:'status', width:80, sortable:false,
                formatter:function(x, v, r){
                    switch (r['status']) {
                        case 0:return '正常';
                        case 1:return '超期';
                        case 2:return '已验收';
                    }
                }
            },
            {label:'密级', name:'classification', index:'classification', width:80, sortable:false,
                formatter:function(x, v, r){
                switch (r['classification']) {
                    case 0:return "非密";
                    case 1:return "秘密";
                }
                }},
            {label:'负责人', name:'leader', index:'leader', width:80, sortable:false},
            {label:'金额(万元)', name:'money', index:'money', width:80, sortable:false},
            {label:'是否提供发票', name:'needInvoice', index:'needInvoice', width:80, sortable:false,
                formatter:function(x, v, r){
                switch (r['needInvoice']){
                    case 0:return "否";
                    case 1:return "是";
                }
                }},
            {label:'归档交付时间', name:'filingTime', index:'filingTime', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'刻盘编号', name:'cdNumber', index:'cdNumber', width:80, sortable:false},
            {label:'合同时间', name:'requirementTimePlan', index:'requirementTimePlan', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'评审时间', name:'requirementTimeReal', index:'requirementTimeReal', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'付款金额(万元)', name:'requirementPayMoney', index:'requirementPayMoney', width:80, sortable:false},
            {label:'合同时间', name:'designTimePlan', index:'designTimePlan', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'评审时间', name:'designTimeReal', index:'designTimeReal', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'付款金额(万元)', name:'designPayMoney', index:'designPayMoney', width:80, sortable:false},
            {label:'合同时间', name:'testTimePlan', index:'testTimePlan', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'评审时间', name:'testTimeReal', index:'testTimeReal', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'付款金额(万元)', name:'testPayMoney', index:'testPayMoney', width:80, sortable:false},
            {label:'合同时间', name:'acceptanceTimePlan', index:'acceptanceTimePlan', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'评审时间', name:'acceptanceTimeReal', index:'acceptanceTimeReal', width:80, sortable:false,
                formatter:"date",formatoptions: {srcformat:'ISO8601Long',newformat:'Y-m-d'}},
            {label:'付款金额(万元)', name:'acceptancePayMoney', index:'acceptancePayMoney', width:80, sortable:false},
            {label:'未付金额(万元)', width:80, sortable:false,
                formatter:function(v, x, r){
                return r['money'] - r['requirementPayMoney'] - r['designPayMoney']
                    - r['testPayMoney'] - r['acceptancePayMoney'];
                }},
            {label:'是否延期', name:'isDelay', index:'isDelay', width:80, sortable:false,
                formatter:function(x, v, r){
                switch (r['isDelay']) {
                    case 0:return "否";
                    case 1:return "是";
                }
                }}
        ],
        height: 'auto',
        shrinkToFit:false,
        autoScroll: true,
        autowidth: false,
        autoheight: true,
        rowNum:20,
        rownumbers : true,
        viewrecords: true,
        pager: '#page1_jDataGrid1_pager',
        ondblClickRow: function(id){
            modify_item();
        }
    });
    jQuery('#page1_jDataGrid1_table').jqGrid('setGroupHeaders',
        {
            groupHeaders:[
                {startColumnName:'requirementTimePlan', numberOfColumns:3, titleText: '需求阶段'},
                {startColumnName:'designTimePlan', numberOfColumns: 3, titleText: '设计阶段'},
                {startColumnName:'testTimePlan', numberOfColumns: 3, titleText: '测试阶段'},
                {startColumnName:'acceptanceTimePlan', numberOfColumns: 3, titleText: '验收阶段'}
            ]
        });

    jQuery('#filingTime_input').datepicker();
    jQuery('#requirementTimePlan_input').datepicker();
    jQuery('#requirementTimeReal_input').datepicker();
    jQuery('#designTimePlan_input').datepicker();
    jQuery('#designTimeReal_input').datepicker();
    jQuery('#testTimePlan_input').datepicker();
    jQuery('#testTimeReal_input').datepicker();
    jQuery('#acceptanceTimePlan_input').datepicker();
    jQuery('#acceptanceTimeReal_input').datepicker();
    jQuery('#btn_ok').button();
    jQuery('#btn_cancel').button();
    jQuery('#search_start_date').datepicker();
    jQuery('#search_end_date').datepicker();

    page1_jContainer1_obj=$('#page1_jContainer1_container').layout({
		onresize:function(){
			page1_jContainer2_obj.resizeAll();
			page1_jContainer3_obj.resizeAll();},
		center__paneSelector:'.page1_jContainer1_center'
		,north__paneSelector:'.page1_jContainer1_north'
		,north__size:	88
		,north__spacing_open:	0
		,west__paneSelector:'.page1_jContainer1_west'
		,west__size:	200
		,maskIframesOnResize: true
	});
	
	page1_jContainer2_DataGrids=new Array("page1_jDataGrid1","49");
	page1_jContainer2_obj=$('#page1_jContainer2_container').layout({
		onresize:function(){
			Vjjq.resizegrid(page1_jContainer2_DataGrids);},
		center__paneSelector:'.page1_jContainer2_center'
		,north__paneSelector:'.page1_jContainer2_north'
		,north__size:	62
		,north__spacing_open:	0
		,maskIframesOnResize: true
	});
	
	
	page1_jContainer3_obj=$('#page1_jContainer3_container').layout({
		onresize:function(){},
		center__paneSelector:'.page1_jContainer3_center'
		,maskIframesOnResize: true
	});
    page1_jContainer1_obj.resizeAll();
});

function add_item() {
    var now = (new Date()).Format('yyyy-MM-dd');
    set_pop_dialog_value({
        status : 0,
        classification : 0,
        needInvoice : 0,
        money : 0,
        filingTime : now,
        requirementTimePlan : now,
        requirementTimeReal : now,
        requirementPayMoney : 0,
        designTimePlan : now,
        designTimeReal : now,
        designPayMoney : 0,
        testTimePlan : now,
        testTimeReal : now,
        testPayMoney : 0,
        acceptanceTimePlan : now,
        acceptanceTimeReal : now,
        acceptancePayMoney : 0,
        isDelay : 0
    });
    $('#item_dialog').attr('method', 'add');
    $('#item_dialog').dialog({
        modal: true,
        resizable:false,
        title: '添加合同',
        width: 900
    });
}
function modify_item() {
    var id = $('#page1_jDataGrid1_table').jqGrid('getGridParam','selrow');
    if(id == null) {
    	return;
	}
	$.ajax({
        url:'/contract/get/' + id,
        type:'get',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            set_pop_dialog_value(data);
            $('#item_dialog').attr('method', 'modify');
            $('#item_dialog').attr('contract_id', id);
            $('#item_dialog').dialog({
                modal: true,
                resizable:false,
                title: '修改合同',
                width: 900
            });
        },
        error: function () {
            messagebox('信息', '从服务器获取数据失败', 'error');
        }
    });
}

function search_item() {
    findContracts();
}

function pop_okClick() {
    var method = $('#item_dialog').attr('method');
    var id = $('#item_dialog').attr('contract_id');
    if(method == 'add') {
        ajax_add_contract();
    } else {
        ajax_modify_contract(id);
    }
}

function build_form_data(addItemInfo) {
    var formData = new FormData;
    formData.append('number', addItemInfo.number);
    formData.append('name', addItemInfo.name);
    formData.append('status', addItemInfo.status);
    formData.append('classification', addItemInfo.classification);
    formData.append('leader', addItemInfo.leader);
    formData.append('money', addItemInfo.money);
    formData.append('needInvoice', addItemInfo.needInvoice);
    formData.append('filingTime', addItemInfo.filingTime);
    formData.append('cdNumber', addItemInfo.cdNumber);
    formData.append('requirementTimePlan', addItemInfo.requirementTimePlan);
    formData.append('requirementTimeReal', addItemInfo.requirementTimeReal);
    formData.append('requirementPayMoney', addItemInfo.requirementPayMoney);
    formData.append('designTimePlan', addItemInfo.designTimePlan);
    formData.append('designTimeReal', addItemInfo.designTimeReal);
    formData.append('designPayMoney', addItemInfo.designPayMoney);
    formData.append('testTimePlan', addItemInfo.testTimePlan);
    formData.append('testTimeReal', addItemInfo.testTimeReal);
    formData.append('testPayMoney', addItemInfo.testPayMoney);
    formData.append('acceptanceTimePlan', addItemInfo.acceptanceTimePlan);
    formData.append('acceptanceTimeReal', addItemInfo.acceptanceTimeReal);
    formData.append('acceptancePayMoney', addItemInfo.acceptancePayMoney);
    formData.append('isDelay', addItemInfo.isDelay);
    return formData;
}

function ajax_add_contract() {
    var addItemInfo = get_pop_dialog_value();
    var formData = build_form_data(addItemInfo);
    $.ajax({
        url: "/contract/add",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '添加新合同信息成功', 'info', function () {
                window.location.reload();
            });
        },
        error: function () {
            messagebox('信息', '添加新合同信息失败', 'error');
        }
    });
}

function ajax_modify_contract(id) {
    var newValue = get_pop_dialog_value();
    var formData =build_form_data(newValue);
    $.ajax({
        url: "/contract/modify/" + id,
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function () {
            messagebox('信息', '合同信息修改成功', 'info',function () {
                window.location.reload();
                //$('#page1_jDataGrid1_table').jqGrid('setRowData', id, newValue);
            })
        },
        error: function () {
            messagebox('信息', '合同信息修改失败', 'error');
        }
    });
}

function findContracts() {
    var search_number = $('#search_number').val();
    var search_name = $('#search_name').val();
    var search_status = $('#search_status').val();
    var search_classification = $('#search_classification').val();
    var search_leader = $('#search_leader').val();
    var search_startDate = $('#search_start_date').val();
    var search_endDate = $('#search_end_date').val();
    var search_isDelay = $('#search_isDelay').val();
    $('#page1_jDataGrid1_table').jqGrid('setGridParam',{
        postData:{number:search_number,
            name:search_name,
            status:search_status,
            classification:search_classification,
            leader:search_leader,
            startDate:search_startDate,
            endDate:search_endDate,
            isDelay:search_isDelay
        }
    }).trigger("reloadGrid");
}

function pop_cancelClick() {
    $('#item_dialog').dialog('close');
}

function set_pop_dialog_value(value) {
    console.log(value);
    $('#number_input').val(value.number);
    $('#name_input').val(value.name);
    $('#status_select').val(value.status);
    $('#classification_select').val(value.classification);
    $('#leader_input').val(value.leader);
    $('#money_input').val(value.money);
    $('#needInvoice_select').val(value.needInvoice);
    $('#filingTime_input').val(value.filingTime);
    $('#cdNumber_input').val(value.cdNumber);
    $('#requirementTimePlan_input').val(value.requirementTimePlan);
    $('#requirementTimeReal_input').val(value.requirementTimeReal);
    $('#requirementPayMoney_input').val(value.requirementPayMoney);
    $('#designTimePlan_input').val(value.designTimePlan);
    $('#designTimeReal_input').val(value.designTimeReal);
    $('#designPayMoney_input').val(value.designPayMoney);
    $('#testTimePlan_input').val(value.testTimePlan);
    $('#testTimeReal_input').val(value.testTimeReal);
    $('#testPayMoney_input').val(value.testPayMoney);
    $('#acceptanceTimePlan_input').val(value.acceptanceTimePlan);
    $('#acceptanceTimeReal_input').val(value.acceptanceTimeReal);
    $('#acceptancePayMoney_input').val(value.acceptancePayMoney);
    $('#isDelay_select').val(value.isDelay);
}

function get_pop_dialog_value() {
    return {
        number : $('#number_input').val(),
        name : $('#name_input').val(),
        status: $('#status_select').val(),
        classification : $('#classification_select').val(),
        leader : $('#leader_input').val(),
        money : $('#money_input').val()=='' ? 0:$('#money_input').val(),
        needInvoice : $('#needInvoice_select').val(),
        filingTime : $('#filingTime_input').val(),
        cdNumber : $('#cdNumber_input').val(),
        requirementTimePlan : $('#requirementTimePlan_input').val(),
        requirementTimeReal : $('#requirementTimeReal_input').val(),
        requirementPayMoney : $('#requirementPayMoney_input').val()=='' ? 0:$('#requirementPayMoney_input').val(),
        designTimePlan : $('#designTimePlan_input').val(),
        designTimeReal : $('#designTimeReal_input').val(),
        designPayMoney : $('#designPayMoney_input').val()=='' ? 0:$('#designPayMoney_input').val(),
        testTimePlan : $('#testTimePlan_input').val(),
        testTimeReal : $('#testTimeReal_input').val(),
        testPayMoney : $('#testPayMoney_input').val()=='' ? 0:$('#testPayMoney_input').val(),
        acceptanceTimePlan : $('#acceptanceTimePlan_input').val(),
        acceptanceTimeReal : $('#acceptanceTimeReal_input').val(),
        acceptancePayMoney : $('#acceptancePayMoney_input').val()=='' ? 0:$('#acceptancePayMoney_input').val(),
        isDelay : $('#isDelay_select').val()
    };
}
