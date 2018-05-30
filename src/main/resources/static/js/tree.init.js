$(document).ready(function(){
	var page1_jTreeView1_setting = {
	};
	var page1_jTreeView1_zNodes =[
		{ name:'合同', url:'#', target:'_self', open: true,
			children: [
			{ name:'合同管理', url:'/contract', target:'_self'},
			{ name:'办公用品管理制度'}
			]},
		{ name:'技术开发'},
		{ name:'智力管理', url:'#', target:'_top'}
	]
	$.fn.zTree.init($('#page1_jTreeView1_ztree'), page1_jTreeView1_setting, page1_jTreeView1_zNodes);
})