document.addEventListener('DOMContentLoaded', function() {
    console.log('DatabaseConnectionCheck.js loaded successfully!');
});

 $(document).ready(function() {
            $("#dbnames1").change(function() {

                var selectedDBName = $("#dbnames1").val()
                $.ajax({
                                    url: '/Main/getDBData',
                                    type: 'GET',
                                    data: { id: selectedDBName },
                                    success: function(data) {

                                        if(selectedDBName == '-1' ){
                                            $("#id").val('');
                                        }else{
                                            $("#id").val(data.id);
                                        }

                                        $("#name").val(data.db_name);
                                        $("#dbsource").val(data.db_connection_name);
                                        $("#dbName").val(data.db_database);
                                        $("#port").val(data.db_port);
                                        $("#hostname").val(data.db_hostname);
                                        $("#username").val(data.db_username);
                                        $("#password").val(data.db_password);
                                    }
                                });

            });
        });


function deleteConnctionString() {

    var selectElement = document.getElementById("dbnames1");
    var scheduleId = selectElement.options[selectElement.selectedIndex].value;
    var deleteUrl = '/Main/databaseSelection/delete/' + scheduleId;

    if (confirm('Are you sure?')) {
        fetch(deleteUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                // Include any additional data you need to send
            })
        })
        .then(response => {
            if (response.ok) {
                // Handle success, maybe redirect or update the UI
                window.location.href = '/Main/databaseSelection'; // Redirect after successful deletion
            } else {
                // Handle error response
                console.error('Error:', response.statusText);
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
}

