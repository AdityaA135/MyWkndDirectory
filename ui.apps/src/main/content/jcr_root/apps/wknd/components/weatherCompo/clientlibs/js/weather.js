document.getElementById("weatherForm").addEventListener("submit", async function (e) {
  e.preventDefault();
  const city = document.getElementById("city").value;

  try {
    const response = await fetch("/bin/weatherServlet?city=" + encodeURIComponent(city));
    const data = await response.json();

    const resultDiv = document.getElementById("weatherResult");
    resultDiv.innerHTML = "";

    if (data.error) {
      resultDiv.innerHTML = `<p style="color: red;">${data.error}</p>`;
    } else {
      const list = document.createElement("ul");
      list.style.listStyle = "none";
      list.style.padding = "0";

      for (const key in data) {
        const item = document.createElement("li");
        item.innerHTML = `<strong>${key}:</strong> ${data[key]}`;
        list.appendChild(item);
      }

      resultDiv.appendChild(list);
    }
  } catch (error) {
    console.error("Error fetching weather:", error);
    document.getElementById("weatherResult").innerHTML = `<p style="color: red;">Unable to fetch weather data.</p>`;
  }
});
