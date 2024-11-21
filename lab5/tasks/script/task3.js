const randomNumber = getRandomInteger();

console.log("Spróbuj zgadnąć liczbę od 1 do 100!");

function guessNumber() {
  let end = false;
  let i = 0;
  while (!end) {
    const userGuess = parseInt(prompt(`Próba ${i}: Podaj liczbę:`));
    if (userGuess === randomNumber) {
      console.log("Zgadłeś!");
      end = true;
    } else if (userGuess > randomNumber) {
      console.log("Twój strzał jest wiekszy niż wylosowana liczba!");
    } else {
      console.log("Twój strzał jest mnijejszy niż wylosowana liczba!");
    }
    i++;
  }
}

guessNumber();
