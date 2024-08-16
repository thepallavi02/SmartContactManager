
    document.addEventListener("DOMContentLoaded", function() {
        const themeToggleButton = document.getElementById("theme-toggle");
        const themeToggleText = document.getElementById("theme-toggle-text");

        // Check the current theme from localStorage
        if (localStorage.getItem("theme") === "dark") {
            document.documentElement.classList.add("dark");
            themeToggleText.textContent = "Light";
        } else {
            document.documentElement.classList.remove("dark");
            themeToggleText.textContent = "Dark";
        }

        // Add click event listener to toggle button
        themeToggleButton.addEventListener("click", function() {
            if (document.documentElement.classList.contains("dark")) {
                document.documentElement.classList.remove("dark");
                themeToggleText.textContent = "Dark";
                localStorage.setItem("theme", "light");
            } else {
                document.documentElement.classList.add("dark");
                themeToggleText.textContent = "Light";
                localStorage.setItem("theme", "dark");
            }
        });
    });

