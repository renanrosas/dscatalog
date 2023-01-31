package com.renanrosas.dscatalog.dto;

import com.renanrosas.dscatalog.services.validation.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserInsertValid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO extends UserDTO{

    private String password;

}
