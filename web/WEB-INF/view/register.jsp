<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login V11</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/external/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/external/main.css">
    <!--===============================================================================================-->

    <%--<script type="text/javascript">--%>
        <%--$(function () {--%>
            <%--<c:if test="${message3 ne null}">--%>
            <%--alert('${message3}');--%>
            <%--</c:if>--%>
        <%--});--%>
    <%--</script>--%>


</head>
<body>

<span style="color: red;">

<% if (request.getAttribute("message3")!=null) {%>
<%=request.getAttribute("message3")%>
<% } %>

</span>




<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
            <form class="login100-form validate-form" action="${pageContext.request.contextPath}/us?action=doRegister" method="POST">
					<span class="login100-form-title p-b-55">
						Register
					</span>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="email" placeholder="Email">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<span class="lnr lnr-envelope"></span>
						</span>
                </div>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <input class="input100" type="password" name="pass" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<span class="lnr lnr-lock"></span>
						</span>
                </div>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <input class="input100" type="password" name="rePass" placeholder="Re-password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<span class="lnr lnr-lock"></span>
						</span>
                </div>


                <div  class="wrap-input100 validate-input m-b-16">
                    <select name="idRole">
                        <option value="">Select role type...</option>

                        <c:forEach var="role" items="${roleList}">
                            <option value="${role.id}">${role.roleType}</option>
                        </c:forEach>

                    </select>
                </div>


                <div class="container-login100-form-btn p-t-25">
                    <button class="login100-form-btn">
                        Register
                    </button>
                </div>

                <div class="text-center w-full p-t-42 p-b-22">
						<span class="txt1">
							Or login with
						</span>
                </div>

                <a href="#" class="btn-face m-b-10">
                    <i class="fa fa-facebook-official"></i>
                    Facebook
                </a>

                <a href="${pageContext.request.contextPath}/ns?action=login" class="btn-google m-b-10">
                    Login
                </a>

            </form>
        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>