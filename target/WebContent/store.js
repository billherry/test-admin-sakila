Ext.onReady(function(){
	
	var store = new Ext.data.JsonStore({
		autoDestroy : true,
		storeId : 'myStore',
		idProperty : 'actor_id',
		root: 'actors',	    		
	    proxy: new Ext.data.HttpProxy({
	    	method:'GET',
            url: 'actors'
        }),
		fields : [  
		           {name : 'actor_id',type : 'int'},
		           'first_name','last_name',{name:'last_update',type:'date'}]	
	});
	  
	store.load({
		callback: function() {
			console.log(store.getCount());
		}
	});
	window.store = store;
});