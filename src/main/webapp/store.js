context.store = new Ext.data.JsonStore({
		autoDestroy : true,
		remoteSort: true,
		totalProperty:'total',
		storeId : 'myStore',
		idProperty : 'actor_id',
		root : 'items',
		proxy : new Ext.data.HttpProxy({
			method : 'GET',
			type : 'ajax',
			url : 'actors/query'
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