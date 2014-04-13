actorStore = new Ext.data.JsonStore({
		autoDestroy : true,
		remoteSort: true,
		totalProperty:'total',
		storeId : 'myStore',
		idProperty : 'actor_id',
		root : 'items',
        writer: {
            type: 'json',
            writeAllFields: false,
        },
		proxy : new Ext.data.HttpProxy({
			method : 'GET',
			type : 'ajax',
			api:{
		        read : 'actors/select',
		        create : 'actors/create',
		        update: 'actors/update',
		        destroy: 'actors/delete'
			}
		}),
		fields : [ {
			name : 'actor_id',
			type : 'int'
		}, 'first_name', 'last_name', {
			name : 'last_update',
			type : 'date'
		} ]		
	});