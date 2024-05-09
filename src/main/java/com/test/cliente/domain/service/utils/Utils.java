package com.test.cliente.domain.service.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.cliente.domain.DTO.response.ResponseDTO;

@Service
public class Utils {

    static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static void getStackTrace(StackTraceElement[] stackTraceElements) {
        for(StackTraceElement stackTraceElement : stackTraceElements) {
            if(stackTraceElement.toString().startsWith("com") || stackTraceElement.toString().startsWith("java")) {
                logger.error(stackTraceElement.toString());
            }
        }
    }
    /**
     *
     * Update the objet response on the services
     *
     * @param isOk is ok the response?
     * @param data data to rensponse
     * @param status the HttpStatus of the response
     * @param currentPage current page of the request
     * @param totalPages total pages retrieved
     * @param msg the mesage
     * @return the Response Data Transfer Object
     */
	public static ResponseDTO updateResponse(Boolean isOk, Optional<?> data, Integer status,
            Integer currentPage, Integer totalPages, String msg){
        ResponseDTO response = new ResponseDTO();
        response.setOk(isOk);
        response.setData(data);
        response.setStatus(status);
        response.setCurrentPage(currentPage);
        response.setTotalPages(totalPages);
        response.setMessage(msg);

        return response;
    }

    /**
     * Method that encapsulates the standard actions carried out when there is an error within the services
     * @param logger logger object of the class trigger
     * @param ex exception
     * @return a response data transfer object
     */
    public static ResponseDTO errorResponse(Logger logger, Exception ex){
        logger.error("El servicio de búsqueda no finalizó correctamente. " + ex.getMessage());
        getStackTrace(ex.getStackTrace());
        return updateResponse(false, Optional.empty(), 500, 1, 1, LogMessages.SERVER_ERROR.getStatus());
    }
}
