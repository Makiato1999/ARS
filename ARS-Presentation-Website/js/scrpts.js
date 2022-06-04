/*!
 * Start Bootstrap - New Age v5.0.6 (https://startbootstrap.com/template-overviews/new-age)
 * Copyright 2013-2019 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-new-age/blob/master/LICENSE)
 */

! function (e) {
    "use strict";
    e('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
        if (location.pathname.replace(/^\//, "") == this.pathname.replace(/^\//, "") && location.hostname == this.hostname) {
            var a = e(this.hash);
            if ((a = a.length ? a : e("[name=" + this.hash.slice(1) + "]")).length) return e("html, body").animate({
                scrollTop: a.offset().top - 48
            }, 1e3, "easeInOutExpo"), !1
        }
    }), e(".js-scroll-trigger").click(function () {
        e(".navbar-collapse").collapse("hide")
    }), e("body").scrollspy({
        target: "#mainNav",
        offset: 54
    });

    function a() {
        100 < e("#mainNav").offset().top ? e("#mainNav").addClass("navbar-shrink") : e("#mainNav").removeClass("navbar-shrink")
    }
    a(), e(window).scroll(a)
}(jQuery);

$(document).ready(function () {
    var chart = {
        type: 'column'
    };
    var title = {
        text: 'Velocity'
    };
    var subtitle = {};
    var xAxis = {
        categories: ['Iteration 1', 'Iteration 2', 'Iteration 3'],
        crosshair: true
    };
    var yAxis = {
        min: 0,
        title: {
            text: 'Time(days)'
        }
    };
    var tooltip = {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} days</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    };
    var plotOptions = {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    };
    var credits = {
        enabled: false
    };

    var series = [{
        name: 'Estimated Time',
        data: [16, 17, 26]
    }, {
        name: 'Actual Time',
        data: [17, 19, 25]
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.subtitle = subtitle;
    json.tooltip = tooltip;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.series = series;
    json.plotOptions = plotOptions;
    json.credits = credits;
    $('#velocity').highcharts(json);

});