var item = new Ext.Panel({
	title : 'Sakila Tables',
	html : '&lt;empty panel&gt;',
	cls : 'empty'
});

var accordion = new Ext.Panel({
	region : 'west',
	margins : '5 0 5 5',
	split : true,
	width : 210,
	layout : 'accordion',
	items : [ item ]
});

Ext.onReady(function() {
		viewport = new Ext.Viewport({
		layout : 'border',
		items : [ accordion, actorGrid, actorForm ]
	});
});
