$(function () {
    $("#logout").click(function () {
        localStorage.clear();
        console.log("logout")
    })

    $('#calendar').datetimepicker({
        format: 'L',
        inline: true
    })

    $('#reservationtime').daterangepicker({
        timePicker: true,
        timePicker24Hour: true,
        timePickerIncrement: 5,
        locale: {
            format: 'YYYY/MM/DD H:mm'
        }
    })

    $('.toast').toast('show')


});