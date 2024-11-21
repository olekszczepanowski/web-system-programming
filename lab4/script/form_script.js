function submitForm(event) {
  event.preventDefault();
  alert("Jesteś prawdziwym Wrocławianinem!");
}

function confirmReset(event) {
  event.preventDefault();
  let confirmation = prompt("Potwierdzam, aby zresetować formularz");
  if (confirmation === "Potwierdzam") {
    document.getElementById("wroclaw-form").reset();
  }
}
