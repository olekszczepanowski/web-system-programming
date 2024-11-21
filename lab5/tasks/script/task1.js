const months = [
  "Styczeń",
  "Luty",
  "Marzec",
  "Kwiecień",
  "Maj",
  "Czerwiec",
  "Lipiec",
  "Sierpień",
  "Wrzesień",
  "Październik",
  "Listopad",
  "Grudzień",
];

const randomMonth = months[Math.floor(Math.random() * months.length)];

let attempts = 3;

console.log("Spróbuj zgadnąć miesiąć! Masz 3 próby.");

function guessMonth() {
  for (let i = 1; i <= attempts; i++) {
    const userGuess = prompt(`Próba ${i}: Podaj nazwę miesiąca:`);

    if (userGuess === randomMonth) {
      console.log("Brawo! Zgadłeś wylosowany miesiąc!");
      return;
    } else {
      console.log("Źle! Spróbuj ponownie.");
    }
  }

  console.log(`Niestety, przegrałeś. Wylosowany miesiąc to: ${randomMonth}.`);
}

guessMonth();
