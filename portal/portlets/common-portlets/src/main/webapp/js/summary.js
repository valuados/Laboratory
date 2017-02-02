(function ($) {
    NC.readyWait('summary', function (portletInstanceId) {

        function updateContent() {
            NC.ajaxResource(portletInstanceId, 'refresh.summary', function (data) {
                var summaryContainer = $(".summary-container");
                summaryContainer.html(data);
            });
        }

        NC.on('refresh.summary', function () {
            updateContent();
        });

        $(".js-click-save-so").on('click', function () {
            NC.ajaxResource(portletInstanceId, 'save.so', function (data) {
                var summaryContainer = $(".summary-container");
                summaryContainer.html("<div class='alert alert-success'><h4>Your sales order submitted.</h4></div>");
            });
        });

    });
})(jQuery);
