let isBlack = false;

function changeTheme() {
  if (isBlack) {
    document.body.style.backgroundImage =
      'url("https://ocdn.eu/pulscms-transforms/1/G0ek9kpTURBXy9hZmZmNGU1ODBjZmI5NGI4YTRmZWZlNjIxMTNjMzgzYy5qcGeTlQPNAe4AzRvHzQ-glQLNBLAAw8OTCaY2Y2Q1MDcG3gABoTAB/wroclaw.jpeg")';
    document.body.style.backgroundColor = "";
  } else {
    document.body.style.backgroundImage = "none";
    document.body.style.backgroundColor = "black";
  }
  isBlack = !isBlack;
}

document.getElementById("theme-changer").addEventListener("click", changeTheme);

function getRandomPosition() {
  const randomPosition = Math.floor(Math.random() * 18) + 1;

  document.getElementById("generated-position").innerHTML = randomPosition;
}

function get404Error() {
  document.writeln("404. Not Found");
}

document.getElementById("404-getter").addEventListener("click", get404Error);
