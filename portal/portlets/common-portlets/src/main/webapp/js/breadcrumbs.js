(function ($) {
    NC.readyWait('breadcrumbs', function (portletInstanceId) {

        var container = $(".breadcrumbs-portlet-container");

        function init() {

            $(".breadcrumb-item").on('click', function () {
                var index = $(this).index();

                NC.fire('breadcrumbs.change',{index:index});
            });

        }


        function updateBreadcrumbs() {
            console.log("update breadcrumb");
        }

        NC.on('breadcrumbs.change', function (data) {

            console.log("event intercept");
            updateBreadcrumbs();
            var index = data.index;

            NC.ajaxResource(portletInstanceId, 'change.breadcrumbs', function (data) {

              var breadcrumbsContainer = container.find('.breadcrumbs-list');
              breadcrumbsContainer.html(data);


            }, {
              index: index
            });
        });

        init();
    });
})(jQuery);