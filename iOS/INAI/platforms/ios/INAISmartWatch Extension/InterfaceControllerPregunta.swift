//
//  InterfaceControllerPregunta.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 12/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//


import WatchKit
import Foundation

class InterfaceControllerPregunta:WKInterfaceController{
    
    var preguntas = PreguntaDAO().selectAll()
    var preguntas_seleccionadas = [Pregunta]()
    var datos_seleccionados = [Dato]()
    @IBOutlet var tableView: WKInterfaceTable!
    
   

    override func awakeWithContext(context: AnyObject?) {
        super.awakeWithContext(context)
         self.datos_seleccionados = ((context as! NSDictionary)["data_datos"] as! [Dato])
        print(self.datos_seleccionados[0].nombre)
        setupTable()
        // Configure interface objects here.
    }
    override func willActivate() {
        // This method is called when watch view controller is about to be visible to user
        super.willActivate()
    }
    
    func setupTable() {
        tableView.setNumberOfRows(preguntas.count, withRowType: "PreguntaRow")
        
        for var i = 0; i < preguntas.count; ++i {
            
            if let row = tableView.rowControllerAtIndex(i) as? PreguntaRow,
                let pregunta = self.preguntas[i] as? Pregunta{
                row.pregunta.setText(preguntas[i].pregunta)
                
                let nombre = pregunta.isChecked == false ?  "check-no" : "check-si"
                row.isSi.setImageNamed(nombre)
            }
        }
    }
    
    override func didDeactivate() {
        // This method is called when watch view controller is no longer visible
        super.didDeactivate()
    }
    
    override func table(table: WKInterfaceTable, didSelectRowAtIndex i: Int) {
        
        if let pregunta = self.preguntas[i] as? Pregunta{
            
            if (pregunta.isChecked != true) {
                let id = find( pregunta.id!)
                self.preguntas_seleccionadas.removeAtIndex(id)
                
                print("si \(self.preguntas_seleccionadas.count)")
                print("si")
            } else{
              
                self.preguntas_seleccionadas.append(pregunta)
                
                print("no \(self.preguntas_seleccionadas.count)")
                
            }
            pregunta.isChecked = !pregunta.isChecked!
            setupTable()
        }
        
        print(table)
        
    }
    
    
    
    func find(id:String) ->Int {
        var id_:Int = -1
        for var i = 0; i < self.preguntas_seleccionadas.count; i++ {
            if self.preguntas_seleccionadas[i].id == id {
                id_ = i
                break
            }
        }
        return id_
    }
    
    @IBAction func cargarResultado() {
        self.pushControllerWithName("ResultadoCtrl", context: [
                                                                                "data_datos":self.datos_seleccionados,
                                                                                "data_preguntas":self.preguntas_seleccionadas
                                                                                ])
    }


}