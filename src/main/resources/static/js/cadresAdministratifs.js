/**
 * 
 */
var root = "";
$(document).ready(function() {
			
			chercher(0);
			
			function chercher(page){
				var strPage = "?page=" + page + "&size=" + 10;
				$.ajax({
					type : "GET",
					url : root + "/rechercherCadresAdministratifs/" + strPage,
					success: function(result){
						console.log("in Success: ", result);
						$('#cadresAdministratifsTable tbody').empty();
						$.each(result.content, function(i, cadreAdministratif){
							console.log("in each voila  cadreAdministratif: ", cadreAdministratif );
							var cadreAdministratifRow = cadreAdministratifToRow(i, cadreAdministratif);
							
							$('#cadresAdministratifsTable tbody').append(cadreAdministratifRow);
							$('#hidden_row' + i).hide();
				        });
						$('#currentPage').text("" + (result.number + 1));
						$('#totalPages').text("" + (result.totalPages));
						
					},
					error : function(e) {
						alert("ERROR: ", e);
						console.log("ERROR: ", e);
					}
				});	
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
			
			function cadreAdministratifToRow(i, cadreAdministratif){
			
				return '<tr onClick="show_hide_row(\'hidden_row' + i + '\')">' +
				'<td>' + cadreAdministratif.cin + '</td>' +
				'<td>' + cadreAdministratif.fonction + '</td>' +
				'<td>' + cadreAdministratif.nom + '</td>' +
				'<td>' + cadreAdministratif.prenom + '</td>' +
				'<td>' + cadreAdministratif.dateAffectation + '</td>' +
				'<td>' + cadreAdministratif.telephone + '</td>' +
				'<td>' + cadreAdministratif.emailProfessionnel + '</td>' +
				'<td>' +
					'<div class="btn-group">'+
					 '<a href="/ModifierCadreAdministratif/?id=' + cadreAdministratif.id + '"><button class="btn btn-primary" >modifier</button></a>'+
					 //'<a href="/SupprimerCadreAdministratif/?id=' + cadreAdministratif.id + '" ><button class="btn btn-danger" >supprimer</button></a>'+
					'</div>'
				+ '</td>' +
			  	'</tr>'
			  	+ '<tr id="hidden_row' + i + '" style="background-color:#F5F5F5;"><td colspan=8>' +
			  		'<div class="col-sm-6 col-md-8 col-lg-10"><p>' 
			  			+'<label class="infoSecond">Grade : </label> ' + cadreAdministratif.grade + '</br>'
			  			+'<label class="infoSecond">Adresse : </label> ' + cadreAdministratif.adresse + '</br>'
			  			+'<label class="infoSecond">Nom et prénom en arabe : </label> ' + cadreAdministratif.nomPrenomArabe + '</br>'
			  			+'<label class="infoSecond">Sexe : </label> ' + cadreAdministratif.sexe + '</br>'
			  			+'<label class="infoSecond">Date de Naissance : </label> ' + cadreAdministratif.dateNaissance + '</br>'
			  			+'<label class="infoSecond">Email Personnel : </label> ' + cadreAdministratif.emailPersonnel
			  		+ '</p></div>'
			  	+ '</td></tr>';
				
			}
			
});
			
			function show_hide_row(row)
			{
			$("#"+row).toggle();
			}
			
			function paging(totalElements, totalPages, currentPage){
			('#PagingInfos').append('<div>total cadres administratifs : ' + totalElements + '</div>');
			
			}
			
			function emptyInputs(divId){
			console.log(divId);
			$(divId).each(function () {
			    $(this).val(''); // "this" is the current element in the loop
			});
			}