angular.module('starter.controllers', ['wSQL','wSQL.config'])

.controller('InicioCtrl', function($scope,wSQL) {
    wSQL.batch_insert("categoria_dato", categoria_datos).then(function(insert){
       // console.log(insert);
    });

    wSQL.batch_insert("categoria_principios",categoria_principios).then(function(insert){
        //console.log(insert);
    });
    wSQL.batch_insert("dato",datos).then(function(insert){
        //console.log(insert);
    });
    wSQL.batch_insert("pregunta",preguntas).then(function(insert){
        //console.log(insert);
    });
    wSQL.batch_insert("configuracion",configuracion).then(function(insert){
        //console.log(insert);
    });
    
})

.controller('CalculadoraCtrl', function($scope,$state,$ionicHistory,Datos,$window,$ionicPopup) {
    $scope.datos = [];
    $scope.datos_seleccionados = [];
    
    function cargaDatos(){
      Datos.selectAll().then(function(d){
        for(var i = 0; i < d.length; i++){
          var dato ={}
          dato.id = d[i].id;
          dato.nombre = d[i].nombre;
          dato.descripcion = d[i].descripcion;
          dato.categoria_dato = d[i].categoria_dato;
          dato.isChecked = false;
          $scope.datos.push(dato);  
              console.log($scope.datos);

        }
      })
    }
    cargaDatos();

    $scope.agregar = function(i,dato,check){
      
      if( check ){
        $scope.datos_seleccionados.push(dato)
         $scope.datos[i].isChecked = false;
      
      }else{
        var id = $scope.datos_seleccionados.indexOf(dato);
        $scope.datos_seleccionados.splice(id, 1);
        $scope.datos[i].isChecked = false;
      }
      console.log($scope.datos_seleccionados.length);
    }
    $scope.showAlert = function() {
     var alertPopup = $ionicPopup.alert({
       title: 'Tenemos un problema',
       template: 'Selecciona como minimo un Dato'
     });
     alertPopup.then(function(res) {
       console.log('Gracias');
     });
   };

    
    $scope.cambiarVista = function(){
        if($scope.datos_seleccionados.length == 0){
          $scope.showAlert();
        }else{
          $state.go("tab.preguntas",{datos:JSON.stringify($scope.datos_seleccionados)});
        }
        
    }
})

.controller('PreguntasCtrl', function($scope,$state,Preguntas,CategoriaPrincipios,$stateParams) {
    $scope.preguntas = [];
    $scope.preguntas_seleccionadas = [];
    var datos = $stateParams.datos;
    console.log(JSON.parse($stateParams.datos));

    Preguntas.selectAll().then(function(p){
      for(var i = 0; i < p.length; i++){
        var pregunta = {}
        pregunta.id = p[i].id;
        pregunta.pregunta = p[i].pregunta;
        pregunta.categoria_principio = p[i].categoria_principio;
        pregunta.isChecked = true;
        pregunta.categoria = categoria(p[i].categoria_principio);
        $scope.preguntas.push(pregunta);  
      }
    });

    $scope.agregar = function(pregunta,index){
      console.log(pregunta);
      if(pregunta.isChecked){
        var id = $scope.preguntas_seleccionadas.indexOf(pregunta);
        $scope.preguntas_seleccionadasº.splice(id, 1);
        console.log('si');
      }else{
        console.log('no');
        $scope.preguntas_seleccionadas.push(pregunta);
      }
      console.log($scope.preguntas_seleccionadas.length);
    }

    $scope.cambiarVista = function(){
       $state.go("tab.resultado",{datos:datos,preguntas:JSON.stringify($scope.preguntas_seleccionadas)});
    }

    function categoria(id){
      if(id == 1){
        return "Transparencia";
      }else if(id == 2){
        return "Confianza";
      }else if(id == 3){
        return "Control";
      }else{
        return "Valoración";
      }
    }
})



.controller('ResultadoCtrl', function($scope,$state,$stateParams,CategoriaDatos,$ionicHistory,Configuracion) {
    var datos = JSON.parse($stateParams.datos);
    var preguntas = JSON.parse($stateParams.preguntas);
    console.log('Datos');
    console.log( datos);
    console.log('Preguntas');
    console.log(preguntas);
    $scope.resultado_final = 0.00;

    $scope.categoria1 = null;

    function generarPreguntas(){
      var resultado = 1;
      for(var i = 0; i < preguntas.length; i++){
        if(!preguntas[i].isChecked){
          resultado++;
        }
      }
      return resultado;
    }

    function generarDatos(){
       var resultado = {};
       resultado.estandar = 0;
       resultado.especial = 0;
       resultado.sensible = 0;
       for(var i = 0; i< datos.length; i++){
          if(datos[i].categoria_dato == 1){
            resultado.estandar++;
          }else if(datos[i].categoria_dato == 2){
            resultado.especial++;
          }else{
            resultado.sensible++;
          }
       }
       return resultado;

    }

    console.log(generarPreguntas());
     var d = generarDatos();
     console.log(d.estandar + " "+  d.especial + " "+  d.sensible);

     function resultado_final(){
        Configuracion.selectById("1").then(function(c){

          var unidad_monetaria = parseInt(c[0].valor);

          var gd = generarDatos();
          var nominador = gd.estandar * 1 + gd.especial * 2 + gd.sensible * 3;
          var denominador = gd.estandar + gd.especial + gd.sensible;

          var resultado_aux = nominador / denominador;
          resultado_aux = resultado_aux * generarPreguntas() * unidad_monetaria;
          $scope.resultado_final = resultado_aux;
          console.log(parseInt(c[0].valor));
        })
     }

     //resultado_final();
     resultado_final()

    $scope.cambiarVista = function(){
        //$state.go("tab.calculadora");
        $ionicHistory.goBack(-2);
    };
})



