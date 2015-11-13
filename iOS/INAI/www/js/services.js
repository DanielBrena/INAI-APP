angular.module('starter.services', ['wSQL'])

.factory('Chats', function(wSQL) {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: 'Ben Sparrow',
    lastText: 'You on your way?',
    face: 'img/ben.png'
  }];

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    }
  };
})

.factory('Datos',function(wSQL){
    return{
        selectAll:function(){
            return wSQL.select().from("dato").query();
        }
    }
})


.factory('Preguntas',function(wSQL){  
    return{
      selectAll:function(){
          return wSQL.select().from("pregunta").query();
      }
    }
})

.factory('CategoriaDatos',function(wSQL){
    return{
      
      selectById:function(id){
        return wSQL.select().from("categoria_dato")
        .where("id", id).query()
        
      }
    }
})

.factory('CategoriaPrincipios',function(wSQL){
    return{
      selectById:function(id){
        return wSQL.select().from("categoria_principios")
        .where("id", id).query()
      }
    }
})

.factory('Configuracion',function(wSQL){
    return{
      selectById:function(id){
        return wSQL.select().from("configuracion")
        .where("id", id).query()
      }
    }
})




