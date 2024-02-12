class User {
    firstName;
    lastName;
    userName;
    phoneNumber;
    city;
    emailAddress;
    bank;
    

    constructor(firstName, lastName, userName, phoneNumber, city, emailAddress, bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.emailAddress = emailAddress;
        this.bank = bank;
    }

    fullName() {
        return `${this.firstName} ${this.lastName}`;
    }

    moneyWon(bet, guess, dice1, dice2, addDice, bank) {
        let game = new Game(bet, guess);
        let money = game.betting(dice1, dice2, addDice, bank);
        this.bank += money;
        this.bank -= bet;
        return money;
    }
}

class Dice {
    constructor() {
        this.dice1Roll();
        this.dice2Roll();
    }
    dice1Roll(dice) {
        if (dice === undefined && isNaN(dice)) {
            this.dice1 = Math.floor(Math.random() * 6) + 1;
        }
        else {
            this.dice1 = dice;
        }
        return this.dice1;
    }
    dice2Roll(dice) {
        if (dice === undefined && isNaN(dice)) {
            this.dice2 = Math.floor(Math.random() * 6) + 1;
        }
        else {
            this.dice2 = dice;
        }
        return this.dice2;
    }
    addDice() {
        return this.dice1 + this.dice2;
    }
}

class Game {
    bet;
    guess;
    constructor(bet, guess) {
        this.bet = bet;
        this.guess = guess;
    }

    betting(dice1, dice2, addDice, bank) {
        if (this.bet <= bank && this.bet >= 0) {
            if (addDice % 2 == 0) {
                if (this.guess == "even") {
                    if ((dice1 == 1 && dice2 == 1)) {
                        return this.bet + this.bet;
                    }
                    else if (dice1 == 2 && dice2 == 2) {
                        return this.bet += (this.bet * 2);
                    }
                    else if (dice1 === 3 && dice2 === 3) {
                        return this.bet += (this.bet * 3);
                    }
                    else if (dice1 == 4 && dice2 == 4) {
                        return this.bet += (this.bet * 4);
                    }
                    else if (dice1 == 5 && dice2 == 5) {
                        return this.bet += (this.bet * 5);
                    }
                    else if (dice1 == 6 && dice2 == 6) {
                        return this.bet += (this.bet * 6);
                    }
                    else {
                        return this.bet;
                    }
                } 
                else {
                    return this.bet -= this.bet;
                }
            }
            else {
                if (this.guess == "odd") {
                    if ((dice1 == 1 && dice2 == 2) || (dice1 == 3 && dice2 == 6) || (dice1 == 2 && dice2 == 1) || (dice1 == 6 && dice2 == 3)) {
                        return this.bet += (this.bet * 0.5);
                    }
                    else if ((dice1 == 1 && dice2 == 4) || (dice1 == 4 && dice2 == 1)) {
                        return this.bet += (this.bet * 0.25);
                    }
                    else if ((dice1 == 1 && dice2 == 6) || (dice1 == 6 && dice2 == 1)) {
                        return this.bet += (this.bet * 0.17);
                    }
                    else if ((dice1 == 2 && dice2 == 3) || (dice1 == 3 && dice2 == 2)) {
                        return this.bet += (this.bet * 0.67);
                    }
                    else if ((dice1 == 2 && dice2 == 5) || (dice1 == 5 && dice2 == 2)) {
                        return this.bet += (this.bet * 0.4);
                    }
                    else if ((dice1 == 3 && dice2 == 4) || (dice1 == 3 && dice2 == 4)) {
                        return this.bet += (this.bet * 0.75);
                    }
                    else if ((dice1 == 4 && dice2 == 5) || (dice1 == 5 && dice2 == 4)) {
                        return this.bet += (this.bet * 0.8);
                    }
                    else {
                        return (this.bet += (this.bet * 0.83)).toFixed(2);
                    }
                }
                else {
                    return this.bet -= this.bet;
                }
            }
        }
    }

}