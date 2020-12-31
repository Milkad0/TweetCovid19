$(document).ready(function () {

    var browserNotSupported = false;

    //Array to Store Streaming HashTags
    var hashTagsArr = ["Stream tweet related to COVID19"];
    var dateArr = ["Please wait..."]

    var hashTagsAndProfile = [];

    var height = $(window).height(), width = $(window).width();

    try {
        var streamLocation = new EventSource('/tweetStream');


        streamLocation.addEventListener('streamHashtags', function (event) {

            var hashtags = event.data;
            if (hashtags !== '' && hashtags !== '""') {
                hashTagsArr.push(hashtags);
            };

        });

        streamLocation.addEventListener('streamDate', function (event) {

            var date = event.data;
            if (date !== '' && date !== '""') {
                dateArr.push(date);
            };

        });



    }
    catch (err) {
        words = [];
        browserNotSupported = true;
    }

    $("#hashTags").height(height).width(width);

    showNewHashTags();

    function showNewHashTags() {
        //var scrollHeight;



        for (var i = 0; i < hashTagsArr.length; i++) {

            $("#hashTags").append("<tr><td style='color:green'><b><p> "
            +dateArr[i] +"</td><td>"+ hashTagsArr[i] + "</p></b></p></td></tr>");

           // scrollHeight = $('#hashTags').prop("scrollHeight")
            $('#hashTags').animate({scrollTop: $('#hashTags').prop("scrollHeight")});

        }





        hashTagsArr = []; //Empty hashTag Array to free up array
        dateArr = [];

        setTimeout(function () {
            showNewHashTags()

        }, 1000);

    }


});