/**

 * Date: 3/22/13
 * Time: 11:06 AM
 */
(function ($) {

    $.fn.loader = function (show) {
        var html = '<div class="css-loader-container">\
                    <div>\
                        <div></div>\
                        <div></div>\
                        <div></div>\
                    </div>\
                </div>';
        this.find('.css-loader-container').remove();
        if (show || show === undefined) {
            this.each(function () {
                $(this).append(html);
            });
        }
        return this;
    };
})(jQuery);
