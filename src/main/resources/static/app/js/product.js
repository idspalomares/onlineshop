var Product = {
    displayAll: function(container) {
        Api.get('./api/products', function(res) {
           var products = populateProduct(res);
           $(container).html(products);
        });
    },
    search: function(name, container) {
        Api.get('./api/products/search?name=' + name, function(res) {
           var products = populateProduct(res);
           $(container).html(products);
        });
    }
}

function populateProduct(products) {
    return '<div class="row"> ' + putProducts(products) + ' </div>';
}

function putProducts(products) {
    var res = '';

    $.each(products, function(index, obj) {
        res = res + '<div class="col-md-4">' +
                        '<div class="card mb-4 box-shadow">' +
                            '<img class="card-img-top" src="' + obj.photoUrls + '" data-holder-rendered="true" style="height: 225px; width: 100%; display: block;">' +
                            '<h5 class="card-title">' + obj.name + '</h5>' +
                            '<h6 class="card-subtitle mb-2 text-muted">' + toCurrency(obj.price) + '</h6>' +
                            '<div class="card-body">' +
                            '    <p class="card-text">' + obj.description + '</p>' +
                            '</div>' +
                            '<button type="button" onClick="addToCart(' + obj.id + ')" class="btn btn-add-cart btn-primary">Add to Cart</button>' +
                        '</div>' +
                    '</div>';
    });

    return res;
}

function toCurrency(price) {
    return "$ " + price.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
}