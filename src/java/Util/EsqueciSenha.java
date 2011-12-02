package Util;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EsqueciSenha {

    UsuarioDAO usuariodao = new UsuarioDAO();
    Usuario usuario = new Usuario();
    private String mailSMTPServer;
    private String mailSMTPServerPort;

    public EsqueciSenha() {
        mailSMTPServer = "smtp.gmail.com.br";
        mailSMTPServerPort = "465";
    }

    public EsqueciSenha(String mailSMTPServer, String mailSMTPServerPort) {
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }

    public String Mail(String emailUsu) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        usuario = usuariodao.recuperaSenha(emailUsu);
        String senhaUsu = usuario.getSenhaUsu();
        String nomeUsu = usuario.getNomeUsu();
        
        String assunto = "Solicitação de senha.";
        String mensagem = ("Caro " + nomeUsu + " sua senha é " + senhaUsu);

        Properties props = new Properties();
        String emitente = "javatcc@gmail.com";
        String destino = emailUsu;

        String login = "javatcc@gmail.com";
        String senha = "java2011";

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", emitente);
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort);
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        SimpleAuth auth = null;
        auth = new SimpleAuth(login, senha);
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true);
        Message msg = new MimeMessage(session);

        try {

            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            msg.setFrom(new InternetAddress(emitente));
            msg.setSubject(assunto);
            msg.setContent(mensagem, "text/plain");
        } catch (Exception e) {
            return ("erro ao completar mensagem!");
        }
        Transport tr;
        try {
            tr = session.getTransport("smtp");
            tr.connect(mailSMTPServer, login, senha);
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            return ("mensagem enviada com sucesso!");
        } catch (Exception e) {
            return ("erro ao enviar");
        }
    }

    public class SimpleAuth extends Authenticator {

        public String username = null;
        public String password = null;

        public SimpleAuth(String user, String pwd) {
            username = user;
            password = pwd;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.username, this.password);
        }
    }
}
