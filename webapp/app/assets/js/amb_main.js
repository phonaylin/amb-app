

$(document).ready(function() {

    // MIXITUP PORTFOLIO
    $(function(){
        $('#container-mixitup').mixItUp({
            load: {
              filter: '.yangon'
            }
        });
    });



	 //Footer click event to scroll to top
    $('#scroll-top').click(function() {
        $('html, body').animate({
            scrollTop: 0
        }, 880);
        return false;
    });


});


