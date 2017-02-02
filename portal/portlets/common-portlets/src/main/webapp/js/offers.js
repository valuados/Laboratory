(function ($) {
    NC.readyWait('offers', function (portletInstanceId) {
        
        function init() {
            $('.offer-item').off();
            $('.offer-item').click(function () {
                var offerId = $(this).attr('data-id-offer');
                var categoryId = $(".offer-container").attr('data-id-category');
                var offerName = $(this).find("h4").text();
                NC.fire('add.order', {id: offerId, name: offerName, categoryId: categoryId});
                return false;
            });
        }

        function updateContent() {
            NC.ajaxResource(portletInstanceId, 'refresh.offers', function (data) {
                var offerContainer = $(".offer-container");
                offerContainer.html(data);
                init();
            });
        }

        NC.on('refresh.offer', function (data) {
            updateContent();
        });
        
        init();
    });
})(jQuery);
