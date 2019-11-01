package br.com.codebit.codemarket.services;

import br.com.codebit.codemarket.entitys.Product;
import br.com.codebit.codemarket.entitys.ProductPrice;
import br.com.codebit.codemarket.entitys.User;
import br.com.codebit.codemarket.repositories.ProductPriceRepository;
import br.com.codebit.codemarket.repositories.ProductRepository;
import br.com.codebit.codemarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private UserRepository userRepository;

    public void instantiateTestDatabase() {

        List<String> products = Arrays.asList(
                "Barrinha de Ceral",
                "Batata Lays",
                "Batata Mr Potato",
                "Batom",
                "Bolacha Tucs",
                "Castanha Caju",
                "Cebolitos",
                "Chokito",
                "Coca Cola",
                "Coloreti",
                "Crocantis",
                "Cup Noodles",
                "Disqueti",
                "Energetico Monster",
                "Fanta Guarana",
                "Ferrero Rocher",
                "Fini",
                "Guarana Antartica",
                "Halls",
                "Hot Hit",
                "Kit Kat",
                "Lollo",
                "Mabel Waffer",
                "Mendorato 27g",
                "Mentos",
                "Minty",
                "Mio Nestle",
                "Moça",
                "Moranguete",
                "Nescau",
                "Nutella",
                "Ouro Branco",
                "Pacoquita",
                "Pastilha Garoto",
                "Pipoca Doce",
                "Pit Stop",
                "Prestigio",
                "Pringles",
                "Quaker Cookie",
                "Rafaello",
                "Salada de Frutas Natural Gourmet",
                "Salaminho Sadia",
                "Sensação",
                "Skiny",
                "Soda Guarana",
                "Sonho de Valsa",
                "Stikadinho",
                "Suco Prats Laranja",
                "Sufflair",
                "Tic Tac",
                "Toddy Cookie",
                "Toddy Waffer",
                "Todynho",
                "Tortuguita",
                "Trento",
                "Trident",
                "Trofeu"
        );

        for (String product : products) {
            Product p = new Product(product);
            ProductPrice pp = new ProductPrice(null, 2.0, 2.5, true, p);

            productRepository.save(p);
            productPriceRepository.save(pp);

        }

        User user = new User("Elias", "edwportela@gmail.com", "34454553");
        userRepository.save(user);


    }
}
