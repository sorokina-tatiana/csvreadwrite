(function() {
    var uploadButton = document.getElementById('upload-button');
    var downloadButton = document.getElementById('download-button');
    var isEditing = false;
    var tableHeaders = [];

    uploadButton.addEventListener('click', function(event) {
        event.preventDefault();
        var data = new FormData();
        data.append('file', document.getElementById('upload-file').files[0]);

        axios.post('/upload', data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function(res) {
            tableHeaders = Object.keys(res.data[0]);

            generateTableHeader();
            generateTableBody(res.data);
        }).catch(function(err) {
            console.log(err)
        });
    });

    downloadButton.addEventListener('click', function() {
        axios.get('/download');
    });

    var generateTableHeader = function() {
        var tableHead = document.getElementById('table-head');
        var tableHeaderRow = document.createElement('tr');

        tableHeaders.forEach(function(key) {
            var th = document.createElement('th');
            th.textContent = key;
            tableHeaderRow.appendChild(th);
        });

        var th = document.createElement('th');
        th.textContent = 'Actions';
        tableHeaderRow.appendChild(th);
        tableHead.appendChild(tableHeaderRow);
    };

    var generateTableBody = function(data) {
        var tableBody = document.getElementById('table-body');

        data.forEach(function(record) {
            var tr = document.createElement('tr');

            Object.keys(record || []).forEach(function(key) {
                var td = document.createElement('td');
                var input = document.createElement('input');
                input.setAttribute('type', 'text');
                input.setAttribute('value', record[key]);
                input.readOnly = true;
                td.appendChild(input);
                tr.appendChild(td);
            });
            var td = document.createElement('td');

            var btnEdit = createButton('Edit', editFields, true);
            td.appendChild(btnEdit);

            var btnSave = createButton('Save', saveChanges, false);
            td.appendChild(btnSave);
            tr.appendChild(td);

            tableBody.appendChild(tr);
        });
    };

    var createButton = function(type, callback, isVisible) {
        var btn = document.createElement('button');
        btn.textContent = type;
        btn.setAttribute('id', type.toLowerCase());
        btn.addEventListener('click', callback);
        if (!isVisible) {
          btn.style.display = 'none';
        }
        return btn;
    };

    var editFields = function(event) {
        if (isEditing) {
           return false;
        }

        var currentElement = event.target;
        toggleTableState(currentElement, true, 'save');
        var cells = getTableCells(currentElement);
        toggleCellsState(cells, false);
    };

    var saveChanges = function(event) {
        var cells = getTableCells(event.target);
        toggleCellsState(cells, true);
        var data = {};
        tableHeaders.forEach(function(item, index) {
            data[item.toLowerCase()] = cells[index].value;
        });

        axios.post('/save-entry', data)
            .then(function(res) {
                toggleTableState(event.target, false, 'edit');
            })
            .catch(function(err) {
                toggleTableState(event.target, false, 'edit');
            })
    };

    var toggleTableState = function(target, isEditingState, btnName) {
        isEditing = isEditingState;
        target.parentNode.querySelector('#' + btnName).style.display = 'block';
        target.style.display = 'none';
    };

    var toggleCellsState = function(cells, readOnly) {
        cells.forEach(function(cell) {
            cell.readOnly = readOnly;
        });
    };

    var getTableCells = function(target) {
        var currentRow = target.parentNode.parentNode;
        return currentRow.querySelectorAll('td input[type="text"]');
    };
})();
