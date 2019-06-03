(function() {
    var uploadButton = document.getElementById('upload-button');
    var tableBody = document.getElementById('table-body');
    var tableHead = document.getElementById("table-head")
    uploadButton.addEventListener('click', function(event) {
        event.preventDefault();
        var data = new FormData();
        data.append('file', document.getElementById('upload-file').files[0]);

        axios.post('/upload', data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function(res) {
            console.log(res);
            var tableHeader = document.createElement('tr');
            Object.keys(res.data[0] || []).forEach(function(key) {
                var th = document.createElement('th');
                th.textContent = key;
                tableHeader.appendChild(th);
            });
            tableHead.appendChild(tableHeader);

            res.data.forEach(function(record) {
                var tr = document.createElement('tr');
                Object.keys(record || []).forEach(function(key) {
                    var td = document.createElement('td');
                    td.textContent = record[key];
                    tr.appendChild(td);
                });
                tableBody.appendChild(tr);
            });
        }).catch(function(err) {
            console.log(err)
        });
    });
})();
