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
				var strPage = searchParams() == "" ? "?page=" + page : "&page=" + page;
				var action = window.location.pathname == "/etudiants" ? "/rechercher/" : "/rechercherArchive/" ;
				$.ajax({
					type : "GET",
					url : root + action + searchParams() + "" + strPage,
					success: function(result){
						console.log("in Success: ", result);
						$('#etudiantsTable tbody').empty();
						$.each(result.content, function(i, etudiant){
							var etudiantRow = etudiantToRow(i, etudiant);
							
							$('#etudiantsTable tbody').append(etudiantRow);
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
				
				if($('#matriculeInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'matricule=' + $('#matriculeInput').val();
				}
				
				if($('#cinInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'CIN=' + $('#cinInput').val();
				}
				if($('#cneInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'CNE=' + $('#cneInput').val();
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
				if($('#promotionInput').val() != ""){
					var divider = "";
					if (params != "") divider = '&' ;
					params = params + divider + 'promotion=' + $('#promotionInput').val();
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
			
			function etudiantToRow(i, etudiant){
				
				if( etudiant.photo == true ){
					image = etudiant.matricule;
				}else{
					image = 0;
				}
				
				urlCv = etudiant.cv == true ? '<a href="/uploads/cvs/' + etudiant.matricule + '.pdf">Télécharger</a>' : 'Pas de cv';
				urlModifier = window.location.pathname == "/etudiants" ? "ModifierEtudiant" : "ModifierEtudiantArchive";
				urlSupprimer = window.location.pathname == "/etudiants" ? "SupprimerEtudiant" : "SupprimerEtudiantArchive";
				
				return '<tr onClick="show_hide_row(\'hidden_row' + i + '\')">' +
				'<td>' + etudiant.matricule + '</td>' +
				'<td>' + etudiant.cin + '</td>' +
				'<td>' + etudiant.nom + '</td>' +
				'<td>' + etudiant.prenom + '</td>' +
				'<td>' + verifyNull(etudiant.telephone) + '</td>' +
				'<td>' + verifyNull(etudiant.emailProfessionnel) + '</td>' +
				'<td>' +
					'<div class="btn-group">'+
					 '<a href="/' + urlModifier + '/?id=' + etudiant.id + '"><button class="btn btn-primary" >modifier</button></a>'+
					 '<a href="/' + urlSupprimer + '/?id=' + etudiant.id + '" ><button class="btn btn-danger" style="margin-left:20px;">supprimer</button></a>'+
					'</div>'
				+ '</td>' +
			  	'</tr>'
			  	+ '<tr id="hidden_row' + i + '" style="background-color:#F5F5F5;"><td colspan=8><div class="row">' +
			  		'<div class="col-sm-6 col-md-4 col-lg-3"><img class="img-responsive img-etudiant" src="/uploads/images/' + image + '.jpg"/></div>' +
			  		'<div class="col-sm-6 col-md-8 col-lg-9"><p>' +
			  			'<label class="infoSecond">Date d\'inscription : </label> ' + etudiant.dateInscription + '</br>'
			  			+'<label class="infoSecond">Adresse : </label> ' + etudiant.adresse + '</br>'
			  			+'<label class="infoSecond">Nom et prénom en arabe : </label> ' + etudiant.nomPrenomArabe + '</br>'
			  			+'<label class="infoSecond">Sexe : </label> ' + etudiant.sexe + '</br>'
			  			+'<label class="infoSecond">Date de Naissance : </label> ' + etudiant.dateNaissance + '</br>'
			  			+'<label class="infoSecond">Email Personnel : </label> ' + verifyNull(etudiant.emailPersonnel) + '</br>'
			  			+'<label class="infoSecond">Année de réserve : </label> ' + verifyNull(etudiant.anneeDeReserve) + '</br>'
			  			+'<label class="infoSecond">CV : </label> ' + urlCv
			  		+ '</p></div>'
			  	+ '</div></td></tr>';
				
			}
			
		});
		
		function show_hide_row(row)
		{
		 $("#"+row).toggle();
		}
		
		function paging(totalElements, totalPages, currentPage){
			('#PagingInfos').append('<div>total etudiants : ' + totalElements + '</div>');
			
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
		/*$( "#callConfirm" ).click(function() {
			Bootpop.ask('Voulez-vous archiver cette promotion?', { title:'Confirmation d\'archivage', size: 'small'});
		});*/

		