Ext.onReady(function() {

	var store = new Ext.data.JsonStore({
		autoDestroy : true,
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

	var grid = CreateGrid(store, 10);

	store.load({
		callback : function() {
			console.log(store.getCount());
		}
	});

	grid.render('array_grid');

	window.store = store;
});