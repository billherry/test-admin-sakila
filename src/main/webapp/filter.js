function CreateFilter() {
	var filter = new Ext.ux.grid.GridFilters({
		filters : [
				{
					type : 'numeric',
					dataIndex : 'id'
				}]
	});
	return filter;
}