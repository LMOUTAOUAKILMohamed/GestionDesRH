var gestionRH = angular.module("gestionRH", []);

gestionRH.controller("InscriptionEtudiantController", function($scope,$http){
	
	$scope.etudiant = {};
	
	$scope.ajouterEtudiant = function(){
		$http.post("AjouterEtudiant", $scope.etudiant)
			.success(function(data){
				$scope.etudiant = data;
			});
	}
	
});