let firstName = localStorage.getItem("firstName");
let lastName = localStorage.getItem("lastName");
let username = localStorage.getItem("username");
let phoneNumber = localStorage.getItem("phoneNumber");
let city = localStorage.getItem("city");
const lastTime = localStorage.getItem("lastTime");
let emailAddress = localStorage.getItem("emailAddress");
let bank = localStorage.getItem("bank");
if (firstName && lastName && username && phoneNumber && city && emailAddress && bank && lastTime) {
    window.location.href = "game.html";
}
try {
$(document).ready(function () {
    let currentDice = 1;

    function changeDice() {
        if (currentDice >= 6) {
            currentDice = 1;
        } else {
            currentDice++;
        }
        $("#dice1").attr("src", `images/dice${currentDice}.png`);
        $("#dice2").attr("src", `images/dice${currentDice}.png`);
    }

    setInterval(changeDice, 150);

    $("#logInForm").validate({
        rules: {
            firstName: {
                required: true,
                pattern: /^[a-zA-Z][a-zA-Z0-9'\s-]{0,28}[a-zA-Z0-9]$/
            },
            lastName: {
                required: true,
                pattern: /^[a-zA-Z][a-zA-Z0-9'\s-]{0,38}[a-zA-Z0-9]$/
            },
            userName: {
                required: true,
                pattern: /^[a-zA-Z][@$%&#]{3}[A-Z][0-9]$/
            },
            phoneNumber: {
                required: true,
                pattern: /^1-\d{3}-\d{3}-\d{4}$/
            },
            city: {
                required: true,
                pattern: /^[a-zA-Z]{1,55}$/
            },
            emailAddress: {
                required: true,
                pattern: /^[\w.-]+@[a-z0-9_]+\.(com|org)$/i
            },
            bank: {
                required: true,
                number: true,
                range: [5, 5000],
                divide: true
            }
        },
        messages: {
            firstName: {
                required: "Please enter a first name.",
                pattern: "First name must start with a letter and end with a letter or number while being 30 characters long."
            },
            lastName: {
                required: "Please enter a last name.",
                pattern: "Last name must start with a letter and end with a letter or number while being 40 characters long."
            },
            userName: {
                required: "Please enter a username.",
                pattern: "Username must start with a lower-case letter, followed by three of @, #, $, %, &, followed by an upper-case letter, and end with a single digit."
            },
            phoneNumber: {
                required: "Please enter a phone number.",
                pattern: "Phone number must be in the format 1-###-###-####."
            },
            city: {
                required: "Please enter a city name.",
                pattern: "City must only contain letters and have a maximum of 55 characters."
            },
            emailAddress: {
                required: "Please enter an email address.",
                pattern: "The email address must be in the format namevalue@domain.(com or org)."
            },
            bank: {
                required: "Please enter a bank amount.",
                number: "Only enter a number without cents.",
                range: "Bank value must be between $5 and $5,000.",
                divide: "Bank value must be divisible by 5."
            }
        },
        submitHandler: function (form) {
            let date = new Date();
            localStorage.setItem("firstName", $("#firstName").val());
            localStorage.setItem("lastName", $("#lastName").val());
            localStorage.setItem("username", $("#userName").val());
            localStorage.setItem("phoneNumber", $("#phoneNumber").val());
            localStorage.setItem("emailAddress", $("#emailAddress").val());
            localStorage.setItem("city", $("#city").val());
            localStorage.setItem("bank", $("#bank").val());
            localStorage.setItem("time", date);
            form.submit();
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.nextAll(".errors").first());
        },
    });

    $.validator.addMethod("divide", function (value, element) {
        return this.optional(element) || (parseInt(value) % 5 === 0);
    }, "Bank value must be divisible by 5.");
});
}
catch (error) {
    console.error("An unexpected error occured! OH NO!");
    window.location.href = "intro.html";
}
