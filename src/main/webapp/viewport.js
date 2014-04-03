function createViewPort(grid) {

	var button = new Ext.Button({
		text : "Hello_btn",
		autoWidth : true
	});

	var item = new Ext.Panel({
		title : 'Accordion Item',
		html : '&lt;empty panel&gt;',
		cls : 'empty',
		items : [ button ]
	});
	var item2 = new Ext.Panel({
		title : 'Accordion Item',
		html : '&lt;empty panel&gt;',
		cls : 'empty'
	});

	var accordion = new Ext.Panel({
		region : 'west',
		margins : '5 0 5 5',
		split : true,
		width : 210,
		layout : 'accordion',
		items : [ item, item2 ]
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ accordion, {
			region : 'center',
			margins : '5 5 5 5',
			cls : 'empty',
			bodyStyle : 'background:#f1f1f1',
		}, {
			title : 'Sakila',
			region : 'center',
			id : 'gridArea',
			cmargins : '0 0 0 0',
			collapsible : false,
			autoScroll : true,
			xtype : 'panel',
			margins : '5 5 5 5',
			items : grid
		} ]
	});
	window.viewport = viewport;
	return viewport;
};