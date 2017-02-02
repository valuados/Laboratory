(function ($) {
    NC.readyWait('serviceCart', function (portletInstanceId) {

        var container = $(".order-container");
        
        function init() {
            $(".js-click-remove-order").on('click', function () {
                var orderId = $(this).closest('.order-item').attr('data-id-order');
                
                NC.ajaxResource(portletInstanceId, 'remove.order', function (data) {
                    updateContent();
                    window.location.reload();
                }, {
                    id: orderId
                });

            });
        }
        
        function updateContent() {
            NC.ajaxResource(portletInstanceId, 'refresh.orders', function (data) {
                var tableContainer = container.find('.order-table');
                tableContainer.html(data);
                init();
            });
        }

        NC.on('orders.table.refresh', function () {
            updateContent();
        });

        NC.on('add.order', function(data) {
            var offerId = data['id'];
            NC.ajaxResource(portletInstanceId, 'add.new.order', function (response) {
                var tableContainer = container.find('.order-table');
                tableContainer.html(response);
                init();
                window.location.reload();
            }, {id: offerId});
        });
        
        init();
    });
})(jQuery);