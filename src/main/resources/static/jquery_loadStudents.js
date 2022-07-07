$(document).ready(loadTable());

function loadTable() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/student",
    success: function (studentArray) {
      console.log(studentArray);
      var studentsTable = $("#studentTable");
      $.each(studentArray, function (index, student) {
        var studentInfo = "<tr id='row" + student.id + "' >";
        studentInfo += "<td>" + student.id + "</td>";
        studentInfo += "<td>" + student.name + "</td>";
        studentInfo += "<td>" + student.address.street + "</td>";
        studentInfo += "<td>" + student.address.city + "</td>";
        studentInfo += "<td>" + student.address.postCode + "</td>";
        studentInfo += "<td>" + student.dob + "</td>";
        studentInfo +=
          "<td><button class='btn btn-primary btn-sm' onClick='handleEdit(" +
          student.id +
          ")'>Edit</button>";
        studentInfo +=
          "<span/> <button class='btn btn-danger btn-sm' onClick='handleDelete(" +
          student.id +
          ")'>Delete</button></td>";
        studentInfo += "</tr>";
        studentsTable.append(studentInfo);
      });
    },
    error: function () {
      console.log(this);
    },
  });
}

function handleDelete(id) {
  console.log(id);
  $.ajax({
    type: "DELETE",
    contentType: "application/json",
    url: "http://localhost:8080/api/student/" + id,
    success: function (response) {
      // location.href = "/index.html";
      document.getElementById("row" + id).remove();
    },
    error: function (response) {
      console.log(response);
    },
  });
}
