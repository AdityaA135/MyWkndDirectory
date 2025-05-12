console.log("calling wknd.dialog");

$(document).on("click", ".page-sort-class", function (e) {
    console.log("Sort Button Clicked");

    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    let selectedPagePaths = [];

    checkboxes.forEach((checkbox) => {
        const pagePath = checkbox.parentElement.parentElement.getAttribute("data-foundation-collection-item-id");
        if (pagePath) selectedPagePaths.push(pagePath);
    });

    if (selectedPagePaths.length === 0) {
        alert("Please select at least one page.");
        return;
    }

    if (selectedPagePaths.length > 1) {
        alert("Please select only one parent page at a time.");
        return;
    }

    const pagePath = selectedPagePaths[0];
    const encodedPath = encodeURIComponent(pagePath);

    fetch(`/bin/sortpagestitle?pagePath=${encodedPath}`)
        .then(response => response.text())
        .then(result => {
            alert("Pages sorted by title: " + result);
        })
        .catch(err => {
            console.error("Error sorting pages", err);
            alert("Failed to sort pages.");
        });
});

$(document).on("click", ".page-sort-date-class", function (e) {
    console.log("Sort Button Clicked");

    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    let selectedPagePaths = [];

    checkboxes.forEach((checkbox) => {
        const pagePath = checkbox.parentElement.parentElement.getAttribute("data-foundation-collection-item-id");
        if (pagePath) selectedPagePaths.push(pagePath);
    });

    if (selectedPagePaths.length === 0) {
        alert("Please select at least one page.");
        return;
    }

    if (selectedPagePaths.length > 1) {
        alert("Please select only one parent page at a time.");
        return;
    }

    const pagePath = selectedPagePaths[0];
    const encodedPath = encodeURIComponent(pagePath);

    fetch(`/bin/sortpagesdate?pagePath=${encodedPath}`)
        .then(response => response.text())
        .then(result => {
            alert("Pages sorted by date: " + result);
        })
        .catch(err => {
            console.error("Error sorting pages", err);
            alert("Failed to sort pages.");
        });
});
