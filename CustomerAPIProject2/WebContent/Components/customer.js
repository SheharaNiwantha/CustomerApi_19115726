



$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------

// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "CustomerAPI",
 type : type,
 data : $("#formCustomer").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});




function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidItemIDSave").val("");
 $("#formCustomer")[0].reset();
}


// UPDATE==========================================
		$(document).on("click", ".btnUpdate", function(event)
		{
		 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
		 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#phonenumber").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#occupation").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#needproduct").val($(this).closest("tr").find('td:eq(5)').text());
		});
		
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "CustomerAPI",
 type : "DELETE",
 data : "id=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerComplete(response.responseText, status);
 }
 });
});
		