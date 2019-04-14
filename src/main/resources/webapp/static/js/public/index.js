var Page = function () {

    var getItems = function () {
        $.get(BASE_PATH + "/" + REST_PATH + REST_ITEMS, function (data) {

            $('#items').html('');
            $.each(data, function (index, value) {
                var h = '';
                h += '<div class="col-lg-3 col-sm-6" id="' + value.oid + '">';
                h += '<div class="card card-stats">';
                h += '<div class="card-body ">';
                h += '<div class="row">';
                h += '<div class="col-12">';
                h += '<p class="card-title">' + value.pid + ': ' + value.name + '</p>';
                h += '<small>OID: ' + value.oid + '</small>';
                h += '</div>';
                h += '<div class="col-5">';
                h += '<div class="icon-big text-center icon-warning">';
                h += '<i class="nc-icon ' + (value.pid.startsWith('BS') ? 'nc-button-pause' : 'nc-bulb-63') + ' text-warning"></i>';
                h += '</div>';
                h += '</div>';
                h += '<div class="col-7">';
                h += '<div class="numbers">';
                h += '<p class="card-category">' + (value.units=='' ? '&nbsp;' : value.units) + '</p>';
                h += '<h4 class="card-title">-</h4>';
                h += '</div>';
                h += '</div>';
                h += '</div>';
                h += '</div>';
                h += '<div class="card-footer ">';
                h += '<hr>';
                h += '<div class="stats">';
                h += '<a href="#" class="query float-left" data-oid="'+value.oid+'"><i class="fa fa-sync"></i> Query Now </a>';
                h += '<a href="#" class="login float-right" data-oid="'+value.oid+'"><i class="fa fa-lock-open"></i> Login </a>';
                h += '</div>';

                h += '</div>';
                h += '</div>';
                h += '</div>';

                $('#items').append(h);
            });

            $.each(data, function (index, value) {
                object_query(value.oid, false);
            });



        });
    };

    var login = function() {
        $(document).on("click", "a.login" , function(e) {
            e.preventDefault();

            var oid = $(this).attr('data-oid');

            $.ajax({
                url: BASE_PATH + "/" + REST_PATH + REST_ITEM_LOGIN,
                type: 'GET',
                data: {
                    oid: oid
                },
                dataType: 'json',
                error: function (xhr, ajaxOptions, thrownError) {
                    var h = '';
                    h += '<div class="icon-big text-center" style="font-size: 80px">';
                    h += '<i class="nc-icon nc-simple-remove text-danger"></i>';
                    h += '</div>';
                    h += '<code>Vicinity Gateway API response was:</code>';
                    h += '<pre>'+JSON.stringify(xhr, null, 4)+'</pre>';
                    bootbox.alert(h);
                },

                success: function(data) {
                    var h = '';
                    h += '<div class="icon-big text-center" style="font-size: 80px">';
                    h += '<i class="nc-icon nc-check-2 text-success"></i>';
                    h += '</div>';
                    h += '<code>Vicinity Gateway API response was:</code>';
                    h += '<pre>'+JSON.stringify(data, null, 4)+'</pre>';
                    bootbox.alert(h);
                }
            });
        });
    };

    var query = function() {
        $(document).on("click", "a.query" , function(e) {
            e.preventDefault();

            var oid = $(this).attr('data-oid');
            object_query(oid, true);

        });
    };


    return {
        init: function () {
            getItems();
            login();
            query();
        }
    }
}();

$().ready(function () {
    "use strict";

    Page.init();

    var timer = setInterval("ajaxd_status()", APP_REFRESH);
});


function object_query(oid, dialog) {
    $.ajax({
        url: BASE_PATH + "/" + REST_PATH + REST_ITEM_QUERY,
        type: 'GET',
        data: {
            oid: oid
        },
        dataType: 'json',
        error: function (xhr, ajaxOptions, thrownError) {
            var h = '';
            h += '<div class="icon-big text-center" style="font-size: 80px">';
            h += '<i class="nc-icon nc-simple-remove text-danger"></i>';
            h += '</div>';
            h += '<code>Vicinity Gateway API response was:</code>';
            h += '<pre>'+JSON.stringify(xhr, null, 4)+'</pre>';
            if (dialog) bootbox.alert(h);
        },

        success: function(data) {
            var h = '';
            h += '<div class="icon-big text-center" style="font-size: 80px">';
            h += '<i class="nc-icon nc-check-2 text-success"></i>';
            h += '</div>';
            h += '<code>Vicinity Gateway API response was:</code>';
            h += '<pre>'+JSON.stringify(data, null, 4)+'</pre>';
            if (dialog) bootbox.alert(h);

            var jsonData = JSON.parse(data.message[0].message);
            $('#items > #' + oid + ' .card .card-body .row .col-7 .numbers .card-title').html(jsonData.value);
        }
    });
}


function ajaxd_status() {
    $("a.query").each(function() {
        var oid = $(this).attr('data-oid');
        object_query(oid, false);
    });

}