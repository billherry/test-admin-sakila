Ext.onReady(function() {

	var pageSize = 10;
	var store = new Ext.data.JsonStore({
		autoDestroy : true,
		remoteSort: true,
		totalProperty:'total',
		storeId : 'myStore',
		idProperty : 'actor_id',
		root : 'actors',
		proxy : new Ext.data.HttpProxy({
			method : 'GET',
			type : 'ajax',
			url : 'actors'
		}),
		fields : [ {
			name : 'actor_id',
			type : 'int'
		}, 'first_name', 'last_name', {
			name : 'last_update',
			type : 'date',
			dateformat : 'c'
		} ]		
	});
	
	var grid = CreateGrid(store, pageSize);	
	grid.getBottomToolbar().doRefresh();
	grid.render('array_grid');
	window.store = store;
	window.grid = grid;
});