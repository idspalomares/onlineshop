var Purchase = {
    addToCart: function(productId, callback) {
        var body = {
            product: {
                id: productId
            }
        }

        Api.post('./api/purchases', body,
            function(res) {
                callback(res);
            }, function(err){
                console.log(JSON.stringify(err));
            }
        );
    },
    showCount: function(element) {
        Api.get('./api/purchases/count', function(res) {
            $(element).text(res);
        });
    },
    showCartItems: function(element) {
        Api.get('./api/purchases/cart', function(res) {
           var items = generateCartPurchase(res);
           $(element).html(items);
        });
    },
    checkout: function(callback) {
        Api.put('./api/purchases/checkout', '', function(res) {
            callback(res);
        });
    },
    showPurchaseHistory: function(element) {
        Api.get('./api/purchases/history', function(res) {
            var items = generatePurchaseHistory(res);
            $(element).html(items);
        });
    }
}


function generatePurchaseHistory(res) {
    var table = '<table class="table table-striped">' +
                '  <thead>' +
                '    <tr>' +
                '      <th scope="col">Invoice #</th>' +
                '      <th scope="col">Transaction Date</th>' +
                '      <th scope="col">Items</th>' +
                '      <th scope="col">Total</th>' +
                '    </tr>' +
                '  </thead>' +
                '  <tbody> ' + generatePurchaseHistoryItems(res) + ' </tbody>' +
                '</table>';
    return table;
}


function generatePurchaseHistoryItems(res) {
    var items = '';

    $.each(res, function(index, obj){
        items = items +  '<tr>' +
                         '  <td>' + obj.invoiceCode + '</td>' +
                         '  <td>' + obj.transactionDate + '</td>' +
                         '  <td>' + generateItemString(obj.purchases) + '</td>' +
                         '  <td>' + toCurrency(obj.totalPrice) + '</td>' +
                         '</tr>';
    });

    console.log(items);
    return items;
}

function generateItemString(items) {
    var item = '';

    $.each(items, function(index, obj){
        item = item + obj.product.name + " </br>";
    });

    return item;
}

function generateCartPurchase(res) {
    var itemsObj = generateItems(res.purchases);
    return '<table class="table">' +
           '  <thead>' +
           '    <tr>' +
           '      <th scope="col">Item</th>' +
           '      <th scope="col">Price</th>' +
           '      <th scope="col">Quantity</th>' +
           '      <th scope="col">Total</th>' +
           '    </tr>' +
           '  </thead>' +
           '  <tbody>' + itemsObj.items +
           '  <tr>' +
           '  <td></td>' +
           '  <td></td>' +
           '  <td></td>' +
           '  <td style="font-weigh: bold;">' + itemsObj.total + '</td>' +
           ' </tbody>' +
           '</table>';
}

function generateItems(purchases) {
    var items = '';
    var grantTotal = 0;

    $.each(purchases, function(index, obj){
        items = items +  '<tr>' +
                         '  <td>' + obj.product.name + '</td>' +
                         '  <td>' + toCurrency(obj.product.price) + '</td>' +
                         '  <td>' + obj.quantity + '</td>' +
                         '  <td>' + toCurrency(obj.product.price * obj.quantity) + '</td>' +
                         '</tr>';
        grantTotal += (obj.product.price * obj.quantity);
    });

    return { items: items, total: toCurrency(grantTotal) };
}

