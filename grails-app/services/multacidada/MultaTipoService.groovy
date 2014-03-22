package multacidada

import grails.transaction.Transactional

@Transactional
class MultaTipoService {

    def loadTipos() {
		MultaTipo.findByNome("Farol vermelho") ?: new MultaTipo(nome:"Farol vermelho").save(failOnError: true);
		MultaTipo.findByNome("Estacionamento proibido") ?: new MultaTipo(nome:"Estacionamento proibido").save(failOnError: true);
		MultaTipo.findByNome("Rodizio") ?: new MultaTipo(nome:"Rodizio").save(failOnError: true);
		MultaTipo.findByNome("Circulando em faixa exclusiva") ?: new MultaTipo(nome:"Circulando em faixa exclusiva").save(failOnError: true);
		MultaTipo.findByNome("Vaga deficiente") ?: new MultaTipo(nome:"Vaga deficiente").save(failOnError: true);
		MultaTipo.findByNome("Vaga idoso") ?: new MultaTipo(nome:"Vaga idoso").save(failOnError: true);
		MultaTipo.findByNome("Parado em faixa de pedestre") ?: new MultaTipo(nome:"Parado em faixa de pedestre").save(failOnError: true);
		MultaTipo.findByNome("Veiculo precario") ?: new MultaTipo(nome:"Veiculo precario").save(failOnError: true);
	}
}
