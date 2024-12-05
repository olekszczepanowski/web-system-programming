function submitForm(event) {
  event.preventDefault();
  alert("Jesteś prawdziwym Wrocławianinem!");
}

function confirmReset(event) {
  event.preventDefault();
  const confirmation = prompt("Potwierdzam, aby zresetować formularz");
  if (confirmation === "Potwierdzam") {
    document.getElementById("wroclaw-form").reset();
  }
}

function showHelp(text) {
  const helpText = document.getElementById("help-text");
  if (helpText) {
    helpText.textContent = text;
  }
}

function hideHelp() {
  const helpText = document.getElementById("help-text");
  if (helpText) {
    helpText.textContent = "";
  }
}

function confirmSubmit() {
  return confirm("Czy na pewno chcesz wysłać formularz?");
}
