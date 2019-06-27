package app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, String asunto, String cuerpo) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject(asunto);
        msg.setText(cuerpo);

        javaMailSender.send(msg);
    }

    public void enviarEmailInvitacion(String nombreApellido, String emailInvitado){

        String asunto = "Invitacion a evento";
        String cuerpo = "Enhorabuena!\n" +
                "El usuario de Eventeando: "+ nombreApellido + " te ha invitado a un evento.\n" +
                "Por favor controla tus invitaciones desde la aplicación.\n" +
                "Saludos!";

        this.sendEmail(emailInvitado, asunto, cuerpo);

    }

}
