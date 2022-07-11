package dev.muetzilla.m133projektarbeitzoo.service;

import com.sun.jersey.multipart.FormDataParam;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.User;
import dev.muetzilla.m133projektarbeitzoo.util.AES256;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import org.apache.commons.codec.digest.DigestUtils;

@Path("user")
public class UserService {

    /**
     * @param username the username of the user who wants to login
     * @param password the password of the user who want to login
     * @return if the login was successful
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);
        User user = DataHandler.getInstance().readUserRole(username, hashedPassword);
        int httpStatus;
        if(user.getRole().equals("guest")){
            httpStatus = 404;
        }else{
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                AES256.encrypt(user.getRole()),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
    }
    /**
     * @return if the logoff was successful
     */
    @GET
    @Path("logoff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoff() {
        NewCookie cookie = new NewCookie(
                "userRole",
                AES256.encrypt("guest"),
                "/",
                "",
                "Login-Cookie",
                1,
                false
        );
        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }


    /**
     *
     * @param recoverySentence the sentence only the user knows for 2FA
     * @param username username of the user
     * @return if hte 2FA was successful
     */
    @POST
    @Path("authentication")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(
            @FormParam("recoverySentence") String recoverySentence,
            @FormParam("username") String username) {
        User user = DataHandler.getInstance().readUser(username);
        int httpStatus;
        String returnMessage = "";

        if(user.getRecoverySentence().equals(recoverySentence)){
            httpStatus = 200;
            returnMessage += "Recovery sentence was correct";
        }else{
            httpStatus = 401;
            returnMessage += "2Factor Authentication failed, please try again";
        }


        return Response
                .status(httpStatus)
                .entity(returnMessage)
                .build();
    }

}
