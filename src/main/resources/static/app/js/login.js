$(document).ready(function() {

    var urlParams = new URLSearchParams(window.location.search);
    var param = urlParams.get('msg');


    if (param && param != null && param == '404') {
        $('.alert').attr("style", "display: block");
    }

});

