package com.bitboffin.mobiquity.packaging.exception;

/**
 * @author Amandeep Singh
 * @class  Exception class that serves for business exception cases.
 */

@SuppressWarnings("serial")
public class PackageAPIException extends RuntimeException {
	
	public PackageAPIException(String strMessage){
		super(strMessage);
	}
}
