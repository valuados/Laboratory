(function ($) {
    var NC = window.NC = Liferay;
    var loaderQueue = 0;

    NC.showLoader = function (modal) {
        $('.nc-loading-indicator').show();
        loaderQueue++;
        if (modal) {
            $('.nc-loading-block').show();
        }
    };

    NC.hideLoader = function (force) {
        loaderQueue--;
        if (loaderQueue <= 0 || force) {
            $('.nc-loading-indicator, .nc-loading-block').hide();
            loaderQueue = 0;
        }
    };

    NC.ajaxPortletResource = function (resourceURL, callback, data, options, context) {
        options = $.extend(true, {
            showSuccessInfo: false,
            showErrorInfo: false,
            errorCallback: false,
            successMessage: Liferay.Language.get('portal.success.general'),
            errorMessage: Liferay.Language.get('portal.error.general'),
            loader: {
                show: false,
                modal: false
            },
            completeCallback: false
        }, options);
        if (options.loader.show) {
            $('.nc-loading-indicator').show();
            loaderQueue++;
            if (options.loader.modal) {
                $('.nc-loading-block').show();
            }
        }
        $.ajax({
            url: resourceURL.toString(),
            type: 'POST',
            data: data,
            success: function (data) {
                if (callback) {
                    callback.call(context, data && data.data ? data.data : data);
                }
                if (options.showSuccessInfo) {
                    $.notify(options.successMessage, "success");
                }
            },
            error: function (data) {
                if (data.responseJSON) {
                    console.error(data.responseJSON.message, data.responseJSON.stackTrace);
                } else {
                    console.error(data.statusText, data.responseText);
                }
                if (options.errorCallback) {
                    options.errorCallback.call(context, data)
                }
                if (options.showErrorInfo) {
                    $.notify(options.errorMessage, "error");
                }
            },
            complete: function (data) {
                if (options.completeCallback) {
                    options.completeCallback.call(context, data)
                }
                if (options.loader.show) {
                    loaderQueue--;
                    if (loaderQueue <= 0) {
                        $('.nc-loading-indicator, .nc-loading-block').hide();
                        loaderQueue = 0;
                    }
                }
            }
        });
    };

    NC.ajaxResource = function (portletInstanceId, action, callback, data, options) {

        AUI().ready('liferay-portlet-url', function () {
            var resourceURL = NC.PortletURL.createResourceURL();
            resourceURL.setPortletId(portletInstanceId);
            resourceURL.setResourceId(action);
            NC.ajaxPortletResource(resourceURL, callback, data, options, NC);
        });
    };

    NC.Portlet.ready(
        function (portletId, node) {
            if (new RegExp("(.*)_WAR_").test(portletId)) {
                NC.fire('nc.portlet.ready', {
                    portletId: RegExp.$1,
                    portletInstanceId: portletId,
                    parentNode: $(node._node).find('.portlet-container')
                });
            }
        }
    );

    NC.readyWait = function (portletId, callback, mode) {
        NC.on('nc.portlet.ready', function (event) {
            if (event.portletId == portletId) {
                var portletMode = $('#type_' + event.portletInstanceId + '_').val();
                if (!portletMode && !mode || portletMode == mode) {
                    callback.call(event.parentNode, event.portletInstanceId);
                }
            }
        });
    };

    NC.alertConfirm = function (setting) {
        return $.hyc.ui.alert(setting);

        /*Example*/

        /*NC.alertConfirm('Alert Dialog Box', function () {
         alert('You Clicked OKey');
         });*/

        /*NC.alertConfirm({
            content: 'Confirm Message',
            buttons: [{
                name: 'Okey',
                id: 'confirmBtn',
                color: '#fff',
                bgColor: '#337AB7',
                callback: function () {
                    alert('Okey');
                },
                closable: false
            }, {
                name: 'Cancle',
                id: 'cancelBtn',
                color: '#337AB7',
                bdColor: '#337AB7',
                bgColor: '#fff',
                callback: function () {
                    this.close();
                },
                closable: false
            }],
            closable: false
        });*/

    }
})(jQuery);