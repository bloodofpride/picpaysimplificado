package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    private static final String URL_NOTIFICATION = "http://o4d9z.mocklab.io/notify";
    private RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws Exception {
        NotificationDTO notifReq = new NotificationDTO(user.getEmail(), message);

        ResponseEntity<String> response = restTemplate.postForEntity(URL_NOTIFICATION, notifReq, String.class);

        if(response.getStatusCode() != HttpStatus.OK){
            System.out.println("erro ao enviar notificação");
            throw new Exception("Serviço de notificação fora do ar.");
        }
    }
}
