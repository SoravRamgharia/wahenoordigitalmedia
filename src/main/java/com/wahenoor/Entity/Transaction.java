package com.wahenoor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.wahenoor.domain.TransactionStatus;
import com.wahenoor.domain.TransactionType;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;

	@ManyToOne
	@JoinColumn(name = "affiliate_id", nullable = false)
	private Affiliate affiliate;

	@Column(name = "sale_amount", precision = 15, scale = 2, nullable = false)
	private BigDecimal saleAmount;

	@Column(name = "commission", precision = 15, scale = 2, nullable = false)
	private BigDecimal commission;

	@Column(name = "sale_date", nullable = false)
	private LocalDateTime saleDate;

//	@Column(name = "transaction_date", nullable = false)
//	private LocalDateTime transactionDate;

	@Enumerated(EnumType.STRING)
	private TransactionType type;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// Life-Cycle CallBacks
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

}