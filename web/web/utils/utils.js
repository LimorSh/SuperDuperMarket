function addElemToTable(elem, tableBodyId, tableCellClass) {
    let tableBody = document.getElementById(tableBodyId);
    let row = tableBody.insertRow();
    let cell;
    Object.keys(elem).forEach(function(key) {
        cell = row.insertCell();
        cell.classList.add(tableCellClass);
        cell.textContent = elem[key];
    })
}