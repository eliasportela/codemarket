package br.com.codebit.codemarket.services;

import br.com.codebit.codemarket.dtos.ProductDTO;
import br.com.codebit.codemarket.dtos.ProductPriceDTO;
import br.com.codebit.codemarket.entitys.Product;
import br.com.codebit.codemarket.entitys.ProductPrice;
import br.com.codebit.codemarket.repositories.ProductRepository;
import br.com.codebit.codemarket.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductPriceService servicePrice;

    public ProductDTO find(Integer id) {
        Optional<Product> obj = repo.findById(id);

        if (obj.isPresent()) {
            return new ProductDTO(obj.get());
        }

        throw new ObjectNotFoundException("Produto não encontrado");
    }

    public Page<Product> search(String name, Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findByNameContainingIgnoreCaseAndEnabledTrueAndExcludedFalse(name, pageRequest);
    }

    public Product save(ProductDTO dto) {
        Product obj = dto.fromDTO();
        obj = repo.save(obj);
        for (ProductPriceDTO pDTO : dto.getPrices()) {
            pDTO.setId(null);
        }

        servicePrice.saveAll(dto.getPrices(), obj);
        return obj;
    }

    public void update(ProductDTO dto) {
        Optional<Product> obj = repo.findById(dto.getId());

        if (obj.isPresent()) {
            Product objSave = updateEntity(obj.get(), dto);
            repo.save(objSave);
            return;
        }

        throw new ObjectNotFoundException("Produto não encontrado");
    }

    public void delete(Integer id) {
        Product obj = repo.findById(id).orElse(null);

        if (obj != null) {
            obj.setExcluded_at(new Date());
            obj.setExcluded(true);
            repo.save(obj);
            return;
        }

        throw new ObjectNotFoundException("Produto não encontrado");
    }

    private Product updateEntity(Product obj, ProductDTO dto) {
        obj.setName(dto.getName());

        //prices removed
        List<ProductPrice> removeds = new ArrayList<>();
        for (ProductPrice p : obj.getPrices()) {
            boolean found = false;
            for (ProductPriceDTO pDTO : dto.getPrices()) {
                if (pDTO.getId() != null && pDTO.getId().equals(p.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                removeds.add(p);
            }
        }

        servicePrice.deleteAll(removeds);
        servicePrice.saveAll(dto.getPrices(), obj);
        repo.save(obj);

        return obj;
    }
}
