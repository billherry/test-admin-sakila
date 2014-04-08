function createGrid(store, pageSize) {	
	var filter = CreateFilter();
	var editform = createForm("","","","");
	
	var grid = new Ext.grid.GridPanel({
		
		getForm : function (){
			return editform;
		},
		region:'center',
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
			},{
				xtype : 'actioncolumn',
				items : [{
					icon : 'images/modify.png',
					tooltip : 'Edit',
					handler : function(grid, rowIndex, colIndex) {
						var rec = store.getAt(rowIndex);
						console.log(rec);
						grid.getForm().load(rec);

					}
				},{
					icon : 'images/cross.png',
					tooltip : 'Delete',
					handler : function(grid, rowIndex, colIndex) {
						var rec = store.getAt(rowIndex);
						alert(rec.get('first_name'));
					}
				}]
			}]
		}),
		plugins: [filter],
		bbar : new Ext.PagingToolbar({
			store : store,
			displayInfo : true,
			pageSize : pageSize,
			prependButtons : true
		}),
		width : 350,
		height : 300,
		frame : false,
		title : 'Actors form sakila'
	});
	window.grid = grid;
	window.editForm = editform;
	grid.getBottomToolbar().doRefresh();
	return grid;
}