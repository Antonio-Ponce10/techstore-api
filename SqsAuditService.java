import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SqsAuditService {

    @Autowired
    private SqsClient sqsClient;
    
    // OJO: Esta URL la cambiaremos más adelante cuando creemos la cola en AWS
    private final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/192980466647/techstore-audit-queue"; 
    
    public void enviarAuditoria(String accion, Long productoId, String nombreProducto, String usuario) {
        try {
            // 1. Armar el JSON de la auditoría
            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put("accion", accion);
            mensaje.put("productoId", productoId);
            mensaje.put("nombre", nombreProducto);
            mensaje.put("usuario", usuario); // El correo del usuario que hizo el cambio
            mensaje.put("fecha", LocalDateTime.now().toString());

            ObjectMapper mapper = new ObjectMapper();
            String mensajeJson = mapper.writeValueAsString(mensaje);

            // 2. Enviar el mensaje a SQS
            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .messageBody(mensajeJson)
                    .build();

            sqsClient.sendMessage(sendMsgRequest);
            System.out.println("✅ [Auditoría] Mensaje enviado a SQS: " + mensajeJson);
            
        } catch (Exception e) {
            System.err.println("❌ Error al enviar mensaje a SQS: " + e.getMessage());
        }
    }
}