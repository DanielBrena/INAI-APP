var categoria_datos = [
    {
        id: 1,
        nombre: "Nivel estandar",
        descripcion: "Esta categoría considera información de identificación, contacto datos laborales y académicos de una persona física identificada o identificable.",
        color:"#4CAF50",
            valor:"1"
    },
    {
        id: 2,
        nombre: "Nivel sensible",
        descripcion: "Esta categoría contempla los datos que permiten conocer la ubicación física de la persona, tales como la dirección física e información relativa al tránsito de las personas dentro y fuera del país.",
        color:"#FFC107",
        valor:"2"
    },
    {
        id: 3,
        nombre: "Nivel especial",
        descripcion: "Esta categoría corresponde a los datos cuya propia naturaleza, o bien debido a un cambio excepcional en el contexto de las operaciones usuales de la organización, pueden causar daño directo al patrimonio o seguridad de los titulares.",
        color:"#F44336",
        valor:"3"
    }

        
];
var categoria_principios = [
	{
		id:1,
		nombre: "Transparencia",
		descripcion: "La percepción del titular respecto a la claridad del tratamiento que el responsable le da a sus datos."
	},
    {
    	id:2,
    	nombre: "Confianza",
    	descripcion: "La confianza que el titular experimenta al proporcionar su información personal al responsable, a cambio de recibir un producto o servicio."
    },
    {
    	id:3,
    	nombre: "Control",
    	descripcion: " La capacidad del titular para ejercer de manera efectiva sus derechos de Acceso, Rectificación, Cancelación y Oposición de los datos que proporciona al responsable."
    },
    {
    	id:4,
    	nombre: "Valoración",
    	descripcion:"La percepción del titular respecto a si sus datos serán explotados para un fin distinto al original de cuando fueron recabados."
    }

]
var estandar = 1
var especial = 2
var sensible = 3
var datos = [
	{id:1, nombre:"Nombre",descripcion:"", categoria_dato:estandar},
            {id:2,nombre:"Teléfono",descripcion: "", categoria_dato: estandar},
            {id:3,nombre:"Edad", descripcion:"",  categoria_dato:estandar},
            {id:4,nombre:"Edad", descripcion:"",  categoria_dato:estandar},
            {id:5,nombre:"Sexo",descripcion: "",  categoria_dato:estandar},
            {id:6,nombre:"RFC", descripcion:"Registro Federal del Contribuyente",  categoria_dato:estandar},
            {id:7,nombre:"CURP", descripcion:"Clave Única del Registro de Poblacion", categoria_dato: estandar},
            {id:8,nombre:"Estado Civil",descripcion: "", categoria_dato: estandar},
            {id:9,nombre:"Dirección de Correo Electrónico",descripcion: "",  categoria_dato:estandar},
            {id:10,nombre:"Lugar y Fecha de Nacimiento", descripcion:"",  categoria_dato:estandar},
            {id:11,nombre:"Nacionalidad",descripcion: "",  categoria_dato:estandar},
            {id:12,nombre:"Puesto de Trabajo",descripcion: "",  categoria_dato:estandar},
            {id:13,nombre:"Lugar de Trabajo",descripcion: "",  categoria_dato:estandar},
            {id:14,nombre:"Experiencia Laboral", descripcion:"", categoria_dato: estandar},
            {id:15,nombre:"Datos de Contacto Laboral", descripcion:"",  categoria_dato:estandar},
            {id:16,nombre:"Idioma o Lengua", descripcion:"", categoria_dato: estandar},
            {id:17,nombre:"Escolaridad", descripcion:"",  categoria_dato:estandar},
            {id:18,nombre:"Trayectoria Educativa",descripcion: "", categoria_dato: estandar},
            {id:19,nombre:"Título", descripcion:"",  categoria_dato:estandar},
            {id:20,nombre:"Certificados",descripcion: "", categoria_dato: estandar},
            {id:21,nombre:"Cédula Profesional",descripcion: "",  categoria_dato:estandar},
            
            
            {id:22,nombre:"Saldo Bancario",descripcion:"", categoria_dato:especial},
            {id:23,nombre:"Estado o Número de Cuenta",descripcion:"", categoria_dato:especial},
            {id:24,nombre:"Cuentas de Inversion",descripcion:"", categoria_dato:especial},
            {id:25,nombre:"Bienes Muebles e Inmuebles",descripcion:"", categoria_dato:especial},
            {id:26,nombre:"Informacion Fiscal",descripcion:"", categoria_dato:especial},
            {id:27,nombre:"Historial Crediticio",descripcion:"", categoria_dato:especial},
            {id:28,nombre:"Ingresos",descripcion:"", categoria_dato:especial},
            {id:29,nombre:"Egresos",descripcion:"", categoria_dato:especial},
            {id:30,nombre:"Buró de Credito",descripcion:"", categoria_dato:especial},
            {id:31,nombre:"Seguros",descripcion:"", categoria_dato:especial},
            {id:32,nombre:"Afores",descripcion:"", categoria_dato:especial},
            
            {id:33,nombre:"Fianzas",descripcion:"",categoria_dato:especial},
            {id:34,nombre:"Tarjeta de Credito o Débito",descripcion:"", categoria_dato:especial},
            {id:35,nombre:"Contraseñas",descripcion:"", categoria_dato:especial},
            {id:36,nombre:"Información Biométrica",descripcion:"Huellas dactilares, iris, voz, entre otros.", categoria_dato:especial},
            {id:37,nombre:"Firma autógrafa",descripcion:"", categoria_dato:especial},
            {id:38,nombre:"Firma eletrónica",descripcion:"", categoria_dato:especial},
            {id:39,nombre:"Antecedentes Penales",descripcion:"", categoria_dato:especial},
            {id:40,nombre:"Amparos",descripcion:"", categoria_dato:especial},
            {id:41,nombre:"Demandas",descripcion:"", categoria_dato:especial},
            {id:42,nombre:"Contratos",descripcion:"", categoria_dato:especial},
            {id:43,nombre:"Litigios",descripcion:"", categoria_dato:especial},
            {id:44,nombre:"Origen Racial o Étnico",descripcion:"", categoria_dato:especial},
            {id:45,nombre:"Estado de Salud",descripcion:"", categoria_dato:especial},
            {id:46,nombre:"Información Genética",descripcion:"", categoria_dato:especial},
            {id:47,nombre:"Creencias Religiosas",descripcion:"", categoria_dato:especial},
            {id:48,nombre:"Creencias Filosóficas y Morales",descripcion:"", categoria_dato:especial},
            {id:49,nombre:"Afiliación Sindical",descripcion:"", categoria_dato:especial},
            {id:50,nombre:"Opiniones politicas",descripcion:"", categoria_dato:especial},
            {id:51,nombre:"Preferencia Sexual",descripcion:"", categoria_dato:especial},
            {id:52,nombre:"Hábitos Sexuales",descripcion:"", categoria_dato:especial},
            {id:53,nombre:"Hábitos Sexuales",descripcion:"", categoria_dato:especial},
            {id:54,nombre:"Dirección",descripcion:"", categoria_dato:especial},
            
            {id:55,nombre:"Fecha Vencimiento de Tarjeta Bancaria",descripcion:"", categoria_dato:sensible},
            {id:56,nombre:"Código de Seguridad de Tarjeta Bancaria",descripcion:"", categoria_dato:sensible},
            {id:57,nombre:"Datos de Banda Magnética",descripcion:"", categoria_dato:sensible},
            {id:58,nombre:"PIN",descripcion:"Número de Identificación Personal", categoria_dato:sensible}
]
var transparencia = 1;
var confianza = 2;
var control = 3;
var valoracion = 4;
var preguntas = [
	{
		id:1,
	 	pregunta:"¿Considera que el responsable es claro respecto al tratamiento que dará a sus datos personales?",
	 	categoria_principio:transparencia
	},
    {
    	id:2,
    	pregunta:"¿Al proporcionar su información personal para recibir un producto o servicio, el responsable le inspira confianza?",
    	categoria_principio:confianza
    },
    {
    	id:3,
    	pregunta:"¿Siente que el responsable le proporciona mecanismos suficientes para acceder, rectificar, cancelar u oponerse al tratamiento de la información personal que le proporcionó? ",
    	categoria_principio: control
    },
    {
    	id:4,
    	pregunta:"¿Percibe que los datos personales que proporcionó tienen un valor adicional para el responsable, de modo que puedan ser explotados posteriormente? ",
    	categoria_principio:valoracion
    }
]

var configuracion = [
	{
		id:1,
		nombre:"unidad_monetaria",
		valor:"150"
	}
]
















