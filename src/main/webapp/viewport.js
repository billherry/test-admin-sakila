	function createViewPort(grid) {	
		var item1 = new Ext.Panel({
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
			items : [ accordion, grid,grid.getForm()]
		});
		window.viewport = viewport;
		return viewport;
	}