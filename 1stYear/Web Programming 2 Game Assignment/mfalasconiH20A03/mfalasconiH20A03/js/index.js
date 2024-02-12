window.onload = function () {
    const canvas = document.querySelector('#titleCanvas');
    const ctx = canvas.getContext('2d');
    canvas.width = 800;
    canvas.height = 200;

    const letters = [
        { letter: 'C', x: 100, y: 100 },
        { letter: 'H', x: 200, y: 100 },
        { letter: 'O', x: 300, y: 100 },
        { letter: '-', x: 400, y: 100 },
        { letter: 'H', x: 500, y: 100 },
        { letter: 'A', x: 600, y: 100 },
        { letter: 'N', x: 700, y: 100 },
    ];

    ctx.font = 'bold 135px Arial Black';
    ctx.fillStyle = '#3b3a3a';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';

    let index = -1;
    function animateCanvas() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        let length = letters.length;
        for (let i = 0; i < length; i++) {
            const letter = letters[i];
            if (i <= index) {
                ctx.fillStyle = '#e3e3e3';
                ctx.shadowColor = '#e3e3e3';
                ctx.shadowBlur = 30;
            } else {
                ctx.fillStyle = '#3b3a3a';
                ctx.shadowColor = 'transparent';
            }
            ctx.fillText(letter.letter, letter.x, letter.y);
        }
        if (index < length - 1) {
            index++;
            setTimeout(animateCanvas, 750);
        } else {
            setTimeout(dark, 750);
            setTimeout(light, 1500);
            setTimeout(dark, 2250);
            setTimeout(light, 3000);
            index = -1;
            setTimeout(animateCanvas, 3750);
        }
    }

    function dark() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        for (const letter of letters) {
            ctx.fillStyle = '#3b3a3a';
            ctx.shadowColor = 'transparent';
            ctx.fillText(letter.letter, letter.x, letter.y);
        }
    }

    function light() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        for (const letter of letters) {
            ctx.fillStyle = '#e3e3e3';
            ctx.shadowColor = '#e3e3e3';
            ctx.shadowBlur = 30;
            ctx.fillText(letter.letter, letter.x, letter.y);
        }
    }
    animateCanvas();

    let currentDice = 1;
    let diceImg1 = document.querySelector("#dice1");
    let diceImg2 = document.querySelector("#dice2");

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

    function changeIntro() {
        window.location.href = "intro.html";
    }
    setTimeout(changeIntro, 15000);
}


