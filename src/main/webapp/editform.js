context.buttonAction.onSave = function(btn, ev) {
	window.records = context.editform.getForm();
};

context.editform = new Ext.form.FormPanel({
	title : 'Edit Actors',
	id : 'edit-form',
	layout : 'form',
	region : 'east',
	split : true,
	width : 285,
	record : null,
	buttons : [ {
		xtype : 'button',
		text : 'Modify',
		handler : context.buttonAction.onSave,
		scope : this
	} ],
	items : [ {
		layout : 'form',
		items : [ {
			xtype : 'fieldset',
			defaultType : 'textfield',
			items : [ {
				xtype : 'numberfield',
				name : 'actor_id',
				fieldLabel : 'Id'
			}, {
				fieldLabel : 'FirstName',
				name : 'first_name'

			}, {
				fieldLabel : 'LastName',
				name : 'last_name'
			}, {
				xtype : 'datefield',
				fieldLabel : 'LastUpdate',
				name : 'last_update'
			} ]
		} ]
	} ],
});