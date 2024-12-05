let isBlack = false;

function changeTheme() {
  if (isBlack) {
    document.body.style.backgroundImage =
      'url("https://ocdn.eu/pulscms-transforms/1/G0ek9kpTURBXy9hZmZmNGU1ODBjZmI5NGI4YTRmZWZlNjIxMTNjMzgzYy5qcGeTlQPNAe4AzRvHzQ-glQLNBLAAw8OTCaY2Y2Q1MDcG3gABoTAB/wroclaw.jpeg")';
    document.body.style.backgroundColor = "";
    document.querySelector("h1").style.color = "blue";
    document.querySelector("h1").style.fontFamily = "Cambria";
  } else {
    document.body.style.backgroundImage = "none";
    document.body.style.backgroundColor = "black";
    document.querySelector("h1").style.color = "white";
    document.querySelector("h1").style.fontFamily = "Times New Roman";
  }
  isBlack = !isBlack;
}

function addElementToList() {
  const ul = document.getElementById("task1-list");

  const li1 = document.createElement("li");
  li1.appendChild(document.createTextNode("Nowy element listy"));
  ul.appendChild(li1);

  console.log("Parent of ul:", ul.parentNode);
}

function addElementBeforeRynek() {
  const ul = document.getElementById("task1-list");
  const rynekLi = document.getElementById("rynek");
  const li2 = document.createElement("li");
  li2.appendChild(document.createTextNode("Element przed rynkiem"));
  ul.insertBefore(li2, rynekLi);
}

function replaceOstrow() {
  const ul = document.getElementById("task1-list");
  const ostrowLi = document.getElementById("ostrow");
  const li3 = document.createElement("li");
  li3.appendChild(document.createTextNode("Element za Ostrów Tumski"));
  ul.replaceChild(li3, ostrowLi);
}
function deleteHala() {
  const ul = document.getElementById("task1-list");
  const halaLi = document.getElementById("hala");

  ul.removeChild(halaLi);
}

function showCollections() {
  console.log("Images:", document.images);
  console.log("Links:", document.links);
  console.log("Forms:", document.forms);
  console.log("Anchors:", document.anchors);

  console.log("Pierwszy obrazek:", document.images.item(0));
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

function changeFont() {
  const selectedFont = document.getElementById("font-family").value;

  document.body.style.fontFamily = selectedFont;
}

function logEvent(event) {
  console.log(
    `Key info - Alt: ${event.altKey}, Ctrl: ${event.ctrlKey}, Shift: ${event.shiftKey}`
  );
  console.log(
    `Mouse position - ClientX: ${event.clientX}, ClientY: ${event.clientY}`
  );
  console.log(
    `Screen position - ScreenX: ${event.screenX}, ScreenY: ${event.screenY}`
  );
}
