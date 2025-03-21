document.addEventListener("DOMContentLoaded", function () {

            let bgElement = document.querySelector(".nishant");
            let imagePath = bgElement.getAttribute("data-bg");
            if (imagePath) {
                bgElement.style.backgroundImage = `url('${imagePath}')`;
            }
        });

