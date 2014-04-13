actorFilter = new Ext.ux.grid.GridFilters({
			filters : [ {
				type : 'numeric',
				dataIndex : 'actor_id'
			}, {
				type : 'string',
				dataIndex : 'first_name',
				disable : true
			}, {
				type : 'string',
				dataIndex : 'last_name',
				disable : true
			}, {
				type : 'date',
				dataIndex : 'last_update'
			} ]
		});
