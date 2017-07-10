
var app = angular.module('AppCars', ['ngAnimate']);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller('CtrlCars', function($scope, $http) {
    $scope.hideform = true;
    $scope.hideedit = false;
    $scope.showlistOfCars = false;
    //$scope.pipedriveToken = "4cc1c8efecc5b2d4cf3f53c095bad29f64fd4d29";
    $scope.pipedriveToken = "";

    $scope.getAllClients = function(){
        var url = "https://api.pipedrive.com/v1/persons?start=0&api_token=" + $scope.pipedriveToken;
        $http.get(url).then(function successCallback(response){
            $scope.showlistOfCars = true;
            $scope.clients = response.data;
        }, function errorCallback(){
            alert("O TOKEN DO PIPEDRIVE É INVÁLIDO, INSIRA UM TOKEN VÁLIDO!");
            $scope.hideform = true;
            $scope.hideedit = false;
            $scope.showlistOfCars = false; 
        });
    };
    
    $scope.fillForm = function(){
        $scope.hideform = false;
        $scope.hideedit = true;
        $scope.selectedClient = "";
        $scope.client = "";
        $scope.carModel = "";
        $scope.carYear = "";
        $scope.carColor = "";
    };
    
    $scope.saveCar = function(){
      
      if(confirm("Confirma inserção de um novo carro?")){
          
          var url = "http://localhost:9000/api/carros";
          
          var attributes = {};
          attributes.responsavel = $scope.selectedClient.name;
          attributes.modelo = $scope.carModel;
          attributes.ano = $scope.carYear;
          attributes.cor = $scope.carColor;

          var req = {method:'POST', url:url, data:attributes};
          
          $http(req).then(function successCallback(response){
              //alert(JSON.stringify(response));
              req = {method:'GET',url:'http://localhost:9000/api/carros'};
              $http(req).then(function(response){
                 $scope.cars = response.data;
                 $scope.hideform = true;
                 alert("CARRO INSERIDO COM SUCESSO!");
              });
              
          }, function errorCallback(response){
              alert(JSON.stringify(response.data));
          });
      }
    };
    
    
    $scope.editCar = function(carId){
        
        $scope.carId = carId;
        var url = "http://localhost:9000/api/carro?id=" + carId;
        var req = {method:"GET",url:url}
        $http(req).then(function successCallback(response){
          //alert(JSON.stringify(response));
          $scope.hideedit = false;
          $scope.hideform = false;
          
          var car = response.data;
          $scope.carColor = car.cor;
          $scope.carModel = car.modelo;
          $scope.carYear = car.ano;
          $scope.client = car.responsavel;
                    
        },function errorCallback(response){
          alert("CARRO INEXISTENTE!");  
        });
      };
      
      $scope.saveCarEdition = function(){
        
        if(confirm("Confirma alteração dos dados?")){

            var url = "http://localhost:9000/api/carros";
            
            var attributes = {};
            attributes.id = $scope.carId;
            attributes.responsavel = $scope.selectedClient.name;
            attributes.modelo = $scope.carModel;
            attributes.ano = $scope.carYear;
            attributes.cor = $scope.carColor;
            
            var req = {method:"PUT",url:url,data:attributes};
            $http(req).then(function successCallback(response){

                  req = {method:'GET',url:'http://localhost:9000/api/carros'};
                  $http(req).then(function(response){
                     //alert(JSON.stringify(response));
                     $scope.cars = response.data;
                     $scope.hideform = true;
                     alert("CARRO EDITADO COM SUCESSO!");
                  });
              
            }, function errorCallback(response){
                alert(JSON.stringify(response.data));
            });
        }
      };

      $scope.removeCar = function(carId){
        if(confirm("Confirma remoção do carro?")){
            var url = "http://localhost:9000/api/carros?id=" + carId;
            var req = {method:"DELETE",url:url};

            $http(req).then(function successCallback(response){
                req = {method:'GET',url:'http://localhost:9000/api/carros'};
                $http(req).then(function(response){
                     //alert(JSON.stringify(response));
                     $scope.cars = response.data;
                     $scope.hideform = true;
                     alert("CARRO REMOVIDO COM SUCESSO!");
                  });
            }, function errorCallback(response){
                alert("CARRO INEXISTENTE!");
            });

        }
      }
    
    //$scope.cars = {"1":{"id":"1", "responsavel":"Maria da Silva", "modelo":"Celta", "ano":"2010", "cor":"Branco"}, "2":{"id":"2", "responsavel":"Maria da Silva", "modelo":"Gol", "ano":"2010", "cor":"Branco"}};
});



