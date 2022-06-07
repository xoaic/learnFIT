$(document).ready(function(){

    $('#email').on('focusin', function(){
        $('.err-msg .error, .email-msg').text('');
    });
    $('#password').on('focusin', function(){
        $('.err-msg .error, .password-msg').text('');
    });
    $('#remember').prop('checked', true);

    $('#login-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            }
        }
    })
});
