package com.flsilva2092.agenda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Class that implements the Statistic structure.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
	
	private BigDecimal sum;
	private BigDecimal avg;
	private BigDecimal max;
	private BigDecimal min;
	private long count;

}
