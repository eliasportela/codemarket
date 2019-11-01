package br.com.codebit.codemarket.services;

import br.com.codebit.codemarket.dtos.ProductPriceDTO;
import br.com.codebit.codemarket.entitys.Product;
import br.com.codebit.codemarket.entitys.ProductPrice;
import br.com.codebit.codemarket.repositories.ProductPriceRepository;
import br.com.codebit.codemarket.repositories.ProductRepository;
import br.com.codebit.codemarket.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductPriceService {

    @Autowired
    private ProductPriceRepository repo;

    public ProductPrice find(Integer id) {
        Optional<ProductPrice> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Preço não encontrado!"));
    }

    public void deleteAll(List<ProductPrice> list) {
        if (list != null && !list.isEmpty()) {
            for (ProductPrice price : list) {
                price.setExcluded_at(new Date());
                price.setExcluded(true);
            }

            repo.saveAll(list);
        }
    }

    public void saveAll(List<ProductPriceDTO> list, Product product) {
        if (list != null && !list.isEmpty()) {
            for (ProductPriceDTO dto : list) {
                if (dto.getId() != null) {
                    ProductPrice obj = find(dto.getId());
                    repo.save(update(obj, dto));

                } else {
                    repo.save(new ProductPrice(dto.getName(), dto.getCostValue(), dto.getValue(), dto.getMain(), product));
                }
            }
        }
    }

    private ProductPrice update(ProductPrice obj, ProductPriceDTO dto) {
        obj.setName(dto.getName());
        obj.setEnabled(dto.getEnabled());
        obj.setCostValue(dto.getCostValue());
        obj.setValue(dto.getValue());
        obj.setMain(dto.getMain());

        return obj;
    }
}
