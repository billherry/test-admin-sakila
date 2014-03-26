function CreateFilter() {
	var filters = new Ext.ux.grid.GridFilters({
		// encode and local configuration options defined previously for easier
		// reuse
		encode : true, // json encode the filter query
		local : false, // defaults to false (remote filtering)
		filters : [ {
			type : 'numeric',
			dataIndex : 'id'
		}]
	});
	return filters;
	
	
}