Ext.onReady(function() {

	var pageSize = 10;
	var store = new Ext.data.JsonStore({
		autoDestroy : true,
		remoteSort: true,
		totalProperty:'total',
		storeId : 'myStore',
		idProperty : 'actor_id',
		autoLoad: {params : {start:0, limit: 10}}, //csak a külön load helyett
		root : 'actors',
		params :{start:0,limit:pageSize},
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
	grid.render('array_grid');
	window.store = store;
	window.grid = grid;
});