function sumNumbers() {
  const N = parseInt(prompt("Podaj, ile liczb chcesz wpisać (N):"));
  if (isNaN(N) || N <= 0) {
    alert("Podaj poprawną liczbę większą od 0.");
    return;
  }

  let sum = 0;

  for (let i = 1; i <= N; i++) {
    const num = parseFloat(prompt(`Podaj liczbę ${i} z ${N}:`));
    if (isNaN(num)) {
      alert("To nie jest poprawna liczba. Spróbuj ponownie.");
      i--;
      continue;
    }
    sum += num;
    alert(`Suma dotychczasowych liczb wynosi: ${sum}`);
  }

  alert(`Ostateczna suma ${N} liczb to: ${sum}`);
}

sumNumbers();
