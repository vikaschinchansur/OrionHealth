$(document).ready(function () {
	//Stops the submit request
    $("#myAjaxRequestForm").submit(function(e){
           e.preventDefault();
    });
	
	$("#addBtn").click(function () {
	
	var formData = {
      medicineName: $("#formControlInput1").val(),
      dose: $("#formControlInputUnits").val(),
      unit: $("#formControlSelect1").val(),
      monBreakfast: $("#monCheck1").prop('checked'),
      monLunch: $("#monCheck2").prop('checked'),
      monDinner: $("#monCheck3").prop('checked'),
      monBedTime: $("#monCheck4").prop('checked'),
      
      tueBreakfast: $("#tueCheck1").prop('checked'),
      tueLunch: $("#tueCheck2").prop('checked'),
      tueDinner: $("#tueCheck3").prop('checked'),
      tueBedTime: $("#tueCheck4").prop('checked'),
      
      wedBreakfast: $("#wedCheck1").prop('checked'),
      wedLunch: $("#wedCheck2").prop('checked'),
      wedDinner: $("#wedCheck3").prop('checked'),
      wedBedTime: $("#wedCheck4").prop('checked'),
      
      thuBreakfast: $("#thuCheck1").prop('checked'),
      thuLunch: $("#thuCheck2").prop('checked'),
      thuDinner: $("#thuCheck3").prop('checked'),
      thuBedTime: $("#thuCheck4").prop('checked'),
      
      friBreakfast: $("#friCheck1").prop('checked'),
      friLunch: $("#friCheck2").prop('checked'),
      friDinner: $("#friCheck3").prop('checked'),
      friBedTime: $("#friCheck4").prop('checked'),
      
      satBreakfast: $("#satCheck1").prop('checked'),
      satLunch: $("#satCheck2").prop('checked'),
      satDinner: $("#satCheck3").prop('checked'),
      satBedTime: $("#satCheck4").prop('checked'),
      
      sunBreakfast: $("#sunCheck1").prop('checked'),
      sunLunch: $("#sunCheck2").prop('checked'),
      sunDinner: $("#sunCheck3").prop('checked'),
      sunBedTime: $("#sunCheck4").prop('checked'),
    };
		
		$.ajax({
			url     : '../RecordPatientMedication',
			method     : 'POST',
			data     : formData,
			dataType: "json",
			encode: true,
			success    : function(data, textStatus, jqXHR){
				console.log(data[0].flag);
				if(data[0].flag){
					$("#ajaxResponse").html("");
                	$("#ajaxResponse").append("<b>Medicine " + data[0].name + " record saved successfully.</b>");
                	$("#formControlInput1").val('');
                	$("#formControlInputUnits").val('');
                	$("#formControlSelect1").val('tablets');
                	$('.custom-control-input').prop('checked', false);
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#ajaxResponse").html(jqXHR.responseText);
                }
		});
    });
});