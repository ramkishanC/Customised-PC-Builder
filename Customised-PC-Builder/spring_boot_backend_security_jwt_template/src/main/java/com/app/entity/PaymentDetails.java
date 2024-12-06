package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
	
	private String paymentMethod;
	private String status;
	private String paymentid;
	private String razorPaymentLonkId;
	private String razorPaymentLinkReferencedId;
	private String razorPaymentLinkStatus;
	private String razorPaymentId;
	
	

}
