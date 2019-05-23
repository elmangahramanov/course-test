<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Course</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/external/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/external/jquery-ui.css">



    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/external/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/external/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>


  </head>
  <body>

  <div class="header"></div>

  <div class="menu">

    <button id="idButtonStudent" class="menu-button" style="font-size: 20px">Student</button>
    <button id="idButtonTeacher" class="menu-button" style="font-size: 20px">Teacher</button>
    <button id="idButtonCourse" class="menu-button" style="font-size: 20px">Course</button>
    <br><br>
    <button id="idButtonAdd" class="menu-button" style="font-size: 20px">Add</button>

  </div>

  <div class="inside">
    <script type="text/javascript">
        $( function() {
            $( "#idDialogNewData" ).dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                autoOpen:false,
                buttons: {
                    "Add": function() {
                        addNewData();
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        } );
        $( function() {
            $( "#idDialogEditData" ).dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                autoOpen:false,
                buttons: {
                    "Edit": function() {
                        editNewData();
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        } );
    </script>

    <div id="idDivStudent" hidden>

    </div>
    <div id="idDivTeacher" hidden>

    </div>
    <div id="idDivCourse" hidden>

    </div>

    <div id="idDialogNewData" ></div>

    <div id="idDialogEditData" ></div>




  </div>



  <div class="footer" align="center" style="color: #fffafa">STEP IT Academy.Copyright 2019 (c)</div>


  </body>
</html>
