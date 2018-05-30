$(document).ready(function(){
    jQuery('#page1_jDataGrid1_table').jqGrid({
        datatype: 'local',
        colModel:[
            {label:'合同编号', name:'number', index:'number', width:80},
            {label:'合同名称', name:'name', index:'name', width:80},
            {label:'执行状态', name:'status', index:'status', width:80},
            {label:'密级', name:'classification', index:'classification', width:80},
            {label:'负责人', name:'leader', index:'leader', width:80},
            {label:'金额(万元)', name:'money', index:'money', width:80},
            {label:'是否提供发票', name:'needInvoice', index:'needInvoice', width:80},
            {label:'归档交付时间', name:'filingTime', index:'filingTime', width:80},
            {label:'刻盘编号', name:'cdNumber', index:'cdNumber', width:80},
            {label:'合同时间', name:'requirementTimePlan', index:'requirementTimePlan', width:80},
            {label:'评审时间', name:'requirementTimeReal', index:'requirementTimeReal', width:80},
            {label:'付款金额(万元)', name:'requirementPayMoney', index:'requirementPayMoney', width:80},
            {label:'合同时间', name:'designTimePlan', index:'designTimePlan', width:80},
            {label:'评审时间', name:'designTimeReal', index:'designTimeReal', width:80},
            {label:'付款金额(万元)', name:'designPayMoney', index:'designPayMoney', width:80},
            {label:'合同时间', name:'testTimePlan', index:'testTimePlan', width:80},
            {label:'评审时间', name:'testTimeReal', index:'testTimeReal', width:80},
            {label:'付款金额(万元)', name:'testPayMoney', index:'testPayMoney', width:80},
            {label:'合同时间', name:'acceptanceTimePlan', index:'acceptanceTimePlan', width:80},
            {label:'评审时间', name:'acceptanceTimeReal', index:'acceptanceTimeReal', width:80},
            {label:'付款金额(万元)', name:'acceptancePayMoney', index:'acceptancePayMoney', width:80},
            {label:'未付金额(万元)', width:80},
            {label:'是否延期', name:'isDelay', index:'isDelay', width:80}
        ],
        height: 'auto',
        shrinkToFit:false,
        autoScroll: true,
        autowidth: false,
        autoheight: true,
        rowNum:20,
        rownumbers : false,
        viewrecords: true,
        pager: '#page1_jDataGrid1_pager',
        ondblClickRow: function(id){
            //双击行
            alert("You double click row with id: "+id);
            $("#page1_jDataGrid1_table").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll'});
            $("#page1_jDataGrid1_table").setGridHeight('150');
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
    jQuery('#btn_add').button();
    jQuery('#btn_cancel').button();

    page1_jContainer1_obj=$('#page1_jContainer1_container').layout({
		onresize:function(){
			page1_jContainer2_obj.resizeAll();
			page1_jContainer3_obj.resizeAll();},
		center__paneSelector:'.page1_jContainer1_center'
		,north__paneSelector:'.page1_jContainer1_north'
		,north__size:	88
		,north__spacing_open:	0
		,west__paneSelector:'.page1_jContainer1_west'
		,west__size:	264
		,maskIframesOnResize: true
	});
	
	page1_jContainer2_DataGrids=new Array("page1_jDataGrid1","49");
	page1_jContainer2_obj=$('#page1_jContainer2_container').layout({
		onresize:function(){
			Vjjq.resizegrid(page1_jContainer2_DataGrids);},
		center__paneSelector:'.page1_jContainer2_center'
		,north__paneSelector:'.page1_jContainer2_north'
		,north__size:	31
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

function addItem() {
    $('#item_dialog').dialog({
        modal: true,
        resizable:false,
        width: 900
    });
}
function modifyItem() {
    var id = $('#page1_jDataGrid1_table').jqGrid('getGridParam','selrow');
    if(id == null) {
    	return;
	}
	alert('你选中了:' + id);
}
