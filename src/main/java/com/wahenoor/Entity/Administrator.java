package com.wahenoor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.wahenoor.domain.AdminRole;
import com.wahenoor.domain.EntityStatus;

@Entity
@Table(name = "administrator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Administrator extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private String phone;

//	@Column(name = "join_date", nullable = false)
//	private LocalDate joinDate;

	@Enumerated(EnumType.STRING)
	private AdminRole role;
}
