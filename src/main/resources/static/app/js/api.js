var Api = {
    get: function(url, callback, err) {
        $.ajax({
            method: 'GET',
            url: url,
            success: function(response) {
                callback(response);
            },
            error: function(e) {
                err(e);
            }
         });
    },
    post: function(url, body, callback, err) {
        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            url: url,
            data: JSON.stringify(body),
            success: function(response) {
                callback(response);
            },
            error: function(e) {
                err(e);
            }
        })
    },
    put: function(url, body, callback, err) {
        $.ajax({
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            url: url,
            data: JSON.stringify(body),
            success: function(response) {
                callback(response);
            },
            error: function(e) {
                err(e);
            }
        })
     }
}