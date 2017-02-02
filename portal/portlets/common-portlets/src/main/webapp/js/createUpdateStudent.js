(function ($) {
    NC.readyWait('createUpdateStudent', function (portletInstanceId) {

        var container = $(".student-config-container");
        var editPopup = container.find(".student-config-popup");

        NC.on('show.edit.student.popup', function (data) {
            NC.showLoader(true);
            var studentId = data['studentId'];
            NC.ajaxResource(portletInstanceId, 'show.popup.student', function (data) {
                NC.hideLoader();
                editPopup.find('.popup__body').html(data);
                editPopup.show();
            }, {
                studentId: studentId
            });
        });


        $('.js-click-save-student').on('click', function () {
            var studentContent = container.find('.student-popup-content');
            var studentId = studentContent.data('studentId');

            var student = {
                id: studentId,
                name: studentContent.find('.student-name').val(),
                surname: studentContent.find('.student-surname').val()
            };

            NC.ajaxResource(portletInstanceId, 'save.student', function () {
                NC.fire('student.table.refresh');
                editPopup.hide();
            }, {student:JSON.stringify(student)});


        });

        $('.button_cancel , .chars-config-popup-close').on('click', function(){
            editPopup.hide();
        });


    });
})(jQuery);