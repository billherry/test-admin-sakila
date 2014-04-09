var pageSize = 10;
context.grid = new Ext.grid.GridPanel({
			region:'center',
			plugins: [context.filter],
			store : context.store,
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
				},{
					xtype : 'actioncolumn',
					items : [{
						icon : 'images/modify.png',
						tooltip : 'Edit',
						handler : function(grid, rowIndex, colIndex) {
							var rec = context.store.getAt(rowIndex);
							context.editform.getForm().loadRecord(rec);
						}
					},{
						icon : 'images/cross.png',
						tooltip : 'Delete',
						handler : function(grid, rowIndex, colIndex) {
							// TODO delete record from database
						}
					}]
				}]
			}),
			bbar : new Ext.PagingToolbar({
				store : context.store,
				displayInfo : true,
				pageSize : pageSize,
				prependButtons : true,
				plugins: [context.filter],
			}),
			width : 350,
			height : 300,
			frame : false,
			title : 'Actors form sakila'
		});
context.grid.getBottomToolbar().doRefresh();