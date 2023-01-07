var script = document.createElement("script");
script.src = "https://code.jquery.com/jquery-3.6.2.js";
document.getElementsByTagName("head")[0].appendChild(script);
const date = new Date();
var a = 0,
  b = 0,
  c = 0,
  d = 0,
  e = 0,
  f = 0;
$("#submit").click(function () {
  b = 1;
  $("#result").text("資料處理中，請稍等");
});

$("#body").keydown(function () {
  if (b == 0) {
    detect();
  }
});

$("#body").keyup(function () {
  if (b == 0) {
    detect();
  }
});

function ajax() {
  $.ajax({
    url: "ajax",
    type: "get",
    data: {
      sno: $("#sno").val(),
      sid: $("#sid").val(),
    },
    dataType: "text",
    error: function () {},
    success: function (data) {
      console.log(data);
      if (data == "success") {
        a = 1;
      } else if (data == "id") {
        a = 2;
      } else {
        a = 0;
      }
    },
  });
}

function detect() {
  document.getElementById("spwd").classList.remove("is-invalid");
  document.getElementById("spwdc").classList.remove("is-invalid");
  document.getElementById("sbday").classList.remove("is-invalid");
  document.getElementById("sno").classList.remove("is-invalid");
  ajax();
  c = document.getElementsByName("sbday")[0].value;
  c = c.toString();
  if (
    $("#sid").val() == "" ||
    $("#sname").val() == "" ||
    $("#sbday").val() == "" ||
    $("#spwd").val() == "" ||
    $("#smail").val() == "" ||
    $("#sno").val() == ""
  ) {
    $("#result").text("有欄位沒填");
    document.getElementById("submit").style.display = "none";
  } else if (
    $("#sname").val().length < 2 ||
    $("#spwd").val().length < 6 ||
    $("#sbday").val().length != 10 ||
    $("#sno").val().length < 4
  ) {
    $("#result").text("長度錯誤");
    document.getElementById("submit").style.display = "none";
  } else if (
    a == 0 &&
    $("#sid").val() != "" &&
    $("#sname").val() != "" &&
    $("#sbday").val() != "" &&
    $("#spwd").val() != "" &&
    $("#smail").val() != ""
  ) {
    document.getElementById("submit").style.display = "none";
    document.getElementById("sno").classList.add("is-invalid");
    $("#result").text("學號重複");
  } else if (
    a == 2 &&
    $("#sid").val() != "" &&
    $("#sname").val() != "" &&
    $("#sbday").val() != "" &&
    $("#spwd").val() != "" &&
    $("#smail").val() != ""
  ) {
    document.getElementById("submit").style.display = "none";
    $("#result").text("身分證格式錯誤");
  } else if ($("#spwd").val() != $("#spwdc").val()) {
    document.getElementById("submit").style.display = "none";
    document.getElementById("spwd").classList.add("is-invalid");
    document.getElementById("spwdc").classList.add("is-invalid");
    $("#result").text("密碼不一致");
  } else if (
    date.getFullYear() - c.substring(0, 4) > 65 ||
    date.getFullYear() - c.substring(0, 4) < 10
  ) {
    document.getElementById("submit").style.display = "none";
    document.getElementById("sbday").classList.add("is-invalid");
    $("#result").text("日期錯誤");
  }
  // else if (
  //   ($("#sid").val().substring(1, 2) == 1 && $("#ssex").val() == 0) ||
  //   ($("#sid").val().substring(1, 2) == 2 && $("#ssex").val() == 1)
  // ) {
  //   document.getElementById("submit").style.display = "none";
  //   $("#result").text("你是不是按錯性別了");
  //   document.getElementById("sid").classList.add("is-invalid");
  // }
  else if (
    a == 1 &&
    $("#sid").val() != "" &&
    $("#sname").val() != "" &&
    $("#sbday").val() != "" &&
    $("#spwd").val() != "" &&
    $("#smail").val() != ""
  ) {
    document.getElementById("submit").style.display = "block";
    $("#result").text("");
  } else {
    document.getElementById("submit").style.display = "none";
  }
  // console.log("123");
}
