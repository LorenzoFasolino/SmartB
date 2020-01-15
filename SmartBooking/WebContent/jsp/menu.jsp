<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.Utente"%>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<title>SmartBooking: Homepage</title>

    </head>
   
    <body>
    
    <%
    	int tipo = 0;
    	Utente utente = null;
    	utente = (Utente) request.getSession().getAttribute("docente");
    	if(utente != null){
    		tipo = 1; // TIPO 1 SI RIFERSCE AL DOCENTE
    	}else{
    		utente = (Utente) request.getSession().getAttribute("studente");
    		if(utente != null){
    			tipo = 2; // TIPO 2 SI RIFERSCE ALLO STUDENTE
    		}else{
    			utente = (Utente) request.getSession().getAttribute("segreteria");
    			if(utente != null){
        			tipo = 3; // TIPO 3 SI RIFERSCE ALLA SEGRETERIA
        		}
    		}
    	}
    %>
    
    

        <!--Navbar-->
        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #F90;">

                <!-- Navbar brand -->
                <a class="navbar-brand" href="index.jsp">SmartBooking</a>
            
                <!-- Collapse button -->
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
                aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span> 
                </button> 
            
                <!-- Collapsible content -->
                <div class="collapse navbar-collapse" id="basicExampleNav">
            
                <!-- Links -->
                <ul class="navbar-nav mr-auto">
                
                	<%
                		if(utente == null){
                	%>
                		 <li class="nav-item active">
		                 <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
		                 </li>
		                 
		                 <li class="nav-item">
                   		 <a class="nav-link" href="Login.jsp">Login</a>
                 		 </li>
                	
                		 <li class="nav-item">
                  		 <a class="nav-link" href="RegView.jsp">Registrazione</a>
                  		 </li>
                	
                	<%
                		}else if(tipo == 1){
                	%>
                		
                		<li class="nav-item active">
		                <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
		                </li>
                		
                		<!--  <li class="nav-item ">
		                <a class="nav-link" href="InfoDocente.jsp?mat=<%=utente.getMatricola()%>">Profilo docente<span class="sr-only">(current)</span></a>
		                </li>-->
		                 <li class="nav-item">
                        <a class="nav-link" href="ViewRicercaDocenti.jsp">Ricerca docente</a>
                        </li>
		                <li class="nav-item">
                        <a class="nav-link" href="../visualizzaRicevimentiServlet" >Visualizza ricevimenti</a>
                        </li>
                        
                        <!--  <li class="nav-item">
                        <a class="nav-link" >Visualizza richieste</a>
                        </li>-->
		                
		                <li class="nav-item">
                        <a class="nav-link" href="FormEmail.jsp">Invio avviso</a>
                        </li>
		                
		                <li class="nav-item">
                        <a class="nav-link" href="CambioPassword.jsp">Cambia password</a>
                        </li>
		                
		                <li class="nav-item ">
		                <a class="nav-link" href="../LogoutServlet" >Logout<span class="sr-only">(current)</span></a>
		                </li>
                	
                	<%
                		}else if(tipo == 2){
                	%>
                	
                		<li class="nav-item active">
		                <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
		                </li>
                		
                	<!--  	<li class="nav-item ">
		                <a class="nav-link" href="ProfiloStudente.jsp">Profilo studente<span class="sr-only">(current)</span></a>
		                </li>-->
		                
		                <li class="nav-item">
                        <a class="nav-link" href="ViewRicercaDocenti.jsp">Ricerca docente</a>
                        </li>
		                
		                <li class="nav-item">
                        <a class="nav-link" href="CambioPassword.jsp">Cambia password</a>
                        </li>
		                
		                <li class="nav-item ">
		                <a class="nav-link" href="../LogoutServlet" >Logout<span class="sr-only">(current)</span></a>
		                </li>
                	
                	<%
                		}else if(tipo == 3){
                	%>
                	
                		<li class="nav-item active">
		                <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
		                </li>
                	
                		<!-- <li class="nav-item ">
		                <a class="nav-link" href="ProfiloSegreteria.jsp">Profilo segreteria<span class="sr-only">(current)</span></a>
		                </li> -->
		                
		                <li class="nav-item">
                        <a class="nav-link" href="RegDocente.jsp">Registrazione docente</a>
                        </li>
                        
                         <li class="nav-item">
                        <a class="nav-link" href="ViewRicercaDocenti.jsp">Ricerca docente</a>
                        </li>
                        
                        <li class="nav-item">
                        <a class="nav-link" href="ViewRichiesteModOrarioSeg.jsp">Richieste modifia orario</a>
                        </li>
		                
		                
		                <li class="nav-item">
                        <a class="nav-link" href="CambioPassword.jsp">Cambia password</a>
                        </li>
		                
		                <li class="nav-item ">
		                <a class="nav-link" href="../LogoutServlet" >Logout<span class="sr-only">(current)</span></a>
		                </li>
                	
                	<%
                		}
                	%>
                
                   <!--   <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home
                        <span class="sr-only">(current)</span>
                    </a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="ViewRicercaDocenti.jsp">Ricerca docente</a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="RegView.jsp">Registrazione</a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="CambioPassword.jsp">Cambia password</a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="FormInserimentoDocente.jsp">Registrazione docente</a>
                    </li>
                    
                    <li class="nav-item">
                    <a class="nav-link" href="ProfiloStudente.jsp">Profilo studente</a>
                    </li> -->
                </ul>
                <!-- Links -->
          <!--  
                <form class="form-inline">
                    <div class="md-form my-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                    </div>
                    
                    -->  
                    
                </form>
                </div>
        
        <!-- Collapsible content -->

    </body>
</html>