function handleUpdate() {
  clearValidation();

  var valid = isValid();
  if (valid) {
    var formData = readFormData();
    console.log(formData);
    postFormUpdate(formData);
  }
}

function postFormUpdate(formData) {
  const id = document.getElementById("id").value;
  $.ajax({
    type: "PUT",
    contentType: "application/json",
    url: "http://localhost:8080/api/student/" + id,
    data: JSON.stringify(formData),
    success: function (student) {
      alert("success");
      location.href = "/index.html";
    },
    error: function (response) {
      console.log(response);
    },
  });
}

$(document).ready(function () {
  console.log("fetching student");
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const id = urlParams.get("id");
  console.log("getting url params");
  console.log(id);
  if (id != null) {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: "http://localhost:8080/api/student/" + id,
      success: function (student) {
        if (student) {
          console.log(student);
          $("#id").val(student.id);
          $("#name").val(student.name);
          $("#street").val(student.address.street);
          $("#city").val(student.address.city);
          $("#postcode").val(student.address.postCode);
          $("#dob").val(student.dob);
        } else {
          $("small#idHelp").html("Student does not exist");
          $("#registerStudent").attr("disabled");
        }
      },
      error: function (response) {
        console.log(response);
      },
    });
  }
});

function handleEdit(id) {
  location.href = "/edit.html?id=" + id;
}

function onFormSubmit() {
  clearValidation();
  var valid = isValid();
  if (valid) {
    var formData = readFormData();
    postFormData(formData);
  }
}

function clearValidation() {
  $(document).ready(function () {
    $("small#nameHelp").html("");
    $("small#streetHelp").html("");
    $("small#cityHelp").html("");
    $("small#postcodeHelp").html("");
    $("small#nameHelp").html("");
  });
}

function isValid() {
  console.log("checking validation");
  var isValid = true;
  if (document.getElementById("name").value.length === 0) {
    $(document).ready(function () {
      $("small#nameHelp").html("Name is required");
    });
    isValid = false;
  }
  if (document.getElementById("street").value.length === 0) {
    $(document).ready(function () {
      $("small#streetHelp").html("Street is required");
    });
    isValid = false;
  }
  if (document.getElementById("city").value.length === 0) {
    $(document).ready(function () {
      $("small#cityHelp").html("City is required");
    });
    isValid = false;
  }
  if (!/^\d+$/.test(document.getElementById("postcode").value)) {
    $(document).ready(function () {
      $("small#postcodeHelp").html("Postcode should only contain numbers");
    });
    isValid = false;
  }
  if (document.getElementById("postcode").value.length === 0) {
    $(document).ready(function () {
      $("small#postcodeHelp").html("Postcode is required");
    });
    isValid = false;
  }

  if (document.getElementById("name").value >= Date.now()) {
    $(document).ready(function () {
      $("small#nameHelp").html("Must be older than today");
    });
    isValid = false;
  }
  return isValid;
}

function readFormData() {
  console.log("reading form data");
  const formData = {
    name: document.getElementById("name").value,
    address: {
      street: document.getElementById("street").value,
      city: document.getElementById("city").value,
      postCode: document.getElementById("postcode").value,
    },
    dob: document.getElementById("dob").value,
  };
  console.log(JSON.stringify(formData));
  return formData;
}

function postFormData(formData) {
  console.log("posting form data");
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "http://localhost:8080/api/student",
    data: JSON.stringify(formData),
    success: function () {
      alert("success");
      location.href = "/index.html";
    },
    error: function (response) {
      console.log(response.responseJSON.message);
      $(document).ready(function () {
        $("small#nameHelp").html(response.responseJSON.message);
      });
    },
  });
}
