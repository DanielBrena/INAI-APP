//
//  PreguntaDAO.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 14/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

class PreguntaDAO {
    func selectAll()->[Pregunta]{
        var arreglo = [Pregunta]()
        
        arreglo.append(Pregunta(
            id:"1",
            pregunta:"¿Considera que el responsable es claro respecto al tratamiento que dará a sus datos personales?",
            categoria:CategoriaPrincipiosDAO().selectById("1"),
            isChecked:true
        ))
        
        arreglo.append(Pregunta(
            id:"2",
            pregunta:"¿Al proporcionar su información personal para recibir un producto o servicio, el responsable le inspira confianza?",
            categoria:CategoriaPrincipiosDAO().selectById("2"),
            isChecked:true
        ))
        
        arreglo.append(Pregunta(
            id:"3",
            pregunta:"¿Siente que el responsable le proporciona mecanismos suficientes para acceder, rectificar, cancelar u oponerse al tratamiento de la información personal que le proporcionó? ",
            categoria: CategoriaPrincipiosDAO().selectById("3"),
            isChecked:true
        ))
        
        arreglo.append(Pregunta(
            id:"4",
            pregunta:"¿Percibe que los datos personales que proporcionó tienen un valor adicional para el responsable, de modo que puedan ser explotados posteriormente? ",
            categoria:CategoriaPrincipiosDAO().selectById("4"),
            isChecked:true
        ))
        return arreglo
    }
}