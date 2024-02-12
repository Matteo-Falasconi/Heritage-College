let quer = (selector) => document.querySelector(selector);
const bet = quer("#bet");
const even = quer("#even");
const odd = quer("#odd");
const roll = quer("#roll");
const errors = quer(".errors");
let firstName = localStorage.getItem("firstName");
let lastName = localStorage.getItem("lastName");
let username = localStorage.getItem("username");
let phoneNumber = localStorage.getItem("phoneNumber")
let city = localStorage.getItem("city");
let emailAddress = localStorage.getItem("emailAddress")
let bank = Number(localStorage.getItem("bank"));
let time = localStorage.getItem("time");
const lastTime = localStorage.getItem("lastTime");
let guess = "";
let diceN1 = quer("#dice1");
let diceN2 = quer("#dice2");
let ol = quer("#userInfo ol");
let dice = new Dice();

let quit = quer("#quit");
quit.addEventListener("click", () => {
  localStorage.setItem("lastTime", time);
});

let change = quer("#change");
change.addEventListener("click", () => {
    localStorage.clear();
})

try {
if (lastTime) {
    let li = document.createElement("li");
    li.textContent = `Welcome back, ${firstName} ${lastName}.`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `Your phone number is ${phoneNumber} and your email address is ${emailAddress}.`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `You have $${bank} left in your bank account.`
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `You last visited was on ${lastTime}.`
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `If you are not ${firstName} ${lastName} just click on "Change User" to change your user info.`
    ol.appendChild(li);
} else {
    let li = document.createElement("li");
    li.textContent = `Player Name: ${firstName} ${lastName}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `Username: ${username}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `Phone Number: ${phoneNumber}`
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `City: ${city}`
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `Email Address: ${emailAddress}`
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent = `Time: ${time}`
    ol.appendChild(li);
}

function validateGuess() {
    const error = quer("#chooseError");
    if (even.checked) {
        guess = "even";
        error.textContent = "";
        return true;
    } else if (odd.checked) {
        guess = "odd";
        error.textContent = "";
        return true;
    } else {
        const errorMsg = "Select one of the options.\n";
        error.textContent = errorMsg;
        return false;
    }
}
bet.addEventListener("change", validateBet);

function validateBet() {
    const error = quer("#bettingError");
    const betAmount = Number(bet.value);
    if (betAmount <= bank) {
        error.textContent = "";
        return true;
    } else if (betAmount <= 0) {
        const errorMsg = `Please bet a number from 1 - ${bank}.\n`;
        error.textContent = errorMsg;
        return false;
    } else {
        const errorMsg = `Your bet amount is higher than your bank amount (${bank}).\n`;
        error.textContent = errorMsg;
        return false;
    }
}

function isValidGame() {
    let valid = true;
    if (!validateGuess()) valid = false;
    if (!validateBet()) valid = false;
    return valid;
}

roll.addEventListener("click", validateGame)
function validateGame() {
    if (isValidGame()) {
        errors.textContent = "";
        bet.textContent = "";
        even.checked = false;
        odd.checked = false;
        let dice1 = dice.dice1Roll();
        let dice2 = dice.dice2Roll();
        rolling(dice1, dice2);
    }
}

let user = new User(firstName, lastName, username, phoneNumber, city, emailAddress, bank);
let game;
function result(dice1, dice2) {
    game = new Game(Number(bet.value), guess);
    let ol = document.querySelector("#results ol");
    while (ol.lastChild) {
        ol.removeChild(ol.lastChild);
    }
    let sum = dice.addDice();
    let moneyGained = user.moneyWon(Number(bet.value), guess, dice1, dice2, sum, bank);
    let li = document.createElement("li");
    li.textContent = `Bet amount: ${Number(bet.value)}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent += `Guessed: ${guess}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent += `Dice rolled: ${dice1} and ${dice2}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent += `Sum: ${sum}`;
    ol.appendChild(li);
    if ((sum % 2 == 0 && guess == "even") || (sum % 2 != 0 && guess == "odd")) {
        li = document.createElement("li");
        li.textContent += `Game Status: Won! DANCE PARTY!!`;
        ol.appendChild(li);
        shakeWin();
    } else {
        li = document.createElement("li");
        li.textContent += `Game Status: Lost...`;
        ol.appendChild(li);
        shakeLose();
    }
    li = document.createElement("li");
    li.textContent += `Money earned: ${moneyGained - Number(bet.value)}`;
    ol.appendChild(li);
    li = document.createElement("li");
    li.textContent += `Bank amount: ${(bank - Number(bet.value)) + moneyGained}`;
    ol.appendChild(li);

    if (bank > 0) {
        bank = bank - Number(bet.value) + moneyGained;
        localStorage.setItem("bank", bank);
        localStorage.getItem("bank");
    }
    if (bank <= 0) {
        localStorage.clear();
        window.location.href = "intro.html";
    }
}

function shakeWin() {
    let count = 0;
    const shakeIt = setInterval(() => {
        if (count % 2 == 0) {
            diceN1.style.transform = "translateX(30px) rotate(30deg)";
            diceN2.style.transform = "translateX(-30px) rotate(-30deg)";
        } else {
            diceN1.style.transform = "translateX(-30px) rotate(-30deg)";
            diceN2.style.transform = "translateX(30px) rotate(30deg)";
        }
        count++;
        if (count == 10) {
            clearInterval(shakeIt);
            diceN1.style.transform = "";
            diceN2.style.transform = "";
        }
    }, 50);
}

function shakeLose() {
    let count = 0;
    const shakeIt = setInterval(() => {
        if (count % 2 === 0) {
            diceN1.style.transform = "translateX(2px) rotate(2deg)";
            diceN2.style.transform = "translateX(-2px) rotate(-2deg)";
        } else {
            diceN1.style.transform = "translateX(-2px) rotate(-2deg)";
            diceN2.style.transform = "translateX(2px) rotate(2deg)";
        }
        count++;
        if (count == 10) {
            clearInterval(shakeIt);
            diceN1.style.transform = "";
            diceN2.style.transform = "";
        }
    }, 50);
}

function rolling(dice1, dice2) {
    let currentDice = 1;
    const interval = setInterval(() => {
        if (currentDice >= 6) {
            currentDice = 1;
        } else {
            currentDice++;
        }
        diceN1.src = `images/dice${currentDice}.png`;
        diceN2.src = `images/dice${currentDice}.png`;
    }, 100);

    setTimeout(() => {
        clearInterval(interval);
        dices(dice1, dice2);
    }, 1500);
}

function dices(dice1, dice2) {
    diceN1.src = `images/dice${dice1}.png`;
    diceN2.src = `images/dice${dice2}.png`;
    result(dice1, dice2);
}

let currentDice = 1;
let diceImg1 = quer("#diceImg1");
let diceImg2 = quer("#diceImg2");
function changeDice() {
    if (currentDice >= 6) {
        currentDice = 1;
    } else {
        currentDice++;
    }
    diceImg1.src = `images/dice${currentDice}.png`;
    diceImg2.src = `images/dice${currentDice}.png`;
}
setInterval(changeDice, 150);
}
catch (error) {
    console.error("An unexpected error occured! OH NO!");
    window.location.href = "intro.html";
}
