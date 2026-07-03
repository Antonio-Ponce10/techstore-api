exports.handler = async (event) => {
    console.log("[FaaS Audit] Función Serverless de Auditoría iniciada...");

    // 1. Iteramos sobre los mensajes que llegaron de SQS
    for (const record of event.Records) {
        // 2. Extraemos el cuerpo del mensaje (el JSON que mandó tu API Java)
        const body = JSON.parse(record.body);
        
        // 3. Imprimimos el log con el formato esperado por el profesor
        console.log("=======================================================");
        console.log("[FaaS Audit] NUEVA AUDITORÍA DETECTADA EN SQS");
        console.log("=======================================================");
        console.log(`Acción Realizada: ${body.accion}`);
        console.log(`ID Producto/Cat: ${body.productoId}`);
        console.log(`Nombre: ${body.nombre}`);
        console.log(`Usuario Operador: ${body.usuario}`);
        console.log(`Fecha Operación: ${body.fecha}`);
        console.log("=======================================================");
    }

    return {
        statusCode: 200,
        body: JSON.stringify('Procesamiento de auditoría finalizado con éxito.'),
    };
};