function createForm(id, first_name, last_name, date) {
	var editform = new Ext.form.FormPanel({
		doAction : function (){},
		title : 'Edit Form',
		id : 'edit-form',
		layout : 'form',
		region : 'east',
		split : true,
		width : 210,
		buttons : [{
			xtype : 'button',
			text : 'Modify'
		}],
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
		} ]
	});
	return editform;
}