package com.syf.api.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class CustomerDetails {

	private String customer_name;
	private String customer_id;
	private Long account_id;
	LocalDateTime created_date;

	public CustomerDetails(String customer_name, String customer_id, Long account_id, LocalDateTime created_date) {
		this.created_date = created_date;
		this.customer_id = customer_id;
		this.account_id = account_id;
		this.customer_name = customer_name;
	}

	@Override
	public String toString() {
		return "Customer Details [customer name = " + customer_name + " , customer_id= " + customer_id
				+ " , account_id= " + account_id + " , created_date= " + created_date + "]";

	}

}
