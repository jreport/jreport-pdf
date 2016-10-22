package br.jreport.pdf.adapter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.jreport.core.api.adapter.TableAdapter;
import br.jreport.pdf.model.PontoTransmissao;

public class PontoTransmissaoTableAdapter implements TableAdapter<PontoTransmissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Optional<String> getColumnValue(PontoTransmissao item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Optional.of(item.getId());
		case 1:
			return Optional.of(item.getNome());
		}
		return Optional.empty();
	}

	@Override
	public List<String> getHeaders() {
		return Arrays.asList("Id", "Nome");
	}
}
