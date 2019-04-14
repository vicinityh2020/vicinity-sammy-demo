
// initialize the map on the "map" div with a given center and zoom
var map = L.map('map', {
    //center: [center_lat/list.length, center_lon/list.length],
    zoom: 5,
    attributionControl: true,
    scrollWheelZoom: false,
    preferCanvas: false,
    //minZoom: 0,
    maxZoom: 21
});

//set tiles from OSM
L.tileLayer('//{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
    maxZoom: 21,
    maxNativeZoom: 18
}).addTo(map);


L.AwesomeMarkers.Icon.prototype.options.prefix = 'fa';
var $icon = L.AwesomeMarkers.icon({
    icon: '',
    markerColor: 'blue'
});

var markerClusters = L.markerClusterGroup({
    spiderfyShapePositions: function(count, centerPt) {
        var distanceFromCenter = 35,
            markerDistance = 45,
            lineLength = markerDistance * (count - 1),
            lineStart = centerPt.y - lineLength / 2,
            res = [],
            i;

        res.length = count;

        for (i = count - 1; i >= 0; i--) {
            res[i] = new Point(centerPt.x + distanceFromCenter, lineStart + markerDistance * i);
        }

        return res;
    }
});



function ArrayFormatter(value, row){
    return value.join(", ");
}


var Page = function() {

    var getData = function(d) {
        return $.ajax(BASE_PATH + "/" + REST_PATH + REST_SAMMY, d, {type: 'GET'});
    };


    var handleMap = function(d1) {
        var data = d1;

        var center_lat = 0.0, center_lon = 0.0;
        data.iot.forEach(function(element) {
            center_lat += parseFloat(element.location.lat);
            center_lon += parseFloat(element.location.lng);
        });


        map.panTo(new L.LatLng(center_lat/data.iot.length, center_lon/data.iot.length));
        markerClusters.clearLayers();

        var markers = [];
        for ( var i = 0; i < data.iot.length; ++i ) {
            var element = data.iot[i];
            var popup = '<div id="content">' +
                '<div id="siteNotice">' +
                '</div>' +
                '<div id="bodyContent">' +
                '<h5>' + element.name + '</h5>' +
                '<h6>' + element.location.name + '</h6>' +
                '<p>value: ' + element.value + '</p>' +
                '</div>' +
                '</div>';


            var m = L.marker([element.location.lat, element.location.lng], {icon: $icon}).bindPopup( popup );
            markerClusters.addLayer( m );
            markers.push(m);
        }

        var group = new L.featureGroup(markers);
        map.fitBounds(group.getBounds());

        map.addLayer( markerClusters );
    };


    var handleUI = function() {
        $(document).on("click", "a.sme" , function(e) {
            e.preventDefault();

            var id = $(this).attr('data-id');
            $.get(REST_PATH + "/get?uuid=" + id, function( data ) {
                $('#modal-sme .modal-title').html(data.name.en);

                var h = '';
                h += '<img src="' + data.image + '" class="img-fluid">';
                h += '<p>' + data.description.en + '</p>';

                h += '<div class="card">' +
                    '<div class="card-header">' +
                    '<h5 class="card-title mb-0">Details</h5>' +
                    '</div>' +
                    '<ul class="list-group list-group-flush">' +
                    '<li class="list-group-item">Area: ' + ArrayFormatter(data.area) + '</li>' +
                    '<li class="list-group-item">Geography: ' + ArrayFormatter(data.geography) + '</li>' +
                    '<li class="list-group-item">Season: ' + ArrayFormatter(data.season) + '</li>' +
                    '<li class="list-group-item">Type: ' + ArrayFormatter(data.type) + '</li>' +
                    '</ul>' +
                    '</div>';

                $.each(data.link, function(k, item) {
                    if (item.title.length) {
                        h += '<p><a href="' + item.link + '" target="_blank" >' + item.title + '</a></p>';
                    } else {
                        h += '<p><a href="' + item.link + '" target="_blank" >' + item.link + '</a></p>';
                    }
                });

                $('#modal-sme .modal-body').html(h);

                $('#modal-sme').modal('show');
            });





        });

    };

    return {
        init: function() {
            //handleUI();
            $.when(getData('')).then(handleMap);
        },
        data: function (d) {
            getData(d);
        },
        map: function (d) {
            handleMap(d);
        }

    }
}();


$(document).ready(function() {
    "use strict";
    //$('#modal-sme').modal({ show: false});

    Page.init();
});