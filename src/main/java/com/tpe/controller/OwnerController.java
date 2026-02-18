package com.tpe.controller;

import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.dto.OwnerDTO;
import com.tpe.response.Response;
import com.tpe.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping(path = "/save")
    public ResponseEntity<Response<String>> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO){

      ownerService.save(ownerDTO);

        Response<String> response = new Response<>(
                true,
                "Owner saved successfully"
        );
   return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<OwnerDTO>>> getAllOwners (){
        List<OwnerDTO> owners = ownerService.getAllOwners();
        Response<List<OwnerDTO>> response = new Response<>(
                true,
                "Owners are listed",
                owners
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<OwnerDTO>>findOwnerById(@PathVariable("id") Long id){

        OwnerDTO ownerDTO =ownerService.getOwnerDTOById(id);
        Response<OwnerDTO> response = new Response<>(
                true,
                "Owner is found by ID:" + id,
                ownerDTO
        );
        return ResponseEntity.ok(response);
    }


 @DeleteMapping("/{id}")
 public ResponseEntity<Response<String>>deleteOwnerById(@PathVariable("id") Long id) {
     ownerService.deleteOwnerById(id);
     Response<String> response = new Response<>(
             true,
             "Owner is deleted successfully by ID:" + id
     );
     return ResponseEntity.ok(response);
 }

@PutMapping("/{id}")
public ResponseEntity<Response<OwnerDTO>> updateOwnerById(@PathVariable("id") Long id,@Valid @RequestBody OwnerDTO ownerDTO){
   ownerService.update(id,ownerDTO);
    Response<OwnerDTO> response = new Response<>(
            true,
            "Owner is updated successfully by ID:" + id,
            ownerDTO
    );
    return ResponseEntity.ok(response);
}

@GetMapping(path = "/{id}/books")
    public ResponseEntity<Response<List<BookDTO>>> getOwnerBookListById(@PathVariable("id") Long id){
        List<BookDTO> books = ownerService.getBookListById(id);
        Response<List<BookDTO>> response = new Response<>(
                true,
                "All books are listed by owner ID: "+id,
                books
        );

return ResponseEntity.ok(response);
    }






































 }
