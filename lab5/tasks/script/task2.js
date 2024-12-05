const getRandomInteger = (min, max) => {
  min = Math.ceil(min);
  max = Math.floor(max);

  return Math.floor(Math.random() * (max - min)) + min;
};

const randomNumber = getRandomInteger(1, 100);
let attempts = 3;

console.log("Spróbuj zgadnąć liczbę od 1 do 100! Masz 3 próby.");

function guessNumber() {
  for (let i = 1; i <= attempts; i++) {
    const userGuess = parseInt(prompt(`Próba ${i}: Podaj liczbę:`));

    if (userGuess === randomNumber) {
      console.log("Brawo! Zgadłeś wylosowaną liczbę!");
      return;
    } else if (userGuess > randomNumber) {
      console.log("Twój strzał jest wiekszy niż wylosowana liczba!");
    } else {
      console.log("Twój strzał jest mnijejszy niż wylosowana liczba!");
    }
  }

  console.log(`Niestety, przegrałeś. Wylosowana liczba to: ${randomNumber}.`);
}

guessNumber();
