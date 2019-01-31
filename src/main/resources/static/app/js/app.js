$(document).ready(function() {

    $('.btn-search').on('click', function(){
        var name = $('#searchField').val();

        if (name == null || name == '') {
            Product.displayAll('.container-body');
        } else {
            Product.search(name, '.container-body');
        }
    })

    $('#cartButton').on('click', function() {
        $('#cartModal').modal('show');

        Purchase.showCartItems('.cart-container');
    });

    $('#transButton').on('click', function() {
        $('#transModal').modal('show');

        Purchase.showPurchaseHistory('.trans-container');
    });


    Product.displayAll('.container-body');
    Purchase.showCount('#purchaseCount');
});

function addToCart(id) {
    Purchase.addToCart(id, function(res) {
        Purchase.showCount('#purchaseCount');
        showToast('Added item to cart!');
    });
}

function checkoutCart() {

    var conf = confirm("You sure you wanna checkout?");

    if (conf == true) {
        Purchase.checkout(function(res) {
            if (res && res.totalPrice > 0) {
                $('#cartModal').modal('hide');
                Purchase.showCount('#purchaseCount');
            }
        });
    }
}

function showToast(message) {
    alert('Item added to cart!');
}

