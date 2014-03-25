Ext.onReady(function() {

	var store = new Ext.data.JsonStore({
		autoDestroy : true,
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

	var pageSize = 10;
	var grid = CreateGrid(store, pageSize);

	store.load({		
		callback : function() {
			console.log(store.getCount());			
		},
		params:{start:0,limit:pageSize}
	});

	grid.render('array_grid');
	
	window.store = store;
	window.grid = grid;
});