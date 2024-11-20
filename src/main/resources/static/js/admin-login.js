$(function () {
    $('#loginForm').on('submit', function (event) {

        event.preventDefault();

        const adminId = $('#idBox').val();
        const password = $('#passwordBox').val();
        const regex = /^[a-zA-Z0-9]+$/;

        if (adminId === "" || password === "") {
            $('#errorMessage').css('visibility', 'unset');
            $('#errorMessage').html(EMPTY_ERROR_MESSAGE);
            return;
        }

        if (adminId.length > 16 || password.length > 16) {
            $('#errorMessage').css('visibility', 'unset');
            $('#errorMessage').html(OVER_LENGTH_ERROR_MESSAGE);
            return;
        }

        if (!regex.test(adminId) || !regex.test(password)) {
            $('#errorMessage').css('visibility', 'unset');
            $('#errorMessage').html(ILLEGAL_CHARACTER_ERROR_MESSAGE);
            return;
        }
    })
});