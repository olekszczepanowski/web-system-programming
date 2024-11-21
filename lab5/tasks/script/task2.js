const randomNumber = getRandomInteger(1, 100);
let attempts = 3;

console.log("Spróbuj zgadnąć liczbę od 1 do 100! Masz 3 próby.");

function guessNumber() {
  for (let i = 1; i <= attempts; i++) {
    const userGuess = parseInt(prompt(`Próba ${i}: Podaj liczbę:`));

    if (userGuess === randomNumber) {
      console.log("Brawo! Zgadłeś wylosowany miesiąc!");
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
