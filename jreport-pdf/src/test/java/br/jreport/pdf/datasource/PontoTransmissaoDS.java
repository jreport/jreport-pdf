package br.jreport.pdf.datasource;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.pdf.model.PontoTransmissao;

public class PontoTransmissaoDS implements NewDatasource<PontoTransmissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DecimalFormat df = new DecimalFormat("#.00");

	@Override
	public List<PontoTransmissao> getList() {
		List<PontoTransmissao> retorno = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			retorno.add(new PontoTransmissao(i + "ª Zona Eleitoral", df.format((Math.random() * i + 10))));
		}
		return retorno;
	}

	@Override
	public Optional<String> emptyMessage() {
		return Optional.of("Não há pontos de transmissão");
	}

}
