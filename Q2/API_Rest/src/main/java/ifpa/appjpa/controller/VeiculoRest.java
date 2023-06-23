package ifpa.appjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifpa.appjpa.domain.Veiculo;
import ifpa.appjpa.domain.ProdutosRepo;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoRest {
	@Autowired 
	private ProdutosRepo produtoRepo;
	
	public VeiculoRest() {	}
	
	@GetMapping("/{id}")
	public Veiculo buscar(@PathVariable Long id) {
		System.out.println("pesquisando o ID = " + id);
		return produtoRepo.findById(id).orElse(new Veiculo());
	}
	
	@GetMapping
	public List<Veiculo> pesquisar() {		
		return produtoRepo.findAll();
	}

	@PostMapping
	public Veiculo salvar(@RequestBody Veiculo produto) {	
		
		return produtoRepo.save(produto);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		produtoRepo.deleteById(id);
	}

	@PutMapping
	public Veiculo alterar(@RequestBody Veiculo produto) {	
		return produtoRepo.save(produto);
	}
}
