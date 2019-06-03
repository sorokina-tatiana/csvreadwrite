(function() {
    var uploadButton = document.getElementById('upload-button');
    var downloadButton = document.getElementById('download-button');

    uploadButton.addEventListener('click', function(event) {
        event.preventDefault();
        var data = new FormData();
        data.append('file', document.getElementById('upload-file').files[0]);

        axios.post('/upload', data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function(res) {
            generateTableHeader(res.data);
            generateTableBody(res.data);
        }).catch(function(err) {
            console.log(err)
        });
    });

    downloadButton.addEventListener('click', function() {
        axios.get('/download');
    });

    var createButton = function(type, callback) {
        var btn = document.createElement('button');
        btn.textContent = type;
        btn.addEventListener('click', callback);
        return btn;
    };

    var editFields = function(event) {
        console.log(event.target);
    };

    var saveChanges = function(event) {
        var data;

        axios.post('/save-entry', data)
            .then(function(res) {
                console.log(res);
            })
            .catch(function(err) {
                console.log(err);
            })
    };

    var generateTableHeader = function(data) {
        var tableHead = document.getElementById('table-head');
        var tableHeaderRow = document.createElement('tr');

        Object.keys(data[0] || []).forEach(function(key) {
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
                td.textContent = record[key];
                tr.appendChild(td);
            });
            var td = document.createElement('td');

            var btnEdit = createButton('Edit', editFields);
            td.appendChild(btnEdit);

            var btnSave = createButton('Save', saveChanges);
            td.appendChild(btnSave);
            tr.appendChild(td);

            tableBody.appendChild(tr);
        });
    }
})();
