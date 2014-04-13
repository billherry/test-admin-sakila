//Ext.ns('App.Grids');

var pageSize = 10;
var actorGrid = new Ext.grid.GridPanel({
	region:'center',
	plugins: [actorFilter],
	store : actorStore,
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
					var rec = actorStore.getAt(rowIndex);
					actorForm.setRecord(rec);
				}
			},{
				icon : 'images/cross.png',
				tooltip : 'Delete',
				handler : function(grid, rowIndex, colIndex) {
					var rec = actorStore.getAt(rowIndex);
					actorStore.remove(rec);
				}
			}]
		}]
	}),
	bbar : new Ext.PagingToolbar({
		store : actorStore,
		displayInfo : true,
		pageSize : pageSize,
		prependButtons : true,
		plugins: [actorFilter],
	}),
	width : 350,
	height : 300,
	frame : false,
	title : 'Actors form sakila'
});
actorGrid.getBottomToolbar().doRefresh();