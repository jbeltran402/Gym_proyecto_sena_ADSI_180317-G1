<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession();

    if (sesion.getAttribute("nivel") == null) {

        response.sendRedirect("vistas/Login.jsp");
    } else {
        String nivel = sesion.getAttribute("nivel").toString();
        if (!nivel.equals("3")) {
            response.sendRedirect("vistas/Login.jsp");
        }
    }

%>

<!--..................................... Revisar funcionamiento .................................................-->
<!--..................................... Revisar enlaces.........................................................-->
<!--..................................... 27/11/2019 .............................................................-->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Factura</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/style.css">
        <style>
            .colrr {
                color: white;
                background-color: #0069d9;                
            }
        </style>
    </head>
    <body  class="colrr">
        <!-------------------------Menú WILMER----------------------------------->

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">


                    <li class="nav-item dropdown">
                        <a class=" navbar-toggler-icon" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="super_admin.jsp">Home</a>
                            <a class="dropdown-item" href="Controlador?accion=listar">Usuarios</a>
                            <a class="dropdown-item" href="#">Planes</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">crear Factura</a>
                        </div>
                    </li>

                </ul>
                <div class="dropdown">

                    <a  style="color:white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar Sesion</a>    

                    <div class="dropdown-menu text-center">
                        <a><img src="../imagenes/avatar.png"  heidht="80" width="80"/></a><br>
                        <a> <%= sesion.getAttribute("nombre")%> </a>
                        <a>${Correo}</a>
                        <div class="dropdown-divider"></div>
                        <a href="vistas/Login.jsp?cerrar=true" class="dropdown-item">Salir</a>

                    </div>
                </div>       
            </div>
        </nav>

        <!------------------------Agregar Factura Wilmer------------------------------------->        
        <div class="container">
            <div class="col-lg-6">
                <br>
                <h1>Agregar Factura</h1>
                
                <form method="post" action="Controlador">
                    
                    <br>Documento Usuario<br>
                    <input class="form-control" type="number" name="docuusu" required><br> 
                    Nombre Sede<br>
                    <select class="form-control" id="exampleFormControlSelect1" name="idsede">
                        <option value="1">Bosa-Piomonte</option>
                        <option value="2">Kennedy-Oikos</option>
                        <option value="3">Chapinero-Central</option>                  
                    </select><br>
                    Documento Del vendedor<br>
                    <input type="number" class="form-control" id="exampleFormControlSelect1" value="<%= sesion.getAttribute("nombre")%>" name="docuvend" readonly=""><br>                        
                    Compra<br>
                    <select class="form-control" id="exampleFormControlSelect1" name="idcompra">
                        <option value="1">Gold</option>
                        <option value="2">Silver</option>
                        <option value="3">Bronze</option>        
                            <option value="10">valoracion medica</option>
                            <option value="11">Instructor Personal</option>
                            <option value="12">Gym</option>                  
                            <option value="13">Nutricionista</option>
                    </select><br>                
                    Forma De Pago<br>
                    <select class="form-control" id="exampleFormControlSelect1" name="formapago">
                        <option value="tarjeta">tarjeta</option>
                        <option value="efectivo">efectivo</option>                
                    </select><br>
                    Meses A Pagar<br>
                    <input class="form-control" class="colrr" type="number" name="mespago"><br> 

                    <input class="btn btn-success" type="submit" name="accion" value="Agregar factura">
                    <a class="btn btn-danger" href="Controlador?accion=listar">Regresar</a>
                </form>
            </div>
        </div>
        <img src="img/logo.png" width="400" height="300" style="position: relative; left: 1240px; top: -650px;">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html> 