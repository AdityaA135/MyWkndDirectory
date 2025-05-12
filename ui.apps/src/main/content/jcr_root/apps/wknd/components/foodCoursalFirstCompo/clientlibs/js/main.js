document.addEventListener("DOMContentLoaded", function () {
  const ingredientButtons = document.querySelectorAll(".ingredient-btn");
  const selectedContainer = document.querySelector(".selected-ingredients");

  // Add event listeners to suggested ingredient buttons
  ingredientButtons.forEach((btn) => {
    btn.addEventListener("click", function () {
      const ingredientName = btn.getAttribute("data-name");

      // Avoid duplicate chips
      if (selectedContainer.querySelector(`[data-name="${ingredientName}"]`)) return;

      // Create chip
      const chip = document.createElement("div");
      chip.className = "chip";
      chip.setAttribute("data-name", ingredientName);
      chip.innerHTML = `
        ${ingredientName} <span class="remove-chip" style="cursor:pointer;">&times;</span>
      `;

      // Append to selected
      selectedContainer.appendChild(chip);

      // Optional: mark button as selected
      btn.classList.add("active");

      // Remove chip on click
      chip.querySelector(".remove-chip").addEventListener("click", function () {
        chip.remove();
        btn.classList.remove("active");
      });
    });
  });
});
