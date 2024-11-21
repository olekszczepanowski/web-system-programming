function changeBackgroundOnResize() {
  if (window.innerWidth < 800) {
    document.body.style.backgroundColor = "lightblue";
  } else {
    document.body.style.backgroundColor = "lightgreen";
  }
}

window.addEventListener("resize", changeBackgroundOnResize);

changeBackgroundOnResize();

function get404Error() {
  document.writeln("404. Not Found");
}

document.getElementById("404-getter").addEventListener("click", get404Error);
