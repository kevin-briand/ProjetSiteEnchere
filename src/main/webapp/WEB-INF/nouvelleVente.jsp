<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <%@ include file="bootstrapHeader.html" %>
    <title>Nouvelle vente</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="header.jspf" %>

<div class="container text-center" style="margin-bottom: 100px;">
	<div class="row">
	<div class="col-1">
		<img alt="" src="#">
	</div>
	
	<div class="col">
	
    <h1 style="margin-bottom: 50px; margin-top: 50px;">Nouvelle vente</h1>
    <form method="post" action="<%=request.getContextPath()%>/NouvelleVente">
    
    	<div class="row justify-content-md-around mb-3">
    		<div class="col md-4">
    		
	        	<div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="nomArticle">Article :</label>
		            </div>
		           <div class="col mb-1">
		                <input
		                	required="required"
                            type="text"
                            name="nomArticle"
                            class="form-control"
                            id="nomArticle"
                            placeholder="Nom de l'article"
                            aria-label="Nom de l'article"
                            value="${article.nomArticle}"
		                >
		            </div>
		         </div>
		         
		         <div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="descriptionArticle">Description :</label>
		            </div>
		           <div class="col mb-1">
		                <textarea
		                	required="required"
                            name="descriptionArticle"
                            class="form-control"
                            id="descriptionArticle"
                            aria-label="Description de l'article"
		                >
		                ${article.description}
		                </textarea>
		            </div>
		         </div>
		         
		         <div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="categorieArticle">Catégorie :</label>
		            </div>
		           <div class="col mb-1">
		                <select
		                	required="required"
                            name="categorieArticle"
                            class="form-control"
                            id="categorieArticle"
                            aria-label="Catégorie de l'article"
		                >
		                <%-- Dans la servlet : fetch une liste des catégories depuis la base de données avec leur i?--%>
		                	<c:if test="${!empty categories}">
		                		<c:forEach var="cat" items="${categories}">
									<option value="${cat.getIdCategorie()}">${cat.getLibelle()}</option>
								</c:forEach>
		                	</c:if>
		                	
						<%-- Et une option de secours s'il n'existe aucune catégorie ?--%>
		                	<c:if test="${empty categories}">
								<option value="1">Sans catégorie</option>
		                	</c:if>
		                	
		                </select>
		            </div>
		         </div>
		         
		         <div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="photoArticle">Photo de l'article :</label>
		            </div>
		           <div class="col mb-1">
		                <input
                            type="file"
                            name="photoArticle"
                            class="form-control"
                            id="photoArticle"
                            placeholder="Photo de l'article"
                            aria-label="Photo de l'article"
                            accept="image/*"
		                >
		            </div>
		         </div>
		         
				<div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="prixInitial">Mise à prix :</label>
		            </div>
		           <div class="col mb-1">
		                <input
		                	required="required"
                            type="number"
                            name="prixInitial"
                            class="form-control"
                            id="prixInitial"
                            placeholder="Mise à prix de l'article"
                            aria-label="Mise à prix de l'article"
                            min="1"
		                >
		            </div>
		         </div>
		         
		         <div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="dateDebEnchere">Début de l'enchère :</label>
		            </div>
		           <div class="col mb-1">
		                <input
		                	required="required"
                            type="date"
                            name="dateDebEnchere"
                            class="form-control"
                            id="dateDebEnchere"
                            placeholder="Date de début de l'enchère"
                            aria-label="Date de début de l'enchère"
		                >
		            </div>
		         </div>
		         
		         <div class="row md-around mb-1">
		            <div class="col mb-1 text-end">
		                <label for="dateFinEnchere">Fin de l'enchère :</label>
		            </div>
		           <div class="col mb-1">
		                <input
		                	required="required"
                            type="date"
                            name="dateFinEnchere"
                            class="form-control"
                            id="dateFinEnchere"
                            placeholder="Date de fin de l'enchère"
                            aria-label="Date de fin de l'enchère"
		                >
		            </div>
		         </div>
		         
		         <fieldset class="border p-2" style="margin-left:175px">
		         	<legend class="w-auto" style="float: none; padding: inherit">Retrait</legend>
						<div class="row justify-content-md-around mb-3">
    						<div class="col md-4">
    						
	        					<div class="row md-around mb-1">
						            <div class="col mb-1 text-end">
				               			 <label for="rueRetrait">Rue :</label>
				               		</div>
				               		 <div class="col mb-1">
						                <input
						                	required="required"
				                            type="text"
				                            name="rueRetrait"
				                            class="form-control"
				                            id="rueRetrait"
				                            placeholder="Adresse de retrait de l'article"
				                            aria-label="Adresse de retrait de l'article"
				                            value="${sessionScope.utilisateurConnecte.rue}"
						                >
						               </div>
					        	 </div>
			            
			           				<div class="row md-around mb-1">
			           					<div class="col mb-1 text-end">
			                				<label for="codePostalRetrait">Code postal :</label>
			                			</div>
				                		<div class="col mb-1">
					                		 <input
							                	required="required"
					                            type="text"
					                            name="codePostalRetrait"
					                            class="form-control"
					                            id="codePostalRetrait"
					                            placeholder="Code postal de la ville de retrait"
					                            aria-label="Code postal de la ville de retrait"
					                            value="${sessionScope.utilisateurConnecte.codePostal}"
					                			>
				                		</div>
			           	 			</div>
			           	 
			           				<div class="row md-around mb-1">
			           					<div class="col mb-1 text-end">
			                				<label for="villeRetrait">Ville :</label>
			                			</div>
			                			<div class="col mb-1">
				                			<input
							                	required="required"
					                            type="text"
					                            name="villeRetrait"
					                            class="form-control"
					                            id="villeRetrait"
					                            placeholder="Ville de retrait de l'article"
					                            aria-label="Ville de retrait de l'article"
					                            value="${sessionScope.utilisateurConnecte.ville}"
				              				>
			                			</div>	
			                		</div>
			                
			           			 </div>
			           		 </div>
		            </fieldset>
		         </div>
			</div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
            <button type="button" class="btn btn-secondary btn-lg">Annuler</button>
    	</div>
    </form>
    </div>
    </div>
</div>

<%@ include file="footer.jspf" %>
<%@ include file="bootstrapScripts.html" %>

<script>
//préremplir par défaut la date minimale de départ des enchères à aujourd'hui (et empêcher de la mettre dans le passé)
//et fix la date minimale de fin d'enchère au lendemain
	var today = new Date();
	var tomorrow = new Date();
	var dd = today.getDate();
	var dd2 = today.getDate()+1;
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	
	setDate();
	
	today = yyyy+'-'+mm+'-'+dd;
	tomorrow = yyyy+'-'+mm+'-'+dd2;
	
	document.getElementById("dateDebEnchere").setAttribute("min", today);
	document.getElementById("dateDebEnchere").setAttribute("value", today);
	document.getElementById("dateFinEnchere").setAttribute("min", tomorrow);

//re-fix la date minimale de fin d'enchère à J+1 lorsqu'une nouvelle date de début d'enchère est choisie :
	var startDate = document.getElementById("dateDebEnchere");
	startDate.addEventListener('change', (event) => {
		var dayAfter = new Date(startDate.value);
		dd = dayAfter.getDate()+1;
		mm = dayAfter.getMonth()+1;
		yyyy = dayAfter.getFullYear();
		setDate();
		dayAfter = yyyy+'-'+mm+'-'+dd;
		console.log(dayAfter);
		document.getElementById("dateFinEnchere").setAttribute("min", dayAfter);
		});
	
	function setDate(){
		if(dd<10){
	        dd='0'+dd
	    } 
    	if(mm<10){
        	mm='0'+mm
    	} 
	}
</script>
</body>
</html>