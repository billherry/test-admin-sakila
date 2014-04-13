actorForm = new Ext.form.FormPanel({
	title : 'Edit Actors',
	id : 'edit-form',
	layout : 'form',
	region : 'east',
	split : true,
	width : 285,
	record : null,
	setRecord : function(rec) {
		this.record = rec;
		this.getForm().loadRecord(this.record);		
	},
	buttons : [ {
		xtype : 'button',
		text : 'Modify',
		handler : function (){
	        if (actorForm.getForm().isValid()) {
	        	actorForm.getForm().updateRecord(actorForm.record);
	        }
			actorForm.getForm().reset();
	        actorGrid.getBottomToolbar().doRefresh();
		},
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
				name : 'last_update',
				format : 'Y.M.d'
			} ]
		} ]
	} ],
});
