package com.bitboffin.mobiquity.packaging.helper;

import com.bitboffin.mobiquity.packaging.exception.PackageAPIException;

/**
 * @author Amandeep Singh
 * @class  PackageService class that used to keep signature for the assignment. Also acts as facade.
 */

public class PackageService {
	
	/**
     * Main service method
     *
     * @param filePath file name
     * @return string output
     * @throws PackageAPIException business cases related exception
     */
	public static String performPackaging(String filePath) throws PackageAPIException {
		DefaultPackaging defaultPackaging = new DefaultPackaging();
		return defaultPackaging.pack(filePath);
	}	
	
}
