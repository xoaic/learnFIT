$(document).ready(function(){
    $.validator.addMethod("strongPassword", function() {
        const STRONG_PASSWORD_REGEX = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?.!@$%^&*-]).{8,}$/;
        return STRONG_PASSWORD_REGEX.test($('#password').val());
    }, 'Weak password!');

    $.validator.addMethod("emailFormat", function() {
        const EMAIL_REGEX = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        return EMAIL_REGEX.test($('#email').val());
    }, 'Email is not valid format!');

    $('#email').on('focusin', function(){
        $('.err-msg .error, .email-msg').text('');
    });
    $('#password').on('focusin', function(){
        $('.err-msg .error, .password-msg').text('');
    });
    $('#rePassword').on('focusin', function(){
        $('.err-msg .error, .re-password-msg').text('');
    });
    $('#firstName').on('focusin', function(){
        $('.err-msg .error, .firstName-msg').text('');
    });
    $('#lastName').on('focusin', function(){
        $('.err-msg .error, .lastName-msg').text('');
    });
    $('#phoneNumber').on('focusin', function(){
        $('.err-msg .error, .phoneNumber-msg').text('');
    });
    $('#address').on('focusin', function(){
        $('.err-msg .error, .address-msg').text('');
    });

    $('#register-form').validate({
        onfocus: false,
        onkeyup: false,
        onclick: false,
        rules: {
            email: {
                required: true,
                emailFormat: true
            },
            password : {
                required: true,
                strongPassword: true
            },
            rePassword : {
                required: true,
                equalTo: '#password'
            },
            firstName: {
                required: true,
                length: 30
            },
            lastName: {
                required: true,
                length: 30
            },
            phoneNumber: {
                required: true,
                minlength: 9,
                length: 12
            },
            address: {
                required: true,
                minlength: 2
            }
        }
    });
});

