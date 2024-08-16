console.log("admin user");

document.querySelector("#image_file_input").addEventListener("change", function (event) {
    let file = event.target.files[0];
    let preview = document.querySelector("#upload_image_preview");

    if (file) {
      let reader = new FileReader();
      reader.onload = function () {
        preview.setAttribute("src", reader.result);
        preview.style.display = "block"; // Show image when file is loaded
      };
      reader.readAsDataURL(file);
    } else {
      preview.style.display = "none"; // Hide image if no file is selected
    }
  });

// Initially hide the image preview on page load
document.querySelector("#upload_image_preview").style.display = "none";
