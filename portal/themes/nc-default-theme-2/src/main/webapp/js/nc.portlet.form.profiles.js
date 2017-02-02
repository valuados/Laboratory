(function ($) {
    var profiles = {
        'standard': {
            before: function () {
                this.find('button[type=submit]').prop('disabled', true);
            },
            options: {
                completeCallback: function () {
                    this.find('button[type=submit]').prop('disabled', false);
                }
            }
        }
    };
    profiles['preferences'] = $.extend(true, {}, {
        options: {
            showSuccessInfo: true,
            showErrorInfo: true
        }
    }, profiles.standard);

    $(function () {
        $('form[data-form-events-profile]').each(function () {
            var form = $(this);
            var profile = profiles[form.attr('data-form-events-profile')];
            form.submit(function () {
                if (profile.before) {
                    profile.before.call(form);
                }
                NC.ajaxPortletResource(form.attr('action'), false, $(this).serialize(), profile.options, form);
                return false;
            });
        });
    });
})(jQuery);