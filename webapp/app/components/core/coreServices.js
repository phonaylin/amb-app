ambApp.service('coreService', function() {
 	this.getMenu = function(){
	 	$http.get("menu.json").then(function (response) {
	        return response.data;
    	});
 	}
 	this.getFooterMenu = function(){
 		$http.get("footer.json").then(function(response){
 			return response.data;
 		})
 	}
     this.checkoutPayPal = function (parms, clearCart) {
        // global data
        var data = {
            cmd: "_cart",
            business: parms.merchantID,
            upload: "1",
            rm: "2",
            charset: "utf-8"
        };

        // item data
        for (var i = 0; i < this.items.length; i++) {
            var item = this.items[i];
            var ctr = i + 1;
            data["item_number_" + ctr] = item.sku;
            data["item_name_" + ctr] = item.name;
            data["quantity_" + ctr] = item.quantity;
            data["amount_" + ctr] = item.price.toFixed(2);
        }

        // build form
        var form = $('<form/></form>');
        form.attr("action", "https://www.paypal.com/cgi-bin/webscr");
        form.attr("method", "POST");
        form.attr("style", "display:none;");
        this.addFormFields(form, data);
        this.addFormFields(form, parms.options);
        $("body").append(form);

        // submit form
        this.clearCart = clearCart == null || clearCart;
        form.submit();
        form.remove();
    }
    this.addFormFields = function (form, data) {
    if (data != null) {
        $.each(data, function (name, value) {
            if (value != null) {
                var input = $("<input></input>").attr("type", "hidden").attr("name", name).val(value);
                form.append(input);
            }
        });
    }
}
});





