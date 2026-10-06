package com.rikkei.pharmacyservice.entity;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="invoices") public class Invoice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal amount;
    @Column(nullable=false,length=30) private String status;
    protected Invoice() {}
    public Invoice(BigDecimal amount) { this.amount=amount; this.status="PENDING"; }
    public Long getId(){return id;} public BigDecimal getAmount(){return amount;} public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
