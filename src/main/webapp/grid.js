function CreateGrid(store, pageSize) {
	var grid = new Ext.grid.GridPanel({
		store : store,
		colModel : new Ext.grid.ColumnModel({
			defaults : {
				width : 100,
				sortable : true
			},
			columns : [ {
				id : 'actor_id',
				header : 'ID',
				width : 30,
				sortable : true,
				dataIndex : 'actor_id'
			}, {
				header : 'First Name',
				dataIndex : 'first_name'
			}, {
				header : 'Last Name',
				dataIndex : 'last_name'
			}, {
				header : 'Last Updated',
				dataIndex : 'last_update',
				xtype : 'datecolumn',
				format : 'Y.M.d'
			} ]
		}),
		bbar : new Ext.PagingToolbar({
			store : store,
			displayInfo : true,
			pageSize : pageSize,
			prependButtons : true,
			items : [ 'text 1' ]
		}),
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),
		width : 350,
		height : 300,
		frame : false,
		title : 'Actors form sakila'
	});
	
	return grid;
}