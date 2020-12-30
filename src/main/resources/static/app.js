$(document).ready(function () {

    var browserNotSupported = false;

    //Array to Store Streaming HashTags
    var hashTagsArr = ["Stream tweet related to COVID19"];

    var hashTagsAndProfile = [];

    var height = $(window).height(), width = $(window).width();

    try {
        var streamLocation = new EventSource('/tweetLocation');


        streamLocation.addEventListener('streamHashtags', function (event) {

            var hashtags = event.data;
            if (hashtags !== '' && hashtags !== '""') {
                hashTagsArr.push(hashtags);
            }
            ;

        });
    }
    catch (err) {
        words = [];
        browserNotSupported = true;
    }

    $("#hashTags").height(height).width(width);

    var lastClassindex = 0;
    showNewHashTags();

    function showNewHashTags() {

        //Put Project Info in beetween hashtags
        if (Math.floor(Math.random() * 20) === 10) {
            var dispInfo = [];
            hashTagsArr = hashTagsArr.concat(dispInfo);
        }

        for (var i = 0; i < hashTagsArr.length; i++) {

            $("#hashTags").append("<tr><td><b><p returnTextClass(lastClassindex + 2) > "
                + hashTagsArr[i] + "</p></b></p></td></tr>");
            lastClassindex++;

        }
        $('#hashTags').animate({scrollTop: $('#hashTags').prop("scrollHeight")}, 2000);
        lastClassindex = hashTagsArr.length;

        hashTagsArr = []; //Empty hashTag Array to free up array

        setTimeout(function () {
            showNewHashTags()
        }, 2000);

    }

    function returnTextClass(index) {
        var cssClass = ["success", "info", "warning", "danger", "primary"];
        var ind = index % 5;
        return cssClass[ind];
    }


});