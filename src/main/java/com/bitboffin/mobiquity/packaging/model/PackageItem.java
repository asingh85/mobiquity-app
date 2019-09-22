/**
 * 
 */
package com.bitboffin.mobiquity.packaging.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Amandeep Singh
 * Class that serves for data wrapping. Also holds some important methods for data processing.
 */
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PackageItem implements Comparable<PackageItem> {
	private Integer packageIndex;
    private Double packageWeight;
    private Integer packageCost;
	
    @Override
	public int compareTo(@SuppressWarnings("NullableProblems") PackageItem arg0) {
    	if(Objects.equals(this.packageCost, arg0.packageCost)){
    		return this.packageWeight > arg0.packageWeight? 1 : -1;
    	}
		return this.packageCost > arg0.packageCost? 1 : -1;
	}
}
