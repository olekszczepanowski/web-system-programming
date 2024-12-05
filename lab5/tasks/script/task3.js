const getRandomInteger = (min, max) => {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
};

const randomNumber = getRandomInteger(1, 100);

console.log("Spróbuj zgadnąć liczbę od 1 do 100!");

function guessNumber() {
  let end = false;
  let i = 0;

  while (!end) {
    const userGuess = parseInt(prompt(`Próba ${i + 1}: Podaj liczbę:`));

    if (isNaN(userGuess)) {
      console.log("Proszę podać liczbę!");
    } else if (userGuess === randomNumber) {
      console.log("Zgadłeś!");
      end = true;
      12;
    } else if (userGuess > randomNumber) {
      console.log("Twój strzał jest większy niż wylosowana liczba!");
    } else {
      console.log("Twój strzał jest mniejszy niż wylosowana liczba!");
    }
    i++;
  }
}

guessNumber();
