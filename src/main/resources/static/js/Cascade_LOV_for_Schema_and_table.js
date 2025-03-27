$(document).ready(function() {

    var s = '<option value=' + -1 + '>SELECT</option>';
    $('#dbcheck').html(s);

   $("#dbschema").change(function() {

      var SchemaName = $(this).val();
      var s = '<option value=' + -1 + '>SELECT</option>';

      if (SchemaName != "") {
      	$.ajax({
        url : 'getTables',
        data : { "SchemaName" : SchemaName },
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        	  s += '<option value="' + result[i] + '">'+ result[i]+ '</option>';
        	}
        	$('#dbtable').html(s);
        }
      });
     }
     //reset data
     $('#dbtable').html(s);
     $('#dbcolumn').html(s);
   });


   $("#dbtable").change(function() {

      var SchemaName = $("#dbschema").val();
      var TableName = $("#dbtable").val();

      var s = '<option value=' + -1 + '>SELECT</option>';

      if (SchemaName != "" && TableName != "") {

      	$.ajax({
        url : 'getColumns',
        data : { "SchemaName" : SchemaName,"TableName" : TableName },
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        	  s += '<option value="' + result[i] + '">'+ result[i]+ '</option>';
        	}
        	$('#dbcolumn').html(s);
        }
      });
     }

     $('#dbcolumn').html(s);


   });



//----------------------------Ram Added -------------------------

   $("#dbcolumn").change(function() {

       const queryString = window.location.search;
       const urlParams = new URLSearchParams(queryString);
       const name = urlParams.get('trans');



        var trans = "";

        if (name == 1 ) {
            trans = "DQ Profiling";
        } else {
            trans = "DQ Custom";
        }

       var ruleType = trans;
       var schemaName = $("#dbschema").val();
       var tableName = $("#dbtable").val();
       var columnName = $(this).val();

        $('#dbcheck').html('');
        
       var options = '<option value="-1">SELECT</option>';

       if (schemaName && tableName && columnName) {
           $.ajax({
               url: 'getRules',
               data: {"RuleType":ruleType,"SchemaName": schemaName,"TableName": tableName,"ColumnName": columnName},
               success: function(result) {
                   var parsedResult = JSON.parse(result);
                   parsedResult.forEach(function(item) {
                       options += '<option value="' + item + '">' + item + '</option>';
                   });

                   $('#dbcheck').html(options);
                   $('#dbcheck option:first').remove();
               }
           });
       } else {
           $('#dbcheck').html(options);
           $('#dbcheck option:first').remove();
       }
   });

 //----------------------End of changes -------------------------

  $('#editMetadataModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var id = button.data('id');
    var schema = button.data('schema');
    var table = button.data('table');
    var column = button.data('column');
    var check = button.data('check');
    var campaign = button.data('campaign');

    var modal = $(this);
    modal.find('#editId').val(id);
    modal.find('#editSchema').val(schema);
    modal.find('#editTable').val(table);
    modal.find('#editColumn').val(column);
    modal.find('#editCheck').val(check);
    modal.find('#editCampaign').val(campaign);
  });

});
