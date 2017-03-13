package br.jreport.pdf.datasource;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.pdf.model.CategoriaPagamentoModel;

public class TableExampleDS implements NewDatasource<CategoriaPagamentoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Random random = new Random();
	DecimalFormat df = new DecimalFormat("#.00");
	
	public TableExampleDS() {
		List<CategoriaPagamentoModel> model = new ArrayList<CategoriaPagamentoModel>();
		CategoriaPagamentoModel model1 = new CategoriaPagamentoModel("ANUIDADE1", criaMap());
		CategoriaPagamentoModel model2 = new CategoriaPagamentoModel("ANUIDADE2", criaMap());
		CategoriaPagamentoModel model3 = new CategoriaPagamentoModel("ALUGUÉIS SALAS E AUDITÓRIOS P/EVENTOS", criaMap());
		CategoriaPagamentoModel model4 = new CategoriaPagamentoModel("CANCELADOS", criaMap());
		CategoriaPagamentoModel model5 = new CategoriaPagamentoModel("COMISSÃO", criaMap());
		CategoriaPagamentoModel model6 = new CategoriaPagamentoModel("DOAÇÃO", criaMap());
		CategoriaPagamentoModel model7 = new CategoriaPagamentoModel("CURSOS", criaMap());
		CategoriaPagamentoModel model8 = new CategoriaPagamentoModel("DEPÓSITOS", criaMap());
		CategoriaPagamentoModel model9 = new CategoriaPagamentoModel("MENSALIDADE", criaMap());
		CategoriaPagamentoModel model10 = new CategoriaPagamentoModel("OUTRAS RECEITAS", criaMap());
		CategoriaPagamentoModel model11 = new CategoriaPagamentoModel("DIVERSOS", criaMap());
		CategoriaPagamentoModel model12 = new CategoriaPagamentoModel("PROPAGANDA", criaMap());
		CategoriaPagamentoModel model13 = new CategoriaPagamentoModel("RECUPERAÇÃO DE DESPESAS", criaMap());
		CategoriaPagamentoModel model14 = new CategoriaPagamentoModel("RENDIMENTO APLICAÇÃO", criaMap());
		CategoriaPagamentoModel model15 = new CategoriaPagamentoModel("SÓCIO1", criaMap());
		CategoriaPagamentoModel model16 = new CategoriaPagamentoModel("SÓCIO2", criaMap());
		model.add(model1);
		model.add(model2);
		model.add(model3);
		model.add(model4);
		model.add(model5);
		model.add(model6);
		model.add(model7);
		model.add(model8);
		model.add(model9);
		model.add(model10);
		model.add(model11);
		model.add(model12);
		model.add(model13);
		model.add(model14);
		model.add(model15);
		model.add(model16);
	}
	
	private static HashMap<String, BigDecimal> criaMap() {
		long LOWER_RANGE = 0; // assign lower range value
		long UPPER_RANGE = 1000000; // assign upper range value

		HashMap<String, BigDecimal> totalMeses = new HashMap<String, BigDecimal>();
		totalMeses.put("JAN", BigDecimal.valueOf(LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE)), 2));
		totalMeses.put("FEV", BigDecimal.valueOf(LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE)), 2));
		totalMeses.put("MAR", BigDecimal.valueOf(LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE)), 2));
		totalMeses.put("ABR", BigDecimal.valueOf(LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE)), 2));
		totalMeses.put("MAI", BigDecimal.valueOf(LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE)), 2));
		return totalMeses;
	}

	@Override
	public List<CategoriaPagamentoModel> getList() {
		List<CategoriaPagamentoModel> retorno = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
		}
		return retorno;
	}

	@Override
	public Optional<String> emptyMessage() {
		return Optional.of("Não há pontos de transmissão");
	}

}
