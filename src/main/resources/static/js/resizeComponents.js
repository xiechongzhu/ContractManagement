var Vjjq={	
	jqgridobj:[],
	tabcontrolobj:[],
	accordionobj:[]	
}
Vjjq.resizeall=function(){

	var jqdg,jqdg_table,tabpage,tabs,jqparent;
	for(var i=0;i<Vjjq.tabcontrolobj.length;i++){
		tabpage=Vjjq.tabcontrolobj[i];
		$("#"+tabpage+"_body").tabs("refresh");
	}	
	for(var i=0;i<Vjjq.accordionobj.length;i++){
		tabpage=Vjjq.accordionobj[i];
		$("#"+tabpage+"_body").accordion("refresh");
	}
	if (Vjjq.jqgridobj.length > 0){
		for(var i=0;i<Vjjq.jqgridobj.length;i=i+2){
			jqdg=Vjjq.jqgridobj[i];
			jqdg_table=jqdg+"_table";
			$("#"+jqdg_table).setGridWidth($("#"+jqdg).width());
		  $("#"+jqdg_table).setGridHeight(($("#"+jqdg).height() - Vjjq.jqgridobj[i+1]));
			$("#"+jqdg_table).setGridWidth($("#"+jqdg).width());
		}
	}
}	

Vjjq.refreshContainer=function(Containertab,Containeracc){
	for(var i=0;i<Containertab.length;i++){
		$('#'+Containertab[i]+"_body").tabs( "refresh" );	
	}
	for(var i=0;i<Containeracc.length;i++){
		$('#'+Containeracc[i]+"_body").accordion( "refresh" );	
	}
}

Vjjq.resizegrid=function(jqdgarray){		
	var jqdg,jqdg_table,jqdgpage,tabpage,tabs,jqparent;
	for(var i=0;i<jqdgarray.length;i=i+2){
		jqdg=jqdgarray[i];
		jqdg_table=jqdg+"_table";
		$("#"+jqdg_table).setGridWidth($("#"+jqdg).width());			
		$("#"+jqdg_table).setGridHeight(($("#"+jqdg).height()-jqdgarray[i+1]));
		$("#"+jqdg_table).setGridWidth($("#"+jqdg).width()); 
	}
}