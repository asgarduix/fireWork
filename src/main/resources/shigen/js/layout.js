// function myFunction() {
//     var x = document.getElementsByClassName("menu-section");
//     if (x.style.display === "none") {
//         x.style.display = "block";
//     } else {
//         x.style.display = "none";
//     }
// }

var $menuSection = document.getElementsByClassName("menu-section");

function menuToggle() {
    if ($menuSection.classList) {
        $menuSection.classList.toggle("open");
    }

    if ($menuSection.classList) {
        $menuSection.classList.toggle("close");
    }
}
