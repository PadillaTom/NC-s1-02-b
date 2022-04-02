package consultation.by.video.call.auth.controller;

import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.auth.service.abstraction.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@Api(value = "User Controller", description = "Crud basic for users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/me")
    @ApiOperation(value = "Get info user", notes = "Return a user info")
    public ResponseEntity<?> getMyUser() {
        try {
          User userInstance = (User) userService.getInfoUser();      
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(userInstance.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }   
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id", notes = "Return a user by id")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {   
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
    
    @GetMapping("/all")
    @ApiOperation(value = "Get all users", notes = "Return list users")
    public ResponseEntity<?> getAllUser() {     
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }
    
   
}
