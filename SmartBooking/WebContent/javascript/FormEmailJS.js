/**
 * 
 */





	
	function inviaEmail(){ 
		
		//alert(checkNome());
		//alert(checkMailMittente());
	//	alert(checkMailDestinario());
		//	if(checkNome()==1 && checkCognome()==1 && checkMailMittente()==0 && checkMailDestinatario()==0){
				//alert("Si può procedere alla registrazione");
				$("#form").submit();
		//	}	
		//	else{
		//		alert("Errore. non è possibile effettuare il submit");
		//	}
			
	}
	


/*
function inviaEmail(){ 
	alert("CCC");
	if(checkNome()==1 && checkCognome()==1 && checkMailMittente()==0 && checkMailDestinatario()==0){
		alert("Si può procedere alla registrazione");
		$("#form").submit();
	}	
	else{
		alert("Errore. non è possibile effettuare il submit");
	}
}

*/


/**
 * controlla nome
 * @returns 1 se nome OK, 0 altrimenti
 */
function checkNome(){
	var nome = $("#name").val();  //ottengo valore campo name
	var expr = /^[A-Za-z]{1,10}$/;
	if(nome.match(expr)){
		alert("Nome corretto");
		return 1;
	}else{
		alert("Nome non corretto"); 
		return 0;
	}
}

/**
 * Controlla cognome
 * @returns 1 se cognome OK, 0 altrimenti
 */
function checkCognome(){
	var cognome = $("#surname").val();  //ottengo valore campo surname
	var expr = /^[A-Za-z]{1,20}$/;
	if(cognome.match(expr)){
		//alert("Cognome corretto");
		return 1;
	}else{
		alert("Cognome non corretto");
		return 0;
	}
		
}



/**
 * controlla se l'email inserita è già presente nel db (associata a un docente), se non è presente non deve permettere l'invio dell'email
 * @returns 0 se indirizzo email esiste già nel DB(può inviare email), 1 altrimenti(errore)
 */
function checkMailMittente(){
	var flag;
	var email = $("#emailMittente").val();    //ottengo valore campo emailMittente
	var expr = /^\w+([\.-]?\w+)*@unisa[.]{1}it$/;
	if(email.length < 20 || email.length > 50){
		alert("Email non corretta");
		return 1;
	}else{
		//alert("Formato email corretto");  //arriva
		
		$.ajax({            //AJAX CON JQUERY
			type : 'Post',   //TIPO DI CHIAMATA
			data : {email : email},  //COPPIE NOME-VALORE DA PASSARE ALLA SERVLET
			async: false,
			url : '../CheckMailDocenteServlet',  //SERVLET DA RICHIAMARE IN MANIERA ASINCRONA
			success : function resultServelt(result) {  //FUNZIONE DA ESEGUIRE IN CASO DI SUCCESSO
				//alert("ajax--> valore restituito dalla servlet CheckMailServlet: "+result);
				if(result == "0"){
					//alert("result == 0. Indirizzo email già esistente nel DB");
					flag = 0;
				}else if(result == "1"){
					//alert("result == 1. L'indirizzo email non esiste nel DB");
					flag = 1;
				}else{
					//alert("Errore.");
					flag = 1;
				}
			}
			
		}); /*fine ajax*/
	}
	return flag;
}



/**
 * controlla se l'email inserita è già presente nel db (associata a uno studente), se non è presente non deve permettere l'invio dell'email
 * @returns 0 se indirizzo email esiste già nel DB(può inviare email), 1 altrimenti(errore)
 */
function checkMailDestinatario(){
	var flag;
	var email = $("#emailDestinatario").val();    //ottengo valore campo email
	var expr = /^\w+([\.-]?\w+)*@studenti[.]{1}unisa[.]{1}it$/;
	if(email.length < 20 || email.length > 50){
		alert("Email non corretta");
		return 1;
	}else{
		//alert("Formato email corretto");  //arriva
		
		$.ajax({            //AJAX CON JQUERY
			type : 'Post',   //TIPO DI CHIAMATA
			data : {email : email},  //COPPIE NOME-VALORE DA PASSARE ALLA SERVLET
			async: false,
			url : '../CheckMailServlet',  //SERVLET DA RICHIAMARE IN MANIERA ASINCRONA
			success : function resultServelt(result) {  //FUNZIONE DA ESEGUIRE IN CASO DI SUCCESSO
				//alert("ajax--> valore restituito dalla servlet CheckMailServlet: "+result);
				if(result == "0"){
					//alert("result == 0. Indirizzo email già esistente nel DB");
					flag = 0;
				}else if(result == "1"){
					//alert("result == 1. L'indirizzo email non esiste nel DB");
					flag = 1;
				}else{
					//alert("Errore.");
					flag = 1;
				}
			}
			
		}); /*fine ajax*/
	}
	return flag;
}