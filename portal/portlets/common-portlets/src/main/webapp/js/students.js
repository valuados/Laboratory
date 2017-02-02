(function ($) {
    NC.readyWait('students', function (portletInstanceId) {

        var container = $(".student-container");

        function init() {
            var domConfiguration = '<"H">t';
            if ($('#orders__table').attr('paging') > 0) {
                domConfiguration = '<"H">t<lp>';
            }

            container.find('.orders__table').dataTable({
                "sPaginationType": "full_numbers",
                "sDom": domConfiguration,
                "aaSorting": [[0, "asc"]]
            });

            $(".js-click-remove-student").on('click', function () {
                var studentId = $(this).closest('.student-item').data('idStudent');
                NC.ajaxResource(portletInstanceId, 'remove.student', function (data) {
                    updateContent();
                }, {
                    studentId: studentId
                });

            });


            $(".js-click-edit-student").on('click', function () {
                var studentId = $(this).closest('.student-item').data('idStudent');
                NC.fire('show.edit.student.popup', {studentId:studentId});
            });

        }


        function updateContent() {
            NC.ajaxResource(portletInstanceId, 'refresh.student', function (data) {
                var tableContainer = container.find('.student-table');
                tableContainer.html(data);
                init();
            });
        }

        NC.on('student.table.refresh', function () {
            updateContent();
        });

        $('.js-click-create-student').on('click', function(){
            NC.fire('show.edit.student.popup');
        });

        init();
    });
})(jQuery);