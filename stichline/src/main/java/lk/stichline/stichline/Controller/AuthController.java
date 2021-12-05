package lk.stichline.stichline.Controller;


import lk.stichline.stichline.entity.AuthenticationRequest;
import lk.stichline.stichline.entity.AuthenticationResponse;
import lk.stichline.stichline.entity.User;
import lk.stichline.stichline.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/newuser")
    private ResponseEntity NewUser(@RequestBody AuthenticationRequest authenticationRequest){
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            userRepo.save(user);
        }catch (Exception e){
            return  ResponseEntity.ok(new AuthenticationResponse("Registration is Failed "+username));
        };

        return  ResponseEntity.ok(new AuthenticationResponse("Successfully Registered "+username));
    }

    @PostMapping("/user")
    private ResponseEntity User(@RequestBody AuthenticationRequest authenticationRequest){

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e){
            return  ResponseEntity.ok(new AuthenticationResponse("Error during User Authentication "+username));
        };

        return  ResponseEntity.ok(new AuthenticationResponse("Successful Authentication for User "+username));

    }
}
