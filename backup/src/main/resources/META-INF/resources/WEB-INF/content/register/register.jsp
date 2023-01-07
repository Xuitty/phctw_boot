<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>學生資料管理系統</title>
    <style></style>
  </head>
  <body id="body">
    <!--<div>
		<br /><br />
		<form action="register" method="post">
			學號(帳號):&nbsp;<input type="text" name="sno" id="sno" />
			
			<br />
			<br />
			密碼:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="password"
			name="spwd"
			id="spwd"
			/><br />
			<br />
			姓名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text"
			name="sname"
          id="sname"
		  /><br />
		  <br />
		  生日:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
          name="sbday"
          id="sbday"
		  />
		  (yyyy-mm-dd)<br />
		  <br />
		  性別:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
          type="radio"
          name="ssex"
          value="1"
		  />男<input type="radio" name="ssex" value="0" />女<br />
		  <br />
		  Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
          type="text"
          name="smail"
          id="smail"
		  /><br />
		  <br />
		  身分證字號:&nbsp;<input type="text" name="sid" id="sid" /><br />
		  <br />
		  <br />
		  <br />
		  <div id="submit" style="display: none">
			  <input type="hidden" name="buttonAction" value="register" />
			  <input id="submit" type="submit" value="送出" />
			</div>
		</form>
		<img src="js/watame-hololive.gif" alt="null"></img>
    </div>-->

    <h2 id="result" class="text-center" style="color: red; size: 20px"></h2>
    <form action="register" method="post">
      <input type="hidden" name="buttonAction" value="register" />
      <div class="container-fluid">
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="text"
              class="form-control"
              id="sno"
              name="sno"
              placeholder="A000001"
            />
            <label for="sno">　 帳號(學號)</label>
          </div>
          <div class="col-sm-4"></div>
        </div>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="password"
              class="form-control"
              id="spwd"
              name="spwd"
              placeholder="A000001"
            />
            <label for="spwd">　密碼</label>
          </div>
          <div class="col-sm-4"></div>
        </div>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="password"
              class="form-control"
              id="spwdc"
              name="spwdc"
              placeholder="A000001"
            />
            <label for="spwdc">　確認密碼</label>
          </div>
          <div class="col-sm-4"></div>
        </div>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="text"
              class="form-control"
              id="sname"
              name="sname"
              placeholder="A000001"
            />
            <label for="spwdc">　姓名</label>
          </div>
          <div class="col-sm-4"></div>
        </div>

        <fieldset class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 d-flex justify-content-center">
            <legend class="col-form-label pt-0">性別</legend>

            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="ssex"
                id="gridRadios1"
                value="0"
                checked
              />
              <label class="form-check-label" for="gridRadios1">女</label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="ssex"
                id="gridRadios2"
                value="1"
              />
              <label class="form-check-label" for="gridRadios2">男</label>
            </div>
          </div>
          <div class="col-sm-4"></div>
        </fieldset>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4">
            <label for="sbday">出生日期</label>
            <input id="sbday" name="sbday" class="form-control" type="date" />
          </div>
          <div class="col-sm-4"></div>
        </div>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="email"
              class="form-control"
              id="smail"
              name="smail"
              placeholder="A000001@"
            />
            <label for="smail">　電子郵件</label>
          </div>
          <div class="col-sm-4"></div>
        </div>
        <div class="row mb-3">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 form-floating">
            <input
              type="text"
              class="form-control"
              id="sid"
              name="sid"
              placeholder="A000001"
            />
            <label for="smail">　身份證字號</label>
          </div>
          <div class="col-sm-4"></div>
        </div>

        <div class="d-flex justify-content-center mb-3">
          <button id="submit" type="submit" class="btn btn-primary">
            送出
          </button>
        </div>
      </div>
    </form>

    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
