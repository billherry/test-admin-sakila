/**
 * @author schiesser
 */
Ext.ns('Extensive.grid');

Extensive.grid.ItemDeleter = Ext.extend(Ext.grid.RowSelectionModel, {

    width: 30,
    
    sortable: false,
	dataIndex: 0, // this is needed, otherwise there will be an error
    
    menuDisabled: true,
    fixed: true,
    id: 'deleter',
    
    initEvents: function(){
        Extensive.grid.ItemDeleter.superclass.initEvents.call(this);
        this.grid.on('cellclick', function(grid, rowIndex, columnIndex, e){
			if(columnIndex==grid.getColumnModel().getIndexById('deleter')) {
				var record = grid.getStore().getAt(rowIndex);
				grid.getStore().remove(record);
				grid.getView().refresh();
			}
        });
    },
    
    renderer: function(v, p, record, rowIndex){
        return '<div class="extensive-remove" style="width: 15px; height: 16px;"></div>';
    }
});

function CreateGrid(store, pageSize) {	
	var filter = CreateFilter();
	var itemDeleter = new Extensive.grid.ItemDeleter();
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
			}]
		}),
		plugins: [filter],
		bbar : new Ext.PagingToolbar({
			store : store,
			displayInfo : true,
			pageSize : pageSize,
			prependButtons : true
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