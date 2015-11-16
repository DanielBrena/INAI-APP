//
//  DatoDAO.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 13/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

class DatoDAO {
    func selectAll()->[Dato]{
        var arreglo = [Dato]()
        let estandar = CategoriaDatoDAO().selectById("1")
        let sensible = CategoriaDatoDAO().selectById("2")
        let especial = CategoriaDatoDAO().selectById("3")
        arreglo.append(Dato(id:"1", nombre:"Nombre",descripcion:"", categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"2",nombre:"Teléfono",descripcion: "", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"3",nombre:"Edad", descripcion:"",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"4",nombre:"Sexo",descripcion: "",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"5",nombre:"RFC", descripcion:"Registro Federal del Contribuyente",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"6",nombre:"CURP", descripcion:"Clave Única del Registro de Poblacion", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"7",nombre:"Estado Civil",descripcion: "", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"8",nombre:"Dirección de Correo Electrónico",descripcion: "",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"9",nombre:"Lugar y Fecha de Nacimiento", descripcion:"",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"10",nombre:"Nacionalidad",descripcion: "", categoria:estandar, isChecked: false))
        arreglo.append(Dato(id:"11",nombre:"Puesto de Trabajo",descripcion: "",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"12",nombre:"Lugar de Trabajo",descripcion: "",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"13",nombre:"Experiencia Laboral", descripcion:"", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"14",nombre:"Datos de Contacto Laboral", descripcion:"",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"15",nombre:"Idioma o Lengua", descripcion:"", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"16",nombre:"Escolaridad", descripcion:"",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"17",nombre:"Trayectoria Educativa",descripcion: "", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"18",nombre:"Título", descripcion:"",  categoria:estandar,isChecked: false))
        arreglo.append(Dato(id:"19",nombre:"Certificados",descripcion: "", categoria: estandar,isChecked: false))
        arreglo.append(Dato(id:"20",nombre:"Cédula Profesional",descripcion: "",  categoria:estandar,isChecked: false))
        
        arreglo.append(Dato(id:"21",nombre:"Saldo Bancario",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"22",nombre:"Estado o Número de Cuenta",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"23",nombre:"Cuentas de Inversion",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"24",nombre:"Bienes Muebles e Inmuebles",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"25",nombre:"Informacion Fiscal",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"26",nombre:"Historial Crediticio",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"27",nombre:"Ingresos",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"28",nombre:"Egresos",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"29",nombre:"Buró de Credito",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"30",nombre:"Seguros",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"31",nombre:"Afores",descripcion:"", categoria:sensible,isChecked: false))
        
        arreglo.append(Dato(id:"32",nombre:"Fianzas",descripcion:"",categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"33",nombre:"Tarjeta de Credito o Débito",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"34",nombre:"Contraseñas",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"35",nombre:"Información Biométrica",descripcion:"Huellas dactilares, iris, voz, entre otros.", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"36",nombre:"Firma autógrafa",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"37",nombre:"Firma eletrónica",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"38",nombre:"Antecedentes Penales",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"39",nombre:"Amparos",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"40",nombre:"Demandas",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"41",nombre:"Contratos",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"42",nombre:"Litigios",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"43",nombre:"Origen Racial o Étnico",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"44",nombre:"Estado de Salud",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"45",nombre:"Información Genética",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"46",nombre:"Creencias Religiosas",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"47",nombre:"Creencias Filosóficas y Morales",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"48",nombre:"Afiliación Sindical",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"49",nombre:"Opiniones politicas",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"50",nombre:"Preferencia Sexual",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"51",nombre:"Hábitos Sexuales",descripcion:"", categoria:sensible,isChecked: false))
        arreglo.append(Dato(id:"53",nombre:"Dirección",descripcion:"", categoria:especial,isChecked: false))
        
        arreglo.append(Dato(id:"54",nombre:"Fecha Vencimiento de Tarjeta Bancaria",descripcion:"", categoria:especial,isChecked: false))
        arreglo.append(Dato(id:"55",nombre:"Código de Seguridad de Tarjeta Bancaria",descripcion:"", categoria:especial,isChecked: false))
        arreglo.append(Dato(id:"56",nombre:"Datos de Banda Magnética",descripcion:"", categoria:especial,isChecked: false))
        arreglo.append(Dato(id:"57",nombre:"PIN",descripcion:"Número de Identificación Personal", categoria:especial,isChecked: false))
        return arreglo

    }
}