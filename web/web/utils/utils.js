function addElemToTable(elem, tableBodyId, tableCellId) {
    let tableBody = document.getElementById(tableBodyId);
    let row = tableBody.insertRow();
    Object.keys(elem).forEach(function(key) {
        let cell = row.insertCell();
        cell.classList.add(tableCellId);
        cell.textContent = elem[key];
    })
}