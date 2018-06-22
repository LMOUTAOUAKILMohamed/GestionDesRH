/**
 * 
 */
var root = "";
$(document).ready(function() {
			
			chercher(0);
			
			$('#btnChercher').click(function(){
				chercher(0);
			});
			
			function chercher(page){
				console.log(window.location.pathname);
				var action = window.location.pathname == "/enseignantsPermanents" ? "/rechercherEnseignantsPermanents/" : "/rechercherEnseignantsVacataires/" ;
				console.log("action : " + action);
				var strPage = searchParams() == "" ? "?page=" + page + "&size=" + 10 : "&page=" + page + "&size=" + 10;
				$.ajax({
					type : "GET",
					url : root + "" + action + "" + searchParams() + "" + strPage,
					success: function(result){
						console.log("in Success: ", result);
						$('#enseignantsTable tbody').empty();
						$.each(result.content, function(i, enseignant){
							console.log("in each voila  enseignant: ", enseignant );
							var enseignantRow = enseignantToRow(i, enseignant);
							
							$('#enseignantsTable tbody').append(enseignantRow);
							$('#hidden_row' + i).hide();
				        });
						$('#currentPage').text("" + (result.number + 1));
						$('#totalPages').text("" + (result.totalPages));
						$('#totalElements').text("" + (result.totalElements));
						
					},
					error : function(e) {
						alert("ERROR: ", e);
						console.log("ERROR: ", e);
					}
				});	
			}
			
			function searchParams(){
				
				var params = "";
				
				if($('#cinInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'CIN=' + $('#cinInput').val();
				}
				
				if($('#nomInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'nom=' + $('#nomInput').val();
				}
				if($('#prenomInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'prenom=' + $('#prenomInput').val();
				}
				if($('#anneeInscriptionInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'dateAffectation=' + $('#anneeInscriptionInput').val();
				}
				if($('#telephoneInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'telephone=' + $('#telephoneInput').val();
				}
				if($('#emailProInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'emailProfessionnel=' + $('#emailProInput').val();
				}
				if($('#emailPersoInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'emailPersonnel=' + $('#emailPersoInput').val();
				}
				
				if($('#adresse').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'adresse=' + $('#adresse').val();
				}
				
				console.log("params", params);
				
				if(params != "") params = '?' + params;
				
				return params;
			}
			
			$('#nextPage').click(function(){
				var totalPages = parseInt($('#totalPages').text());
				var currentPage = parseInt($('#currentPage').text()) - 1;
				if(currentPage < totalPages - 1)
					chercher(++currentPage);
			});
			
			$('#previousPage').click(function(){
				var currentPage = parseInt($('#currentPage').text()) - 1;
				console.log("currentPage : " + currentPage);
				if(currentPage > 0)
					chercher(--currentPage);
			});
			
			$('#btnViderChamps').click(function(){
				emptyInputs('#filtresContainer input');
				chercher(0);
			});
			
			function enseignantToRow(i, enseignant){
				var type = window.location.pathname == "/enseignantsPermanents" ? "EP" : "EV" ;
				var actionModifier = window.location.pathname == "/enseignantsPermanents" ? "/ModifierEnseignantPermanent/" : "/ModifierEnseignantVacataire/" ;
				var actionSupprimer = window.location.pathname == "/enseignantsPermanents" ? "/SupprimerEP/" : "/SupprimerEV/" ;
				var infoDiff = "";
				if(type == "EV"){
					var etatPaiement;
					enseignant.etatPaiement == true ? etatPaiement = "Payé" : etatPaiment = "Non payé";
					infoDiff = '<label class="infoSecond">Etat de paiement : </label> ' + etatPaiement + '</br>'
								+ '<label class="infoSecond">établissement d\'attache: </label> ' + enseignant.etablissement + '</br>';
				}
				
				return '<tr onClick="show_hide_row(\'hidden_row' + i + '\')">' +
				'<td>' + enseignant.cin + '</td>' +
				'<td>' + enseignant.nom + '</td>' +
				'<td>' + enseignant.prenom + '</td>' +
				'<td>' + enseignant.dateAffectation + '</td>' +
				'<td>' + enseignant.telephone + '</td>' +
				'<td>' + verifyNull(enseignant.emailProfessionnel) + '</td>' +
				'<td>' +
					'<div class="btn-group">'+
					 '<a href="' + actionModifier + '?id=' + enseignant.id + '"><button class="btn btn-primary" >modifier</button></a>'+
					 '<a href="' + actionSupprimer + '?id=' + enseignant.id + '" ><button class="btn btn-danger" style="margin-left:20px;">supprimer</button></a>'+
					'</div>'
				+ '</td>' +
			  	'</tr>'
			  	+ '<tr id="hidden_row' + i + '" style="background-color:#F5F5F5;"><td colspan=8>' +
			  		'<div class="col-sm-6 col-md-8 col-lg-10"><p>' 
			  			+'<label class="infoSecond">Grade : </label> ' + enseignant.grade + '</br>'
			  			+'<label class="infoSecond">Echelle : </label> ' + enseignant.echelle + '</br>'
			  			+ infoDiff
			  			+'<label class="infoSecond">Adresse : </label> ' + enseignant.adresse + '</br>'
			  			+'<label class="infoSecond">Nom et prénom en arabe : </label> ' + enseignant.nomPrenomArabe + '</br>'
			  			+'<label class="infoSecond">Sexe : </label> ' + enseignant.sexe + '</br>'
			  			+'<label class="infoSecond">Date de Naissance : </label> ' + enseignant.dateNaissance + '</br>'
			  			+'<label class="infoSecond">Email Personnel : </label> ' + enseignant.emailPersonnel
			  		+ '</p></div>'
			  	+ '</td></tr>';
				
			}
			
		});
		
		function show_hide_row(row)
		{
		 $("#"+row).toggle();
		}
		
		function paging(totalElements, totalPages, currentPage){
			('#PagingInfos').append('<div>total enseignants : ' + totalElements + '</div>');
			
		}
		
		function emptyInputs(divId){
			console.log(divId);
			$(divId).each(function () {
			    $(this).val(''); // "this" is the current element in the loop
			});
		}
		
		function verifyNull(item){
			if( item == null)
				return "-";
			return item;
		}