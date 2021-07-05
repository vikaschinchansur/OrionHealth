$(document).ready(function () {
  $("#signUpBtn2").submit(function (event) {
    var formData = {
      firstName: $("#firstName").val(),
      lastName: $("#lastName").val(),
      emailID: $("#emailID").val(),
      address: $("#address").val(),
      phone: $("#phone").val(),
      insuranceID: $("#insuranceID").val(),
    };

    $.ajax({
      type: "POST",
      url: "/OrionHealth/SignUpPatient",
      data: formData,
      dataType: "json",
      encode: true,
    }).done(function (data) {
      console.log(data);
    });

    event.preventDefault();
  });
});