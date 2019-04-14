var Page = function() {

    var handleUI = function() {
        $('.error-log > img').css('display', 'block');
        //$('.error-log > a').css('display', 'none');
        $('.error-log > .scroll').css('display', 'none');

        $('input[type=checkbox]').checkboxpicker({});
        $('#demo .refresh-option input[type=checkbox]').prop('checked', true);


        $(document).on("click", "a.btn-info" , function(e) {
            e.preventDefault();
            var type = $(this).attr('data-type');
            var control = $('#' + type + ' .refresh-option input[type=checkbox]');
            var v = $(control).is(':checked');

            $(control).prop('checked', true);
            log(type);
            $(control).prop('checked', v);
        });


        $('.to-top').click(function(e) {
            e.preventDefault();
            var type = $(this).parent().attr('data-type');
            var scrollPos = $('#row-' + type + '-1').position().top; // use the text of the span to create an ID and get the top position of that element
            $('#' + type + ' .error-log .scroll').animate({ // animate your right div
                scrollTop: scrollPos // to the position of the target
            }, 800);
        });
        $('.to-bottom').click(function(e){
            e.preventDefault();
            var type = $(this).parent().attr('data-type');
            var count = $('#' + type + ' .error-log .scroll div').length;

            var scrollPos = $('#row-' + type + '-' + count).position().top; // use the text of the span to create an ID and get the top position of that element
            $('#' + type + ' .error-log .scroll').animate({ // animate your right div
                scrollTop: scrollPos // to the position of the target
            }, 800);
        });


        setInterval("ajaxd()", LOG_REFRESH);
    };

    return {
        init: function() {
            handleUI();
        }
    }
}();


$(document).ready(function() {
    "use strict";

    Page.init();
});

function ajaxd() {
    log('gateway');
    log('agent');
    log('adapter');
    log('demo');
}

function log(type) {
    var control = $('#' + type + ' .refresh-option input[type=checkbox]');
    if ($(control).is(':checked') === false) return;

    $.get(BASE_PATH + "/" + REST_PATH + REST_LOG + "?type=" + type, "", function(data, textStatus) {
        //data contains the JSON object
        //textStatus contains the status: success, error, etc
        if ( textStatus === 'success' ) {

            $('#' + type + ' div.error-log div.scroll').empty();
            $('#' + type + ' div.error-log div.scroll').css('display', 'block');
            $('#' + type + ' div.error-log img').hide();

            var i = 0;
            $.each(data, function(id, item){
                i++;
                var h = '<div>' +
                    '<span id="row-'+type+'-'+i+'" class="label label-info">'+item+'</span>&nbsp;' +
                    '</div>';
                $('#' + type + ' div.error-log div.scroll').append(h);
            });
        }
    }, "json");



}