
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <h3 class="text-center text-white pt-5">.....</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">

                        <form action="${pageContext.request.contextPath}/Login" method="post">
                            <h3 class="text-center text-info">Login</h3>

                            <div class="form-group">
                                <label class="text-info">Email: </label><br>
                                <input type="text" name="Email" value="${Email}" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="text-info">Password:</label><br>
                                <input type="password" name="Password" value="${password}" class="form-control">
                            </div>
                            
                            <div style="color: red">${errorMessage}</div>

                            <div class="form-group">
                                <input type="submit" class="btn btn-info btn-md" value="Login">
                            </div>

                        </form>
                        <div id="formFooter">
                            <a class="underlineHover" href="${pageContext.request.contextPath}/Register">Don't have account ?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
