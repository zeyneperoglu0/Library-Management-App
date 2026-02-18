package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.dto.OwnerDTO;
import com.tpe.exception.BookNotFoundException;
import com.tpe.exception.ConflictException;
import com.tpe.exception.OwnerNotFoundException;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;


    public void save(OwnerDTO ownerDTO){
        boolean exists = ownerRepository.existsByEmail(ownerDTO.getEmail());
        if (exists) {
        throw new ConflictException("This email already used "+ownerDTO.getEmail());

        }
        Owner owner = new Owner();

        owner.setName(ownerDTO.getName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(owner);
    }

    public List<OwnerDTO> getAllOwners(){
        List<Owner> ownerDTOS = ownerRepository.findAll();

        if (ownerDTOS.isEmpty()) {
        throw new OwnerNotFoundException("Owners not found");
        }

        return ownerDTOS.stream().map(OwnerDTO::new).collect(Collectors.toList());
    }

    public OwnerDTO getOwnerDTOById(Long id){
      Owner owner = findOwnerById(id);

      return new  OwnerDTO(owner);




    }

    public Owner findOwnerById(Long id){
        return  ownerRepository.findById(id).orElseThrow(()->
                new OwnerNotFoundException("Owner not found ID: " + id));

    }



  public void deleteOwnerById(Long id){
        Owner owner = findOwnerById(id);
        for (Book book:owner.getBooks()){
         book.setOwner(null);

        }
        ownerRepository.delete(owner);
  }

  public void update(Long id,OwnerDTO ownerDTO){
 Owner owner = findOwnerById(id);
 owner.setName(ownerDTO.getName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(owner);
  }

 public List<BookDTO> getBookListById(Long id){

        Owner owner = findOwnerById(id);
        List<Book> books = owner.getBooks();

     if (!owner.getBooks().isEmpty()) {
         return books.stream().map(BookDTO::new).collect(Collectors.toList());

     }else {
         throw  new BookNotFoundException("This owner has no books");
     }



 }













}
