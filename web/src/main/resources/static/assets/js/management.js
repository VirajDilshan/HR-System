$(function () {
    $('.select2').select2()

    //Initialize Select2 Elements
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    })
    //Bootstrap Duallistbox
    $('.duallistbox').bootstrapDualListbox()

    $("#tbl-user").DataTable({
        "responsive": true,
        "autoWidth": false,
    });

    $("#tbl-role").DataTable({
        "responsive": true,
        "autoWidth": false,
    });

    $("#tbl-group").DataTable({
        "responsive": true,
        "autoWidth": false,
    });
    //Money Euro
    $('[data-mask]').inputmask()
    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })

    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });

    //time in dashboard
    setInterval(function() {
        var momentNow = moment();
        $('#date-part').html(momentNow.format('YYYY MMMM DD') + ' '
            + momentNow.format('dddd')
                .substring(0,3).toUpperCase());
        $('#time-part').html(momentNow.format('hh:mm:ss A'));
    }, 100);

    $("#auth-form").on("submit", function () {
        if($("#password").val() == $("#confirm-password").val()) {
            return true;
        } else {
            $("#auth-pass-match").html('password not match');
            return false;
        }
    })
});