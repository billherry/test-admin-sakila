function createForm(id,first_name,last_name,date){
	var editform = new Ext.form.FormPanel({
		title : 'Edit Form',
		id : 'editform',
		region : 'east',
		split : true,
		width : 210,
		items : [ {
			xtype : 'numberfield',
			name : 'id',
			fieldLabel : 'Id ',
			value : id
		}, {
			xtype : 'textfield',
			name : 'firstname',
			fieldLabel : 'First Name',
			value : first_name
		}, {
			xtype : 'textfield',
			name : 'lastname',
			fieldLabel : 'Last Name',
			value : last_name
		}, {
			xtype : 'datefield',
			name : 'lastupdate',
			fieldLabel : 'Last update',
			value : date
		},
		{
			xtype : 'button',
			text: 'Modify'
		}]
	});	
	return editform;
}