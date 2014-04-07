function createViewPort(grid) {
	var editform2 = createForm("12","","","");
	
	var item1 =new Ext.Panel({
		title : 'First',	
	});
	
	var item2 =new Ext.Panel({
		title : 'Second',	
	});

	var accordion = new Ext.Panel({
		region : 'west',
		split : true,
		width : 210,
		title : 'Actions',
		layout:'accordion',
		items : [item1, item2]
	});
	
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ accordion, grid,editform2 ]
	});
	window.viewport = viewport;
	return viewport;
};