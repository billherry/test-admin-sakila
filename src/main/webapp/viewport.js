function createViewPort(grid) {

	var accordion = new Ext.Panel({
		region : 'west',
		split : true,
		width : 210,
		title : 'Actions',
	});
	var editform = new Ext.form.FormPanel({
		title : 'Edit Form',
		id : 'editform',
		region : 'east',
		split : true,
		width : 210,
		items : [ {
			xtype : 'numberfield',
			name : 'id',
			fieldLabel : 'Id '
		}, {
			xtype : 'textfield',
			name : 'firstname',
			fieldLabel : 'First Name'
		}, {
			xtype : 'textfield',
			name : 'lastname',
			fieldLabel : 'Last Name'
		}, {
			xtype : 'datefield',
			name : 'lastupdate',
			fieldLabel : 'Last update'
		},
		{
			xtype : 'button',
			text: 'Modify'
		}]
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ accordion, grid, editform ]
	});
	window.viewport = viewport;
	return viewport;
};