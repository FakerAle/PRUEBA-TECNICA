package com.test.cliente.domain.service.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * With the aim of standardizing responses in services, this class has been created
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LogMessages {

    // SERVER
    SERVER_ERROR("Error interno del servidor"),
    NOT_FOUND("No se encontraron registros."),
    ERROR_DB_CONNECTION("Error de conexión con BD."),
    BAD_REQUEST("Error en la petición."),
    SERVICE_UNAVAILABLE("Servicio temporalmente no disponible"),

    // QUERIES
    PROCESS_ERROR("Error de ejecución durante el proceso."),
    SUCCESS_QUERY("Finalizó correctamente la consulta."),
    ERROR_QUERY("No fue posible realizar la consulta."),

    //UPDATES
    SUCCESS_UPDATE("Registro modificado exitosamente."),
    SUCCESS_PUT("Registro creado exitosamente."),
    NOT_UPDATE("No se actualizaron registros."),
    ERROR_UPDATE("No fue posible actualizar el registro.");

    private String status;
}