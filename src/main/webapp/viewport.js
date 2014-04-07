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

	var accordion = new Ext.Panel({
		region : 'west',
		margins : '5 0 5 5',
		split : true,
		width : 210,
		layout : 'accordion',
		items : [ item]
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ accordion, {
			region : 'center',
			margins : '5 5 5 5',
			cls : 'empty',
			bodyStyle : 'background:#f1f1f1',
			items : grid
		}]
	});
	window.viewport = viewport;
	return viewport;
};