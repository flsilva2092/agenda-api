package com.flsilva2092.agenda.service;

import com.flsilva2092.agenda.model.Contato;
import com.flsilva2092.agenda.model.Estatistica;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that implements methods related to the statistics.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@Service
public class EstatisticaService {
	
	/**
	 * Method that creates statistics based on travels.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @return Statistic
	 */
	public Estatistica create (List<Contato> contatos) {
		
		var statistics = new Estatistica();
		statistics.setCount(contatos.stream().count());
//		statistics.setAvg(BigDecimal.valueOf(contatos.stream().mapToDouble(t -> t.getAmount().doubleValue()).average().orElse(0.0))
//				.setScale(2, RoundingMode.HALF_UP));
//		statistics.setMin(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getAmount().doubleValue()).min().orElse(0.0))
//				.setScale(2, RoundingMode.HALF_UP));
//		statistics.setMax(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getAmount().doubleValue()).max().orElse(0.0))
//				.setScale(2, RoundingMode.HALF_UP));
//		statistics.setSum(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getAmount().doubleValue()).sum())
//				.setScale(2, RoundingMode.HALF_UP));
		
		return statistics;
	}

}
