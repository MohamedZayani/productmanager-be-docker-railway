package com.example.productmanager.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.productmanager.model.Product;
import com.example.productmanager.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:4200") // 👈 Autorise Angular
public class ProductController {

	private final ProductRepository repository;

	public ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAll() {
		System.out.println("ici");
		return repository.findAll();
	}

	@PostMapping
	public Product create(@RequestBody Product p) {
		return repository.save(p);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable(name = "id") Long id) {
		return repository.findById(id).get();
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {

		Product pu = repository.findById(id).get();
		pu.setName(product.getName());
		pu.setPrice(product.getPrice());
		pu.setQuantity(product.getQuantity());
		return repository.save(pu);
	}

}
