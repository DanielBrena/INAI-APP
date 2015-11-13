/**
 * db_params = {
 *      name: "my_db_name",
 *      version: "my_db_version",
 *      sub_name: "my_db_sub_name",
 *      size: "my_db_size"
 * }
 *
 * tables_sql = {
 *
 *      "table1"    :   [
 *          "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
 *          "category_id INTEGER NULL"
 *      ],
 *      "table2"    :   [
 *          "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
 *          "category_id INTEGER NULL"
 *      ],
 *
 * }
 */
angular.module('wSQL.config', [])
.constant("W_SQL_CONFIG", {
    PARAMS: {
        name: "db_INAI",
        version: "1.0",
        sub_name: "db_inai",
        size: 1000000
    },
    TABLES_SQL: {
        "categoria_dato"    :   [
            "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
            "nombre VARCHAR(255) NULL",
            "descripcion VARCHAR(255)",
            "color VARCHAR(100)",
            "valor VARCHAR(100)"
        ],
        "dato"    :   [
            "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
            "nombre VARCHAR(255) NULL",
            "descripcion VARCHAR(255)",
            "categoria_dato INTEGER",
            "FOREIGN KEY (categoria_dato) REFERENCES categoria_dato(id)"
        ],
        "categoria_principios"    :   [
            "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
            "nombre VARCHAR(255) NULL",
            "descripcion VARCHAR(255)"
        ],
        "pregunta"    :   [
            "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
            "pregunta VARCHAR(255) NULL",
            "categoria_principio INTEGER",
            "FOREIGN KEY (categoria_principio) REFERENCES categoria_principios(id)"
        ],
        "configuracion"    :   [
            "id INTEGER PRIMARY KEY AUTOINCREMENT NULL",
            "nombre VARCHAR(255) NULL",
            "valor VARCHAR(255)"
        ]
    },
    
});



