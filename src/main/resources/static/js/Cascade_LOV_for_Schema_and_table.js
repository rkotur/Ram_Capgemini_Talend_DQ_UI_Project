$(document).ready(function() {
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
        	 // s += '<option value="' + result[i][0] + '">'+ result[i][0]+ '</option>';
        	  s += '<option value="' + result[i] + '">'+ result[i]+ '</option>';
        	}
        	$('#dbtable').html(s);
        }
      });
     }
     //reset data
     $('#dbtable').html(s);
     $('#dbcolumn').html(s);

     //alert($("#dbschema").val());
     //alert($("#dbtable").val());

   });


   $("#dbtable").change(function() {

      var SchemaName = $("#dbschema").val();
      var TableName = $("#dbtable").val();

      //var s = '<option value=' + -1 + '>SELECT</option>';
      var s = '<option value=' + -1 + '>SELECT</option>';


      if (SchemaName != "" && TableName != "") {
      	$.ajax({
        url : 'getColumns',
        data : { "SchemaName" : SchemaName,"TableName" : TableName },
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        	 // s += '<option value="' + result[i][0] + '">'+ result[i][0]+ '</option>';
        	  s += '<option value="' + result[i] + '">'+ result[i]+ '</option>';
        	}
        	$('#dbcolumn').html(s);
        }
      });
     }
     //reset data
     //$('#dbtable').html(s);
     $('#dbcolumn').html(s);

     //alert($("#dbschema").val());
     //alert($("#dbtable").val());

   });



/*
   $("#dbtable").change(function() {
      var TableName = $(this).val();
      var s = '<option value=' + -1 + '>SELECT</option>';
      if (stateId > 0) {
      	$.ajax({
        url : 'getColumns',
        data : {"stateId" : TableName},
        success : function(result) {
        	var result = JSON.parse(result);
        	for (var i = 0; i < result.length; i++) {
        		s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
        	}
        	$('#cities').html(s);
        }
       });
     }
     //reset data
     $('#cities').html(s);
  });
  */

});
