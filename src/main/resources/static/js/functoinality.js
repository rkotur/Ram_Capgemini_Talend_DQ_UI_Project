 function toggleDropdown(dropdownId) {
    var dropdownContent = document.getElementById(dropdownId);
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  }

  // Close dropdowns when clicking outside of them
  document.addEventListener("click", function(event) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
      if (!event.target.matches('.dropbtn') && !dropdowns[i].contains(event.target)) {
        dropdowns[i].style.display = "none";
      }
    }
  });

  // Function to update the data grid with selected dropdown values
  // Function to remove the row when the remove button is clicked
function removeRow(row) {
  row.parentNode.removeChild(row);
}

// Function to update the data grid with selected dropdown values
function updateDataGrid() {
  var dropdown1 = document.getElementById("schema-dropdown").value;
  var dropdown2 = document.getElementById("table-dropdown").value;
  var dropdown3 = document.getElementById("column-dropdown").value;
  var table = document.getElementById("dataGrid").getElementsByTagName('tbody')[0];

  // Check if the combination already exists in the table
  var rows = table.getElementsByTagName('tr');
  for (var i = 0; i < rows.length; i++) {
    var cells = rows[i].getElementsByTagName('td');
    if (cells[0].textContent === dropdown1 &&
        cells[1].textContent === dropdown2 &&
        cells[2].textContent === dropdown3) {
      alert("This combination already exists!");
      return; // Exit the function if the combination exists
    }
  }

  // Create a new row
  var newRow = table.insertRow();
  var cell1 = newRow.insertCell(0);
  var cell2 = newRow.insertCell(1);
  var cell3 = newRow.insertCell(2);
  var cell4 = newRow.insertCell(3);

  // Populate cells with selected dropdown values
  cell1.textContent = dropdown1;
  cell2.textContent = dropdown2;
  cell3.textContent = dropdown3;

  // Create remove button
  var removeBtn = document.createElement("button");
  removeBtn.textContent = "Remove";
  removeBtn.className = "remove-btn";
  removeBtn.onclick = function() {
    removeRow(newRow); // Call removeRow function passing the row to be removed
  };

  // Append remove button to cell
  cell4.appendChild(removeBtn);
}

// Function to display the data grid and update the table on Apply button click
function apply() {
  updateDataGrid();
  var dataGrid = document.getElementById("dataGrid");
  dataGrid.style.display = "table";
}
